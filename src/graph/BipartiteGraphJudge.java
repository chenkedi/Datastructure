package graph;

import java.io.FileNotFoundException;
import java.io.File;

import java.util.Scanner;

import graph.structure.Graph;

/**
 * 判断一幅图能否用两种颜色着色
 * 即一幅图是不是二分图
 * @author chenkedi
 *
 */
public class BipartiteGraphJudge{
	private boolean[] marked;
	private boolean[] color;
	private boolean isBipartite = true;
	
	public BipartiteGraphJudge(Graph G){
		marked = new boolean[G.V()];
		color = new boolean[G.V()];
		for(int s = 0; s < G.V(); s++)
			if(!marked[s])
				dfs(G,s);
	}
	
	private void dfs(Graph G, int v){
		marked[v] = true;
		for(int w : G.adj(v))
			if(!marked[w]){
				color[w] = !color[v];
				dfs(G ,w);
			}else if(color[w] == color[v])
				isBipartite = false;
	}
	
	public boolean isBipartite(){
		return isBipartite;
	}
	
	public static void main(String[] args){
		Graph G = null;
		try{
			G = new Graph(new Scanner(new File(args[0])));
		}catch (FileNotFoundException e){
			
		}
		
		BipartiteGraphJudge bipartite = new BipartiteGraphJudge(G);
		if(bipartite.isBipartite())
			System.out.println("该图是二分图");
		else
			System.out.println("该图不是二分图");
	}
	
}
