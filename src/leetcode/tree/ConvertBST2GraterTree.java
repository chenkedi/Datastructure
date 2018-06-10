package leetcode.tree;

/**
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

 * Example:
 *
 * Input: The root of a Binary Search Tree like this:
 *       5
 *     /   \
 *    2    13
 *
 * Output: The root of a Greater Tree like this:
 *      18
 *    /   \
 *  20     13
 */
public class ConvertBST2GraterTree {
    int sum = 0;
    /**
     * 特别注意此题的思维漏洞，即上一层的所有节点均大于其左子节点的右子节点,如下图的节点7所示,其转换后的值为18：
     *
     *      5
     *    /   \
     *   2     6
     *  / \
     * 3  7
     * 解题思路为按照类似后续遍历的方式累加求和，只是顺序变为：右子树，root， 左子树, 这样就可以很好的处理上述的问题
     * 使用类变量来作为递归遍历求和的全局变量，这样在递归的任何一个堆栈均可访问当前总和
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
       convert(root);
       return root;
    }

    private void convert(TreeNode node) {
        if(node == null) return;
        convert(node.right);
        node.val += sum;
        sum = node.val;
        convert(node.left);
    }
}
