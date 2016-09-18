package oj51nod;

import java.io.*;
public class LongestCommonSubsequent{
	public static void main(String[] args) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		String a = reader.readLine();
		String b = reader.readLine();
		System.out.println(LCS(a,b));
		
	}
	
	public static String LCS(String a, String b){
		int aN = a.length();
		int bN = b.length();
		int[][] lcs = new int[aN+1][bN+1];
		StringBuilder res = new StringBuilder();
		for(int i=0; i<=aN; i++){
			for(int j=0; j<=bN; j++){
				if(i==0 || j==0)
					lcs[i][j] = 0;
				else if(a.charAt(i-1) == b.charAt(j-1))
					lcs[i][j] = lcs[i-1][j-1]+1;
				else
					lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
			}
		}
		
		for(int i=aN,j=bN; i>=1 && j>=1;){
			if(a.charAt(i-1)==b.charAt(j-1)){
				res.append(a.charAt(i-1));
				i--;
				j--;
			}else if(lcs[i-1][j]>=lcs[i][j-1]){
				i--;
			}else
				j--;
		}
		return res.reverse().toString();
	}
}
