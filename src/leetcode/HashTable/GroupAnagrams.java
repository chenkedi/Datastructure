package leetcode.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 此题关键在于HashMap中分组的Key的设计，下面的解决方案为将字符数组排序后作为key
 * Discussion区有使用自定义的使用素数的哈希函数设计，虽然避免了排序，将复杂度降到了O(n * m), 但是这些解决方案不能保证完全解决哈希的碰撞问题
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solve(s));
    }

    public static List<List<String>> solve(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for(String str : strs) {
            char[] strArray = str.toCharArray();
            Arrays.sort(strArray);
            String key = new String(strArray);
            if(!res.containsKey(key)) {
                res.put(key, new ArrayList<>());
            }
            res.get(key).add(str);
        }
        return new ArrayList<>(res.values());
    }
}
