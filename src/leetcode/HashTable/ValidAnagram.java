package leetcode.HashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
 * @author chenkedi
 * 简而言之题目的意思是判断两个单词是否是错位构成的单词，映射为算法即判断两个单词所含的字母的种类和个数是否都相同
 * 下面所有的算法均可以使用charAt()和toCharArray两种方法，事实证明后者要快很多。
 */
public class ValidAnagram{
	
	/**
	 * 第一次我没有弄懂题目意思，使用了判断一个字符串是否包含另外一个字符串所有的字母的最优位图算法
	 * 即A为长度为m的字符串，B为长度n的字符串，m>n,两个字符串均只包含小写字母，问A中是否包含B中所有的字母
	 * 这个方法能通过一部分用例，但是它不能判断两个字符串中字母种类相同，但数量不同的情况。所以本算法无法解此题。
	 * 如“aacc”与aaac本方法会报告true，而实际上是false
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram_a(String s, String t) {
		 if(s==null || t==null || s.length()!=t.length())
	         return false;
	     int bitmap = 0;
	     for(int i=0;i<s.length();i++){
	         bitmap|=(1<<(s.charAt(i)-'a'));
	     }
	     for(int i=0;i<t.length();i++){
	         if((bitmap&(1<<t.charAt(i)-'a'))==0)
	             return false;
	     }
	     return true;
	}
	
	/**
	 * 此方法没有对空间那么苛求，所以使用int数组来表示每个字母的个数。s串中出现一次，就在对应的字母处加一，t出现一次就减一
	 * 最终的数组要全是0才能表明两个字符串字母类型和数目完全相同
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram_b(String s, String t) {
		 if(s.length()!=t.length())
	         return false;
	     char[] cs = s.toCharArray();
	     char[] ct = t.toCharArray();
	     int[] map = new int[26];
	     for(int i=0;i<s.length();i++){
	       map[cs[i]-'a']++;
	       map[ct[i]-'a']--;
	     }
	     for(int i=0;i<26;i++){
	         if(map[i]!=0)
	             return false;
	     }
	     return true;
	}
	
	/**
	 * 次方法使用hash表代替数组存储字母与其对应出现的次数，算法效率是46ms，时间大约是其他算法的10倍左右
	 * 原因很简单，每次对哈希表中的字母数目进行加减的时候都多了一次containsKey操作，而且本身集合类的开销就要高于数组
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram_c(String s, String t) {
		if(s.length()!=t.length())
	         return false;
		//此处注意，为了避免rehash过程，对于已经确定26个字母的使用场景来说，可以直接定最接近26并大于26的二进制数32,
	    Map<Character,Integer> map = new HashMap<>(32);
	    char[] cs = s.toCharArray();
	    char[] ct = t.toCharArray();
	    for(int i=0;i<cs.length;i++){
	        if(!map.containsKey(cs[i]))
	            map.put(cs[i],1);
	        else
	            map.put(cs[i],map.get(cs[i])+1);
	    }
	    
	    for(int i=0;i<t.length();i++){
	        if(map.containsKey(ct[i])){
	            int count = map.get(ct[i]);
	            if(count==1)
	                map.remove(ct[i]);
	            else
	                map.put(ct[i],count-1);
	        }else
	            return false;
	    }
	    if(!map.isEmpty())
	        return false;
	    return true;
	}
	
	/**
	 * 此方法诠释了何为高效的算法。用一个count，一次for循环即可标示最终的结果。
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram_d(String s, String t) {
		  if (s.length() != t.length()) return false;
	     char[] cs = s.toCharArray();
	     char[] ct = t.toCharArray();
	     int[] map = new int[26];
	     int count = 0;
	     for (int i = 0; i < cs.length; i++) {
	         if(++map[cs[i]-'a'] == 1) count ++;
	         if(--map[ct[i]-'a'] == 0) count --;
	     }
	     return count == 0;
	}    
	    
}
