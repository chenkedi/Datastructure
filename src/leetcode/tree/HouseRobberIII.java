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

import java.util.HashMap;
import java.util.Map;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

 Determine the maximum amount of money the thief can rob tonight without alerting the police.

 Example 1:

 Input: [3,2,3,null,3,null,1]

 3
 / \
 2   3
 \   \
 3   1

 Output: 7
 Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 Example 2:

 Input: [3,4,5,1,3,null,1]

 3
 / \
 4   5
 / \   \
 1   3   1

 Output: 9
 Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 贼抢劫一个街区，街区的住户刚好组成一个二叉树，二叉树节点的数字代表钱。如果连续抢劫两家则会惊动警察被抓，问给定一个二叉树，不惊动警察能偷的最大的钱是多少
 注意，不能连续抢意味着既必须至少跳过一家，但是也可以跳过多家
 * @date 2019/1/6
 **/
public class HouseRobberIII {

    // 解法一：将求一个点的最大rob问题，视为已知其左右子树最大rob时的子问题
    // 则此时，root的最大rob要么为（root不被抢）左右子树最大rob之和，要么为root + 左子树的左子树和右子树最大rob之和 +
    // 右子树的右子树最大rob之和
    public int rob_1(TreeNode root) {
        if (root == null)
            return 0;

        int val = 0;
        if (root.left != null)  {
            val += rob_1(root.left.left) + rob_1(root.left.right);
        }

        if (root.right != null) {
            val += rob_1(root.right.left) + rob_1(root.right.right);
        }

        return Math.max(root.val + val, rob_1(root.left) + rob_1(root.right));
    }


    // 解法二：解法一在return语句和if语句中，递归重复求取了很多次子节点的最优rob数
    // 按解法一可发现这是一个动态规划问题，所以可使用缓存来降低重复求取的时间开销
    public int rob_2(TreeNode root) {
        Map<TreeNode, Integer> dp = new HashMap<>();
        return rob(root, dp);
    }

    private int rob(TreeNode root, Map<TreeNode, Integer> dp) {
        if (root == null)
            return 0;
        // 注意，使用缓存后，第一件事就是判断是否能命中缓存。
        // 如果在后续的rob中再查询缓存，则无效
        if (dp.containsKey(root)) {
            return dp.get(root);
        }

        int val = 0;
        if (root.left != null)  {
            // 注意，后续所有的rob递归调用的都是带dp缓存的方法，而不是原rob方法
            val += rob(root.left.left, dp) + rob(root.left.right, dp);
        }

        if (root.right != null) {
            val += rob(root.right.left, dp) + rob(root.right.right, dp);
        }

        int maxRob = Math.max(root.val + val, rob(root.left, dp) + rob(root.right, dp));
        // 在求得某个节点的maxRob后要写入缓存
        dp.put(root, maxRob);
        return maxRob;
    }

    // 解法三：无论是解法一还是解法二，都无法避免重复递归求某些子节点最大rob值的问题，我们要从本质上思考能否避免这个问题
    // 解法一二的问题在于没有区分某一节点仅存的两种状态：即要么被偷，要么没被偷。所以在内部需要同时在if和return中处理这两种
    // 情况。即便使用缓存，也只能缓存两种状态取max后的值，所以仍存在重复求子节点最大rob值的问题
    // 解决办法是对每个节点均设置两个缓存，分别存被偷和不被偷时的maxRob，这样就只需要在if中进行递归调用，而避免了return中的重复求     // max rob
    public int rob_3(TreeNode root) {
        int[] res = robHelp(root);
        return Math.max(res[0], res[1]);
    }

    public int[] robHelp(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};

        // 设0表示不被偷， 1表示被偷
        int[] val = new int[2];

        // 注意，由于root的左子树和右子树均要在后面求val用到多次，所以要先求出来，并用遍历缓存值,否则再求val时多次递归调用会超时
        int[] left = robHelp(root.left);
        int[] right = robHelp(root.right);

        // 特别注意，当root不被抢时，其左子节点可能被抢，也可能不被抢（因为贼可以跳过两个节点再抢），具体要看那种情况rob值更大
        val[0] = Math.max(left[0], left[1]) +
                Math.max(right[0], right[1]);


        val[1] = root.val + left[0] + right[0];

        return val;
    }



}
