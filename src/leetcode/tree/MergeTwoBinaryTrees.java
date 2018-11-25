package leetcode.tree;

/**
 * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
 * <p>
 * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.
 * <p>
 * Example 1:
 * Input:
 * Tree 1                     Tree 2
 * 1                         2
 * / \                       / \
 * 3   2                     1  3
 * /                          \   \
 * 5                           4   7
 * Output:
 * Merged tree:
 * 3
 * / \
 * 4   5
 * / \   \
 * 5   4   7
 * Note: The merging process must start from the root nodes of both trees.
 */
public class MergeTwoBinaryTrees {

    /**
     * 使用深度复制，生成一颗新的树
     * 未进行深度复制，会造成合并后
     * 的树仍被t1 和 t2 引用，而被外部的函数修改
     * <p>
     * 若不考虑引用安全问题，可直接使用两个树的其中一个生成合并后的树
     * 但对于解本题而言，由于不需要新建对象，所以更快
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode newNode = new TreeNode(t1.val += t2.val);
        newNode.left = mergeTrees1(t1.left, t2.left);
        newNode.right = mergeTrees1(t1.right, t2.right);
        return t1;
    }


    /**
     * 实现深度复制，返回的合并数不能被t1 t2 引用
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        int val = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);
        TreeNode newNode = new TreeNode(val);
        newNode.left = mergeTrees2(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        newNode.right = mergeTrees2(t1 == null ? null : t1.right, t2 == null ? null : t2.right);
        return newNode;
    }

}
