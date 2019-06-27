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
 * 48. Rotate Image
 You are given an n x n 2D matrix representing an image.

 Rotate the image by 90 degrees (clockwise).

 Note:

 You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

 Example 1:

 Given input matrix =
 [
 [1,2,3],
 [4,5,6],
 [7,8,9]
 ],

 rotate the input matrix in-place such that it becomes:
 [
 [7,4,1],
 [8,5,2],
 [9,6,3]
 ]
 Example 2:

 Given input matrix =
 [
 [ 5, 1, 9,11],
 [ 2, 4, 8,10],
 [13, 3, 6, 7],
 [15,14,12,16]
 ],

 rotate the input matrix in-place such that it becomes:
 [
 [15,13, 2, 5],
 [14, 3, 4, 1],
 [12, 6, 8, 9],
 [16, 7,10,11]
 ]
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/25
 **/
public class RotateImage {

    /**
     * 顺时针旋转，只要先将矩阵上下翻转后，再进行斜对角线元素调换即可（或者说转置）
     * @param matrix
     */
    public void rotateClockWise(int[][] matrix) {
        // 特别注意上下翻转时，i变化的公式为 length - i- 1，且i不能超过矩阵第一维的一半
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - i][j];
                matrix[matrix.length - 1 - i][j] = tmp;
            }
        }

        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j]  = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }


    }

    /**
     * 逆时针旋转
     * 与上面不同的是，先进行矩阵的左右翻转，再进行斜对角对称元素交换
     * @param matrix
     */
    public void rotateAntiClockWise(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            // 左右翻转时，j变i不变，j变化的公式为 length - j- 1，且j不能超过矩阵第一维的一半
            for (int j = 0; j < matrix[0].length / 2; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = tmp;
            }
        }
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j]  = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}
