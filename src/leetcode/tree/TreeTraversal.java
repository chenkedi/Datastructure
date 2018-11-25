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
            // 先一直访问一个树的左子树，并加入堆栈，直到碰到叶子节点（cur.left为NULL）
            if(cur != null) {
                stack.push(cur);
                // 在访问左子树之前加入list
                res.add(cur.val);
                cur = cur.left;
            // cur到达叶子节点而为null时，应该通过堆栈进行回溯，取到栈顶元素时，立刻转到未被访问的右子树
            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
    }

    /**
     * 一个树后序遍历的特点是，任一子树的根节点永远在右节点后，左节点永远在右节点前面。
     * 所以可从根节点一直遍历右子树，压栈的同时将值一直插入链表的首节点前，直到最后一
     * 个右子节点，保证了根节点永远在右节点后
     * 在从堆栈中逐一回溯遍历过的右子节点，对右子节点的左子树（如果存在的话）也做相同
     * 的遍历，压栈和插入，保证了左节点永远在右子节点前
     *
     * 形象地来说，整个算法以"右斜平行线"向左平移的方式扫描二叉树。对同一斜线上的点，
     * 靠下的点在前；对于不同斜线上的点，左边斜线上的点，在所有右边斜线上的点的前面
     *
     * 这与前序遍历是完全程中心对称的扫描方式：前序遍历以"左斜平行线"扫描树，而后续
     * 以右斜平行线扫描；前序遍历以从左到右的顺序插入List，而后续遍历以从右到左的顺
     * 序插入LinkedList
     * @param root
     */
    public static void postOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> res = new LinkedList<>();

        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                 // 向前序遍历那样在左子树被访问之前加入list，只不过之后所有的元素
                // 都插入其前方，这样就达到了左子树->右子树->root的顺序
                res.addFirst(cur.val);
                cur = cur.right;

            } else {
                cur = stack.pop();
                cur = cur.left;
            }
        }
    }

    public static void inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();

        TreeNode cur = root;
        // 两个条件缺一不可，stack不为空表示栈内还有元素为输出，
        // 但遍历到跨root时，一定会造成stack为空
        // 而此时cur一定不为空（只要root的还有右子树）
        while(!stack.isEmpty() || cur != null) {
            // 采用内部if的方式，if后面的分支不需要判断stack.isEmpty
            // 因为 cur 不为null时，一定会push元素进入stack
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;

            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
    }


}
