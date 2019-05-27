/**
 * Copyright 2018 Baidu, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package leetcode.dynamicprogramming;

import java.util.Arrays;

/**
 * Longest Increasing Subsequence
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

 Example:

 Input: [10,9,2,5,3,7,101,18]
 Output: 4
 Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 Note:

 There may be more than one LIS combination, it is only necessary for you to return the length.
 Your algorithm should run in O(n2) complexity.
 Follow up: Could you improve it to O(n log n) time complexity?

 * @author chenkedi chenkedi@baidu.com
 * @date 2019/3/2
 **/
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] test = new int[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        solve_3(test);
    }

    /**
     * 动态规划写法一
     * 更好理解一些，但是需要进行边界值判断（否则输入nums为空，就会输出max为1），并设置初始max为1
     * @param nums
     * @return
     */
    public int solve_1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 动态规划写法二
     * 初始max设置为，所以不需要进行nums为空的边界判断。i从0开始，j小于等于i
     * 速度比发一稍慢，因为i从0开始了
     * @param nums
     * @return
     */
    public int solve_2(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
           dp[i] = 1;
           for (int j = 0; j <= i; j++) {
               // 由于有这个if的存在，不用担心 i = j时，dp[i]会变成自己加1
               // 因为此时nums[i]==nums[j]
               if (nums[i] > nums[j]) {
                   dp[i] = Math.max(dp[j] + 1, dp[i]);
               }
               max = Math.max(max, dp[i]);
           }
        }
        return max;
    }


    /**
     * 基于patience sorting 变种的解法
     * 1. If A[i] is smallest among all end
     candidates of active lists, we will start
     new active list of length 1.

     2. If A[i] is largest among all end candidates of
     active lists, we will clone the largest active
     list, and extend it by A[i].

     3. If A[i] is in between, we will find a list with
     largest end element that is smaller than A[i].
     Clone and extend this list by A[i]. We will discard all
     other lists of same length as that of this modified list.
     * @param nums
     * @return
     */
    public static int solve_3(int[] nums) {
        // tails中的第i个位值，存储了长度为i + 1的所有递增子序列中，最小的end元素
        // 各个长度子序列的最小end元素是递增的
        // 情况1 对应于 tails[0] = nums[i]，此时最大子序列长度不变
        // 情况3 对应于 小于nums[i]的最大tails元素的下一个元素，被替换为num[i], 此时最大子序列长度不变
        // 情况2 对应于 tais[size](size为当前最长子序列的长度）的下一个元素变为nums[i]，即长度被扩展了1
        int tails[] = new int[nums.length];
        int size = 0;
        for (int key : nums) {
            int left = 0, right = size;
            while(left < right) {
                int mid = (left + right) / 2;
                if (key > tails[mid]) {
                    // 由下面可知，那么left = mid + 1就是小于key的最大tails元素的下一个元素，
                    left = mid + 1;
                } else {
                    // 由于此时mid位置的值小于或者等于key，所以right一定是tails中小于num的最大的那个tail
                    right = mid;
                }

            }
            tails[left] = key;
            // 由于size的数值比当前最大的tail所在的位置i还要大1，所以，若left等于size，说明产生了情况2
            // 当left为情况2时，最大的tail长度应该加一
            if (left == size)
                size++;
        }
        return size;
    }
}
