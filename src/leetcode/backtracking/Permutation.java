package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.

 Example:

 Input: [1,2,3]
 Output:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]
 */
public class Permutation {
    List<List<Integer>> res = new ArrayList<>();
    public static void main(String[] args) {
       Permutation permutation = new Permutation();
        int[] nums = new int[]{1, 2, 3};
        System.out.println(permutation.solve_1(nums));
        System.out.println(permutation.solve_2(nums));

    }

    /**
     * 使用回溯法解
     */
    public List<List<Integer>> solve_1(int[] nums) {
        List<Integer> component = new ArrayList<>();
        permutate(component, nums);
        return res;
    }

    private void permutate(List<Integer> component, int[] nums) {
       if(component.size() == nums.length) {
           // 注意此处添加component中的元素到最终结果需要深度拷贝，因为component中的元素一直在动态改变
           res.add(new ArrayList<>(component));
           return;
       }

       for(int i = 0; i < nums.length; i++) {
           // 当目前的排列中不含有nums[i]时，才进行递归调用，避免元素重复
           if(!component.contains(nums[i])) {
               component.add(nums[i]);
               // 递归调用，开始安排下一个元素
               permutate(component, nums);
               // 此时permutate已经完成一种排列，通过删除最后添加的元素来进行一次回溯，从而返回for循环探索下一种排列
               component.remove(component.size() - 1);
           }
       }
    }


    /**
     * 通过正向的循环解题
     * 基本思路为：先取第一个数生成集合{{1}},再取第二个数，对当前结果中的每个组合的每个位置进行插空，得到{{1，2}, {2, 1}},依次类推
     * 注意要对每次插空后的结果要进行复制，否则会造成结果错误
     * 同时，由于最内层循环需要对结果中的元素进行遍历的同时向元素中插空(修改），所以必须在每次外循环时新建一个tmp res
     * 最后，会有三层循环，第一次遍历nums中所有的元素，第二层遍历结果集合中每个组合的所有空位，第三层遍历当前结果集中的所有元素
     * @param nums
     * @return
     */
    public List<List<Integer>> solve_2(int[] nums) {
       List<List<Integer>> res = new ArrayList<>();
       List<Integer> ini = new LinkedList<>();
       // 先添加nums中的第一个数作为种子
       ini.add(nums[0]);
       res.add(ini);

       // 第一个元素已经加入结果集，从第二个元素开始
       for(int i = 1; i < nums.length; i++) {
           // 新建tmp res，防止对res遍历的同时进行修改造成的ConcurrentModificationException
           List<List<Integer>> tmpRes = new ArrayList<>();
           // res中任一元素的空位均从0开始，到i结束（即最后一个元素的后面）
           for (int j = 0; j <= i; j++) {
               // 对res中已有的每个组合进行复杂，然后在每个可能位置插空
              for(List<Integer> permu : res) {
                  List<Integer> tmp = new ArrayList<>(permu);
                  tmp.add(j, nums[i]);
                  tmpRes.add(tmp);
              }
           }
           res = tmpRes;
       }
       return res;
    }


}
