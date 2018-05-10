package graph.structure;

import java.util.Scanner;

import linear.list.Bag;

/**
 * 使用节点的邻接表实现的图结构，其中图的顶点使用整数表示
 * 其中，使用Bag类存储邻接数组中的集合
 * @author chenkedi
 */
public class Graph implements IGraph{
	//顶点数
	int V;
	//边数
	int E;
	//图的邻接表
	Bag<Integer>[] adj;
	
	/**
	 * 用于构造一个含有V个顶点，但不含有边的图
	 */
	@SuppressWarnings("unchecked")
	public Graph(int V){
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
	}
	
	/**
	 * 从标准输入流构造一个图，文件的格式如下：
	 * 第一行是顶点数，第二行是边数，后面每一行是一对整数，表示这两个整数对应的顶点之间的边
	 * @param in
	 */
	public Graph(Scanner sc){
		//先初始化一个没有边的图
		this(sc.nextInt());
		int E = sc.nextInt();
		for(int i = 0; i < E; i++){
			//添加一条边
			int v = sc.nextInt();
			int w = sc.nextInt();
			addEdge(v,w);
		}
	}
	
	/**
	 * 向邻接表中添加一条边，注意邻接表中，一条边会出现两次，所以分别在顶点V的邻接表中添加w，同时在顶点W的邻接表中添加V
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
		
	}
	
	/**
	 * 返回图中顶点的数量
	 * @return
	 */
	public int V(){
		return V;
	}
	
	/**
	 *返回图中边的数量
	 * @return
	 */
	public int E(){
		return E;
	}
	
	/**
	 * 返回图中与顶点V相邻的所有顶点，也即返回顶点V的邻接表
	 * @param V
	 * @return
	 */
	public Iterable<Integer> adj(int V){
		return adj[V];
	}
}
