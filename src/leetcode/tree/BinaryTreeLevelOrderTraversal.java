package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import apple.laf.JRSUIUtils;

/**
 Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its level order traversal as:
 [
 [3],
 [9,20],
 [15,7]
 ]

 */
public class BinaryTreeLevelOrderTraversal {
    /**
     * 使用队列模拟的循环解法
     * 关键在于每一次进入while循环后，queue内部的所有节点属于同一层
     * 可使用一个变量记住该层元素数量，而后出队遍历和入队就不会超过
     * 该层元素的数目
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> comp = new ArrayList<>(levelSize);
            for(int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                comp.add(node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
           res.add(comp);
        }
        return res;
    }
}
