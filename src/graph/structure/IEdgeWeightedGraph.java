package graph.structure;

/**
 * 加权无向图API
 */
public interface IEdgeWeightedGraph {

    /**
     * 图的点数
     * @return
     */
    int V();

    /**
     * 图的边数
     * @return
     */
    int E();

    /**
     * 添加一条边
     * @param e
     */
    void addEdge(IEdge e);

    /**
     * 与点v关联的所有边
     * @param v
     * @return
     */
    Iterable<IEdge> adj(int v);

    /**
     * 图的所有边
     * @return
     */
    Iterable<IEdge> edges();
}

