package Tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Test{
	public static void main(String[] args){
		try {
			hashSearch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void hashSearch() throws IOException{
		//初始化哈希表,键为用户ID，值为此ID在20w.txt 文件中的序号，要求输入的20w.txt 文件中的ID本身是有序的
		//默认第一个ID从编号1开始
		Map<Long,Integer> map = new HashMap<>(270000);
		
		//读取20万条记录,和6000条记录文件
		System.out.println("开始读取20w条记录,并建立哈希表...");
		FileReader fr1=null,fr2 =null;
		try {
			fr1 = new FileReader("TestResource/20w.txt");
			fr2 = new FileReader("TestResource/6k.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br1 = new BufferedReader(fr1);
		BufferedReader br2 = new BufferedReader(fr2);
		//开始建立哈希表
		for(String s = br1.readLine();s!=null;s=br1.readLine()){
			map.put(Long.valueOf(s.trim()),map.size()+1);
		}
		System.out.println("哈希表建立完成！开始查找....");

		System.out.println("6000条记录中，在20w条记录中存在的ID如下：");
		int sum =0;//记录6000条中存在于20w条中的记录条数
		List<Long> notExists = new ArrayList<>();//记录不存在于20w中的id
		Map<Integer,Long> exists = new TreeMap<>();//记录存在于20w中的id，并根据id在原文件中的序号从小到大排序
		for(String s = br2.readLine();s!=null;s=br2.readLine()){
			if(map.containsKey(Long.valueOf(s.trim()))){
				exists.put(map.get(Long.valueOf(s.trim())),Long.valueOf(s.trim()));
//				System.out.println(s.trim()+"  "+map.get(Long.valueOf(s.trim())));
				sum++;
			}else{
				notExists.add(Long.valueOf(s.trim()));
			}
				
		}
		for(Integer order : exists.keySet())
			System.out.println(exists.get(order)+"  "+order);
		System.out.println("存在于20w条记录中的总条数："+sum);
		
		System.out.println("6000条记录中，不存在于20w条记录中存在的ID如下：");
		for(long tmp : notExists)
			System.out.println(tmp);
		
		

		
	}
	
	public void testString(){
		String str3 = new StringBuilder("计算").append("机").toString();
		String str1 = "计算机";
		String str2 = "计算" + new String("机");
		System.err.println(str3.intern() == str2);
	}
}
