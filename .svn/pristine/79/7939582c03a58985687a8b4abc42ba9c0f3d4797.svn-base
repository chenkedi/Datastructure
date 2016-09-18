package high.performance.algorithm;

import java.util.Scanner;

/**
 * 最长回文子串的问题
 * @author chenkedi
 *
 */
public class LongestPalindrome{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			String str=sc.next();
			long startTime=System.currentTimeMillis();
			for(int i=0;i<1000;i++) centerExpand(str);
			long endTime=System.currentTimeMillis();
			System.out.println("您输入字符串中最长回文串为: "+centerExpand(str));
			
			System.out.println("中心扩展算法耗时："+(endTime-startTime)/1000.0);
			
			startTime=System.currentTimeMillis();
			for(int i=0;i<1000;i++) manacher(str);
			endTime=System.currentTimeMillis();
			System.out.println("您输入字符串中最长回文串为: "+manacher(str));
			System.out.println("Manacher算法耗时："+(endTime-startTime)/1000.0);
		}
		sc.close();
	}
	private static String preProcess(String str){
		//使用Stringbuilder比使用String连接快4倍，由60ms变为15ms
		StringBuilder res = new StringBuilder("^");
		char[] strs = str.toCharArray();
		for(int i=0; i<strs.length; i++){
			res.append("#").append(strs[i]);
		}
		return res.append("#$").toString();
	}
	private static String manacher(String str) {
		//T为预处理后的字符串
		char[] T = preProcess(str).toCharArray();
		int n = T.length;
		int[] p = new int[n];
		//C表示当前字符串的中心
		int C=0;
		//R表示当前字符串的又边界
		int R=0;
		//由于首尾分别是 ^ 和  $的标记字符，所以从1开始到n-2结束
		for(int i=1; i<n-1; i++){
			//先求出i的对称位置 i',为中心位置减去偏移量，即c-(i-c)=2c-i;
			int iMirror = 2*C-i;
			p[i] = R>i ? Math.min(R-i, p[iMirror]) : 0;
			
			//开始进行中心扩展，确定p[i]的最终值。注意，这里p[i]计数不包括中心字符本身，初值为0，所以扩展时需要额外加1减1。
			//另外一种方法是包含中心字符本身，则初值为1
			while(T[i+p[i]+1]==T[i-p[i]-1])
				p[i]++;
			
			//如果以i为中心的回文串的范围已经超过了以C为中心回文串的右边界R，则将C置为i,并相应更新右边界R
			if(i+p[i]>C){
				C=i;
				R=i+p[i];
			}
		}
		//上述循环完成后p[i]求出来了，可以找出最长的回文字符了
		int maxLen = 0;
		int centerIndex = 0;
		for(int i=0; i<p.length; i++)
			if(p[i]>maxLen){
				maxLen = p[i];
				centerIndex = i;
			}
		return str.substring((centerIndex-1-maxLen)/2, (centerIndex+maxLen)/2);
		
	}
	
	/**
	 * 中心扩展法，时间复杂度O(n2),空间复杂度O(1)
	 * @param str
	 */
	private static String centerExpand(String str) {
		if(str.length()==0)
			return null;
		int longest=0,startIndex=0,endIndex=0;
		int startIndexCandidate=0,endIndexCandidate=0,candidate=0;
		char[] strArray=str.toCharArray();
		int n=strArray.length;
		
		//开始枚举中心位置，同时向两边展开，看两边的字符串是否相等
		for(int i=0;i<n;i++){

			//对于以i为中心，长度为奇数的回文字符串进行扫描
			for(int j=0;(i-j)>=0 && (i+j)<n;j++){
				if(strArray[i-j]!=strArray[i+j])
					break;
				candidate=2*j+1;
				startIndexCandidate=i-j;
				endIndexCandidate=i+j;
			}
			
			if(candidate>longest){
				longest=candidate;
				startIndex=startIndexCandidate;
				endIndex=endIndexCandidate;			
			}
			
			//对于以i为中心，长度为偶数的的回文字符串进行扫描
			for(int j=0;(i-j)>=0 && (i+j+1)<n;j++){
				if(strArray[i-j]!=strArray[i+j+1])
					break;
				candidate=2*j+2;
				startIndexCandidate=i-j;
				endIndexCandidate=i+j+1;
			}
			if(candidate>longest){
				longest=candidate;
				startIndex=startIndexCandidate;
				endIndex=endIndexCandidate;
			}			
		}
		return longest==1?"没有回文字符串":str.substring(startIndex, endIndex+1);
		
	}
}
