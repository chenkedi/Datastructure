package graph.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * 与普通无权重无向图不同，该实现使用边的邻接表
 **/
public class EdgeWeightGraph implements IEdgeWeightedGraph {

    private final int V;
    private int E;
    private List<IEdge>[] adj;

    public EdgeWeightGraph (int V) {
        this.V = V;
        this.E = 0;
        adj = (List<IEdge>[]) new List[V];
        for (int v = 0; v < V; v++)
            adj[v] = new ArrayList<>();
    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    @Override
    public void addEdge(IEdge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    @Override
    public Iterable<IEdge> adj(int v) {
        return adj[v];
    }

    @Override
    public Iterable<IEdge> edges() {
        List<IEdge> edges = new ArrayList<>();
        for (int v = 0; v < V; v++)
            for(IEdge e : adj[v])
                if(e.other(v) > v) // 避免将邻接表中的重复边加入到返回结果中，只返回邻接表索引点比其中包含边的另一端点小的点
                    edges.add(e);

        return edges;
    }

}
