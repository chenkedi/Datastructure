package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class TreeTraversal {

    public static void main(String[] args) {

    }

    public static void preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();

        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) { // 先一直访问一个树的左子树，并加入堆栈，直到碰到叶子节点（cur.left为NULL）
                stack.push(cur);
                res.add(cur.val); // 在访问左子树之前加入list
                cur = cur.left;

            } else { // cur到达叶子节点而为null时，应该通过堆栈进行回溯，取到栈顶元素时，立刻转到未被访问的右子树
                cur = stack.pop();
                cur = cur.right;
            }
        }
    }

    public static void inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();

        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) { // 先一直访问一个树的左子树，并加入堆栈，直到碰到叶子节点（cur.left为NULL）
                stack.push(cur);
                res.add(cur.val);
                cur = cur.left;

            } else { // cur到达叶子节点而为null时，应该通过堆栈进行回溯，取到栈顶元素时，立刻转到未被访问的右子树
                cur = stack.pop();
                res.add(cur.val); // 在访问所有左子树之后加入list
                cur = cur.right;
            }
        }
    }

    public static void postOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> res = new LinkedList<>();

        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) { // 先一直访问一个树的左子树，并加入堆栈，直到碰到叶子节点（cur.left为NULL）
                stack.push(cur);
                res.addFirst(cur.val); // 向前序遍历那样在左子树被访问之前加入list，只不过之后所有的元素都插入其前方，这样就达到了左子树->右子树->root的顺序
                cur = cur.right;

            } else { // cur到达叶子节点而为null时，应该通过堆栈进行回溯，取到栈顶元素时，立刻转到未被访问的右子树
                cur = stack.pop();
                cur = cur.left;
            }
        }
    }
}
