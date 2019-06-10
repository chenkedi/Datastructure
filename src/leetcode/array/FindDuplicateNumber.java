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
 *287. Find the Duplicate Number
 Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Example 1:

 Input: [1,3,4,2,2]
 Output: 2
 Example 2:

 Input: [3,1,3,4,2]
 Output: 3
 Note:

 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/8
 **/
public class FindDuplicateNumber {
    // 模拟链表寻找环的算法
    // 通过数组数字和数组索引连接各个数字。先让快慢指针相遇，找到meet point
    // 再将slow指针回到头部（即slow为0），然后寻找cycle的entry point
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;

        while(true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast){
                break;
            }
        }

        slow = 0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;

    }
}
