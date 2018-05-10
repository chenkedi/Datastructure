package graph.structure;

/**
 * 无向图结构的API
 */
public interface IGraph {
    /**
     * 返回图的顶点数
     * @return
     */
    int V();

    /**
     * 返回图的边数
     * @return
     */
    int E();

    /**
     * 给定一条边的两个顶点，向图中添加这条边
     * @param v
     * @param w
     */
    void addEdge(int v, int w);


    /**
     * 返回与给定顶点v相连的所有顶点的迭代器
     * @param v
     * @return
     */
    Iterable<Integer> adj(int v);
}
