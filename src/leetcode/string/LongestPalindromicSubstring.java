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
 * 5. Longest Palindromic Substring
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example 1:

 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.
 Example 2:

 Input: "cbbd"
 Output: "bb"
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/9
 **/
public class LongestPalindromicSubstring {
    /**
     * 方法一：动态规划
     * 使用布尔型的二维数组dp(i, j)记录起始点为i，终止点为j的子串是否为回文串
     * 则有递推状态转移方程：dp(i, j) = (s[i] == s[j]) && (j - i < 3 || dp(i+1, j - 1))
     * 其中，第一个条件表示，i和j处的字符必须相等，这个很容易理解
     * 第二个条件分两种情况表示，j - i < 3表示i到j的字符个数不超过3个（此处也可写为j - 1 + 1 <=3),此时可以直接判断i到j为回文
     * 如果超过了3个，则查看历史状态，即dp(i+1, j-1)即可
     * @param s
     * @return
     */
    public String solve1(String s) {
        int n = s.length();
        if (n == 0 || n == 1)
            return s;
        boolean[][] dp = new boolean[n][n];

        int start = 0, end = 0;

        // 注意，此处的循环一定要保证j >= i，即字符串是从i到j的，这与下面的j  - i和dp[i + 1][j- 1]相对应
        // 两个for循环也可以从0开始，让i不超过j
        // for(int j = 0; j < n; j++) {
        //   for(int i = 0; i <= j; i++) {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j] && j - i + 1 > end - start + 1) {
                    end = j;
                    start = i;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
