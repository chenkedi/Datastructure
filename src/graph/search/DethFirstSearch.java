package graph.search;

import graph.search.ISearch;
import graph.structure.IGraph;

/**
 * 深度优先遍历
 */
public class DethFirstSearch extends ISearch {


    public DethFirstSearch(IGraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(IGraph G, int v) {
       marked[v] = true;
       count++;
       for(int w : G.adj(v)) {
           if (!marked[w])
               dfs(G, w);
       }
    }
}
