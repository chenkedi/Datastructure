package high.performance.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个包含整数、负数和0的数组，求其中最大的和为0的连续子数组
 * 如  A[]= 7 -7 8  6  5  -5  -5  0  -6 11
 *   sum[]= 7  0 8 14 19  14   9  9  3  14
 * 通过观察可以发现，对数组遍历累加，当和为0时，该项（包括其本身）到数组索引为0的子数组之和显然为0；
 * 若累加的和不为0，但是出现与历史累加和相同的和时，则说明历史累加和的位置（不包括该位置的数）到当前索引处（包含当前索引处的数）之间的子数列和为0
 * 针对这两种和未0的情况，遍历数据累加，并用哈希表记住每个首次出现的累加和的索引，同时用max记住遍历到当前位置的最长和为0的子列的长度
 * 要求时间复杂度为O(n)
 * @author chenkedi
 *
 */
public class MaxSubsetSumToZero{
	public static void main(String args[]){
		int[] a = {7,-7,8,6,5,-5};
//		int[] a = {7,-7,8,6,5,-5,-5,0,-6,11};
//		int[] a = {5,6,-7,7,5,1,2,3,-3,-2,-1};
		maxSubsetSumToZero(a);
	}
	
	public static void maxSubsetSumToZero(int[] a){
		// max表示遍历到当前位置，最长的和为0的子数列
		int max=0,start=0,end=0;
		Map<Integer,Integer> sumMap = new HashMap<>();
		int sum=0;
		for(int i=0;i<a.length;i++){
			sum+=a[i];
			if(sum==0){//等于0则前面所有的项相加为0，包括本身
				if(i+1>max){
					max=i+1;
					start=0;
					end=i;
				}
			}else if(sumMap.containsKey(sum)){//不等于0的话则查询哈希表，存在的话，说明有前缀和相等的情况
				int index = sumMap.get(sum);
				if(i-index>max){
					max=i-index;
					start=index+1;
					end=i;
				}
			}else{//将"首次"出现的前缀和存储在HashMap中，方便与以后出现的相同的前缀和对比
				sumMap.put(sum,i);
			}
		}
		
		for(int i=start;i<=end;i++){
			System.out.print(a[i]);
			if(i!=end)
				System.out.print(",");
		}
		
		
	}
}
