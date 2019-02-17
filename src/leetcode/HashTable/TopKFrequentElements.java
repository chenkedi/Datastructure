package leetcode.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 3;
        System.out.println(solve1(nums, k));
        System.out.println(solve2(nums, k));
        System.out.println(solve3(nums, k));


    }

    /**
     * 使用基于堆的优先队列将复杂度下降到o(N + NlogK)
     *
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> solve1(int nums[], int k) {
        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : nums) {
//            if (map.containsKey(num))
//                map.put(num, map.get(num) + 1);
//            else
//                map.put(num, 1);
//        }
        // 使用java8的getOrDefault方法替换
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

//        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Integer, Integer> obj1, Map.Entry<Integer, Integer> obj2) {
//                if (obj1.getValue() > obj2.getValue())
//                    return 1;
//                else if (obj1.getValue() < obj2.getValue())
//                    return -1;
//                else
//                    return 0;
//            }
//        });

        // 使用java8 lambda表达式 或者函数引用来简化
        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(k, Comparator.comparing(Map.Entry::getValue));
//        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(k, (a, b) -> a.getValue().compareTo(b.getValue());
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (queue.size() < k)
                queue.add(e);
                // 小顶堆求topk,如果新元素小于堆顶，直接丢弃，否则替换堆顶元素，进行一次sink操作
                // peek操作不会删除堆顶元素
            else {
                if (e.getValue() > queue.peek().getValue()) {
                    queue.poll();
                    queue.add(e);
                }
            }
        }

        List<Integer> res = new ArrayList<>(k);
        for (Map.Entry<Integer, Integer> e : queue)
            res.add(e.getKey());
        return res;
    }

    /**
     * 使用桶排序将复杂度下降到O(N)
     * 此题可以使用桶排序的原因是，以数字出现的次数为key，此key一定小于等于输入数组的长度
     *
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> solve2(int nums[], int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 桶排序的key为数字出现的次数，由于数组索引从0开始，所以0不使用。为了应对nums中只有一个数字或者全部数字均相同，bucket的长度要加一
        List<Integer>[] bucket = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            int feq = e.getValue();
            if (bucket[feq] == null)
                bucket[feq] = new LinkedList<>(); // 这里用LinkedList，是由于subList截取时，使用链表其返回的是原list在该子段的快照，不需要复制实例
            bucket[feq].add(e.getKey());
        }

        List<Integer> res = new ArrayList<>();
        for (int i = nums.length; i > 0 && k > 0; i--) {
            if (bucket[i] != null) {
                if (bucket[i].size() <= k) {
                    res.addAll(bucket[i]);
                    k -= bucket[i].size();
                } else {
                    res.addAll(bucket[i].subList(0, k));
                    k = 0;
                }
            }

        }
        return res;
    }

    /**
     * 桶排序的另一种写法。主要区别在于最后生成res的阶段，该写法认为题目仅有一种答案
     * 对于不确定是否只有一种答案的情况，处理也很简单，只要返回res[0:k]即可
     *
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> solve3(int nums[], int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        for (int num : map.keySet()) {
            int frequency = map.get(num);
            if (bucket[frequency] == null)
                bucket[frequency] = new ArrayList<>();
            bucket[frequency].add(num);
        }
        List<Integer> res = new ArrayList<>();
        // 这里的res.size()<k的条件判断是鉴定题目中的test case 仅有一个答案
        for (int i = bucket.length - 1; i >= 0 && res.size() < k; i--) {
            if (bucket[i] != null)
                res.addAll(bucket[i]);
        }
        return res;


    }
}
