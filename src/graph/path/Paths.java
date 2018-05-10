package graph.path;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

import graph.structure.Graph;


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
	//对于DFS，其隐式的使用了栈，每次继续搜索有待搜索的边，都是从最晚遇到的那条边开始。其中存储的路径与图的结构和图被读入构造时邻接表中顶点的顺序有关,所以从起点s到每个联通的点v并不一定是最短路径；
	//而对于BFS，其显示使用了队列，每次将点的邻居遍历完成继续所有有待搜索的边时，都是从最早遇到的那条边开始。当一个其中的路径只与图结构有关，由于每次都是从起点向外扩散，所以可以保证路径为从起点s到各个联通点v最短的距离
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
