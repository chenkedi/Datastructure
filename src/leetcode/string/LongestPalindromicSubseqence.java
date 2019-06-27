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
 * 516. Longest Palindromic Subsequence
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

 Example 1:
 Input:

 "bbbab"
 Output:
 4
 One possible longest palindromic subsequence is "bbbb".
 Example 2:
 Input:

 "cbbd"
 Output:
 2
 One possible longest palindromic subsequence is "bb".
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/23
 **/
public class LongestPalindromicSubseqence {
    /**
     * 动态规划法
     * dp[i][j]表示从i到j的最长回文子序列（注意子序列和子串的区别）
     * 首先，dp[i][i]被初始化为1，因为每个字符本身就是一个回文子串
     * 若i~j的子序列中，两头的i位置字符等于j位置字符，则很显然有：dp[i][j] = dp[i+1][j-1] + 2，即最长子回文序列扩展了两个字符
     * 注意，上述情况不适用于"子串"的情况
     * 若不相等，则dp[i][j]应该等于加上i位置字符，不加j位置字符，或者加上j位置字符，不加i位置字符中，回文子串最长的那个（即两端仅加一个
     * 字符）
     * @param s
     * @return
     */
    public int solve1 (String s) {
        int n = s.length();
        if(n < 2)
            return s.length();

        int[][] dp = new int[n][n];

        for(int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            // 特别注意，这里j从i + 1开始，否则dp[i+1][j-1]在第一次循环的时候就会出现数组越界
            // 与求subString时，dp为布尔型数组不同，这里的dp直接存储i~j之间最大自回文序列长度，
            for (int j = i + 1; j < n; j++) {

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j - 1]);
                }

            }
        }
        return dp[0][n - 1];


    }
}
