package algorithm.exercise;

/**
 * 将两个n位二进制整数加起来的问题，其存储在两个n元数组A与B中，最后的结果存放在n+1元数组C中
 * @author chenkedi
 *
 */
public class Exe2_1_4{
	public int[] add(int[] a,int[] b){
		int lastBit=0;
		int[] c=new int[a.length+1];
		
		if(a.length!=b.length) {
			System.out.println("输入的两个数组必须长度相同");
			System.exit(-1);
		}
		
		for(int i=a.length;i>=0;i--){
			int sum=a[i]+b[i]+lastBit;
			if(sum>1){
				c[i+1]=sum%2;
				lastBit=1;
			}else{
				c[i+1]=sum;
				lastBit=0;
			}		
		}
		c[0]=lastBit;
		
		return c;
	}
}
