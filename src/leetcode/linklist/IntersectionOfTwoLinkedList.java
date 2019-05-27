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
 * Intersection of Two Linked Lists
 *
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * if the two linked lists have no intersection at all, return null.
 The linked lists must retain their original structure after the function returns.
 You may assume there are no cycles anywhere in the entire linked structure.
 Your code should preferably run in O(n) time and use only O(1) memory.
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/5/26
 **/
public class IntersectionOfTwoLinkedList {

    /**
     * 哈希表法，比方法二空间复杂度高，O(m)或O(n)
     * @param headA
     * @param headB
     * @return
     */
    public ListNode solve1(ListNode headA, ListNode headB) {
        Map<ListNode, Integer> map = new HashMap<>();

        ListNode point = headA;
        while(point != null) {
            map.put(point, 1);
            point = point.next;
        }

        point = headB;
        while(point != null) {
            if (map.containsKey(point)) {
                return point;
            }
            point = point.next;
        }

        return null;
    }

    /**
     * 总共有四种情况：
     1、等长相交链表，此时第二种方法应该是a,b均为交点时结束
     2、等长不相交链表，此时第二种方法应该是在a，b均为null是结束
     3、不等长相交链表，此时第二种方法应该是在每个链表第二次被遍历时（第二次遍历是另一个链表的尾巴出开始，遍历到当前链表），在a，b均为交点时结束
     4、不等长不相交链表，此时应该是在每个链表均被遍历两次后，a, b均等于null，在两个链表的结尾处结束
     * @param headA
     * @param headB
     * @return
     */
    public ListNode solve2(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        if (headA == null || headB == null)
            return null;

        while(a != b) {
            a = (a == null ? headB : a.next);
            b = (b == null? headA : b.next);
        }
        return a;
    }
}
