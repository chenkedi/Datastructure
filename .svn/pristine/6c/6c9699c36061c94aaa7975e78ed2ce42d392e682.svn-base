package graph;

/**
 * 求解图中连通分量的抽象父类
 * @author chenkedi
 *
 */
public abstract class ConnectedComponent{
	//连通分量的计数
	protected int count;
	
	//对于DFS和quick find算法，记录通过顶点索引的连通分量的标识符，即属于相同连通分量的顶点的标识符是相同的
	//对于union find 算法则稍有不同，其记录的是父链接表示的一片森林，森林中的每颗树即为一个连通分量
	protected int[] id;
	
	public ConnectedComponent(Graph G){
		id = new int[G.V()];
	}
	
	/**
	 * 用于判断给定的两个顶点是否连通
	 * @param v
	 * @param w
	 * @return
	 */
	public boolean connected(int v, int w){
		return find(v) == find(w);
	}
	
	/**
	 * 找出顶点v的连通分量标识符，即点v属于哪个连通分量
	 * 范围在0~count()-1
	 * @param v
	 * @return
	 */
	public abstract int find(int v);
	
	/**
	 * 图中连通分量的总数
	 * 即图中连通子图的个数
	 * @return
	 */
	public int count(){
		return count;
	}
	
	
}
