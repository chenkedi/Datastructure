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

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 445. Add Two Numbers II
 You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Follow up:
 What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

 Example:

 Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 8 -> 0 -> 7
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/25
 **/
public class AddTwoNumberListII {

    /**
     * 注意，此题如果可以改变输入的链表，则可以通过反转两个链表后，再相加，最后结果再反转
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();

        while(l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while(l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode added = new ListNode(0);
        while(!stack2.isEmpty() || !stack1.isEmpty()) {
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }
            // 特别注意此处由第一个Node，向左构造整个结果的过程，非常精妙
            // 首先替换added当前节点的值为sum除10的余数
            // 然后将进位的数先加到该Node左边，再将added指针左移。这样做的目的是，到了最后一位时，不用在循环外部检查sum是否不为0了，
            added.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = added;
            added = head;
            sum /= 10;
        }
        // 特别注意，返回时要查看最高位是否为0.因为存在两个位数不同的数字相加，导致循环内添加了一个为0的head节点
        return added.val == 0? added.next : added;
    }

}
