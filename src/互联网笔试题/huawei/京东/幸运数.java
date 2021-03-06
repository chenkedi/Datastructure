package 互联网笔试题.huawei.京东;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 来自京东2017校招数据与算法类工程师笔试题第一题
 * 定义函数f(x)=一个整数的十进制形式中各位数字之和
 * 定义函数g(x)=一个整数的二进制形式中各位数字之和
 * 当f(x) = g(x)之时，就认为该数字是幸运数
 * 求所有 <= n 的幸运数字的个数
 * @author chenkedi
 * 输入:第一行为输入的n的个数，表示后面有多少个n
 * 第一行后面每行一个数字，表示n，其中n<=100000,如：
 * 3
 * 1
 * 5
 * 21
 * 输出：
 * 1
 * 1
 * 3
 * 小于等于21的数字中，1,20,21是幸运数
 */
public class 幸运数 {
    public static void main(String[] args) throws Exception{
    	//使用缓冲加快IO 51nod OJ 的经验
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(reader.readLine());
        
        int[] nums = new int[N];
        for(int i = 0; i < N; i++)
        	nums[i] = Integer.parseInt(reader.readLine());
        
        int[] numsOutOrder = nums.clone();
        Arrays.sort(nums);
        Map<Integer,Integer> map = new HashMap<>();
        int lastres = 0;
      //下一个输入数字只要从上一个输入+1的数字开始判断，结果加上上一个数的结果即可，可大大降低复杂度
        for(int j=0; j < N; j++){
        	int k = 1;
        	if(j != 0){
        		k = nums[j-1]+1;
        	}
        	int res = 0;
        	for(; k <=nums[j]; k++){
        		if(sumOfTen(k) == sumOfBinary(k)){
        			//System.out.print(k + ",");
        			res++;
        		}       			
        	}
        	
        	map.put(nums[j], res+lastres);
        	lastres = res+lastres;
        }
        for(int i = 0; i < N; i++){
        	writer.write(map.get(numsOutOrder[i]) + "\n");
        }
        writer.flush();
        reader.close();
        writer.close();
    }
    
    public static int sumOfTen(int num){
    	int sum = 0;
    	while(num > 0){
    		sum += (num%10);
    		num /= 10;
    	}
    	return sum;
    }
    //使用查4bit表法，时间与空间相对最优
    public static int sumOfBinary(int num){
    	int[] map = {0,1,1,2,
				1,2,2,3,
				1,2,2,3,
				2,3,3,4
				};
    	int sum = 0;
    	for(; num > 0; num >>>= 4){
    		sum += map[num & 0xF];
    	}
    	return sum;
    }
}

