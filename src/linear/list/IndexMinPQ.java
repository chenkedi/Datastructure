package linear.list;

/**
 * 索引优先队列
 */
public class IndexMinPQ<T> {

    T value;
    int key;
    /**
     * 修改队列中已存在的键对应的值
     */
    public void change(int key, T val) {

    }

    /**
     * 向队列中插入不存在的键值
     * @param key
     * @param val
     */
    public void insert(int key, T val) {

    }


    /**
     * 判断给定的key在队列中是否存在
     * @param key
     * @return
     */
    public boolean contains(int key) {
       return true;
    }

    public boolean isEmpty() {
       return true;
    }

    public int delMin() {
        return key;
    }
}
