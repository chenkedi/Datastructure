package leetcode.HashTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

 * The order of output does not matter.

 * Example 1:

 * Input:
 * s: "cbaebabacd" p: "abc"

 * Output:
 * [0, 6]

 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:

 * Input:
 * s: "abab" p: "ab"

 * Output:
 * [0, 1, 2]

 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        String s1 = "cbaebabacd";
        String p1 = "abc";
        System.out.println(solve(s1, p1));

        String s2 = "abab";
        String p2 = "ab";
        System.out.println(solve(s2, p2));
    }

    /**
     * 与MinimumWindowSubString的解法结构类似
     * 维护一个Hash窗口，记录每个字符出现的次数，与目标字符对比
     * 与MWS不同给的是，在收缩左指针start后，只有start到之间不含有其他的字符的窗口（即i-start+1 == p.length())才能记为一个Anagram
     * 而后我们想MWS中一样，将pHash维护好后，使start向前移动一位，并将found减一即可
     * @return
     */
    public static List<Integer> solve(String s, String p) {
        // 注意处理边界情况
        List<Integer> res = new ArrayList<>();
        if(s.length() == 0 || p.length() > s.length())
            return res;
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        int[] sHash = new int[26];
        int[] pHash = new int[26];

        for(int i = 0; i < pArr.length; i++)
            pHash[pArr[i] - 'a']++;
        int start = 0, found = 0;
        for(int i = 0; i < sArr.length; i++){
            sHash[sArr[i] - 'a']++;
            if(sHash[sArr[i] - 'a'] <= pHash[sArr[i] - 'a'])
                found++;

            if(found == pArr.length){
                while(start < i && sHash[sArr[start] - 'a'] > pHash[sArr[start] - 'a']) {
                    sHash[sArr[start] - 'a']--;
                    start++;
                }
                if(i - start + 1 == pArr.length)
                    res.add(start);

                sHash[sArr[start] - 'a']--;
                found--;
                start++;
            }

        }
        return res;

    }
}
