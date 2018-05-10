package graph.path;

import graph.path.Paths;
import graph.structure.Graph;

/**
 * 基于深度优先的路径寻找
 * @author chenkedi
 *
 */
public class DepthFirstPaths extends Paths
{


	public DepthFirstPaths(Graph G, int s) {
		super(G, s);
		dfs(G, s);
	}
	
	/**
	 * 核心算法，每次标记顶点v后，继续标记顶点v相邻点w，然后继续标记与w相邻的点...,一直到这个路径遇到已经标记的点从而返回
	 * 这就是所谓的深度优先遍历
	 * @param G
	 * @param v
	 */
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v))
			if(!marked[w]){
				edgeTo[w] = v;
				dfs(G, w);
			}
		
	}


}
