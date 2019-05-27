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
 * Median of two sorted arrays
 * 求两个有序数组合并后的中位数
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.

 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

 You may assume nums1 and nums2 cannot be both empty.

 Example 1:

 nums1 = [1, 3]
 nums2 = [2]

 The median is 2.0
 Example 2:

 nums1 = [1, 2]
 nums2 = [3, 4]

 The median is (2 + 3)/2 = 2.5
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/5/26
 **/
public class MedianOfTwoSortedArrays {

    // 该方法使用归并排序的思想，复杂度是O(m + n)，与题目要求并不相同
    public double solve_1(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        int mid = total / 2;
        int a[] = new int[mid + 1];

        int index1 = 0, index2 = 0;
        for(int i = 0; i <= mid; i++) {
            if (index1 > nums1.length - 1)
                a[i] = nums2[index2++];
            else if (index2 > nums2.length - 1)
                a[i] = nums1[index1++];
            else if (nums1[index1] <= nums2[index2])
                a[i] = nums1[index1++];
            else
                a[i] = nums2[index2++];
        }

        if (total % 2 == 0)
            return (a[mid - 1] + a[mid]) / 2.0;
        else
            return a[mid];
    }
}
