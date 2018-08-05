package leetcode.tree;

import apple.laf.JRSUIUtils;

/**
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

 Example 1:
 Given tree s:

 3
 / \
 4   5
 / \
 1   2
 Given tree t:
 4
 / \
 1   2
 Return true, because t has the same structure and node values with a subtree of s.
 Example 2:
 Given tree s:

 3
 / \
 4   5
 / \
 1   2
 /
 0
 Given tree t:
 4
 / \
 1   2
 Return false.
 */
public class SubTreeOfAnotherTree {

    /**
     * 先从s的root开始遍历，每到树s的一个节点，即用isIdentity函数判断是否与t树相同, 当找到第一个节点包含t树时，立即返回
     * isIdentity函数与SameTree题目的写法一样
     * @param s
     * @param t
     * @return
     */
    public boolean solve_1(TreeNode s, TreeNode t) {
        if(t == null) return true;
        if(s == null) return false;
        if(!isIdenticla(s, t))
            // 若从s的当前节点出发，不嫩刚找到t的子树，则看s的右子树或者左子树是否包含t
            return solve_1(s.left, t) || solve_1(s.right, t);
        else
            // 找到则直接返回
            return true;



    }

    private boolean isIdenticla(TreeNode s, TreeNode t) {
        if(s == null || t == null) return s == t;
        return s.val == t.val && isIdenticla(s.left, t.left) && isIdenticla(s.right, t.right);
    }
}
