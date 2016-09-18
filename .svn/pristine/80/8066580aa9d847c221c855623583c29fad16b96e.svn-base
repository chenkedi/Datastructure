package linear.list;

import java.util.Random;



public class LinkedList<T extends Comparable<T>>{
	//链表的头指针
	public Node<T> header;
	
	/**链表节点的结构**/
	class Node<E extends Comparable<E>>{
		public E data;
		public Node<E> next=null;
		public Node(){
			super();
		}
		public Node(E data){
			this.data=data;
		}
	}
	
	public LinkedList(){
		header=null;
	}
	
	
	/**
	 * 按序号查找链表中的元素
	 * 对于链表中的第i个元素，指向他的指针实际上是在前一个节点中的，本方法中的i的计数从1开始，意义为p指向的节点，而不是p所在的节点。
	 * 对于课程中的讨论，改成如题所示，程序时错误的。主要针对index非法输入中的index为负数的情况，应该返回null，实际上返回的是第一个节点
	 * @param index
	 * @return
	 */
	public T get(int index){
		Node<T> p=this.header;//设置用于移动的临时指针变量
		int i=1;//由于初始的p指向第一个节点，所以i从1开始，所以这里的i的意思是p指向的节点，而不是p所在的节点。
		
		while(p!=null && i<index){	
			p=p.next;
			i++;			
		}
		if(i==index)
			return p.data;
		else
			return null;
	}
	
	/**
	 * 按值查找链表中的元素
	 * @param data
	 * @return
	 */
	public T find(T element){
		Node<T> p=this.header;//设置用于移动的指针变量
		while(p!=null && !p.data.equals(element)){									
			p=p.next;
		}
		return p.data;
	}
	
	/**
	 * 求链表的表长
	 * 表长的计数从0开始，因为p等于某个节点的next域，但是p指向的是下一个节点。而长度是计算到当前节点，所以此处i的意义是技术到p所在的节点。
	 * @return
	 */
	public int length(){
		int i=0;
		Node<T> p=this.header;
		while(p!=null){
			p=p.next;
			i++;
		}
		return i;
	}
	
	/**
	 * 在头指针处插入新的节点
	 * @param nodeData
	 */
	public void insert(T nodeData){
		insert(1,nodeData);
	}
	
	/**
	 * 在链表中将节点插入到第index的位置，即在index-1的后面插入一个节点，这样被插入的节点就在第index位置了。所以(1<=i<=n+1)
	 * @param index，第几个节点，从1开始
	 * @param node 要插入的节点
	 */
	public void insert(int index,T nodeData){
		Node<T> p=this.header;
		
		//index=1，即插入的节点要在第一个，即在头指针后插入该节点
		if(index==1){
			Node<T> node=new Node<T>(nodeData);
			node.next=p;
			header=node;			
		}else if(index<1){
			System.out.println("您输入的索引值不合法！");
			return;
		}else{
			int i=1;//由于初始的p指向第一个节点，所以i从1开始，所以这里的i的意思是p指向的节点，而不是p所在的节点。
			//我们需要找到第index-1个节点的位置，然后将该节点插入到这个节点的后面，这样这个节点才是第index个节点
			for(;p!=null && i<index-1;i++){
				p=p.next;
			}
			if(i==index-1){
				Node<T> node=new Node<T>(nodeData);
				node.next=p.next;
				p.next=node;
			}else{
				System.out.println("您输入的索引值不合法！");
				return;
			}
		}	
	}
	
