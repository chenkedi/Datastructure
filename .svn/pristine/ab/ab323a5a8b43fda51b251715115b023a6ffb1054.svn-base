package graph;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * 判断图中是否有环
 * @author chenkedi
 */
public class GraphCycleJudge{
	private boolean[] marked;
	private boolean hasCycle;
	
	public GraphCycleJudge(Graph G){
		marked = new boolean[G.V()];
		for(int s = 0; s < G.V(); s++)
			if(!marked[s])
				dfs(G,s,s);
		
	}
	
	/**
	 * w表示本次遍历顶点v的上一个顶点
	 * 如果与本次遍历的顶点v相连的下个顶点u已经被标记过了，则如果这个已经被标记的的顶点u不等于v的上一个顶点w，则这个图中有环路
	 * @param g
	 * @param v
	 * @param w
	 */
	private void dfs(Graph G, int v, int w) {
		marked[v] = true;
		for(int u : G.adj(v))
			if(!marked[u])
				dfs(G, u, v);
			else if(u!=w) 
				hasCycle = true;
	}
	
	public boolean hasCycle(){
		return hasCycle;
	}
	
	public static void main(String[] args){
		Graph G = null;
		try{
			G = new Graph(new Scanner(new File(args[0])));
		}catch(FileNotFoundException e){
			
		}
		
		GraphCycleJudge cycle = new GraphCycleJudge(G);
		if(cycle.hasCycle)
			System.out.println("图中有环");
		else
			System.out.println("图中无环");
	}

}
