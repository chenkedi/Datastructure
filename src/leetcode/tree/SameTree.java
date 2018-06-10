package leetcode.tree;

import java.util.Stack;

/**
 * Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 * @author chenkedi
 * 给定两个二叉树，判断这两个二叉树是否相等。相等指的是结构完全相同，并且对应节点的值相同。
 * 
 */
public class SameTree{
	 public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }
	 	 
	 
	 /**
	  * 递归解法。
	  * 递归结束时，如果两个节点不同时为null，则返回false(对应第二条if),否则返回true。这里保证了结构的一致性
	  * 然后比较p、q中val是否相等，以及p、q的左子树和右字数是否分别相等，递归执行即可
	  * @param p
	  * @param q
	  * @return
	  */
	 public boolean isSameTree(TreeNode p, TreeNode q){
	        if(p==null && q==null) return true;
	        if(p==null || q==null) return false;
	        return p.val==q.val && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
	    }
	
	/**
	 * 栈模拟，循环
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTreeLoop(TreeNode p, TreeNode q) {
        Stack<TreeNode> stackp = new Stack<>();
        Stack<TreeNode> stackq = new Stack<>();
        
        stackp.push(p);
        stackq.push(q);
        while(!stackp.isEmpty() && !stackq.isEmpty()){
            TreeNode tmp = stackp.pop();
            TreeNode tmq = stackq.pop();
            if(tmp!=null && tmq!=null){
                if(tmp.val==tmq.val){
                    if(tmp.left!=null && tmq.left!=null){
                        stackp.push(tmp.left);
                        stackq.push(tmq.left);
                    }else if(tmp.left!=tmq.left) return false;
                    
                    if(tmp.right!=null && tmq.right!=null){
                        stackp.push(tmp.right);
                        stackq.push(tmq.right);
                    }else if(tmp.right!=tmq.right) return false;
                }else{
                    return false;
                }
            }else if(tmp!=tmq) return false;
        }
        if(stackp.isEmpty() && stackq.isEmpty())
            return true;
        else
            return false;
    }
}
