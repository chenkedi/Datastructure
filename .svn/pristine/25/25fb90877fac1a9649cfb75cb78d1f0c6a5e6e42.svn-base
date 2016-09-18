package leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 将罗马数字转换为阿拉伯整数
 * 罗马数字有如下符号：
Ⅰ（1）Ⅴ（5）Ⅹ（10）L（50）C（100）D（500）M（1000）
计数规则：
1.若干相同数字连写表示的数是这些罗马数字的和，如III=3；

2.小数字在大数字前面表示的数是用大数字减去小数字，如IV＝4；
3.小数字在大数字后面表示的数是用大数字加上小数字，如VI=6;

罗马数字转阿拉伯数字：
从前往后遍历罗马数字，如果某个数比前一个数小，则把该数加入到结果中；反之，则在结果中两次减去前一个数并加上当前这个数；
 * @author chenkedi
 *
 */
public class RomanToInt{
	
	public static void main(String[] args){
		System.out.println(romanToInt("MCMXCVI"));
	}
	
	 public static int romanToInt(String s) {
//	        Map<Character,Integer> map = new HashMap<>();
//	        map.put('I',1);
//	        map.put('V',5);
//	        map.put('X',10);
//	        map.put('L',50);
//	        map.put('C',100);
//	        map.put('D',500);
//	        map.put('M',1000);
//	        
//	        char[] str = s.toCharArray();
//	        int pre=map.get(str[0]);
//	        int result = pre;
//	        for(int i = 1; i<str.length; i++){
//	            if(map.get(str[i])<=pre)
//	                result+=map.get(str[i]);
//	            else
//	                result=result-2*pre+map.get(str[i]);
//	            pre = map.get(str[i]);
//	        }
//	        return result;
		 //使用整数数组代替hashmap可以获得3倍的性能提升，从22ms到7ms
		 int[] map = new int[26];
	       map['I'-'A'] = 1;
	       map['V'-'A'] = 5;
	       map['X'-'A'] = 10;
	       map['L'-'A'] = 50;
	       map['C'-'A'] = 100;
	       map['D'-'A'] = 500;
	       map['M'-'A'] = 1000;
	        char[] str = s.toCharArray();
	        int pre=map[str[0]-'A'];
	        int result = pre;
	        for(int i = 1; i<str.length; i++){
	            if(map[str[i]-'A']<=pre)
	                result+=map[str[i]-'A'];
	            else
	                result=result-2*pre+map[str[i]-'A'];
	            pre = map[str[i]-'A'];
	        }
	        return result;

	 }
}
