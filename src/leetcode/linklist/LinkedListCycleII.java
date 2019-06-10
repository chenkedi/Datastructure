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
 * 142. Linked List Cycle II
 * Given a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 * 与1的区别在于，I中快慢指针相遇，不一定在环开始的节点
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/3
 **/
public class LinkedListCycleII {

    // 常规解法，使用哈希
    public ListNode solve1(ListNode head) {
        Map<ListNode, Integer> map = new HashMap<>();
        ListNode  p = head;

        while(p != null) {
            if (map.containsKey(p)) {
                return p;
            } else
                map.put(p, 1);

            p = p.next;
        }
        return null;
    }


    /**
     * floyd algorithm
     * 先判断是否有环，然后让慢指针回到头部，两个指针再以相同的速度移动
     * 最终两者相等时，一定就是环的起始节点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {

        ListNode slow = head, fast = head;
        boolean isCycle = false;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isCycle = true;
                // 注意，这里一定要break，不然死循环
                break;
            }

        }

        if (!isCycle)
            return null;
        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;

    }
}
