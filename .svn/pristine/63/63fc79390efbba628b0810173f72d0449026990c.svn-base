package oj51nod;

import java.io.*;

public class 两个字符串之间的编辑距离 {
	public static void main(String[] args) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = reader.readLine();
		String t = reader.readLine();
		System.out.println(longestEditDis(s,t));

		reader.close();
		writer.close();
	}
	
	public static int longestEditDis(String s, String t){
		int sn = s.length();
		int tn = t.length();
		int[][] dp = new int[sn+1][tn+1];
		for(int j=0; j<=tn; j++)
			dp[0][j] = j;
		for(int i=0; i<=sn; i++)
			dp[i][0] =i;
		for(int i=1; i<=sn; i++){
			for(int j=1; j<=tn; j++){
				//这里有三种情况，s的前i-1位与t的前j-1位已经对齐;
				//s的前i-1位与t的前j位已经对齐;
				//s的前i位与与t的前j-1位对齐
				//这分别对应二维数组中，一个格子只能从其左上方、上方或者与左方获得值，由于这里求编辑距离，即最小的修改次数，所以取最小值
				if(s.charAt(i-1)==t.charAt(j-1)){
					dp[i][j] = dp[i-1][j-1];
				}else{
					dp[i][j]=Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]),dp[i][j-1])+1;
					
				}

			}
		}
		return dp[sn][tn];
	}
}
