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
 * 33. Search in Rotated Sorted Array
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.

 Your algorithm's runtime complexity must be in the order of O(log n).

 Example 1:

 Input: nums = [4,5,6,7,0,1,2], target = 0
 Output: 4
 Example 2:

 Input: nums = [4,5,6,7,0,1,2], target = 3
 Output: -1
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/26
 **/
public class SearchInRotatedSortedArray {
    /**
     * 该题主要是分两种情况，可通过left[mid] < left[high]来判断
     * 1、最小的点在mid左边，此时左半部分无序，右半部分有序
     * 2、最小的点在mid右边，此时左半部分有序，右半部分无序
     * 同时注意到，low或者high变动后，又是递归的执行上述的判断
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        // https://blog.csdn.net/sinat_35261315/article/details/78425262
        // 数组的二分查找，为何这里length必须减1？
        // 因为如果第一次，变动的是low指针，那么high就会数组越界
        int low = 0, high = nums.length - 1;

        while(low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return mid;
            // 最小点在mid左边
            if (nums[mid] < nums[high]) {
                // 由于事先判断了mid位置是否等于target，所以此处target与num[mid]不需要等号
                if (target > nums[mid] && target <= nums[high])
                    // target在右边的有序部分
                    low = mid + 1;
                else
                    high = mid - 1;
            } else { // 最小点在mid右边
                if (target < nums[mid] && target >= nums[low])
                    // target在左边的有序部分
                    high = mid - 1;
                else
                    low = mid + 1;
            }
        }

        if (nums.length == 0 || nums[low] != target)
            return -1;
        return low;
    }
}
