package google;

import java.io.*;
import java.math.BigInteger;

/**
 * Google APAC Test Round
 * @author chenkedi
 *
 */
public class LazySpellingBee{
	public static void main(String[] args) throws Exception{
//		File inFile = new File("TestResource/google/A-large-practice.in");
//		File outFile = new File("TestResource/google/result/outLarge.txt");
		File inFile = new File("TestResource/google/A-small-practice.in");
		File outFile = new File("TestResource/google/result/outSmall.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		int N = Integer.parseInt(reader.readLine());
		for(int i=0; i<N; i++){
			writer.write("Case #" + (i+1) +": " + String.valueOf(count(reader.readLine()))+"\n");
		}
		writer.flush();
		reader.close();
		writer.close();
	}
	public static long count(String s){
		char[] strs = s.toCharArray();
		BigInteger count = BigInteger.valueOf(1);
		if(strs.length == 1) return count.longValue();
		for(int i=0; i<strs.length; i++){
			if(i==0){
				if(strs[i]!=strs[i+1])
					count=count.multiply(BigInteger.valueOf(2));
			}else if(i==strs.length-1){
				if(strs[i-1]!=strs[i])
					count=count.multiply(BigInteger.valueOf(2));
			}else if(strs[i]!=strs[i-1] && strs[i]!=strs[i+1] && strs[i-1]!=strs[i+1])
				count=count.multiply(BigInteger.valueOf(3));
			else if(!(strs[i]==strs[i-1] && strs[i]==strs[i+1]))
				count=count.multiply(BigInteger.valueOf(2));
						
		}
		return count.mod(BigInteger.valueOf(1000000007)).longValue();
	}
}
