package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
    to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
This problem was inspired by this original tweet by Max Howell:
Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so fuck off.
 * @author chenkedi
 *
 */
public class InvertBinaryTree {

	class TreeNode {

		int			val;
		TreeNode	left;
		TreeNode	right;

		TreeNode(int x) {

			val = x;
		}
	}


	/**
	 * 递归法
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode invertTree(TreeNode root) {

		if(root == null)
			return root;
		TreeNode right = invertTree(root.right);
		TreeNode left = invertTree(root.left);
		root.left = right;
		root.right = left;
		return root;
	}


	/**
	 * 循环法，用栈模拟 在leetcode中测试，入栈或者入队的顺序对于结果的正确与否没有影响。
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode invertTreeStack(TreeNode root) {

		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()){
			TreeNode node = stack.pop();
			if(node != null){
				stack.push(node.right);
				stack.push(node.left);
				TreeNode tmp = node.left;
				node.left = node.right;
				node.right = tmp;
			}
		}
		return root;
	}


	/**
	 * 循环法，用队列模拟
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode invertTreeQueue(TreeNode root) {

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()){
			TreeNode node = queue.poll();
			if(node != null){
				queue.offer(node.left);
				queue.offer(node.right);
				TreeNode tmp = node.left;
				node.left = node.right;
				node.right = tmp;
			}
		}
		return root;
	}
}
