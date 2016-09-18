package google;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Google APAC Test Round
 * @author chenkedi
 *
 */
public class RobotRockBand{
	public static void main(String[] args) throws Exception{
		File inFile = new File("TestResource/google/B-large-practice.in");
		File outFile = new File("TestResource/google/result/robotLarge.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		//总共输入的组数
		int num = Integer.valueOf(reader.readLine());
		for(int i=0; i<num; i++){
			//N的值
			String[] line = reader.readLine().split(" ");
			int N = Integer.valueOf(line[0]);
			int K = Integer.valueOf(line[1]);
			
			//初始化长度为4的ArrayList数组，模仿图中邻接表的数据结构，数组索引对应一个ArrayList
			@SuppressWarnings("unchecked")
			ArrayList<Integer>[] data = (ArrayList<Integer>[]) new ArrayList[4];
			
			for(int j=0; j<4; j++){
				//这里不写Integer就不能正确的插入data[j],都add进data[0]了，为何？
				data[j] = new ArrayList<>(N);
				String[] tmpLine = reader.readLine().split(" ");
				for(int k=0; k<N; k++){
					data[j].add(Integer.valueOf(tmpLine[k]));
				}
			}
			
			writer.write("Case #" + (i+1) +": " + String.valueOf(xorEqualK(data,N,K))+"\n");
		}
		writer.flush();
		writer.close();
		reader.close();
	}
		public static long xorEqualK(ArrayList<Integer>[] data, int N, int K){
			//开始解决四个数是否符合异或等于K的问题，通过暴力方式解决
//			long result = 0;
//			for(int tmp1 : data[0])
//				for(int tmp2 : data[1])
//					for(int tmp3 : data[2])
//						for(int tmp4 : data[3])
//							if((tmp1^tmp2^tmp3^tmp4)==K)
//								result++;
			//使用hashMap暂存前两组的异或结果，再枚举CD，将时间复杂度降低到n^2，典型的以空间换时间
			//键是前两组异或的结果，value是得到相同结果的组和数量
			long result = 0;
			Map<Integer,Integer> map = new HashMap<>(N);
			for(int tmp1 : data[0])
				for(int tmp2 : data[1]){
					int xor = tmp1^tmp2;
					if(map.containsKey(xor))
						map.put(xor, map.get(xor)+1);
					else
						map.put(xor,1);
				}
			
			for(int tmp3 : data[2])
				for(int tmp4 : data[3]){
					int need = K^tmp3^tmp4;
					if(map.containsKey(need))
						result+=map.get(need);
				}
			return result;			
		}
		
		
}

