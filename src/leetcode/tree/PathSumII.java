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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

 Note: A leaf is a node with no children.

 Example:

 Given the below binary tree and sum = 22,

 5
 / \
 4   8
 /   / \
 11  13  4
 /  \    / \
 7    2  5   1
 Return:

 [
 [5,4,11,2],
 [5,8,4,5]
 ]
 * @author chenkedi chenkedi@baidu.com
 * @date 2018/12/24
 **/
public class PathSumII {

    /**
     * 使用回溯的思想，加上PathSumI 的方法（即当前层的和与节点val相等，且左右子节点均为null时，才有root-leaf的pathsum）
     * 每遍历一个节点，就在LinkedList中加入。当发现到达leaf，且和为sum时，将LinkedList中的序列复制加入最终的res
     * 并在访问完leaf的左右子节点后，对LinkedList中的值进行回溯，即删除最后一个节点的值，然后递归方法返回。
     *
     * 另一种方式为，将LinkedList中的序列复制加入最终的res后，立马进行回溯，删除最后一个节点并返回。同时，其他不满足条件的leaf节点
     * 仍然在遍历完左右子节点后回溯。
     *
     * 第一种方式会在达到符合条件的leaf节点后，比第二种方式多递归一层（即访问符合条件叶子节点的两个空指针），但是第一种方式，递归
     * 方式更为统一
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> solve(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curRes = new LinkedList<>();
        pathSum(root, res, curRes, sum);
        return res;
    }

    public void pathSum(TreeNode root, List<List<Integer>> res, List<Integer> curRes, int sum) {
        if (root == null)
            return;

        curRes.add(root.val);
        if(root.val == sum && root.left == null && root.right == null) {
            res.add(new ArrayList<>(curRes));
            // 加上这两句就是第二种方式
//            curRes.remove(curRes.size() - 1);
//            return;
        }

        pathSum(root.left, res, curRes, sum - root.val);
        pathSum(root.right, res, curRes, sum - root.val);

        curRes.remove(curRes.size() - 1);
        return;

    }
}

