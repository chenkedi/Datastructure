package leetcode.tree;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

 * Example:
 * Given a binary tree
 *      1
 *     / \
 *    2   3
 *   / \
 *  4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBinaryTree {

    private int maxDepthSumForAllSubTrees = 0;

    /**
     * 注意避免该问题的思维漏洞，那就是将问题转换为root节点左子树最大深度与右子树最大深度之和
     * 实际上最大半径有可能不跨root节点，而是root节点某个子节点的两个子树最大深度之和，当然同样的问题也有可能发生在该root的子节点上
     * 所以我们需要在递归展开求解左右子树最大深度的同时，使用全局变量max递归遍历时每一对左右子树最大深度之和的最大值，这才是正确答案
     * @param root
     * @return
     */
    public TreeNode DiameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return root;
    }

    private int  maxDepth(TreeNode node) {
        if (node == null) return 0;
        int leftSubTreeMaxDepth = maxDepth(node.left);
        int rightSubTreeMaxDepth = maxDepth(node.right);

        // 非常重要，通过堆栈的展开，记录树中所有可能的左右子树最大深度之和
        maxDepthSumForAllSubTrees = Math.max(leftSubTreeMaxDepth + rightSubTreeMaxDepth, maxDepthSumForAllSubTrees);

        // 继续maxDepth的递归求解
        return Math.max(leftSubTreeMaxDepth, rightSubTreeMaxDepth) + 1;

    }
}
