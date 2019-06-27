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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 Example:

 Input:
 [
 1->4->5,
 1->3->4,
 2->6
 ]
 Output: 1->1->2->3->4->4->5->6
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/15
 **/
public class MergeKSortedLists {
    // 方法一：通过 merge two sorted linked list 方式，两两合并。复杂度O(kn)
    public ListNode solve1(ListNode[] lists) {
        ListNode merged = null;
        for (int i = 0; i < lists.length; i++) {
            merged = merge(lists[i], merged);
        }
        return merged;
    }

    ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode node = head;

        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }

        node.next = (l1 == null) ? l2 : l1;
        return head.next;
    }

    // 方法二：遍历全部K个链表，并加入集合，对结合进行重排后，再生成一个新的链表O(nlogn)
    public ListNode solve2(ListNode[] lists) {
        ListNode merged = new ListNode(0);
        List<Integer> list = new ArrayList<>();
        ListNode node = merged;
        for (int i = 0; i < lists.length; i++) {
            while(lists[i] != null) {
                list.add(lists[i].val);
                lists[i] = lists[i].next;
            }
        }

        Collections.sort(list);
        for (int val : list) {
            node.next = new ListNode(val);
            node = node.next;
        }
        return merged.next;
    }

    // 方法三：使用优先队列（基于堆），先依次添加k个队列的头，然后利用大小为K的堆，出队一个，再根据出队的node的next入队一个
    // 充分利用有序信息，复杂度O（NlogK）
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.val < o2.val)
                return -1;
            else if (o1.val == o2.val)
                return 0;
            else
                return 1;
        });

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.offer(lists[i]);
            }

        }
        ListNode merged = new ListNode (0);
        ListNode point = merged;
        while(!queue.isEmpty()) {
            ListNode node = queue.poll();
            point.next =node;
            point = point.next;
            if (node.next != null) {
                queue.offer(node.next);
            }
        }
        return merged.next;
    }


    // 方法四：O（NlogK）使用归并排序的思想，每次合并一半的lists：
    // To put it simpler, assume the k is 2^x, So the progress of combination is like a full binary tree,
    // from bottom to top. So on every level of tree, the combination complexity is n。Total level is logk
    // 最关键的点，每一层树的左右子链表，没有重复节点！！！这是改进第一个算法的核心要点
    public ListNode solve4(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];
        if (lists.length == 2)
            return mergeTwoList(lists[0], lists[1]);
        return mergeTwoList(solve4(Arrays.copyOfRange(lists, 0, lists.length / 2)), solve4(Arrays.copyOfRange(lists, lists.length / 2, lists.length)));
    }

    ListNode mergeTwoList(ListNode l1, ListNode l2) {

        ListNode node = new ListNode(0);

        ListNode point = node;
        while(l1 != null && l2 != null) {
            if (l1.val < l2.val){
                point.next = l1;
                l1 = l1.next;
            } else {
                point.next = l2;
                l2 = l2.next;
            }
            point = point.next;
        }
        point.next = l1 == null ? l2 : l1;
        return node.next;
    }


}
