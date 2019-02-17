package leetcode.HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * <p>
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * <p>
 * The order of output does not matter.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * Output:
 * [0, 6]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * <p>
 * Input:
 * s: "abab" p: "ab"
 * <p>
 * Output:
 * [0, 1, 2]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        String s1 = "cbaebabacd";
        String p1 = "abc";
        System.out.println(solve_3(s1, p1));

        String s2 = "abab";
        String p2 = "ab";
        System.out.println(solve_2(s2, p2));
    }

    /**
     * 与MinimumWindowSubString的解法结构类似
     * 维护一个Hash窗口，记录每个字符出现的次数，与目标字符对比
     * 与MWS不同给的是，在收缩左指针start后，只有start到之间不含有其他的字符的窗口（即i-start+1 == p.length())才能记为一个Anagram
     * 而后我们想MWS中一样，将pHash维护好后，使start向前移动一位，并将found减一即可
     *
     * @return
     */
    public static List<Integer> solve_1_copy(String s, String p) {
        // 注意处理边界情况
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0 || p.length() > s.length())
            return res;
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        int[] sHash = new int[26];
        int[] pHash = new int[26];

        for (int i = 0; i < pArr.length; i++)
            pHash[pArr[i] - 'a']++;
        int start = 0, found = 0;
        for (int i = 0; i < sArr.length; i++) {
            sHash[sArr[i] - 'a']++;
            if (sHash[sArr[i] - 'a'] <= pHash[sArr[i] - 'a'])
                found++;

            if (found == pArr.length) {
                while (start < i && sHash[sArr[start] - 'a'] > pHash[sArr[start] - 'a']) {
                    sHash[sArr[start] - 'a']--;
                    start++;
                }
                if (i - start + 1 == pArr.length)
                    res.add(start);

                sHash[sArr[start] - 'a']--;
                found--;
                start++;
            }

        }
        return res;

    }


    /**
     * 解法一代表滑动窗口的一种特殊模式：当要求解的字符空间较小时
     * （如字母为26个， ASCII为128个，Extended ASCII为256个char），可采用此模式
     * <p>
     * 其核心思想是，维护两个所有字符的"哈希"数组（注意这里是存储了所有字符空间中的字符，
     * 严格来讲不是哈希），其中一个对应于目标字符串p，另一个对应于被查找的字符串s;使用
     * 一个found变量代表当前滑动窗口内已经命中的字符的个数;使用start遍历作为滑动窗口的
     * 起点，循环自带遍的变量i作为滑动窗口终点
     * <p>
     * 当found等于目标字符串p的
     * 长度时，则存在找到目标串的可能性。此时需要向右移动start（当然start要小于i），直到
     * 碰到p中包含的字符为止。当此时的窗口长度恰好等于p的长度，即中间没有其他p中不存在的
     * 字符时，才标志着找到了Anagram
     * <p>
     * 完成上述操作后，将start对应的字符在s的哈希表中出现次数减一，并将start向前移动一位，
     * found也减一，表示滑动窗口向前移动一位时，找到的p中字符的数量也变少了一个
     * <p>
     * 另外两个个关键点为：
     * 一、若s中的字符命在p中存在，则有s中对应的字符次数<=p中对应的字符次数
     * 二、若s中字符在p中不存在，则有s对应的字符次数 > p中对应的字符次数
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> solve_1(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0)
            return res;
        // 维护两个字符的哈希表，索引是字符的char值,记录p中各字符出现的次数
        int[] sMap = new int[26];
        int[] pMap = new int[26];

        char[] sArray = s.toCharArray();
        for (char c : p.toCharArray()) {
            pMap[c - 'a']++;
        }
        int found = 0;
        int start = 0;
        // 外层遍历s字符串，当内层出现found等于p的长度时，
        // 窗口起始点往前移动一位，直到碰到p中的字母时停止。这个操作可以降低评估滑动窗的数量
        for (int i = 0; i < sArray.length; i++) {
            int index = sArray[i] - 'a';
            sMap[index]++;

            if (sMap[index] <= pMap[index]) {
                found++;
            }

            if (found == p.length()) {
                // 只有当start指向的字母不在p字符串中时，才移动窗口的start
                while (start < i && sMap[sArray[start] - 'a'] > pMap[sArray[start] - 'a']) {
                    sMap[sArray[start] - 'a']--;
                    start++;
                }
                // 只有当滑动窗口中没有p中字符以外的字符时，才能加入res
                // 上面的while循环仅保证了两端没有p以外的字符
                if (i - start + 1 == p.length())
                    res.add(start);
                sMap[sArray[start++] - 'a']--;
                found--;
            }
        }
        return res;
    }

    /**
     * 解法二代表滑动窗口方法的另一种更为通用的解法，适用于字符空间为任意字符的情况
     * <p>
     * 其核心思想是仅使用HashMap存储目标串p中字符出现的次数；使用start表示滑动窗的起点，
     * 用循环自带遍历i作为滑动窗终点；用count来表示p中的无重复
     * 字符的个数; 遍历被查找串s时，若哈希表中存在该字符，则对应字符次数减一，并立即判断
     * 该字符是否次数为0，当为0时表示当前滑动窗命中一个p中的字符；所以当count为0时，表示
     * 滑动窗命中所有字符。
     * <p>
     * 在count == 0中，也如方式一一样，将start不断右移，去除p中不存在的字符，当碰到p中
     * 存在的字符时，将哈希表对应的字符次数加一。当对应字符数大于0时，表示当前窗口命中的p中
     * 的字符数减少了一个，此时同时count也加一，则跳出count == 0的循环
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> solve_2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0)
            return res;

        Map<Character, Integer> pMap = new HashMap<>();
        char[] sArray = s.toCharArray();
        for (char c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }

        int count = pMap.size();
        int start = 0;

        for (int i = 0; i < sArray.length; i++) {
            char endChar = sArray[i];

            if (pMap.containsKey(endChar)) {
                pMap.put(endChar, pMap.get(endChar) - 1);
                // 只有当字符数量减为0时，才表示命中一个字符
                if (pMap.get(endChar) == 0)
                    count--;
            }
            // 命中所有字符
            while (count == 0) {
                char startChar = sArray[start];
                // 此处一个示例是，虽然a是p中的字符，但如果a出现了两次的情况下（aabc，p为abc），此时hash表中对应次数为-1，
                // 当start指向第一个a时，hash为0，此时窗口长度明显不等于p的长度3（为4），所以index=0这个错误的结果不会加入res中
                // 同时count也不会加一，这样下一次while循环使得index=1被加入结果，同时count+1，跳出循环。完美
                if (pMap.containsKey(startChar)) {
                    pMap.put(startChar, pMap.get(startChar) + 1);
                    if (pMap.get(startChar) > 0)
                        count++;
                }

                if (i - start + 1 == p.length()) {
                    res.add(start);
                }
                start++;
            }
        }
        return res;
    }

    /**
     * 最终的窗口方法。其既能使用整型数组做哈希表，也能适用字符空间较大情况下，用HashMap做hash表
     * 其特点是：只需要缓存目标字符串p中各字符出现的次数
     * 它使用 start表示滑动窗口的起点，循环变量i表示滑动窗口的终点；count记录字符串p的长度（或者
     * 当前尚未命中的目标字符串字符个数）。当i处遇到的字符在hash表中的计数仍大于0时（此时表示为有效命中）
     * ，则表示命中一个字符, count - 1；同时，无论i处的字符是否属于p，出现次数均要减1，这样p中未出现的
     * 字符次数一定为负数。当count为0时，表示当前窗口含有p中所有的字符，一直进行循环判断count是否仍为0,
     * 此时先判断i - start + 1是否等于p的长度，等于则找到一个答案。然后再判断start位置的字符的次数是否
     * 恰好等于0（因为只有p中出现的字符，且窗口中出现的次数恰好等于p中出现的次数，该字符计数才为0），为0
     * 则表示窗口左端向右缩短时，会让当前窗口少命中一个字符，count++。同时start处的字符在hash表中的次数加一，
     * 然后将窗口左端点start右移一位
     *
     * 总结来讲，就是窗口右端点的移动会让map中字符的次数减少一次（map中尚未加入的则减为-1），而窗口左端点
     * 的移动会让map中字符的次数增加一次，使得窗口滑过后的区域，不影响hash表中初始的字符出现次数（初始次数是
     * p中存在的字符标记为其次数，不存在的都为0）。这样，右端点移动时，其对应字符在hash表中大于0的次数表示窗口命中
     * 一个p中的字符，而左端点移动时，其对应字符在hash表中等于0的次数才表示窗口miss一个p中的字符。这样我们
     * 只要在count为0时，进行对应的结果收集即可
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> solve_3(String s, String p) {
        List<Integer> res = new ArrayList<>();
        // 这里的字符仅有小写英文，若字符空间很大，改成Map没有问题
        Map<Character, Integer> map = new HashMap<>();
        char[] sArr = s.toCharArray();
        int start = 0;
        int count = p.length();

        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < sArr.length; i++) {
            if (map.getOrDefault(sArr[i], 0) > 0) {
                count--;
            }
            map.put(sArr[i], map.getOrDefault(sArr[i], 0) - 1);

            while (count == 0) {
                if (i - start + 1 == p.length()) {
                    res.add(start);
                }
                if (map.get(sArr[start]) == 0) {
                   count++;
                }
                map.put(sArr[start], map.get(sArr[start]) + 1);
                start++;
            }
        }
        return res;
    }
}
