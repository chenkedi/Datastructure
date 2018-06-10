package leetcode.HashTable;

/**
 *
 */
public class SingleNumber {

    /**
     * 由于异或运算是本身的逆运算，所以可以一直异或
     * 使用该方法，可以将题目推广到：只有一个数字出现奇数次，其他数字出现偶数次，求出现奇数次的数字
     * @param nums
     * @return
     */
    public static int solve1(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    /**
     * 使用哈希表来解，时间复杂度相同，空间复杂度为O(N)
     * @param nums
     * @return
     */
    public static int solve2(int[] nums) {
       return 1;
    }
}
