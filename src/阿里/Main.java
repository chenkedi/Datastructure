package 阿里;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 定义两个大于2的偶数之间的距离，为这两个数之间质数的个数。从小到大输入n个大于2的偶数，输出所有数两两之间距离的总和（应该有n*(n-1)/2个距离，输出总和就好)。
 * @author chenkedi
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{

		//使用缓冲加快IO
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
//		Scanner reader = new Scanner(System.in);
//		int N = reader.nextInt();
        
        int[] nums = new int[N];
        for(int i = 0; i < N; i++)
        	nums[i] = Integer.parseInt(reader.readLine());
        //求素数
        boolean[] prime = new boolean[nums[N-1]+1];
        for(int i=0;i < prime.length;i++)
			prime[i]=true;
        primeNumber(prime);
        
        //先求各个相邻数之间的质数个数并存储下来，总共有N-1个距离，这样可避免重复计算素数个数，将复杂度降为O(n)
        int[] distance = new int[N-1];
        for(int i = 0; i < N-1; i++){
        	for(int j = nums[i]; j <= nums[i+1]; j++){
        		if(prime[j])
        			distance[i]++;
        	}	
        }
        //由disdance数组计算距离总和,第一段距离和最后一段距离要加N-1次，其他段距离加（n-2）*2次
        int result = 0;
        for(int k = 1; k < distance.length-2; k++)
        	result  = result + (N-2)*2*distance[k];
        result = result + distance[0]*(N-1) + distance[distance.length-1]*(N-1);
        
        
        System.out.print(result);
        reader.close();
    }
	//使用筛法求区间内的素数
	public static void primeNumber(boolean[] n){
		n[0]=n[1]=false;
		for(int i=2;i<n.length;i++)
			if(n[i]){
				for(int j=i*i;j<n.length;j+=i)

						n[j]=false;
			}
	}

}
