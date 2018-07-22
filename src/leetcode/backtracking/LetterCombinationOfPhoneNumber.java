package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

 A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

 2: abc
 3: def
 4: ghi
 5: jkl
 6: mno
 7: pqrs
 8: tuv
 9: wxyz

 Example:

 Input: "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

 */
public class LetterCombinationOfPhoneNumber {

    /**
     * 回溯解法
     * 注意这里为了优化运行时间，使用了额外的字符数组空间
     * 也可以直接使用string的charAt方法进行相同的操作
     * @param digist
     * @return
     */
    public List<String> solve_1(String digist) {
        List<String> res = new ArrayList<>();
        if(digist.length() == 0)
            return res;
        char[] nums = digist.toCharArray();
        // component使用数组的形式，可以避免使用list进行回溯时的remove(component.size() - 1)开销
        char[] component = new char[nums.length];
        String[] map = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        combine(res, map, nums, component, 0);
        return res;
    }

    private void combine(List<String> res, String[] map, char[] nums, char[] component, int index) {
        // index代表了当前栈是第几个数字表示的字母
        if(index == nums.length) {
            res.add(new String(component));
            return;
        }
        // 这里的for循环无需对nums也进行遍历，因为每一层堆栈仅对应一个数字对应的所有字母的遍历
        // 堆栈最大深度为一
        // 回溯通过index的值，直接对component数组中的值进行覆写实现
        char[] words = map[nums[index] - '0'].toCharArray();
        for(int i = 0; i < words.length; i++) {
           component[index] = words[i];
           combine(res, map, nums, component, index + 1);
        }
    }


    public List<String> solve_2(String digits) {
        String[] map = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        char[] digitsList = digits.toCharArray();
        List<String> res = new ArrayList<>();
        String first = map[digitsList[0] - '0'];
        for(int i = 0; i < first.length(); i++) {
            res.add(String.valueOf(first.charAt(i)));
        }
        for(int i = 1; i < digitsList.length; i++) {
            List<String> tmpRes = new ArrayList<>();
            String words = map[digitsList[i] - '0'];
            for(int j = 0; j < words.length(); j++) {
                for(String str : res) {
                    tmpRes.add(str + words.charAt(j));
                }
            }
            res = tmpRes;
        }
        return res;

    }

    public static void main(String[] args) {
        String digits = "23";
        LetterCombinationOfPhoneNumber combine = new LetterCombinationOfPhoneNumber();
        System.out.println(combine.solve_1(digits));
        System.out.println(combine.solve_2(digits));

    }
}
