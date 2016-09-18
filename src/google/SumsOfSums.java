package google;

import java.io.*;
import java.util.Arrays;

/**
 * Google APAC Test Round
 * @author chenkedi
 *
 */
public class SumsOfSums{
	public static void main(String[] args) throws Exception{
		File inFile = new File("TestResource/google/D-large-practice.in");
//		File inFile = new File("TestResource/google/sample");
		File outFile = new File("TestResource/google/result/D-large.txt");
		BufferedReader  reader = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		
		int nums = Integer.valueOf(reader.readLine());
		for(int i=0; i<nums; i++){
			String[] indexs = reader.readLine().split(" ");
			int N = Integer.valueOf(indexs[0]);
			int Q = Integer.valueOf(indexs[1]);
			
			int[] iniArr = new int[N];
			//注意，当数组大小到达2^31次方时，将无法分配！！所以大数据量如何处理是个问题
			int[] newArr = new int[N*(N+1)/2];
			
			//初始化数组
			String[] iniStrs = reader.readLine().split(" ");
			for(int j=0; j<N; j++){
				iniArr[j] = Integer.valueOf(iniStrs[j]);
			}
			//Arrays.sort(iniArr);
			int tag = 0;
			for(int j=0; j<N; j++)
				for(int k=0; k<N-j; k++){
					newArr[tag]=(int)sum(iniArr,k,k+j);
					tag++;
				}
			
			Arrays.sort(newArr);
			
			String result = "";
			for(int j=0; j<Q; j++){
				String[] line = reader.readLine().split(" ");
				result += sum(newArr,Integer.valueOf(line[0])-1,Integer.valueOf(line[1])-1)+"\n";
			}
			
			writer.write("Case #" + (i+1) +":\n" + result);
					
		}
		writer.flush();
		writer.close();
		reader.close();
		
	}
	
	public static long sum(int[] arr, int s, int e){
		long res = 0;
		for(int i=s; i<=e; i++){
			res+=arr[i];
		}
		return res;
	}
	
}
