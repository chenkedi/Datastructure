package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

 The same repeated number may be chosen from candidates unlimited number of times.

 Note:

 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 Example 1:

 Input: candidates = [2,3,6,7], target = 7,
 A solution set is:
 [
 [7],
 [2,2,3]
 ]
 Example 2:

 Input: candidates = [2,3,5], target = 8,
 A solution set is:
 [
 [2,2,2,2],
 [2,3,3],
 [3,5]
 ]
 */
public class CombinationSum {

    /**
     * 使用回溯法
     * 注意递归调用中，每次循环开始的位置
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> solve_1(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> component = new ArrayList<>();

        combinationSum(candidates, target, component, res, 0, 0);

        return res;
    }

    private void combinationSum(int[] candidates, int target, List<Integer> component, List<List<Integer>> res, int start, int sum) {
        // 注意，sum如果比target大要直接返回，否则会造成stackoverflow
        if(sum > target)
            return;
        if(sum == target) {
            res.add(new ArrayList<>(component));
            return;
        }
        for(int i = start; i < candidates.length; i++) {
           component.add(candidates[i]);
            // 此处不对本层栈中sum的值进行改变，从而也就不需要在后续的remove过程中将sum减去remove的元素
            // 注意，由于某一个数字可以重复多次，且不允许有重复组合，所以下一次start必须从i（即元素本身开始）
            // 从i开始能避免下一层堆栈使用i之前的元素从而产生重复（如[2,2,3]和[2,3,2]就能避免
           combinationSum(candidates, target, component, res, i, sum + candidates[i]);
           component.remove(component.size() - 1);
        }
    }


    /**
     * 循环解法暂时空缺
     * @param canditates
     * @param target
     * @return
     */
    public List<List<Integer>> solve_2(int[] canditates, int target) {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2, 3, 5};
        CombinationSum combinationSum = new CombinationSum();
        System.out.println(combinationSum.solve_1(nums, 8));
    }
}
