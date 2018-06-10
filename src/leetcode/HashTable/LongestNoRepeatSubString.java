package leetcode.HashTable;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，求其中最长的无重复子串,子串就表示连续
 *
 * Given a string, find the length of the longest substring without repeating characters.

 * Examples:
 *
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 *
 * Given "bbbbb", the answer is "b", with the length of 1.
 *
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 * 如果当前遍历到的这个字符在front后面没有出现过，那么front不需要移动，接着遍历后面的字符
 * 如果当前遍历到的这个字符在front后面出现过，那么从front到当前位置这个子串肯定就有重复的字符了，此时就需要改变front的位置到出现的那个字符后面的位置。也就是和当前遍历到的这个字符上一次出现的位置的下一个位置。
 * 在这个过程中，时刻更新最大的长度，因为front到back这段区域永远不可能有重复的字符，如果有，已经在第二步解决了
 */
public class LongestNoRepeatSubString {

    public static void main(String[] args) {
        System.out.println(solve("abba"));

    }

    /**
     * @param str
     * @return
     */
    public static int solve(String str) {
        char[] strArray = str.toCharArray();
        // 使用int数组存储每个字符对应的最后在字符串中出现的位置, 先使用-1进行初始化
        int[] set = new int[256];
        for (int i = 0; i < set.length; i++)
            set[i] = -1;
        // Arrays.fill(set, -1)

        // front指针到当前的指针之间的字符串一定没有重复
        int front = 0, max = 0;
        for(int i = 0; i < strArray.length; i++) {
            // 当前被遍历的字符若在front指针之后（包括front自己所指向的字符）和i指针(不包括i当前指向的指针)之间，则将front移动到该重现字符的后一位
            // 若不加第二个条件，当输入为"abba"时，则i = 3碰到a时, front = 2(之前碰到第二个b，将front移动到1+1=2的位置）, front指针会回到第一个a的后面的位置
            // 导致输出错误。直观上理解就是，现在front和之间（包括front但不包含i)没有当前所指向的字符a（即前面注释中所说的front后面没有当前字符）
            if (set[strArray[i]] != -1 && set[strArray[i]] >= front) {
                front = set[strArray[i]] + 1;
            }
            // 时刻更新每个字符最后出现的位置到set中（无论set中该字符的位置值是否为-1,都需要更新）
            set[strArray[i]] = i;
            max = Math.max(i - front + 1, max);
        }
        return max;
    }
}
