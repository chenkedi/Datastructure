package leetcode;

/**
 * Reverse a singly linked list.
 * @author chenkedi
 * 虽然题目没有要求，但是我们尽量要使用常数空间和在O(n)的时间内实现循环的方式
 * 还有递归的方式也可以
 */
public class ReverseLinkedList{

	public class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	/**
	 * 方法一和方法二唯一的区别在与，用几个临时指针来进行链表的逆转
	 * 方法一使用一个临时的指针，而方法二使用两个临时指针。所以方法一需要在第一次循环时将原链表的第一个节点指向null，因为逆转后其就是最后一个节点
	 * 方法二head节点在循环完成后仍然指向原链表的第一个节点，所以只要在循环结束后将head所指向的节点的next置为null即可
	 * @param head
	 * @return
	 */
	public static ListNode reverse_loop1(ListNode head){
		//链表为空或者链表只包含一个节点，则链表的逆等于自身
		if(head==null || head.next==null)
			return head;
		int i =1;
		ListNode headNext=head.next;
		while(headNext!=null){
			if(i==1){
				head.next=null;
			}
			ListNode tmp = headNext.next;
			headNext.next=head;
			head = headNext;
			headNext=tmp;
			i++;
		}
		return head;
	}
	
	public static ListNode reverse_loop2(ListNode head){
		//链表为空或者链表只包含一个节点，则链表的逆等于自身
		if(head==null || head.next==null)
			return head;
		ListNode index=head,indexNext=head.next;
		while(indexNext!=null){
			ListNode tmp = indexNext.next;
			indexNext.next=index;
			index = indexNext;
			indexNext=tmp;		
		}
		head.next=null;
		return index;
	}
}
