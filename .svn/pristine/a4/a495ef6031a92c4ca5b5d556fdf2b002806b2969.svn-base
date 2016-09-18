package oj51nod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/**
 * 动态规划，求从一个N*N的矩阵中的左上角到右下角经过的点的和最大的值是多少
 * @author chenkedi
 *
 */
public class 左上角到右下角{
    public static void main(String[] args) throws Exception{
    	 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 16);
         //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
        int N = Integer.parseInt(reader.readLine());
        int[][] matrix = new int[N][N];
        for(int i = 0; i<N; i++){
        	String[] line = reader.readLine().split(" ");
            for(int j = 0; j<N; j++)
                matrix[i][j]=Integer.parseInt(line[j]);
            
        }
        System.out.println(max(matrix,N,N));
       System.out.println(max2(matrix,N,N));
       reader.close();
        
    }
    /**
     * This method is not accepted when calculating the minimum sum of the path,so dose the
     * maxWithSpaceImproved
     * @param matrix
     * @param x
     * @param y
     * @return
     */
    public static long max(int[][] matrix, int x, int y){
    	long[][] dp = new long[x+1][y+1];
    	for(int i = 1; i <= x; i++)
    		for(int j = 1; j <= y; j++){
    			//当前位置的值
    			int current = matrix[i-1][j-1];
    			//记录最优子问题解的矩阵，等于左边最优解和上面最优解最大的那个与当前值相加
    			dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1])+current;
    		}
    	//print the dynamic programming matrix
    	for(int i=0; i <= x; i++){
    		for(int j=0; j <= y; j++){
    			System.out.print(dp[i][j]+" ");
    		}
    		System.out.println();
    	}
    	return dp[x][y];
        
    }
    /**
     * This method use one dimension array to calculate the dynamic programming matrix
     * @param matrix
     * @param x
     * @param y
     * @return
     */
    public static long maxWithSpaceImprove(int[][] matrix, int x, int y){
    	long[] dp = new long[y+1];
    	for(int i = 1; i <= x; i++)
    		for(int j = 1; j <= y; j++){
    			//当前位置的值
    			int current = matrix[i-1][j-1];
    			//记录最优子问题解的矩阵，等于左边最优解和上面最优解最大的那个与当前值相加
    			dp[j]=Math.max(dp[j], dp[j-1])+current;
    		}
    	return dp[y];
        
    }
    
    /**
     * This method is more general for dynamic program questions, though it is 
     * a little bit slower than the first tow methods
     * @param matrix
     * @param x
     * @param y
     * @return
     */
    public static long max2(int[][] matrix, int x, int y){
    	long[][] dp = new long[x][y];
    	for(int i = 0; i < x; i++)
    		for(int j = 0; j < y; j++){
    			if(i == 0){
    			    if(j == 0)
    			        dp[i][j]=matrix[0][0];
    			     else
    			        dp[i][j]=dp[0][j-1]+matrix[0][j];
    			}else if(j == 0)
    			        dp[i][j]=dp[i-1][0]+matrix[i][0];
    			    else
    			        dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+matrix[i][j];
    		}
    	//print the dynamic programming matrix
    	for(int i=0; i < x; i++){
    		for(int j=0; j < y; j++){
    			System.out.print(dp[i][j]+" ");
    		}
    		System.out.println();
    	}
    	return dp[x-1][y-1];
        
    }
    
    /**
     * just like the first two method, this one only use one dimension array to calculate
     * @param matrix
     * @param x
     * @param y
     * @return
     */
    public static long max2WithSpaceImprove(int[][] matrix, int x, int y){
    	long[] dp = new long[y];
    	for(int i = 0; i < x; i++)
    		for(int j = 0; j < y; j++){
    			if(i == 0){
    			    if(j == 0)
    			        dp[j]=matrix[0][0];
    			     else
    			        dp[j]=dp[j-1]+matrix[0][j];
    			}else if(j == 0)
    			        dp[j]=dp[0]+matrix[i][0];
    			    else
    			        dp[j]=Math.max(dp[j],dp[j-1])+matrix[i][j];
    		}
    	return dp[y-1];
        
    }

}