package oj51nod;

import java.io.*;
import java.util.Arrays;

/**
 * Longest Increasing Subsequent
 * @author chenkedi
 *
 */
public class 最长递增子序列 {
	public static void main(String args[]) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.valueOf(reader.readLine());
		int[] list = new int[N];
		for(int i=0; i<N; i++){
			list[i] = Integer.valueOf(reader.readLine());
		}
		int[] list2 = new int[N+1];
		list2[0] = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++)
			list2[i] = list[i-1];
		System.out.println(dpSolver(list,N));
		System.out.println(dpSolver2(list2,N+1));
		
		
	
}

	
	/**
	 * 该问题的时间为o(n2)空间为o(n)的解法
	 * @return
	 */
	public static int dpSolver(int[] list,int N){
		if(N<=1) return N;
		int[] dp = new int[N];
		dp[0] = 1;
		for(int i=1; i<N; i++){
			dp[i] = 1;
			for(int j=0; j<i; j++)
				if(list[j]<list[i])
					dp[i] = Math.max(dp[i],dp[j]+1);
		}
			int longest = 0;
			for(int i=0; i<N; i++)
				longest = Math.max(dp[i],longest);
			return longest;
		
	}
	
	/**
	 * 该问题的时间为o(n2)空间为o(n)的解法，但是初始化dp为n+1，并设置list[0]=负无穷
	 * 为优化算法做准备
	 * @return
	 */
	public static int dpSolver2(int[] list,int N){
		//这里的N已经是元素个数加1了
		if(N<=2) return N-1;
		int[] dp = new int[N+1];
		dp[0] = 0;
		//因为list和dp数组都是N+1(已通过出入参数+1)
		for(int i=1; i<N; i++){
			dp[i] = 1;
			for(int j=0; j<i; j++)
				if(list[j]<list[i])
					dp[i] = Math.max(dp[i],dp[j]+1);
		}
			int longest = 0;
			for(int i=0; i<=N; i++)
				longest = Math.max(dp[i],longest);
			return longest;
		
	}
	
	/**
	 * 改进后的o(nlogn)算法
	 * @return
	 */
	public static int dpSolverImprove(int[] list,int N){
//		if(N<=2) return N-1;
//		int[] dp = new int[N+1];
//		dp[0] = 0;
//		for(int i=1; i<N; i++){
//			dp[i] = list[i];
//			for(int j=0; j<i; j++)
//				if(list[j]<list[i])
//					dp[i] = Math.max(dp[i],dp[j]+1);
//		}
//			int longest = 0;
//			for(int i=0; i<N; i++)
//				longest = Math.max(dp[i],longest);
//			return longest;
//		
//	}
		
		int[] dp = new int[N];
        int len = 0;

        for(int x : list) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++;
        }

        return len;
    }
}
