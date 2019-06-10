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
 * 19. Remove Nth Node From End of List
 Given a linked list, remove the n-th node from the end of list and return its head.

 Example:

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:

 Given n will always be valid.

 Follow up:

 Could you do this in one pass?
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/8
 **/
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 新建一个头结点，是因为n可以为整个链表的长度，此时需要删除的是头结点
        // 如 [1, 2], n = 2, 则结果为[2]
        ListNode start = new DeleteNodeInLinkedList().new ListNode(0);
        ListNode slow = start, fast = start;
        start.next = head;

        // 核心点在于，先让fast移动n+1步（n+1是因为我们新建了一个start节点）
        // 然后利用fast为null的条件，让slow指针移动 N - n步，则slow恰好移动到了待删除节点的前面
        for(int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return start.next;

    }
}
