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

import leetcode.linklist.DeleteNodeInLinkedList.ListNode;
/**
 * Given a singly linked list, determine if it is a palindrome.

 Example 1:

 Input: 1->2
 Output: false
 Example 2:

 Input: 1->2->2->1
 Output: true
 Follow up:
 Could you do it in O(n) time and O(1) space?
 注意区分链表为奇数和偶数时的情况
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/8
 **/
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 当链表节点为奇数个时，fast指向链表的最后一个元素，而slow恰好执行正中间的元素
        // 当链表节点为偶数个时，fast指向null，slow指向链表虚拟中点的下一个元素
        // 这恰好与数组的情况一致
        if (fast != null) {
            // 为奇数时，回文串不考虑正中间的元素
            slow = slow.next;
        }

        return compare(head, slow);
    }

    boolean compare(ListNode l1, ListNode l2) {
        l2 = reverse(l2);
        while(l1 != null && l2 != null) {
            if (l1.val != l2.val)
                return false;
            l1 = l1.next;
            l2 = l2.next;
        }

        return true;
    }

    ListNode reverse(ListNode head) {
        ListNode l1Left = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = l1Left;
            l1Left = head;
            head = next;
        }
        return l1Left;
    }
}
