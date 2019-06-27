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

import java.util.HashSet;
import java.util.Set;

/**
 * 349. Intersection of Two Arrays
 Given two arrays, write a function to compute their intersection.

 Example 1:

 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2]
 Example 2:

 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [9,4]
 Note:

 Each element in the result must be unique.
 The result can be in any order.
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/26
 **/
public class InterSectionOfTwoArray {

    /**
     * 使用两个哈希集，一个记录数组1的元素，用于判断数组2中的元素是否在数组一中出现。
     * 另一个哈希集用于记录相交元素，并将多次相交的相同的元素去重
     *
     * 另一种解法是，将两个数组中较小的数组排序，然后遍历较大数组，使用二分查找在小数组中找对应的数，找到即为交集元素
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();

        for (int n : nums1) {
            set.add(n);
        }

        for(int n : nums2) {
            if (set.contains(n)) {
                intersect.add(n);
            }
        }

        int i = 0;
        int[] res = new int[intersect.size()];
        for (int ele : intersect) {
            res[i++] = ele;
        }
        return res;
    }
}
