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
package leetcode.string;

/**
 * 647. Palindromic Substrings
 * Given a string, your task is to count how many palindromic substrings in this string.
 * <p>
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist
 * of same characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/15
 **/
public class PalindromicSubStrings {
    // 动态规划法 o(n^2)
    public int solve1(String s) {
        int n = s.length();
        if (n == 0)
            return 0;

        boolean[][] dp = new boolean[n][n];

        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j])
                    count++;
            }
        }
        return count;
    }

    // 基于递归的中心扩展法。最坏时间o(n^2)，平均时间实测比这个好，比方法一快4倍
    public int solve2(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int count = 0;
        for (int i = 0; i < s.length(); i++) { // 每次遍历的i都是可能的中心点
            count += extendPalindrome(s, i, i); // 考察奇数个的回文
            count += extendPalindrome(s, i, i + 1); // 考察偶数个的回文
        }

        return count;
    }

    int extendPalindrome(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--; // 从中心往左边扩展
            right++; // 从中心往右边扩展
        }
        return count;
    }

}
