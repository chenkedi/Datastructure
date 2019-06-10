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
package leetcode;

/**
 * 9. Palindrome Number
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

 Example 1:

 Input: 121
 Output: true
 Example 2:

 Input: -121
 Output: false
 Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 Example 3:

 Input: 10
 Output: false
 Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 Follow up:

 Coud you solve it without converting the integer to a string?
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/9
 **/
public class PalindromeNumber {
    // 解法一：将数字转为字符数组
    // 根据奇偶性调整mid位置后，以mid为中心对比，向两侧扩展，对比字符是否相同
    public boolean slove1(int x) {
        char[] strArr = Integer.toString(x, 10).toCharArray();
        int mid = strArr.length / 2;
        if (strArr.length % 2 != 0)
            mid++;
        for (int i = mid; i < strArr.length; i++) {
            if (strArr[i] != strArr[strArr.length - 1 - i])
                return false;
        }
        return true;
    }



    // 解法二：将这个数字反转，为了避免数字反转后，出现溢出（非回文整数的情况下），反转中间结果使用long存储
    public boolean solve2(int x) {
        if(x < 0) return false;
        long reverse = reverse(x);
        return reverse == (long) x;
    }
    public long reverse(int x){
        long reverse = 0;
        while(x > 0){
            reverse = reverse*10+x%10;
            x /= 10;
        }
        return reverse;
    }

    // 通过仅反转一半的数字，避免产生溢出
    public boolean solve3(int x) {
        // 第二个边界条件是说：结尾为0的数字，其第一个数字也必须为0，这种情况只有0满足
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        int reverse = 0;
        // 由于每次x都要除以10，而reverse每次要乘以10，所以当x小于reverse时，表示我们已经反转一半的数字了
        while(x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        // 这里的第二个条件也为了兼容数字的个数为奇数个的情况
        // 如 12321，我们会得到reverse = 123， 而x = 12，正中间位置的数字对回文判断无影响，所以要除以10
        return reverse == x || x == reverse / 10;

    }
}
