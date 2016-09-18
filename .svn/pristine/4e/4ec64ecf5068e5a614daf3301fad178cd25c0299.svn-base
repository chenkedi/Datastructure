package leetcode;

import java.util.*;
/**
 * 
 * 微软笔试题
题目1 : Magic Box 
时间限制:10000ms 单点时限:1000ms 内存限制:256MB 
描述 
The circus clown Sunny has a magic box. When the circus is performing, Sunny puts some balls into the box one by one. 
The balls are in three colors: red(R), yellow(Y) and blue(B). Let Cr, Cy, Cb denote the numbers of red, yellow, blue balls in the box. 
Whenever the differences among Cr, Cy, Cb happen to be x, y, z, all balls in the box vanish.
Given x, y, z and the sequence in which Sunny put the balls, you are to find what is the maximum number of balls in the box ever.
For example, let's assume x=1, y=2, z=3 and the sequence is RRYBRBRYBRY. 
After Sunny puts the first 7 balls, RRYBRBR, into the box, Cr, Cy, Cb are 4, 1, 2 respectively. The differences are exactly 1, 2, 3. 
(|Cr-Cy|=3, |Cy-Cb|=1, |Cb-Cr|=2) Then all the 7 balls vanish. Finally there are 4 balls in the box, after Sunny puts the remaining balls. 
So the box contains 7 balls at most, after Sunny puts the first 7 balls and before they vanish. 
输入 
Line 1: x y z 
Line 2: the sequence consisting of only three chaccracters 'R', 'Y' and 'B'. For 30% data, the length of the sequence is no more than 200. 
For 100% data, the length of the sequence is no more than 20,000, 0 <= x, y, z <= 20. 
输出 
The maximum number of balls in the box ever. 

提示  
Sample Input
0 0 0 RBYRRBY 
Sample Output
4

样例输入 
1 2 3 
RRYBRBRYBRY 
样例输出 
7 
 * @author chenkedi
 *
 */
public class MagicBox{
	public static void main(String[] args){
		int[] dif = new int[3];
		int[] tempDif = new int[3];
		int[] count = new int[3];
		int maxBalls=0,lastUpdateIndex=-1;//注意，这里lastupdateIndex初始化为-1，因为只有第一次计算vanish球的个数时需要加一，后面直接用数组下标相减即可
		
		Scanner sc = new Scanner(System.in);
		for(int i=0;sc.hasNextInt() && i<3;i++){
			dif[i]=sc.nextInt();
		}
		String str=sc.next();
		sc.close();
		char[] sequence = str.toCharArray();
		
		for(int i=0;i<sequence.length;i++){
			//每读入一个sequence中的字符，将count数组更新
			switch (sequence[i]){
			case 'R' : count[0]++; break;
			case 'Y' : count[1]++; break;
			case 'B' : count[2]++; break;
			default : System.exit(0);
			}
			//计算各个颜色的球不同的个数，更新到tempDif数组
			computeDiff(tempDif,count);
			
			if(comparediff(tempDif,dif)){
				if(i-lastUpdateIndex>maxBalls){
					//如果tempDif中的不同值刚好包含输入的Dif值中所有的值（表明此时balls要消失了），且此时maxBalls的值比当前读入的字符的个数小(减去上次消失的球的数量)，
					//则更新maxBalls,同时更新上次消失球的位置。由于后面的消失球的个数有可能比前面的大，所以需要不断的循环下去，直至找到最大的
					maxBalls=i-lastUpdateIndex;
					lastUpdateIndex=i;
					//count数组清零
					count[0]=count[1]=count[2]=0;
				}
				//如果剩下的球的个数比maxBalls球的个数要少，则计算结束
				if(sequence.length-i+1<maxBalls)
					break;
			}
				
		}
		System.out.println(maxBalls>sequence.length-1-lastUpdateIndex?maxBalls:sequence.length-1-lastUpdateIndex);
//		System.out.println(Arrays.toString(count));
//		System.out.println(Arrays.toString(dif));
//		System.out.println(Arrays.toString(tempDif));
//		System.out.println(Arrays.toString(sequence));
	}
	
	//判断tempDif是否包含Dif中所有的元素
	private static boolean comparediff(int[] tempDif, int[] dif) {
		if(tempDif[0]==dif[0]){
			if(tempDif[1]==dif[1]){
				if(tempDif[2]==dif[2])
					return true;
				else return false;
			}else if(tempDif[1]==dif[2]){
				if(tempDif[2]==dif[1])
					return true;
				else return false;
			}
		}else if(tempDif[0]==dif[1]){
			if(tempDif[1]==dif[0]){
				if(tempDif[2]==dif[2])
					return true;
				else return false;
			}else if(tempDif[1]==dif[2]){
				if(tempDif[2]==dif[0])
					return true;
				else return false;
			}
		}else if(tempDif[0]==dif[2]){
			if(tempDif[1]==dif[0]){
				if(tempDif[2]==dif[1])
					return true;
				else return false;
			}else if(tempDif[1]==dif[1]){
				if(tempDif[2]==dif[0])
					return true;
				else return false;
			}
		}
		return false;
	}
	
	/**
	 * 计算每次读入一个sequence元素后，cr-cy，cy-cb，cb-cr的值，并更新进tempDif数组
	 * @param count
	 */
	private static void computeDiff(int[] tempDif,int[] count) {
		tempDif[0]=Math.abs(count[0]-count[1]);
		tempDif[1]=Math.abs(count[1]-count[2]);
		tempDif[2]=Math.abs(count[2]-count[0]);
		
	}
}
