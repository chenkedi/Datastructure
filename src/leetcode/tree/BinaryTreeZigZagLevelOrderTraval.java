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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Binary Tree Zigzag Level Order Traversal
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its zigzag level order traversal as:
 [
 [3],
 [20,9],
 [15,7]
 ]
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/5/27
 **/
public class BinaryTreeZigZagLevelOrderTraval {

    public static void main(String[] args) {
        TreeNode l31 = new TreeNode(15);
        TreeNode l32 = new TreeNode(17);
        TreeNode l21 = new TreeNode(9);
        TreeNode l22 = new TreeNode(20);
        l22.left = l31;
        l22.right = l32;
        TreeNode root = new TreeNode(3);
        root.left = l21;
        root.right = l22;
        zigzagLevelOrder(root);
    }
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root == null) {
            return res;
        }
        int levelCount = 0;
        deque.offerLast(root);

        while(!deque.isEmpty()) {
            int levelSize = deque.size();
            List<Integer> level = new ArrayList<>();
            boolean isEven = (levelCount % 2 == 0);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = null;
                if (isEven) {
                    node = deque.pollFirst();
                    if (node.left != null)
                        deque.offerLast(node.right);
                    if (node.right != null)
                        deque.offerLast(node.left);

                } else {
                    node = deque.pollLast();
                    if (node.right != null)
                        deque.offerFirst(node.left);
                    if (node.left != null)
                        deque.offerFirst(node.right);
                }
                level.add(node.val);

            }
            levelCount++;
            res.add(level);
        }

        return res;
    }
}
