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
package leetcode.array;

/**
 * 59. Spiral Matrix II
 Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

 Example:

 Input: 3
 Output:
 [
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
 ]
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/26
 **/
public class SpiralMatrixII {
    /**
     * 此题与SpiralMatrixI非常类似，特别注意counter与元素总数n*n的关系即可
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if (n == 0)
            return res;

        int counter = 1;
        int left = 0, right = n - 1;
        int up = 0, down = n - 1;
        int total = n * n;
        while(counter <= total) {
            // 特别注意，由于计数从0开始，所以counter必须小于等于total
            // 否则会由于counter到达不了total，而导致while循环无法结束
            for (int i = left; i <= right && counter <= total; i++)
                res[up][i] = counter++;

            for (int j = up + 1; j <= down && counter <= total; j++)
                res[j][right] = counter++;

            for (int i = right - 1; i >= left && counter <= total; i--)
                res[down][i] = counter++;

            for (int j = down - 1; j >= up + 1 && counter <= total; j--)
                res[j][left] = counter++;

            left++;
            right--;
            up++;
            down--;
        }

        return res;
    }
}
