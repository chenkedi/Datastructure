package leetcode.tree;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

 Note: A leaf is a node with no children.

 Example:

 Given the below binary tree and sum = 22,

 5
 / \
 4   8
 /   / \
 11  13  4
 /  \      \
 7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

 */
public class PathSum {

    /**
     * 题目要求改sum必须从root加到任一叶子节点，所以主方法只需要判断特殊情况即可，不需要对树进行遍历
     * 注意一些特殊的case类型：
     *    1
     *   /
     *  2
     *  sum: 1
     *  如果不向下面的程序，对一个root的左右分支是否为null进行判断，直接进行left || right的递归，则该case会返回true（因为右边的分支为true）
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        // 使用此种递归形式，以下条件不能在主方法中使用，因为当输入为 [], sum: 0 时， 会得出错误的结果true，实际为false
        // 所以使用这种递归形式，无法将递归统一到一个方法中
//        if(root == null) return sum == 0;
        if (root == null) return false;
        return isEqualSum(root, sum);
    }

    private boolean isEqualSum(TreeNode root, int sum) {
        if(root == null) return sum == 0;
        // 注意下面这种截止条件是不正确的，因为必须加到叶子节点，这个条件可能在中途就返回true。所以只能在root为null时，判断sum是否为来确定最终结果
//        if(root.val == sum) return true;
        if(root.left != null && root.right != null) {
            return isEqualSum(root.left, sum - root.val) || isEqualSum(root.right, sum - root.val);
        } else if(root.left != null) {
            return isEqualSum(root.left, sum - root.val);
        } else // 即使left right 皆为空，只递归右分支程序也是正确的
            return isEqualSum(root.right, sum - root.val);

    }

    /**
     * 该方法的核心思想为，
     * 递归截止点往上提升
     * 提升一层，即到达叶子节点时，sum应该与当前叶子节点
     * 的val相等，而不是再往下递归，直到碰到null时
     * 再判断sum是否为0
     *
     * 递归到叶子节点后，再往下继续递归，碰到null来判断
     * 是否含有PathSum的方法主要的确定在与无法统一以下
     * 输入：
     * [],0
     * 即树为空，且sum为0时，应该返回false，而判断sum为0
     * 的方式无法使用同一个递归结构处理这个问题，需要拆分
     * 成两个方法，单独处理上述case
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum_2(TreeNode root, int sum) {
        if (root == null) return false;

        // 此判断说明树已经到达底叶子节点，null再来判断
        if (root.left == null && root.right == null) return sum == root.val;

        return hasPathSum_2(root.left, sum - root.val) || hasPathSum_2(root.right, sum - root.val);

    }
}
