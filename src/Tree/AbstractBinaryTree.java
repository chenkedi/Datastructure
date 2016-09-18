package Tree;

import java.util.Stack;

/**
 * 各种二叉树的抽象父类
 * 公共节点结构，get、size、前中后遍历等方法
 * @author chenkedi
 *
 */
abstract class AbstractBinaryTree<Key extends Comparable<Key>,Value>{
	protected class Node{
		Key key;
		Value value;
		Node left,right;
		int N;
		boolean color;//在普通二叉树时并未使用此域
		
		//普通二叉树构造方法
		public Node(Key key,Value value, int N){
			this.key = key;
			this.value = value;
			this.N = N;
		}
		//红黑树构造方法
		public Node(Key key,Value value, int N, boolean color){
			this.key = key;
			this.value = value;
			this.N = N;
			this.color = color;
		}
	}
	protected Node root;
	
	
	/**
	 * 求树中包含的节点总数
	 * 需要依赖下面的私有size方法
	 * @return
	 */
	public int size(){
		return size(root);
	}
	/**
	 * 求以当前节点为根的子树中包含的节点数
	 * @param x
	 * @return
	 */
	protected int size(Node x){
		if(x==null)
			return 0;
		else
			return x.N;
	}
	
	/**
	 * 查找给定键，返回键中的值，如果不存在返回null
	 * 需要依赖私有的get(Node,Key)方法
	 * @param key
	 * @return
	 */
	public Value get(Key key){
		return get(root,key);
	}
	protected Value get(Node x, Key key){
		if(x==null)
			return null;
		int compare = key.compareTo(x.key);
		if(compare<0)//key比x.key小，左子树继续遍历
			return get(x.left,key);
		else if(compare>0)
			return get(x.right,key);
		else 
			return x.value;
	}
	
	/**
	 * 向树中插入元素。如果key存在则更新其中的val值，否则将其作为新节点插入到子树中
	 * 需要依赖私有的put(Node,Key,Value)方法
	 * @param key
	 * @param value
	 */
	public void put(Key key, Value value){
		root=put(root,key,value);
	}
	/**
	 * 子类需要实现自己不同的具体插入方法
	 * @param x
	 * @param key
	 * @param value
	 * @return
	 */
	abstract protected Node put(Node x, Key key, Value value);
	
	/**
	 * 前序遍历二叉树，其有两种不同的实现，一种是递归遍历，一种是用栈模拟的循环遍历
	 */
	public void preOrder(){
		preOrder(root);
		System.out.println();
		preOrderLoop(root);
		System.out.println();
		preOrderLoop2(root);
	}
	protected void preOrder(Node x){
		if(x!=null){
			System.out.print(x.value+",");
			preOrder(x.left);
			preOrder(x.right);
		}
	}
	protected void preOrderLoop(Node x){
		Stack<Node> stack = new Stack<>();
		stack.push(x);
		while(!stack.isEmpty()){
			Node tmp = stack.pop();
			if(tmp!=null){
				System.out.print(tmp.value+",");
				if(tmp.right!=null) stack.push(tmp.right);
				if(tmp.left!=null) stack.push(tmp.left);
			}						
		}
	}
	protected void preOrderLoop2(Node x){
		Stack<Node> stack = new Stack<>();
		Node p = root;
		while(p!=null || !stack.isEmpty()){
			while(p!=null){
				System.out.print(p.value+",");
				stack.push(p);
				p=p.left;
			}
			if(!stack.isEmpty()){
				p=stack.pop();
				p=p.right;
			}
		}
	}
	
	/**
	 * 中序遍历二叉树，有递归和栈模拟循环两种实现
	 */
	public void inOrder(){
		inOrder(root);
		System.out.println();
		inOrderLoop(root);
	}
	protected void inOrder(Node x){
		if(x!=null){
			inOrder(x.left);
			System.out.print(x.value+",");
			inOrder(x.right);
		}
	}
	protected void inOrderLoop(Node x){
		Stack<Node> stack = new Stack<>();
		Node p = root;
		while(p!=null || !stack.isEmpty()){
			while(p!=null){
				stack.push(p);
				p=p.left;
			}
			if(!stack.isEmpty()){
				p=stack.pop();
				System.out.print(p.value+",");
				p=p.right;
			}
			
		}
	}
	
	/**
	 * 后续遍历，循环与递归两种实现方式
	 * @param x
	 */
	public void postOrder(){
		postOrder(root);
		System.out.println();
		postOrderLoop(root);
	}
	protected void postOrder(Node x){
		if(x!=null){
			postOrder(x.left);
			postOrder(x.right);
			System.out.print(x.value+",");
		}
	}
	//使用两个指针，pre表示cur前一次访问的节点
	protected void postOrderLoop(Node x){
		Stack<Node> stack = new Stack<>();
		Node cur = root;
		Node pre = root;
		while(cur!=null || !stack.isEmpty()){
			while(cur!=null){//先遍历左子树，并按顺序压入栈，一直到左子树为空。这与前序中序无区别
				stack.push(cur);
				cur=cur.left;
			}
			if(!stack.isEmpty()){
				cur=stack.pop();
				//这里栈顶有两种情况，一种是该节点既没有左孩子，有没有右孩子，对应if中的第一种情况。为何这里不判断左孩子，是因为上面的while保证了右子树为null的cur.left一定为null(这是while的结束条件)。如果不满足第一个条件，则转入第二条
				//第二种情况是，访问完左子树和右子树后，再次回到这两个子树的根。这时该根节点在左右子树之后被访问(也是第二次被访问，第一次是在左子树被访问后向上回溯时)，也可以输出。
				//第二种情况通过pre节点判断，当pre节点是cur的右孩子时，说明是从右孩子处回溯过去的。又由于左孩子的访问一定在右孩子之前，所以只要满足pre节点是cur的右孩子，则表明该根是第二次被访问，可以输出
				if(cur.right==null || pre==cur.right){
					System.out.print(cur.value+",");
//					if(pre==cur.right){//注意，当满足第二个条件输出是，不能将cur移动到其右子树。因为回溯到第二次的节点必定是根节点，其必定有右子树，会造成死循环
//						pre=cur;
//						cur=null;
//					}else{//貌似这里的cur.right也只能是null
//						pre=cur;
//						cur=cur.right;
//					}
					//所以上述两条语句可以精简如下
					pre=cur;
					cur=null;
				}	
				else{
					stack.push(cur);
					pre=cur;
					cur=cur.right;
				}
			}
		}
	}
}
