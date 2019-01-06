package leetcode.tree;

import java.util.HashMap;
import java.util.Map;

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


    /**
     * 此题降低复杂度的关键是，通过历史前缀和出现的次数，来计算下一次命中前缀和时，path应该能组合出多少种可能
     * 如 序列1,2,-1,-1,2，其前缀和为：1, 3, 2, 1, 3。
     * 变为map即：
     * 0 -> 1次（这个是默认要加入的）
     * 1->2次
     * 3->2次
     * 2->1次
     * 如果我们要找目标sum为2的路径，有3 - 2 = 1，此时前缀和1的次数为1次
     * 2 -2 = 0，此时前缀和0的次数为1次
     * 3-2 = 1此时前缀和的次数为2次
     * 所以总共的次数为4次，对应的path为下：
     * {2}(对应前缀和为1的那次），{1, 2, -1}（对应前缀和为0的那次）, {2, -1, -1, 2}, {2}（这两次对应前缀和为1，而前缀和1出现过两次
     * ，所以此处也有两条路径组合）
     * @param root
     * @param sum
     * @return
     */
    public int solve_2(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        return countEqual(root, 0, sum, prefixSum);
    }

    private int countEqual(TreeNode root, int curSum, int targetSum, Map<Integer, Integer> prefixSum) {
        if(root == null)
            return 0;

        curSum = root.val + curSum;

        // 用当前和减去目标sum，在前缀和中寻找，若找到则表示此path的sum为目标sum，且可以组合出找到次数
        // 注意这里的count不需要先初始化为0，再与map命中的值相加，因为default的值就是0
        int count = prefixSum.getOrDefault(curSum-targetSum, 0);
        // 将当前的前缀和更新或者加入到map中，注意getOrDefault的使用
        prefixSum.put(curSum, prefixSum.getOrDefault(curSum, 0) + 1);

        count += countEqual(root.left, curSum, targetSum, prefixSum) + countEqual(root.right, curSum, targetSum, prefixSum);

        // 注意，在return网上回溯前，要将当前的前缀和的次数减去1，不能让树其他path分支的前缀和影响其他path分支
        prefixSum.put(curSum, prefixSum.get(curSum) - 1);
        return count;



    }
}
