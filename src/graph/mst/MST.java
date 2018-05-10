package graph.mst;

import graph.structure.IEdge;

/**
 * MST的API
 */
public interface MST {
    /**
     * 最小生成树的所有边
     */
    Iterable<IEdge> edges();

    /**
     * 最小生成树的权重
     * @return
     */
    double weight();
}
