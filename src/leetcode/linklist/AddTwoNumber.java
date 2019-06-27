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
package leetcode.linklist;

/**
 * 2. Add Two Numbers
 You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example:

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/25
 **/
public class AddTwoNumber {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode start = new ListNode(0);
        ListNode node = start;
        // 使用一个数sum来记录上次两位数相加得到的数
        int sum = 0;
        while(l1 != null || l2 != null) {
            // 一定要在循环开始就除10取整，因为如果放到最后，那么像[5] [5]这种两个list的输入，就会丢掉十位数
            sum /= 10;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            ListNode tmp = new ListNode(sum % 10);
            node.next = tmp;
            node = node.next;

        }
        // 循环结束后，sum仍可能大于10（但是不可能到20），所以这种情况需要额外加一个节点
        if (sum / 10 == 1) {
            ListNode tmp = new ListNode(1);
            node.next = tmp;
        }
        return start.next;
    }
}
