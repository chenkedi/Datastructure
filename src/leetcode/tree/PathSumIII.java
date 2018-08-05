package leetcode.tree;

/**
 * You are given a binary tree in which each node contains an integer value.

 Find the number of paths that sum to a given value.

 The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

 The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

 Example:

 root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

 10
 /  \
 5   -3
 / \    \
 3   2   11
 / \   \
 3  -2   1

 Return 3. The paths that sum to 8 are:

 1.  5 -> 3
 2.  5 -> 2 -> 1
 3. -3 -> 11
 */
public class PathSumIII {
    /**
     * 主方法对树进行递归遍历的同时，累加每一个树节点等于sum的所有可能线路的count
     * 主方法为左右递归直到叶子节点
     *
     * 统计从某一节点开始，所有相加等于sum的线路条数的函数为DFS的写法，每往下递归一层，则sum减少node.val,
     * 只有碰到node为null才返回（返回0次,表示到了叶子节点，线路和仍不为sum）
     * 注意，在DFS中若碰到node.va == sum的情况不能返回，只能将次数累加1，因为继续往下一层相加仍有可能得到sum（节点有正负）
     * @param root
     * @param sum
     * @return
     */
    public int solve_1(TreeNode root, int sum) {
        if(root == null) return 0;
        return solve_1(root.left, sum) + solve_1(root.right, sum) + countEqual(root, sum);

    }

    private int countEqual(TreeNode node, int sum) {
        if(node == null) return 0;
        return (node.val == sum ? 1 : 0) +
        countEqual(node.left, sum - node.val) + countEqual(node.right, sum - node.val);
    }

    // TODO 使用prefix sum 来求解，降低时间复杂度
    public int solve_2(TreeNode root, int sum) {
        return 0;
    }
}
