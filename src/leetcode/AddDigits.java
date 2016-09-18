package leetcode;

import java.util.Scanner;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?
 * @author chenkedi
 *简而言之，就是将一个整数的各个位相加，得到的结果的各个位再相加，如此往复，直至最后的结果只有一个数字
 *从结果而言，只能是0~9直接的数字
 *我们可以发现，这个数字的出现是有规律的，即以9为周期循环（不包括0，因为任意一个大于等于两位数的正整数结果不可能等于0）。当一个数可以被9整除时，即余数为0时，
 *返回结果9，如9、18、27...等，其他的情况只需要对9求余，即的返回的结果
 */
public class AddDigits{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()){
			int num = sc.nextInt();
			System.out.println(addDigits(num));
		}
		sc.close();
	}
	
	public static int addDigits(int num) {
        if(num==0)
            return 0;
        else
            return num%9==0?9:num%9;
    }
	
	/**
	 * 其实我们可以发现，不需要对0这个数字特殊对待，可以将所有数字减1之后再对9求余，得到的余数加一后刚好可以统一公式
	 * @param num
	 * @return
	 */
	public static int addDigits_b(int num){
		
		return (num-1)%9+1;
	}
}
