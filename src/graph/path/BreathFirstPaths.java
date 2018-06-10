package graph.path;

import java.util.LinkedList;
import java.util.Queue;

import graph.path.Paths;
import graph.structure.Graph;

/**
 * 基于广度优先遍历的路径查找
 * 与深度优先遍历不同，BFS可以找出从顶点到达任何一点的最短路径
 * @author chenkedi
 *
 */
public class BreathFirstPaths extends Paths
{

	public BreathFirstPaths(Graph G, int s) {
		super(G, s);
		bfs(G, s);
	}
	
	/**
	 * 核心算法，标记起点s后，再标记与s直接相连的所有为被标记的点，并将其加入队列。而后通过循环层层的扫描相邻顶点直至结束
	 * 这就是所谓的广度优先遍历算法
	 * @param G
	 * @param s
	 */
	private void bfs(Graph G, int s) {
		Queue<Integer> queue = new LinkedList<>();
		marked[s] = true; //标记起点
		queue.add(s);
		while(!queue.isEmpty()){
			int v = queue.poll();
			for(int w : G.adj(v))
				if(!marked[w]){
					edgeTo[w] = v;
					marked[w] = true;
					queue.add(w);
				}
		}
		
	}

}
