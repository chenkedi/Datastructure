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
 * Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

 For example, given a 3-ary tree:
        1
       / \ \
      3  2 4
     / \
    5  6



 We should return its level order traversal:

 [
 [1],
 [3,2,4],
 [5,6]
 ]


 Note:

 The depth of the tree is at most 1000.
 The total number of nodes is at most 5000.
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/27
 **/
public class NAryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>>  res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(root);

        while(!deque.isEmpty()) {
            List<Integer> level  = new ArrayList<>();
            int size  = deque.size();
            for (int i = 0; i < size; i++) {
                Node node = deque.poll();
                level.add(node.val);
                for (Node n : node.children) {
                    if (n != null)
                        deque.offer(n);
                }
            }
            res.add(level);
        }
        return res;
    }
}
