package leetcode;

import java.util.Scanner;
//Time Limit: 2000/1000 MS (Java/Others) Memory Limit: 65536/65536 K (Java/Others)
//Problem Description:
//括号表达式在数学四则计算中经常用到，用于改变运算的优先级，这也是一个关于括号的问题。对于一个给定的括号序列，如果在其中插入"+"和"1"后构成一个正确的数学表达式，则认为该括号序列是正则的。根据该定义，括号序列"(())()"、"()"及"(()(()))"都构成正则序列，序列")("、"(()"和"(()))("不是正则序列。
//
//小B正在头疼的一个问题是，给定一个由"("、")"和"?"构成的序列，如何将其中的"?"替换为单个的括号，从而使得该序列成为一个正则序列。更麻烦的事情在于，替换每个"?"都有着不同的代价，如何才能花费最小的代价将给定的序列转换为一个正则序列。作为朋友，小B希望你能够帮到她。

//对每组测试数据，先在单独的一行中输出构成正则序列的最小代价，然后在第二行中给出该序列。
//若无法将该序列转换为正则序列，则输出-1。若答案不唯一，则输出能够最早形成括号配对的序列。
/**
 * HDOJ，求两点之间的距离。上面的描述是2016年4月份的百度实习生招聘笔试题第一题
 * @author chenkedi
 *
 */

public class Distance{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		 while(sc.hasNextDouble()){
			 double x1=sc.nextDouble();
			 double y1=sc.nextDouble();
			 double x2=sc.nextDouble();
			 double y2=sc.nextDouble();
			 System.out.printf("%.2f",distance(x1,y1,x2,y2));
			 System.out.println();
		 }
		 sc.close();
	}
	private static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
	}
}
