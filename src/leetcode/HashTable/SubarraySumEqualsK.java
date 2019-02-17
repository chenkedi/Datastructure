package leetcode.HashTable;


import java.util.HashMap;
import java.util.Map;



public class SubarraySumEqualsK {
    public static void main(String[] args) {
/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

 Example 1:
 Input:nums = [1,1,1], k = 2
 Output: 2
 Note:
 The length of the array is in range [1, 20,000].
 The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
    }

    /**
     * 优化了空间复杂度的brutal false 算法（另外的方法是将sum[0] ~ sum[n]存储，然后用sum（i,j) = sum(0, j) - sum(0, i)来求
     * @param nums
     * @param k
     * @return
     */
    public int solve1(int nums[], int k) {
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for(int j = i; j < nums.length; j++) {
                sum += nums[j];
                if(sum == k)
                    count ++;
            }
        }
        return count;
    }

    public int solve2(int nums[], int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
