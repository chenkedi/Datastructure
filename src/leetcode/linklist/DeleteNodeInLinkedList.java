package leetcode.linklist;

/**
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
 * @author chenkedi
 * 给定链表中的一个节点（非最后一个节点），要直接删除这个节点。注意不是给定要删除节点的前一个节点，所以无法通过修改前一个节点next指针的方式删除该节点
 * 但是我们可以通过修改val值的方式，交换本节点和下一个节点的val值，再通过本节点next指针的修改删除下一个节点，最终到达删除本节点相同的效果
 */
public class DeleteNodeInLinkedList{
	public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
		  }
	
	
	 public void deleteNode(ListNode node) {
         node.val=node.next.val;
         node.next=node.next.next;
     }

}
