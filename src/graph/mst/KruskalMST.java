package graph.mst;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import graph.connectedComponent.WeightedUnionFindCC;
import graph.structure.Edge;
import graph.structure.IEdge;
import graph.structure.IEdgeWeightedGraph;

/**
 * kruskal 算法求解MST
 * kruskal 按照图中边的权重处理它们，将边加入最小生成树中，新加入的边确保不会树中已有的边成环，直到V-1条边被加入
 */
public class KruskalMST {
    private Queue<IEdge> mst;

    public KruskalMST(IEdgeWeightedGraph G) {
        mst = new LinkedList<>();
        Queue<IEdge> pq = new PriorityQueue<>();
        // 现将所有的边加入优先队列，以按照边权进行排序
        for(IEdge edge : G.edges())
            pq.add(edge);
        WeightedUnionFindCC uf = new WeightedUnionFindCC(G.V());

        while(!pq.isEmpty() && mst.size() < G.V() - 1) { // 注意kruskal算法的停止条件是已经有V-1条边被加入
            IEdge edge = pq.remove();
            int v = edge.either();
            int w = edge.other(v);
            // 注意失效的边（新访问的边在未通过union连接是，两个端点是已经通过mst其他边联通）
            if (uf.connected(v, w))
                continue;
            uf.union(v, w);
            mst.add(edge);

        }
    }



}
