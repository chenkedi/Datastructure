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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *350. Intersection of Two Arrays II
 Given two arrays, write a function to compute their intersection.

 Example 1:

 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2,2]
 Example 2:

 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [4,9]
 Note:

 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.
 Follow up:

 What if the given array is already sorted? How would you optimize your algorithm?
 What if nums1's size is small compared to nums2's size? Which algorithm is better?
 What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/26
 **/
public class InterSectionOfTwoArrayII {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (int n : nums1) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for (int n : nums2) {
            // 核心要点在于，哈希表中记录数组一中元素的次数
            // 数组二遇到一次，就将哈希表的元素次数减一，这样多余的元素不会超出真实相交的次数
            if (map.containsKey(n) && map.get(n) > 0) {
                res.add(n);
                map.put(n, map.get(n) - 1);
            }
        }

        int i = 0;
        int[] r = new int[res.size()];
        for (int n : res) {
            r[i++] = n;
        }
        return r;
    }
}
