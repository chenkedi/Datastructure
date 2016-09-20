odps sql 
********************************************************************--
author:smart3329
create time:2016-04-13 20:23:43
********************************************************************--
drop table if exists tianchi_mobile_recommendation_predict;

********************第一次提交**************************************--
F-Score	    准确率	      召回率
0.04931275	0.03438389	0.087152997
首次提交的结果count数：83004

step 1，构建user_manner_in_p,即与商品子集P中的商品有交互行为的用户
create table user_manner_in_p as select a.*,b.item_geohash from user_manner a join item b on a.item_id=b.item_id and a.item_category=b.item_category;

step 2,构建单日的user,item对的交叉列表。如果构建17日的，是预测18日，并计算F-score进行初步评估；如果构建18日的，则是预测19日，为提交结果构建中间表
create table user_manner_in_p_18 as
select user_id,item_id,
case when substr(time,1,10)='2014-12-18' and behavior_type=1 then 1 else 0 end as 18_view,
case when substr(time,1,10)='2014-12-18' and behavior_type=2 then 1 else 0 end as 18_fav,
case when substr(time,1,10)='2014-12-18' and behavior_type=3 then 1 else 0 end as 18_cart,
case when substr(time,1,10)='2014-12-18' and behavior_type=4 then 1 else 0 end as 18_buy
from user_manner_in_p;

step 3，以第二步中构建的18日的交叉列表中被用户加入购物车的user,item对去除重复后为预测结果提交。即预测18号加入购物车的，19号就会被购买
create table tianchi_mobile_recommendation_predict
as select user_id,item_id
from user_manner_in_p_18
where 18_cart=1 and user_id is not null and item_id is not null		
group by user_id,item_id;

测试step,可以用相同的方法通过17号的数据对18号的数据进行预测，以进行提交之前的自主测试
结果 准确率	              召回率	              F-Score
    0.023991658202018932   0.05723905723905724   0.03381134561301275
create table user_manner_in_p_17 as
select user_id,item_id,
case when substr(time,1,10)='2014-12-17' and behavior_type=1 then 1 else 0 end as 17_view,
case when substr(time,1,10)='2014-12-17' and behavior_type=2 then 1 else 0 end as 17_fav,
case when substr(time,1,10)='2014-12-17' and behavior_type=3 then 1 else 0 end as 17_cart,
case when substr(time,1,10)='2014-12-17' and behavior_type=4 then 1 else 0 end as 17_buy,
case when substr(time,1,10)='2014-12-18' and behavior_type=4 then 1 else 0 end as 18_buy,
from user_manner_in_p;

select 2*precision*recall/(precision+recall) as f1,precision,recall from
	(select true_positive/positive_count as precision,true_positive/true_count as recall
	from 
		(select sum(if(a.user_id is not null and a.item_id is not null and b.user_id is not null and b.item_id is not null,1,0)) as true_positive,
		if(sum(a.true_count) is null,0,sum(a.true_count)) as true_count,
		if(sum(b.positive_count) is null,0,sum(b.positive_count)) as positive_count
		from
			(select user_id,item_id,count(*) as true_count
			from user_manner_in_p_17
			where 18_buy=1 and user_id is not null and item_id is not null
			group by user_id,item_id
			) a
			full outer join
			(select user_id,item_id,count(*) as positive_count
			from user_manner_in_p_17
			where 17_cart=1 and user_id is not null and item_id is not null
			group by user_id,item_id
			) b
			on a.user_id=b.user_id and a.item_id=b.item_id
		) c
	) d;
********************第一次提交结束**************************************--

********************第二次提交****************************************--
F-Score	    准确率	      召回率
0.03566554	0.03542275	0.035911681
第二次提交的count数：33199
第二次提交按照新手教程的提交步骤提交，召回率远远低于自己的第一次改进的提交
row_number()那里的含义是：取加入购物车次数最多的商品作为用户购买的预测
create table tianchi_mobile_recommendation_predict as
select user_id,item_id
from
	(select user_id,item_id,row_number() over(partition by user_id order by num desc) as rank
	from
		(select a.user_id,a.item_id,a.num
		from
			(select user_id,item_id,count(*) as num
			from user_manner
			where substr(time,1,10)='2014-12-18' and behavior_type=3
			group by user_id,item_id
			) a
			join
			(select distinct item_id
			from item
			) b
			on a.item_id=b.item_id
		where b.item_id is not null
		) c
	) d
