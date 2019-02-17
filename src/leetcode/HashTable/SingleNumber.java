package leetcode.HashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *Given a non-empty array of integers, every element appears twice except for one. Find that single one.

 Note:

 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

 Example 1:

 Input: [2,2,1]
 Output: 1
 Example 2:

 Input: [4,1,2,1,2]
 Output: 4
 */
public class SingleNumber {

    /**
     * 法一，使用加法：2*（a + b + c) - (a + a + b + b + c) = c
     * 时间复杂度O(2n)，空间复杂度O（n),实测时间19ms
     *
     * @param nums
     * @return
     */
    public int solve1(int[] nums) {
        int sum = 0;
        int sumOfDistinctDouble = 0;
        Set<Integer> distinctNum = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            distinctNum.add(nums[i]);
        }
        for (int n : distinctNum) {
            sumOfDistinctDouble += n;
        }
        return 2 * sumOfDistinctDouble - sum;
    }

    /**
     * 使用哈希表来解，时间复杂度O（n)，空间复杂度为O(N)，实测时间23ms
     *
     * @param nums
     * @return
     */
    public static int solve2(int[] nums) {
        Map<Integer, Integer> distinctNum = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            distinctNum.put(nums[i], distinctNum.getOrDefault(nums[i], 0) + 1);
        }
        for (int n : distinctNum.keySet()) {
            if (distinctNum.get(n) == 1)
                return n;
        }
        return 0;
    }


    /**
     * 由于异或运算是本身的逆运算，所以可以一直异或
     * 使用该方法，可以将题目推广到：只有一个数字出现奇数次，其他数字出现偶数次，求出现奇数次的数字
     *
     * @param nums
     * @return
     */
    public static int solve3(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }


}
