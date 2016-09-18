package leetcode;

import java.util.Scanner;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".
 * @author chenkedi
 *编写函数输入一个字符串，将其中的元音字母逆置
 */
public class ReverseVowels{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()){
			String str = sc.nextLine();
			System.out.println(reverseVowels(str));
		}
		sc.close();
	}

	/**
	 * 481用例 10ms
	 * @param s
	 * @return
	 */
	private static String reverseVowels(String s) {
		char[] str = s.toCharArray();
        int begin=0;
        int end=str.length-1;
        while(begin<end){
            if(!isVowels(str[begin])){
                begin++;
                continue;
            }
            if(!isVowels(str[end])){
                end--;
                continue;
            }
                
            swap(str,begin,end);
            begin++;
            end--;
        }
        
        return new String(str);
        
    }
	
	
	public static boolean isVowels(char a){
        if("aeiou".indexOf(Character.toLowerCase(a))!=-1)
            return true;
        else
            return false;
    }
	
	
    public static void swap(char[] str,int a,int b){
        char tmp = str[a];
        str[a]=str[b];
        str[b]=tmp;
    }

}
