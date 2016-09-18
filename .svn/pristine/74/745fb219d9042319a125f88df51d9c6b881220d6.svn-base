package leetcode;

import java.util.Scanner;

/**
 * everse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 * @author chenkedi
 *
 */
public class ReverseInteger {
	
	public static void main(String args[]){
		System.out.println(reverse(1000000003));
		System.out.println(reverse(-123));
		Scanner sc = new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		System.out.println(reversePlus(a,b));
		
		sc.close();
	
	}
	
	//求两个整数反转之后相加的值，反转之后首位的0要除去。
	public static int reversePlus(int a, int b) {
		int reverseA=reverse_a(a);
		int reverseB=reverse_a(b);
		return reverseA+reverseB;
	}

	/**
	 * 字符串反转法，使用try catch块来解决溢出问题
	 * @param x
	 * @return
	 */
    public static int reverse(int x) {
      String reverseStr="";
      int tmp=Math.abs(x);
      for(;tmp>0;tmp/=10){
          reverseStr+=tmp%10;
      }
      int reverse=0;
	    try{
	        reverse=Integer.valueOf(reverseStr);
	    }catch(NumberFormatException e){
	        return 0;
	    }
        return x>=0?reverse:-reverse;
    }
    
    /**
     * 整数直接反转法，使用long来存储中间过程进行溢出判断
     * 效率更高
     * @param x
     * @return
     */
    public static int reverse_a(int x) {
    	long reverse=0;
    	int tmp=Math.abs(x);
	   for(;tmp>0;tmp/=10){
		   reverse=reverse*10+tmp%10;
		   if(reverse>Integer.MAX_VALUE)
			   return 0;	   
	   }
      return (int) (x>=0?reverse:-reverse);
    }
}
