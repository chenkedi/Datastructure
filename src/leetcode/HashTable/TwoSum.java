package leetcode.HashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * 输入保证有且仅有一个解
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,

 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] n1 = new int[] {2, 7, 11, 15};
        int t1 = 9;
        // 特别注意下面这两个例子，nums中的数字恰好为target的一半
        int[] n2 = new int[] {3, 3};
        int t2 = 6;
        int[] n3 = new int[] {3};
        int t3 = 6;
    }

    public static int[] solve_1(int[] nums, int target) {
        int index_1 = 0, index_2 = 0;
        Map<Integer, Integer> reverseIndex = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            // 若有相同的数字，则后面的会覆盖前面的
            reverseIndex.put(nums[i], i);
        }
        for(int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            // 这里的判断很重要，被减数与差恰好相等时，要注意判断其索引是否与被减数不同
            // 注意题目中的"have exactly one solution"的条件
            if(reverseIndex.containsKey(key) && (index_2 = reverseIndex.get(key)) != i) {
                index_1 = i;
                break;
            }
        }
        return new int[] {index_1, index_2};
    }

    /**
     * 该题最关键的问题是如何解决输入中有重复元素的问题。思考时不要将问题复杂化，比如存储重复元素的多个index等。
     * 考虑到输入中有且仅有一个解，且target - 当前遍历的元素的值，若在当前的map中找不到，则后续遍历另一个应该
     * 命中的数时，另外一个数一定已经在map中了。所以按序遍历数组即可
     * @param nums
     * @param target
     * @return
     */
    public int[] solve_2(int[] nums, int target) {

        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length ; i++) {
            int key = target - nums[i];
            if (map.containsKey(key)) {
                res[0] = i;
                res[1] = map.get(key);
            }
            map.put(nums[i], i);
        }

        return res;
    }


}
