package basic.concepts;

/**
 * 最大子列和问题的三种解法
 * @author chenkedi
 *
 */
public class MaxSubSet
{
	public static int[] set={-2, 11, -4, 13, -5, 2, -5, -3, 12, -9};

	public static void main(String[] args) {
		int sum=0;
		for(int i=0;i<=1000000000;i++){
			//sum=MaxMethod1(set);
			sum=MaxMethod2(set);
			//sum=MaxMethod3(set);
		}
		System.out.println(sum);

	}
	
	/**
	 * 方法一，复杂度为N3（对于内层的两个循环而言，虽然不是一个0到N的循环，而是一个第一次是0~N，最后一次是1次的循环，
	 * 即等差数列，但是由于O（N）的特性，只关注最高项，所以用放缩法，可以认为每次内层循环都是0-n的，所以最后是N3）
	 * @param set
	 * @return
	 */
	public static int MaxMethod1(int[] set){
		int maxSum=0;
		for(int i=0;i<set.length;i++){
			for(int j=i;j<set.length;j++){
				int thisSum=0;
				for(int k=i;k<=j;k++){
					thisSum+=set[k];
				}
				if(thisSum > maxSum)
					maxSum=thisSum;
			}
		}
		return maxSum;
	}
	
	/**
	 * 方法二，复杂度为N2，去除第三层非必要循环，直接在第二个for循环的每个上次结果上加下一个元素，避免每次都从头开始
	 * @param set
	 * @return
	 */
	public static int MaxMethod2(int[] set){
		int maxSum=0;
		for(int i=0;i<set.length;i++){
			int thisSum=0;
			for(int j=i;j<set.length;j++){
				thisSum+=set[j];
				if(thisSum > maxSum)
					maxSum=thisSum;
			}
		}
		return maxSum;
	}
	
	/**
	 * 方法三，复杂度为N，使用动态规划算法
	 * 注意此方法在序列全为负的情况下结果会出错，解决方案是先遍历数组一次，将maxSum初始化为其中最大的数，然后在进行动态规划求解
	 * @param set
	 * @return
	 */
	public static int MaxMethod3(int[] set){
		int maxSum=set[0],thisSum=0;
		//为防止序列全为负数程序出现错误，先遍历一次数组，将maxSum初始化为数组中最大的负数
		for(int i = 1; i < set.length; i++)
			if(set[i] > maxSum)
				maxSum = set[i];
		
		for(int i=0;i<set.length;i++){
			thisSum+=set[i];
			if(thisSum<0)
				thisSum=0;
			else if(thisSum>maxSum){
				maxSum=thisSum;
			}
		}
		return maxSum;
	}

}
