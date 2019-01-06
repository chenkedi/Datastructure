package leetcode.tree;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * <p>
 * Example:
 * Given a binary tree
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * <p>
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBinaryTree {

    private int maxDepthSumForAllSubTrees = 0;

    /**
     * 与求树的最大深度关联理解
     *
     * 注意避免该问题的思维漏洞，那就是将问题转换为root节点
     * 左子树最大深度与右子树最大深度之和(依次类推到子树的root节点）
     *
     * 实际上最大半径有可能不跨root节点，而是root节点某个子节点的
     * 两个子树最大深度之和，当然同样的问题也有可能发生在该root的子节点上，如下
     *        1
     *       /\
     *      2  5
     *     /\
     *    3 4
     *       \
     *        6
     * 所以我们需要在递归求解左右子树最大深度的同时，使用全局变量max记住全局的最大半径
     * 全局最大半径需要比较左右子树的最大深度之和，与历史的最大半径，这样就能将不跨root
     * 节点的最大半径记住
     *
     * 左右子树与的最大深度与直径的最大关系是，从叶子节点自底向上的递归
     * 过程中，最大半径一定会出现在某个左右子树最大深度之和。所以最大半径与最大深度的递归
     * 程序其实仅仅做了一个中间步骤拆解，在return前，用变量为递归过程中产生的所有最大左右
     * 子树深度之和做了记忆
     *
     * @param root
     * @return
     */
    public TreeNode DiameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return root;
    }

    private int maxDepth(TreeNode node) {
        if (node == null) return 0;
        int leftSubTreeMaxDepth = maxDepth(node.left);
        int rightSubTreeMaxDepth = maxDepth(node.right);

        // 非常重要，通过记录堆栈递归过程中的全局最大值，防止出现不跨root节点（或不跨下层子树root节点的最大半径情况的发生）
        maxDepthSumForAllSubTrees = Math.max(leftSubTreeMaxDepth + rightSubTreeMaxDepth, maxDepthSumForAllSubTrees);

        // 继续maxDepth的递归求解
        return Math.max(leftSubTreeMaxDepth, rightSubTreeMaxDepth) + 1;
    }
}
