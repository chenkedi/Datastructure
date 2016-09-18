package Tree;



/**
 * 红黑树
 * @author chenkedi
 *
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> extends AbstractBinaryTree<Key,Value>{
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	/**
	 * 判断一个节点和其父节点之间的链接是否是红色
	 * @param x
	 * @return
	 */
	private boolean isRed(Node x){
		if(x==null) return false;
		else return x.color==RED;
	}
	
	/**
	 * 用来转换一个节点的两个红色子节点的颜色，并转换自己的颜色为红色
	 * @param h
	 */
	private void flipColor(Node h){
		h.color=RED;
		h.left.color=BLACK;
		h.right.color=BLACK;
	}
		
	/**
	 * 红黑树的左旋转操作，返回选择后的子树的根节点
	 * @param h
	 * @return
	 */
	private Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color=h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left)+size(h.right);
		return x;
	}
	
	private Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1+size(h.left)+size(h.right);
		return x;
	}
	
	/**
	 * 查找key，如果站到则更新它的值，没有找到则将键值最为新节点插入红黑树
	 * @param key
	 * @param value
	 */
	@Override
	public void put(Key key,Value value){
		root = put(root,key, value);
		root.color=BLACK;//注意，根节点是黑色的
	}
	protected Node put(Node h,Key key, Value value){
		if(h==null)//标准的插入操作，插入一个节点后，其与父节点中的连接总是被标注作为红色
			return new Node(key,value,1,RED);
		int compare = key.compareTo(h.key);
		if(compare<0)
			h.left = put(h.left,key,value);
		else if(compare>0)
			h.right = put(h.right,key,value);
		else
			h.value = value;
		if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);//将任意含有红色右链接的3-节点或临时的4-节点向左旋转
		if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);//将临时的4-节点中两台连续的红链接中的上层链接向右旋转
		if(isRed(h.left) && isRed(h.right)) flipColor(h);//对左右均为红链接的平衡子树更改颜色，并将红链接向上传递
		h.N = 1+size(h.left)+size(h.right);
		return h;
	}
	
	
	
	public static void main(String[] args){
		AbstractBinaryTree<Integer, Integer> tree = new RedBlackBST<>();
		tree.put(4,4);
		tree.put(2,2);
		tree.put(6,6);
		tree.put(1,1);
		tree.put(3,3);
		tree.put(5,5);
		tree.put(7,7);
		
		tree.preOrder();
		System.out.println();
		tree.inOrder();
		System.out.println();
		tree.postOrder();
		System.out.println();
		System.out.println(tree.size());
		System.out.println(tree.get(6));
	}
}
