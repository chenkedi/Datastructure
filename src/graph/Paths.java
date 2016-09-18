package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;


/**
 * 图路径算法的API
 * @author chenkedi
 *
 */
public abstract class Paths{
	//这个顶点是否已经被访问过？
	protected boolean[] marked;
	//存储从起点到任何一个连通的顶点的路径
	//如edgeTo[w]中存储的是到顶点w的上一个点，通过不断的调用edgeTo,直到到达起点s，即可获得起点s到任何一个顶点的路径
	//对于DFS，其中存储的路径与图的结构和图被读入构造时邻接表中顶点的顺序有关；而对于BFS，其中的路径只与图结构有关，且为起点到达任何一点的最短路径
	protected int[] edgeTo;
	//起点
	protected final int s;
	
	//构造一个起点为S，图结构为G的Path对象，可以使用根据不同的子类实现，选择DFS算法或者BFS算法
	public Paths(Graph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
	}
	/**
	 * 是否存在从起点S到点V的路径
	 * 即s与v是否连通
	 * @param v
	 * @return
	 */
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	/**
	 * 返回从s到v的路径的点的集合
	 * @param v
	 * @return
	 */
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<>();
		for(int x = v; x!=s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
	
	public static void main(String[] args){
		Graph G = null;
		try {
			G = new Graph(new Scanner(new File(args[0])));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int s = Integer.parseInt(args[1]);
		Paths paths = new BreathFirstPaths(G,s);
		for(int v = 0; v<G.V(); v++){
			System.out.print(s + " to " + v + ": ");
			if(paths.hasPathTo(v)){
				Stack<Integer> temp = (Stack<Integer>) paths.pathTo(v);
				while(!temp.isEmpty()){
					int x = temp.pop();
					if(x==s) System.out.print(x);
					else System.out.print("-" + x);
				}
			}
				
			System.out.println();
				
		}
	}

}
