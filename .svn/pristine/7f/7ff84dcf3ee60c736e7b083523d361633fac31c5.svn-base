package oj51nod;

import java.util.*;
import java.io.*;
import java.math.*;
public class MaxSubsetSum{
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] array = new int[N];
        for(int i=0; i<N; i++)
            array[i] = Integer.parseInt(reader.readLine());
        long max = 0,sum = 0;
        int start = 0,end = 0;
        for(int i=0; i<N; i++){
            if(sum>0)
                sum += array[i];
            else{
            	sum = array[i];
            	//这里还可以记住最大子列和的子列开始的位置
            	start = i;
            }
            //最大子列结束的地方显然就是max被替换的地地方    
            if(sum>max){
            	max = sum;
            	end = i;
            }
                
        }
        System.out.println(max);
    }
}