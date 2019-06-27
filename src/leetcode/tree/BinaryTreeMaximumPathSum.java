/**
 * Copyright 2018 Baidu, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package leetcode.tree;

/**
 * 124. Binary Tree Maximum Path Sum
 * Given a non-empty binary tree, find the maximum path sum.

 For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

 Example 1:

 Input: [1,2,3]

 1
 / \
 2   3

 Output: 6
 Example 2:

 Input: [-10,9,20,null,null,15,7]

 -10
 / \
 9  20
 /  \
 15   7

 Output: 42

 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/23
 **/
public class BinaryTreeMaximumPathSum {
    int maxSum = Integer.MIN_VALUE;

    /**
     * 注意，最大的路径和可能不过root点。但是最大和路径的path可能跨越某个子树的根节点。
     * 因此我们需要一个全局变量max，记录这种情况可能获得的最大路径和
     * 同时，每一个节点向上传递最大的路径和时，有三种情况：
     * 1、左子树最大和（此时左边大于0且大于右边） + 当前节点的值
     * 2、右子树最大和 + 当前节点值
     * 3、当前节点值（此时左右子节点的最大和均小于0）
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        help(root);
        return maxSum;
    }

    public int help(TreeNode root) {
        if (root == null)
            return 0;

        // 左右子节点小于0时，不需要向上传递其值，因为他们会使得最大和变小
        int left = Math.max(help(root.left), 0);
        int right = Math.max(help(root.right), 0);

        maxSum = Math.max(maxSum, left + right + root.val);
        return Math.max(left, right) + root.val;

    }
}