where rank<=1;
********************第二次提交结束****************************************--


********************第三次提交****************************************--
F-Score	    准确率	      召回率
0.05600337	0.04168843	0.085290278
第三次提交召回率只下降0.0019，而准确率直线提升0.007
第三次提交的结果数：66997
在结果一中剔除18号当天加入购物车并且当天已被购买的用户，商品对
step1，构建18号当天加入购物车，并被用户购买的用户，商品对表:cart_and_buy_18,并去重
cart_and_buy_18 count数：16007
create table cart_and_buy_18 as
select a.user_id,a.item_id
		from
			(select user_id,item_id
			from user_manner_in_p_18
			where 18_cart=1
			) a
			join
			(select user_id,item_id
			from user_manner_in_p_18
			where 17_buy=1
			) b
			on a.item_id=b.item_id and a.user_id=b.user_id
		where a.user_id is not null and a.item_id is not null
		group by a.user_id,a.item_id;

step2，通过左外连接的方式，去除第一次提交中包含在cart_and_buy_18中的用户商品对
具体来说，当连接中的右表中的两列均为null时，则表示该用户，商品对不包含在cart_and_buy_18中，应该保留。其余的去除
create table tianchi_mobile_recommendation_predict as
select a.user_id,a.item_id
from 
		(select user_id,item_id
		from user_manner_in_p_18
		where 18_cart=1 and user_id is not null and item_id is not null
		) a
		left outer join
		(select user_id,item_id
		from cart_and_buy_18
		) b
		on a.user_id=b.user_id and a.item_id=b.item_id
where b.item_id is null and b.user_id is null
group by a.user_id,a.item_id;
********************第三次提交结束****************************************--

********************第四次提交****************************************--
F-Score	    准确率	      召回率
0.04524301	0.04052095	0.051210799
结果证明这个规则根本没有作用，在不能提升精确度的情况下，反倒大大降低了召回率
主要筛除30天内从未购买东西的用户。事实上由于双十二价格最低，宣传力度最低，如果在这一天都没有
购买，并且其他天内也没有购买，我们认为其在18号加购物车后19号购买的可能性很低
第四次结果的count数：41386

step1，计算包含在第三次提交结果中的30天从未购买东西的用户的个数
该表count数为：10892
从该处可以看出，30天没有购买行为的用户其中有很多人在18号加入了很多购物车(66997-41386=25611),这些人平均每人将2.3件物品加入了购物车
select count(*) from
	(select a.user_id 
	from
		(select user_id
		from tianchi_mobile_recommendation_predict_3
		) a	
		join
		(select user_id
		from
			(select user_id,
			case when behavior_type=4 then 1 else 0 end as buy
			from user_manner_in_p
			) g
		group by user_id
		having sum(buy)=0
		) b
		on a.user_id=b.user_id
	group by a.user_id
	)c;

step2，用左外连接的方式保留在第三次提交结果中，30天内买过东西的用户
create table tianchi_mobile_recommendation_predict as
select a.user_id,a.item_id
from
	(select user_id,item_id
	from tianchi_mobile_recommendation_predict_3
	) a
	left outer join
	(select user_id
	from
		(select user_id,
		case when behavior_type=4 then 1 else 0 end as buy
		from user_manner_in_p
		) g
	group by user_id
	having sum(buy)=0
	) b
	on a.user_id=b.user_id
where b.user_id is null
group by a.user_id,a.item_id;
********************第四次提交结束****************************************--


********************第五次提交****************************************--
F-Score	    准确率	      召回率
0.05629571	0.04410212	0.077808666
剔除18号中，已经购买商品的用户的购物车中(没有考虑是否是同一个购物车，因为时间跨度不限定)其他没有被购买的商品
召回率降低的程度大于精确度提高的程度。说明有作用，但是误判太多，尝试在第六次中增加更为严格的条件
count数：57775(66997-17678=49319)???

