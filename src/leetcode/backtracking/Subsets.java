package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).

 Note: The solution set must not contain duplicate subsets.

 Example:

 Input: nums = [1,2,3]
 Output:
 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]
 此题求一个数组中的二项组合系数的全集，即Cn-1, Cn-2, ... Cn~n
 */
public class Subsets {
    /**
     * 循环解法
     * 相比全排列，组合数要保留每个临时res中的值
     * 同时，比排列少了一层插入所有可能位置的循环
     * 因为组合不考虑元素间的顺序
     * @param nums
     * @return
     */
    public List<List<Integer>> solve_1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> l0 = new ArrayList<>();
        res.add(new ArrayList<>()); // 注意加入空集合，符合输出中包含[]的条件，同时用于插入单个元素，形成Cn-1
        l0.add(nums[0]);
        res.add(l0);
        for(int i = 1; i < nums.length; i++) {
            // 为了防止出现ConcurrentModificationException(在遍历list的同时修改)，建立临时结果
            List<List<Integer>> tmpRes = new ArrayList<>();
            for(List<Integer> component : res) {
                List<Integer> tmpComp = new ArrayList<>(component);
                tmpComp.add(nums[i]);
                tmpRes.add(tmpComp);
            }
            res.addAll(tmpRes);
        }
        return res;
    }


    /**
     * 递归解法，运用Backtracking的思想
     * @param nums
     * @return
     */
    public List<List<Integer>> solve_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> component = new ArrayList<>();
        subset(nums, res, component, 0);
        return res;
    }
    private void subset(int[] nums, List<List<Integer>> res, List<Integer> component, int start) {
        // 组合数全集不需要component中的元素个数达到nums.length就要添加
        // 同时要深度复制component，因为该对象是重复利用的
        res.add(new ArrayList<>(component));
        for(int i = start; i < nums.length; i++) {
            if(!component.contains(nums[i])) {
                component.add(nums[i]);
                // 组合数不包含重复元素，下次递归调用从当前元素的下一个元素开始，所以为i+1
                // (如第一个数为3，则 i + 1 = 3, 后续无元素可用，从而避免出现[3，2]）
                // 这个递归调用相当于按下层可用nums中可用元素的个数，以树的形式层层展开，
                // 直到到达底部子节点层，当遍历完最后子节点层的最后
                // 一个子节点后，开始回溯
                subset(nums, res, component, i + 1);
                // backTracking, 通过删除最后添加的元素进行回溯
                component.remove(component.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int[] nums = new int[] {1, 2, 3};
        System.out.println(subsets.solve_1(nums));
        System.out.println(subsets.solve_2(nums));
    }
}
