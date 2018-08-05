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

    /**
     * 注意到一个数的后续遍历的特点是，树的根节点永远在右节点前面，左节点永远在右节点前面。
     * 对于同一性质的节点，层数高的点在层数低的点前面
     * 所以可以从根节点一直遍历右子树，并始终插入结果集的第一位（保证右子节点在根节点后面），同时也压入堆栈，直到最后一个右子节点
     * 在从堆栈中弹出右子节点，对右子节点的左子树（如果存在的话）也做相同的遍历，并插入结果集的第一位，保证左子节点在右子节点前
     *
     * 整个算法以类似"右斜平行线"向左平移的方式扫描二叉树。对同一斜线上的点，靠下的点在前；对于不同斜线上的点，左边的斜线上点，在所有
     * 右边斜线上的点前面
     * @param root
     */
    public static void postOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> res = new LinkedList<>();

        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) { // 先一直访问一个树的右子树，并加入堆栈，直到碰到叶子节点（cur.left为NULL）
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
