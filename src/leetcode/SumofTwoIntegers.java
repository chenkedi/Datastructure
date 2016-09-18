package leetcode;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * @author chenkedi
 *
 */
public class SumofTwoIntegers {

	public static void main(String[] args) {
		int a = 1;
		int b = 2;
		System.out.println(sum(a,b));
		
	}

	public static int sum(int a, int b) {

		if(a == 0) return b;
		if(b == 0) return a;
		while(b != 0){
			//先求进位，即a与b中同时为1的位
			int carray = a & b;
			a = a ^ b;
			b = carray << 1;
		}
		return a;
	}

}
