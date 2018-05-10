package graph.structure;

/**
 * 加权无向图边的数据结构
 */
public interface IEdge {

    /**
     * 边的权重
     * @return
     */
    double weight();

    /**
     * 边两端的顶点之一
     * @return
     */
    int either();

    /**
     * 一条边给定顶点v后的另一个顶点
     * @return
     */
    int other(int vertex);

    /**
     * 比较该边与that边的大小，用于堆实现的优先队列中实现comparable 接口
     * @param that
     * @return
     */
    int compareTo(IEdge that);

}
