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
import java.util.HashMap;
import java.util.Map;

import leetcode.linklist.DeleteNodeInLinkedList.ListNode;
/**
 * 141. Linked List Cycle
 * To represent a cycle in the given linked list,
 * we use an integer pos which represents the position (0-indexed)
 * in the linked list where tail connects to. If pos is -1,
 * then there is no cycle in the linked list.
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/3
 **/
public class LinkedListCycle {

    // 哈希解法，比较常规
    public boolean solve1(ListNode head) {
        int pos = 0;
        Map<ListNode, Integer> map = new HashMap();

        while(head != null) {
            if (map.containsKey(head)) {
                return true;
            } else
                map.put(head, 1);
            head = head.next;
        }
        return false;
    }


    // O(1)解法。设无环部分长度为m，环长度为为n，快指针一定会在 m + (n - m % n) 步骤后相遇
    public boolean solve2(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }
}
