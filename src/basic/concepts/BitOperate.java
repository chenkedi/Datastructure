package basic.concepts;

import java.util.Arrays;

public class BitOperate
{

	public static void main(String[] args) {
		bitAnd(16);
		bitAnd(19);
		
		bitOr(21);
		
		bitXORSwap(5,5);
		
		bitXORViaReference(new int[] {5,6},0,0);
		
		bitNot(65535);
		

	}
	
	/**
	 * 注意，这里不使用临时变量的交换方式，不能正确处理对于两个引用指向同一个元素的交换，会产生交换后该值恒等于0的bug
	 * 所以，仅当max!=i时作交换，max=i时不用做任何处理，因为他们就是本身
	 * @param set
	 * @param max
	 * @param i
	 */
	private static void bitXORViaReference(int[] set, int max, int i) {
		System.out.println("交换前："+Arrays.toString(set));
		set[max]=set[max]^set[i];
		set[i]=set[max]^set[i];
		set[max]=set[max]^set[i];
		System.out.println("交换后："+Arrays.toString(set));
	}

	/**
	 * and运算常用语二进制的取位(取末位n位）操作，如一个数and 1 的结果就是取二进制的末位。
	 * 取末位可以用来判断一个整数的奇偶，末位0为偶数，1为奇数
	 */
	public static void bitAnd(int a){
		if((a & 1)==0)
			System.out.println("a是偶数");
		else
			System.out.println("a是奇数");
	}
	
	/**
	 * or运算同城用于二进制特定位上的无条件赋值，如一个数or 1的结果就是把二进制最末位强行变成1
	 * 如果需要把二进制最末位变成0，对这个数or 1之后再减一就可以了，其实际意义就是把这个数强行变成最接近的偶数。
	 * @param a
	 */
	public static void bitOr(int a){
		System.out.println("比a小且最接近a的偶数是："+((a|1)-1));
	}
	
	/**
	 * xor运算通常用于对二进制的特定一位进行取反操作，因为异或可以这样定义：0和1异或0都不变，异或1则取反。
	 * 使用异或运算来进行无临时变量的swap过程
	 * 这样做的理由是异或运算的逆运算是其本身
	 * @param a
	 * @param b
	 */
	public static void bitXORSwap(int a,int b){
		System.out.println("交换前：a="+a+" b="+b);
		a=a^b;
		b=a^b;
		a=a^b;
		System.out.println("交换后：a="+a+" b="+b);
	}
	
	/**
	 * not运算的定义是把内存中的0和1全部取反。
	 * 使用not运算时要格外小心，你需要注意整数类型有没有符号。如果not的对象是无符号整数（不能表示负数），那么得到的值就是它与该类型上界的差，因为无符号类型的数是用$0000到$FFFF依次表示的。
	 * 对一个有符号的数进行not运算后，最高位的变化将导致正负颠倒，并且数的绝对值会差1。也就是说，not a实际上等于-a-1。这种整数储存方式叫做“补码”。
	 * @param a
	 */
	public static void bitNot(int a){
		System.out.println("a进行not运算后，绝对值应该加一，并且正负颠倒："+~a);
	}
	
}
