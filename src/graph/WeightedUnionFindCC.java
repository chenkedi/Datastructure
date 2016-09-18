package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import linear.list.Bag;

/**
 * 使用算法一书中 1.5节的union find 算法解决连通分量问题
 * 与1.5不同的是，这里使用图的数据结构,而不是直接从文件中读取整数对表示的连通关系（这样貌似会使算法复杂度翻一倍，因为图中每条边存储了两次）
 * @author chenkedi
 *
 */
public class WeightedUnionFindCC extends ConnectedComponent{
	//记录每个连通分量含有的顶点数量，为构造扁平的父链接数提供条件
	private int[] sz;
	
	public WeightedUnionFindCC(Graph G){
		super(G);
		sz = new int[G.V()];
		count = G.V();
		//首先，每个顶点都初始化为一个单独的连通分量标识符，与自己的顶点号相同
		for(int i = 0; i < G.V(); i++)
			id[i] = i;
		//那么很显然，每个连通分量的初始定点数都为1
		for(int i = 0; i < G.V(); i++)
			sz[i] = 1;
	}
	
	//通过点V和记录父链接关系的id数组，一直回溯寻找到根节点，根节点即是这个顶点的标识符
	@Override
	public int find(int v) {
		while(v!=id[v]) v = id[v];
		return v;
	}
	
	public void union(int v,int w){
		int vRoot = find(v);
		int wRoot = find(w);
		
		if(vRoot == wRoot)
			return;
		if(sz[vRoot] < sz[wRoot]){
			id[vRoot] = wRoot;
			sz[wRoot] += sz[vRoot];
		}else{
			id[wRoot] = vRoot;
			sz[vRoot] += wRoot;
		}
		count--;
	}
	
	public static void main(String[] args){
		Graph G = null;
		try{
			G = new Graph(new Scanner(new File(args[0])));
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WeightedUnionFindCC cc = new WeightedUnionFindCC(G);
		for(int v = 0; v < G.V(); v++)
			for(int w : G.adj(v)){
				if(cc.connected(v, w)) 
					continue;
				cc.union(v, w);
			}
		int ccCount = cc.count();
		System.out.println(ccCount+ " Components");
		
		@SuppressWarnings("unchecked")
		Bag<Integer>[] components = (Bag<Integer>[]) new Bag[ccCount];
		for(int i = 0; i < ccCount; i++)
			components[i] = new Bag<>();
		for(int v = 0; v < G.V(); v++)
			components[cc.find(v)].add(v);
		for(int i = 0; i < ccCount; i++)
			for(int w : components[i]){
				System.out.print(w + " ");
			}
		System.out.println();
	}
}
