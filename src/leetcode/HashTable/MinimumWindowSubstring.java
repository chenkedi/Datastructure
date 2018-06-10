package leetcode.HashTable;


/**
 * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 *
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 需要注意的是，Target字符串中的若有字符出现多次，则输出的结果中S的字符也要出现相同的次数
 *
 * 总结的思路就是，使用两个以字符ASCII码为索引的类哈希表来存储目标字符串和源字符串中所有字符出现的次数
 *
 * 然后通过双指针(start和)维持一个Window，窗口右指针向右扩张，以找到包含Target字符串的子串为目的
 * 窗口左指针向右收缩跳过多余字符，以使子串最小。
 *
 * 源字符串中的字符出现次数不超过目标字符串中对应字符的出现次数，则表示当前窗口发现了一个符合条件的目标字符, 此为右指针的扩张规则
 * 源字符串中的字符出现次数超过对应目标字符串中对应字符的出现次数，则表示该字符多余，此为左指针收缩窗口的准则
 *
 * 注意在缩小或者扩张窗口前，先维护窗口的哈希表中对应字符的次数，和已经找到的字符的个数，即可得到正确的程序
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
       String s = "ADOBECODEBANC";
       String t = "ABC";
        System.out.println(solve(s, t));
    }

    private static String solve(String s, String t) {
        if (s.length() == 0 || t.length() == 0)
            return "";
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        int[] tHash = new int[256];
        int[] sHash = new int[256];
        int begin = -1, end = 0, found = 0;
        int minLen = source.length;
        // 先统计目标字符串中各个字符出现的次数
        for (char ch : target)
            tHash[ch]++;
        for (int start = 0, i = 0; i < source.length; i++) {
            // 给记录当前窗口内所有字符出现次数的哈希表对应的字母位置出现次数加一
            sHash[source[i]]++;
            // 如果当前窗口字符串对应字母位置的出现次数小于等于目标字符串中对应字母的出现次数，记为找到一个有效的目标字符串
            // 中的字符。因为当源串中字母出现次数比目标串中要大时，要么是目标字符串中不包含的字符（1 > 0）,要么是源串中的
            // 的字符出现的次数比目标串中的多，而该字符已经被计入到found中了，最多被计入该字符在目标串中出现的次数这么多次
            if (sHash[source[i]] <= tHash[source[i]])
                found++;
            // 当found等于目标串的长度时，说明当前窗口所截取（由start到i）的字符串已经包含了目标串中所有的字符，但有可能
            // 在start那一边包含多余的字符
            if (found == target.length) {
                // 跳过多余的字符.当start指向的字符在源串中出现的次数比目标串中大时，start前移一位
                // 这里有两种情况，第一种比较简单，即目标串中没有start指向的字符（1 > 0）
                // 第二种是目标串中有start指向的字符，但是源串中的次数超过了目标串的次数，此时start仍然可以前移一位以缩小                 // 窗口,同时保证窗口内的字符串符合题意
                // 在移动start前，要记得维护sHash，将start指向的字符出现次数减1
                while (start < i && sHash[source[start]] > tHash[source[start]]) {
                    sHash[source[start]]--;
                    start++;
                }

                // 当跳过所有多余字符后，就已经将当前窗口缩小到符合条件的最小值了，此时与minLen比较以确定是否更新最小串
                // 真实的长度应该是 i - start + 1,但是我们的minLen初始值为source字符串的长度，为了保证当source串本身
                // 就是minimum window时程序的正确，所以这里没有 + 1
                if (i - start < minLen) {
                    minLen = i - start;
                    begin = start;
                    end = i;
                }

                // 处理更新后，维护当前窗口sHash中对应的字符次数减1，相应found也减一，最后将start后移一位，开始看新的子串
                sHash[source[start]]--;
                found--;
                start++;

            }
        }
        return begin == -1 ? "" : s.substring(begin, end + 1);
    }
}
