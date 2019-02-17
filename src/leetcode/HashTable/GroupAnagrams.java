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

    /**
     * 解法二重点在于通过重新设计各个string key的定义，将求key的复杂度由O(nlgn)下降至O（n）
     * 一个anagram字符串的key可由不重复的字符和每个字符出现的次数来唯一确定
     * @param strs
     * @return
     */
    public static List<List<String>> solve_2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        int[] words = new int[26];

        for (String s : strs) {
            Arrays.fill(words, 0);
            char[] sArr = s.toCharArray();

            for (int i = 0; i < sArr.length; i++) {
                words[sArr[i] - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (words[i] != 0) {
                    // 核心的key设计，每个字符串可以用字符+每个字符的次数唯一定位
                    sb.append(i + 'a').append(words[i]);
                }
            }


            String key = sb.toString();
            if (map.get(key) == null)
                map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
