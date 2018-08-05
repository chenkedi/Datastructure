package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import leetcode.tree.SameTree;

public class Debug {
    public List<List<Integer>> levelOrder(SameTree.TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if(root == null) return res;
        Stack<Map<Integer, SameTree.TreeNode>> stack = new Stack<>();
        int lastLevel = 0;
        Map<Integer, SameTree.TreeNode> l0 = new HashMap<>();
        List<Integer> component = new LinkedList<>();
        l0.put(lastLevel, root);
        stack.push(l0);
        while(!stack.isEmpty()) {
            Map<Integer, SameTree.TreeNode> nodeMap = stack.pop();
            SameTree.TreeNode node = nodeMap.entrySet().iterator().next().getValue();

            if(!nodeMap.containsKey(lastLevel)) {
                lastLevel++;
                res.add(new LinkedList<>(component));
                component.clear();
            }

            if(node.right != null) {
                Map<Integer, SameTree.TreeNode> tmp = new HashMap<>();
                tmp.put(lastLevel + 1, node.right);
                stack.push(tmp);
            }
            if(node.left != null) {
                Map<Integer, SameTree.TreeNode> tmp = new HashMap<>();
                tmp.put(lastLevel + 1, node.left);
                stack.push(tmp);
            }
            component.add(node.val);
        }
        res.add(component);
        return res;
    }

    public static void main(String[] args) {
        Debug de = new Debug();
        SameTree.TreeNode root = new SameTree().new TreeNode(3);
        SameTree.TreeNode l1 = new SameTree().new TreeNode(9);
        SameTree.TreeNode r1 = new SameTree().new TreeNode(20);
        SameTree.TreeNode l2 = new SameTree().new TreeNode(15);
        SameTree.TreeNode r2 = new SameTree().new TreeNode(7);
        root.left = l1;
        root.right = r1;
        r1.left = l2;
        r1.right = r2;
        System.out.println(de.levelOrder(root));

    }
}
