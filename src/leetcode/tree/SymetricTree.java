package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

      1
     / \
    2   2
   / \ / \
  3  4 4  3
 But the following [1,2,2,null,3,null,3] is not:
     1
    / \
   2   2
   \   \
   3    3
 Note:
 Bonus points if you could solve it both recursively and iteratively.
 注意题目的意思是镜像对称，即按中心折叠后，树能够重合
 所以应该是left.left = right.right && left.right == right.left
 */
public class SymetricTree {

    /**
     * 递归解法
     * 解法核心在类说明中已经给出，只要对每个左右子树均进行此项递归检测即可判断
     *
     * 算法的核心在于将一个单节点的递归函数，变为两个节点的递归
     * 函数，root节点以及其左右子节点需要特殊对待，后面的子树都可
     * 按同样的模式递归
     * @param root
     * @return
     */
    public boolean solve_1(TreeNode root) {
        if(root == null)
            return true;
        // 当root不为空时，递归检测其左右子树是否为镜像对称
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        // 当左右子树的根节点至少有一个为空时，仅有两者同为空时才能满足镜像对称条件
        // 其余情况均为false
       if(left == null || right == null)
           return left == right;
       // 执行到此处，left和right一定都不为null, 若两者的值不同，则直接返回false
       if(left.val != right.val)
           return false;
       // 若当前left和right的值相同，则递归的检测两者的子树是否同时满足镜像条件
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    /**
     * 循环解法
     * @param root
     * @return
     */
    // TODO
    public boolean solve_2(TreeNode root) {

        return false;
    }


    /**
     * !!!!!!错误的解法!!!!!!!!
     * 中序遍历树，若结果关于中心对称(因为镜像树一定有基数个节点)，则树为镜像对称
     * 该解法虽然对于某些特殊case(如下所示）不正确，但是仍有记录价值
     *         1
     *        / \
     *       3   2
     *      /   /
     *     2   3
     * @param root
     * @return
     */
    public boolean solve_error3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.add(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }

        // 检查对称位置的数是否相同
        for(int i = 0, j = res.size() - 1; i < res.size() / 2; i++, j--) {
            if(res.get(i).equals(res.get(j)))
                return false;
        }
        return true;


}
}
