package graph.search;

import graph.structure.IGraph;
import linear.list.Queue;

public class BreathFirstSearch  extends ISearch {

    public BreathFirstSearch(IGraph G, int s) {
        marked = new boolean[G.V()];
        try {
            bfs(G, s);
        } catch (Exception e) {}
    }

    private void bfs(IGraph G, int s) throws Exception {
        // TODO add adaptive queue
        Queue<Integer> queue = new Queue<>(10);
        queue.enQueue(s);
        while (!queue.isEmpty()) {
           int v = queue.deQueue();
           for(int w : G.adj(v)) {
               if (!marked[w]) {
                   marked[w] = true;
                   count ++;
                   queue.enQueue(w);
               }
           }
        }
    }


}
