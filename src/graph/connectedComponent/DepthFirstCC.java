package graph.connectedComponent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.structure.Graph;
import linear.list.Bag;

/**
 * 使用DFS算法求解连通分量
 * 并可以将对应的连通分量所有顶点返回
 * @author chenkedi
 *
 */
public class DepthFirstCC extends ConnectedComponent {
	//标记顶点是否已经被访问过
	private boolean[] marked;
	
	public DepthFirstCC(Graph G){
		super(G);
		//初始化marked数组
		marked = new boolean[G.V()];
		for(int s = 0; s < G.V(); s++)
			if(!marked[s]){
				dfs(G,s);
				count++;
			}
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v))
			if(!marked[w])
				dfs(G,w);
		
	}

	@Override
	public int find(int v) {
		return id[v];
	}
	
	public static void main(String[] args){
		Graph G = null;
		try{
			G = new Graph(new Scanner(new File(args[0])));
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DepthFirstCC cc = new DepthFirstCC(G);
		int ccCount = cc.count();
		System.out.println(ccCount + " Components");
		
		@SuppressWarnings("unchecked")
		Bag<Integer>[] components = (Bag<Integer>[]) new Bag[ccCount];
		for(int i = 0; i < ccCount; i++)
			components[i] = new Bag<>();
		//遍历顶点，将顶点加入对应连通量表示的背包中
		for(int v = 0; v < G.V(); v++)
			components[cc.find(v)].add(v);
		for(int i = 0; i < ccCount; i++){
			for(int v : components[i])
				System.out.print(v + " ");
			System.out.println();
		}
	}
}
