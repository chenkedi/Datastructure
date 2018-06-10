package leetcode.tree;

import java.util.Stack;

/**
 * Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * @author chenkedi
 * 给定一个二叉树，求其最大深度
 */
public class MaximumDepthOfBT{
	
	  public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }
	
	/**
	 * 将这两个方法分开写，是因为第二个方法的实现更具通用性，可以求任何一个节点到叶节点的最大深度，而不仅仅包括root节点
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {
        return maxDepth(root,0);
    }
    
	/**
	 * 递归法
	 * @param node
	 * @param depth
	 * @return
	 */
    public int maxDepth(TreeNode node, int depth){
        if(node==null)
            return depth;
        return Math.max(maxDepth(node.left,depth+1),maxDepth(node.right,depth+1));
    }

	/**
	 * 递归法2
	 * @param node
	 * @return
	 */
	public int maxDepth2(TreeNode node) {
    	if(node == null)
    		return 0;
    	return Math.max(maxDepth2(node.left), maxDepth2(node.right)) + 1;
	}
    
    /**
     * 非递归，用栈模拟。不知道为何不正确
     * @param node
     * @return
     */
    public int maxDepthStack(TreeNode node){
    	if(node == null)
    		return 0;
    	
    	Stack<TreeNode> stack = new Stack<>();
    	stack.push(node);
    	int count = 0;
    	while(!stack.isEmpty()){
    		int size = stack.size();
    		while(size-- > 0){
    			TreeNode tmp = stack.pop();
    			if(tmp.left!=null)
    				stack.push(tmp.left);
    			if(tmp.right!=null)
    				stack.push(tmp.right);
    		}
    		count++;
    	}
    	return count;
    }
    
    /**
     * 非递归，用队列模拟
     * @param node
     * @return
     */
    public int maxDepthQueue(TreeNode node){
    	return 0;
    }
    
}
