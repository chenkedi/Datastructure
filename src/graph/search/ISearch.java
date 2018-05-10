package graph.search;

import graph.structure.IGraph;

/**
 * 图搜索API
 */
public abstract class ISearch {

    protected boolean[] marked;
    protected int count;
    /**
     * 判断v点与要搜索的图G中给定的s点是否联通
     * @param v
     * @return
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * 在图G中与给定点s联通的点数
     * @return
     */
    public int count() {
        return count;
    }
}
