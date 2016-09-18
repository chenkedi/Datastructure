package leetcode;

import java.util.Scanner;

/**
 * Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".
 * @author chenkedi
 *
 */
public class ReverseString{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			String str = sc.next();
			System.out.println(reverse_d(str));
		}
		sc.close();
	}
	/**
	 * jdk自带库反转法，476用例，4ms	
	 * @return
	 */
	public static String reverse_a(String s){
		return new StringBuilder(s).reverse().toString();
	}
	
	/**
	 * 转char数组逆向循环拼接法，476用例，5ms	
	 * @return
	 */
	public static String reverse_b(String s){
		 StringBuilder buffer = new StringBuilder();
         char[] str= s.toCharArray();
         for(int i = str.length-1;i>=0;i--){
             buffer.append(str[i]);
         }
         return buffer.toString();
	}
	
	/**
	 * 转char数组后，数组两半内部交换法，476用例，2ms
	 * 使用此法注意输入空字符串时会导致for循环数组越界，需要单独处理	
	 * @return
	 */
	public static String reverse_c(String s){
		if(s.equals(""))
            return s;
        char[] str = s.toCharArray();
        int n = str.length-1;
        for(int i=0;i<=n/2;i++){
            char temp = str[i];
            str[i]=str[n-i];
            str[n-i]=temp;
        }
        return new String(str);
	}
	
	/**
	 * 递归法，476用例，26ms
	 * 
	 * @return
	 */
	public static String reverse_d(String s){
		int length = s.length();
        if (length==1 || length==0)
			return s;
//		String left = s.substring(0,length/2);
//		String right = s.substring(length/2);
//		return reverse_d(right)+reverse_d(left);
        return reverse_d(s.substring(1)+s.charAt(0));
        
	}
	
	/**
	 * 两个指针,从头尾分别向中心收缩，并用异或交换的方法，476用例，3ms
	 * 
	 * @return
	 */
	public static String reverse_f(String s){
		char[] str = s.toCharArray();
        int begin = 0;
        int end = str.length-1;
        while(begin<end){
            str[begin]=(char)(str[begin]^str[end]);
            str[end]=(char)(str[begin]^str[end]);
            str[begin]=(char)(str[begin]^str[end]);
            begin++;
            end--;
        }
        return new String(str);
	}
	
}