	/**
	 * 在链表中删除节点，节点计数从1开始
	 * @param index 第几个节点，从1开始
	 */
	public void delete(int index){
		Node<T> p=this.header;
		Node<T> del=null;//指向被删除的节点，方便释放
		if(index<1){
			System.out.println("您输入的索引值不合法！");
			return;
		}else if(index==1){//删除头结点的情况
			//用del记住需要删除的节点
			del=p;
			//如果该链表不为空，则可以删除
			if(this.header!=null){
				this.header=this.header.next;
				//将del和p都置为空，将对象解引用，并通知jvm回收该Node
				del=p=null;
			}
			else{
				System.out.println("链表为空，无法删除！");
				return;
			}
			
		}else{
			int i=1;//由于初始的p指向第一个节点，所以i从1开始，所以这里的i的意思是p指向的节点，而不是p所在的节点。
			for(;p!=null && i<index-1;i++){
				p=p.next;
			}
			if(i==index-1){
				//p指向的是要删除节点的前一个节点，为了确保要删除的几点存在，p.next不能为空
				if(p.next!=null){
					//del记住要删除的节点
					del=p.next;
					p=p.next;
					del=null;
				}				
			}else{
				System.out.println("第"+index+"个节点不存在");
				return;
			}
		}
	}
	
	
	public void insertion_sort(){
		if(header!=null && header.next!=null){
			Node<T> x = header.next;
			Node<T> preX = header;
			while(x!=null){
				Node<T> key = x;
				x=x.next;
				Node<T> tmp=header,preTmp = header;
				for(int i=1;tmp!=key && key.data.compareTo(tmp.data)>0;i++){
					if(i!=1)
						preTmp=preTmp.next;
					tmp=tmp.next;
				}
				if(tmp==preTmp){//插入位置在头结点处,必定由循环中的第二个条件不符合跳出
					preX.next=key.next;//将key指向的节点从后面未排序的部分删除
					key.next=header;
					header=key;
				}else if(tmp!=key){//插入节点在非头结点处,且由与不满足循环中的第二个条件跳出。即此处tmp没有到达key。如果到达了key，则链表从key到头结点都是已经排好序的,不需要调整
					preX.next=key.next;
					key.next=tmp;
					preTmp.next=key;
				}else{//如果不在上述两种情况之中，则链表不需要调整，这时，为保证preX指向x的前一个节点，必须要移动preX，指向下一个节点
					preX=preX.next;				
				}

			}
		}
	}
	
	/**
	 * 对整个链表使用自顶向下的归并排序
	 */
	public void mergeSort(){
		header = mergeSorta(header);
	}
	/**
	 * 从第x个节点处对链表进行排序
	 * @param x
	 */
	private Node<T> mergeSorta(Node<T> x){
		if(x==null || x.next==null) return x;
			//使用快慢指针找到中间位置
			Node<T> fast=x,slow=x;
			while(fast.next!=null && fast.next.next!=null){
				slow = slow.next;
				fast = fast.next.next;
			}
			Node<T> left = x;
			Node<T> right = slow.next;
			//这句截断很关键
			slow.next = null;
			left = mergeSorta(left);
			right = mergeSorta(right);
			return merge(left,right);
	}
	private Node<T> merge(Node<T> left, Node<T> right){
		//先使用固定的头指向小的那个节点
		Node<T> merged = new Node<T>();
		//在使用一个可以移动的指针构合并两个有序的链表
		Node<T> tail  = merged;
		while(left!=null && right!=null){
			int cmp = left.data.compareTo(right.data);
			if(cmp<=0){
				tail.next = left;
				left = left.next;
			}else{
				tail.next = right;
				right = right.next;
			}
			tail = tail.next;
		}
		//如果左边的链表不为空，直接将tail接上左边的链表
		if(left!=null)
			tail.next = left;
		if(right!=null)
			tail.next = right;
		return merged.next;
	}
	
	
	
	
	/**
	 * 形象化打印当前的链表
	 * @return 
	 */
	public void print(){
		Node<T> p=this.header;
		System.out.print("\n header——>");
		while(p!=null){
			System.out.print(" |"+p.data+":pointer| ——>");
			p=p.next;
		}
		System.out.println("null");		
	}
	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args){
		LinkedList<Integer> list=new LinkedList<Integer>();
//		list.insert(1, 1);
//		list.insert(1, 2);
//		list.insert(1, 3);
//		
//		list.insert(1, 4);
//		list.insert(1, 5);
//		
//		list.insertion_sort();

		long begin = System.currentTimeMillis();
		Random ra = new Random();
		int element = 0;
		for(int i=0;i<=6000000;i++){			
			element = ra.nextInt(200000);
			list.insert(element);
		}
		list.mergeSort();
		long end = System.currentTimeMillis();
		System.out.println("归并排序耗时："+(end-begin));
		//list.print();
		list=new LinkedList<Integer>();
		
		begin = System.currentTimeMillis();			
		for(int i=0;i<=60000;i++){
			element = ra.nextInt(200000);
			list.insert(element);
		}
		list.insertion_sort();
		end = System.currentTimeMillis();
		System.out.println("插入排序耗时："+(end-begin));
		
		//list.insertion_sort();
		//list.mergeSort();
//		list.insert(1, 100);
//		
//		list.delete(6);
//		list.delete(1);
//		System.out.println("链表长度："+list.length());
		//list.print();
//		System.out.println(list.header.data);
	}
		
}
