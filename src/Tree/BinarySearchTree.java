package Tree;

/**
 * 二叉搜索树
 * @author chenkedi
 *
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> extends AbstractBinaryTree<Key, Value>{	
	
	
	/**
	 * 向树中插入元素。如果key存在则更新其中的val值，否则将其作为新节点插入到子树中
	 * 需要依赖私有的put(Node,Key,Value)方法
	 * @param key
	 * @param value
	 */
	protected Node put(Node x, Key key, Value value){
		if(x==null) return new Node(key,value,1);//当树为空的情况，直接返回这个新节点作为根节点；当遍历到数的叶子结点指向的null时，也新建结点并返回
		int compare = key.compareTo(x.key);
		if(compare<0)
			x.left = put(x.left,key,value);
		else if(compare>0)
			x.right = put(x.right,key,value);
		else x.value=value;
		x.N=size(x.left)+size(x.right)+1;
		return x;
	}
	
	
	
	
	public static void main(String[] args){
		AbstractBinaryTree<Integer, Integer> tree = new BinarySearchTree<>();
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