drop table if exists tianchi_mobile_recommendation_predict;
create table tianchi_mobile_recommendation_predict as
select g.user_id,g.item_id
from
	(select user_id,item_id
	from tianchi_mobile_recommendation_predict_3
	) g
	left outer join
	(select d.user_id,d.item_id
	from
		(select a.user_id,a.item_id,a.hour
		from
			(select user_id,item_id,hour
			from user_manner_in_p_18
			where 18_cart=1
			) a
			join
			(select user_id,item_id,hour
			from user_manner_in_p_18
			where 18_buy=1
			) b
			on a.item_id=b.item_id and a.user_id=b.user_id
		where a.user_id is not null and a.item_id is not null
		) c --18号中被加入购物车并被购买的商品，用户对
		join
		(select user_id,item_id,hour
		from user_manner_in_p_18
		where 18_cart=1
		) d
		on d.user_id=c.user_id 
	where d.item_id!=c.item_id and abs(c.hour-d.hour)<=2
	group by d.user_id,d.item_id
	) e
	on g.user_id=e.user_id and g.item_id=e.item_id
where e.user_id is null and e.item_id is null
group by g.user_id,g.item_id;
********************第五次提交结束****************************************--

********************第六次提交****************************************--
购买时间与加购物车没购买的商品的时间差等于1个小时
F-Score	    准确率	      召回率
0.05603554	0.04214595	0.083580159
count数：64316(66997-4895)
drop table if exists tianchi_mobile_recommendation_predict;
create table tianchi_mobile_recommendation_predict as
select g.user_id,g.item_id
from
	(select user_id,item_id
	from tianchi_mobile_recommendation_predict_3
	) g
	left outer join
	(select d.user_id,d.item_id
	from
		(select a.user_id,a.item_id,a.hour
		from
			(select user_id,item_id,hour
			from user_manner_in_p_18
			where 18_cart=1
			) a
			join
			(select user_id,item_id,hour
			from user_manner_in_p_18
			where 18_buy=1
			) b
			on a.item_id=b.item_id and a.user_id=b.user_id
		where a.user_id is not null and a.item_id is not null
		) c --18号中被加入购物车并被购买的商品，用户对
		join
		(select user_id,item_id,hour
		from user_manner_in_p_18
		where 18_cart=1
		) d
		on d.user_id=c.user_id 
	where d.item_id!=c.item_id and c.hour-d.hour>0 and c.hour-d.hour<=1
	group by d.user_id,d.item_id
	) e
	on g.user_id=e.user_id and g.item_id=e.item_id
where e.user_id is null and e.item_id is null
group by g.user_id,g.item_id;

********************第七次提交****************************************--
F-Score	    准确率	      召回率
0.05729104	0.04453668	0.080282142
购买时间与加购物车没购买的商品的时间差等于0个小时或者一个小时。
59030
drop table if exists tianchi_mobile_recommendation_predict;
create table tianchi_mobile_recommendation_predict as
select g.user_id,g.item_id
from
	(select user_id,item_id
	from tianchi_mobile_recommendation_predict_3
	) g
	left outer join
	(select d.user_id,d.item_id
	from
		(select a.user_id,a.item_id,a.hour
		from
			(select user_id,item_id,hour
			from user_manner_in_p_18
			where 18_cart=1
			) a
			join
			(select user_id,item_id,hour
			from user_manner_in_p_18
			where 18_buy=1
			) b
			on a.item_id=b.item_id and a.user_id=b.user_id
		where a.user_id is not null and a.item_id is not null
		) c --18号中被加入购物车并被购买的商品，用户对
		join
		(select user_id,item_id,hour
		from user_manner_in_p_18
		where 18_cart=1
		) d
		on d.user_id=c.user_id 
	where d.item_id!=c.item_id and c.hour-d.hour>=0 and c.hour-d.hour<=1
	group by d.user_id,d.item_id
	) e
	on g.user_id=e.user_id and g.item_id=e.item_id
where e.user_id is null and e.item_id is null
group by g.user_id,g.item_id;

********************第八次提交****************************************--