package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 符号图类，用于顶点是任意字符串，且输入的图的数据的前两行中不包含顶点数和边数的情况
 * @author chenkedi
 * 算法一书中对这个符号图的实现可以有两个改进点：1、不必扫描输入文件两次，在第一次扫描是可以将文件缓存中内存中，这样构造图的过程会更快
 * 2、可以使用BufferedReader或者BufferdInputStream等方式代替Scanner，从而加快读取文件的IO速度
 */
public class SymbolGraph{
	//用于保存顶点名字与索引值的映射
	private Map<String, Integer> st;
	//用于反向索引，保存每个索引对应的顶点名
	private String[] keys;
	//保存这个符号图文件所构造的以整数为索引的图对象
	private Graph G;
	
	/**
	 * 构造符号图
	 * @param fileName
	 * @param delim
	 */
	public SymbolGraph(String fileName, String delim){
		//第一次遍历输入的文件，构建符号表索引
		st = new HashMap<>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		while(sc.hasNext()){
			String[] line = sc.nextLine().split(delim);
			for(String vertextName : line)
				if(!st.containsKey(vertextName))	
					st.put(vertextName,st.size());//使用size来确定名字的索引，刚好使得名字索引从0开始
		}
		//构建反向索引
		keys = new String[st.size()];
		//这里使用常规的带临时变量的for循环会提高性能
		for(String vertextName : st.keySet())
			keys[st.get(vertextName)] = vertextName; 
		
		//第二次遍历输入的符号图文件，构造图
		G = new Graph(st.size());
		try {
			sc = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(sc.hasNext()){
			String[] line = sc.nextLine().split(delim);
			int v = st.get(line[0]);
			G.addEdge(v,st.get(line[1]));
		}
	}
	
	/**
	 * 判断给定的key是不是符号图中的一个顶点
	 * @param key
	 * @return
	 */
	public boolean contains(String key){
		return st.containsKey(key);
	}
	
	/**
	 * 返回一个顶点的索引
	 * @param key
	 * @return
	 */
	public int index(String key){
		return contains(key)?st.get(key):null;
	}
	
	/**
	 * 返回一个顶底索引对应的名字
	 * @param v
	 * @return
	 */
	public String name(int v){
		return (v>=0 || v<keys.length)?keys[v]:null;
	}
	
	/**
	 * 返回图对象 
	 * @return
	 */
	public Graph G(){
		return G;
	}
}
