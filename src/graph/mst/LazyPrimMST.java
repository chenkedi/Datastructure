package graph.mst;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import graph.structure.IEdge;
import graph.structure.IEdgeWeightedGraph;

/**
 * Prim算法的延时实现
 */
public class LazyPrimMST implements MST {

    private boolean[] marked; // 最小生成树中的顶点,如果顶点v在MST中，则marked[v]为true
    private Queue<IEdge> mst; // 用来保存最小生成树中的边的普通队列
    private Queue<IEdge> pq; // 用于比较横切边权重的优先队列，默认使用堆实现，每次插入新边后，都能将最小权重的边置于堆顶（小顶堆）

    public LazyPrimMST(IEdgeWeightedGraph G) {
       marked = new boolean[G.V()];
       mst = new LinkedList<>();
       pq = new PriorityQueue<>();

       visit(G, 0);

       while(!pq.isEmpty()) {
           IEdge mstEdge = pq.remove();
           int v = mstEdge.either();
           int w = mstEdge.other(v);
           // 特别注意，要跳过失效的边（两个端点同时在MST中的边)
           if (marked[v] && marked[w])
               continue;
           mst.add(mstEdge);
           if (!marked[v])
               visit(G, v);
           if (!marked[w])
               visit(G, w);
       }
    }

    private void visit(IEdgeWeightedGraph G, int v) {
        marked[v] = true;
        for(IEdge edge : G.adj(v)) {
            int w = edge.other(v);
            if (!marked[w]) {
                pq.add(edge);
            }
        }
    }

    @Override
    public double weight() {
        double sum = 0D;
        while (!mst.isEmpty()) {
           sum += mst.remove().weight();
        }
        return sum;
    }

    @Override
    public Iterable<IEdge> edges() {
        return mst;
    }
}
