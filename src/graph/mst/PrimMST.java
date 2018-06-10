package graph.mst;

import java.util.Arrays;

import graph.structure.IEdge;
import graph.structure.IEdgeWeightedGraph;
import linear.list.IndexMinPQ;

/**
 * Prim算法的即时实现
 */
public class PrimMST implements MST{

    // TODO implements IndexMinPQ

    // edgeTo数组中存储了各个节点到mst距离最短的边
    private IEdge[] edgeTo;
    // distTo数组存储了各个节点到mst距离最短的边权重
    private double[] distTo;
    // 标记节点是否在mst中
    private boolean[] marked;
    // 带索引的优先队列，存储了edgeTo中的v和其权值distTo的映射，用于求所有不在mst中，但与mst有一条最短边相连的所有顶点中，与mst连接边最短的那个顶点
    private IndexMinPQ<Double> pq;

    public PrimMST(IEdgeWeightedGraph G) {
        edgeTo = new IEdge[G.V()];
        distTo = new double[G.V()];
        // 初始化distTo为正无穷，则任何新加入的边权重都可对distTo进行更新
        for(int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>();

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while(!pq.isEmpty())
            visit(G, pq.delMin());
    }

    private void visit(IEdgeWeightedGraph G, int v) {
       marked[v] = true;
       for(IEdge edge : G.adj(v)) {
           int w = edge.other(v);
           // 特别注意失效的边
           if(marked[w])
               continue;
           // 如果新加入mst的点v和其邻居w构成的边edge比disTo[W]（即w连接到mst的最短边的权重)更小，则更新edgeTo[w]中的边和disTo[w]的权重
           if(edge.weight() < distTo[w]) {
               edgeTo[w] = edge;
               distTo[w] = edge.weight();
               // 这条边是mst到非树顶点w目前而言最小的边，如果w索引在优先队列中已经存在，则更新，否则插入
               if (pq.contains(w))
                   pq.change(w, edge.weight());
               else
                   pq.insert(w, edge.weight());
           }
       }
    }

    @Override
    public double weight() {
       double sum = 0D;
       for (double weight : distTo)
           sum += weight;
       return sum;
    }

    @Override
    public Iterable<IEdge> edges() {
        return Arrays.asList(edgeTo);

    }

}
