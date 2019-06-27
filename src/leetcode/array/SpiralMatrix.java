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

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 Example 1:

 Input:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 Output: [1,2,3,6,9,8,7,4,5]
 Example 2:

 Input:
 [
 [1, 2, 3, 4],
 [5, 6, 7, 8],
 [9,10,11,12]
 ]
 Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/26
 **/
public class SpiralMatrix {
    /**
     * 此题核心要点是注意控制上、下、左、右的边界，和打印何时结束这五大边界
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0)
            return res;

        int m = matrix.length;
        int n = matrix[0].length;
        // 上下左右分别初始化为0和行数-1，0和列数-1
        int left = 0, right = n - 1;
        int up = 0, down = m - 1;

        // 打印合适结束，都通过size() < m * n来判断
        while(res.size() < m * n) {
            for (int i = left; i <= right && res.size() < m * n; i++)
                res.add(matrix[up][i]);

            for (int j = up + 1; j <= down && res.size() < m * n; j++)
                res.add(matrix[j][right]);

            for (int i = right - 1; i >= left && res.size() < m *n; i--)
                res.add(matrix[down][i]);

            for (int j = down - 1; j >= up + 1 && res.size() < m * n; j--)
                res.add(matrix[j][left]);

            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }
}
