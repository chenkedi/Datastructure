package leetcode;

/**
 * 求一个32位无符号整数二进制形式中1的个数
 * @author chenkedi
 *
 */
public class NumberOf1Bits
{
	public static void main(String[] args){
			for(int i=0;i<=20;i++){
				System.out.println(method4(i)+","+method1(i)+"  "+method2(i)+"  "+method3(i));
			}
		
	}
	//leetcode上这四个算法在600个用例的时候时间消耗都是2ms
    /**
     * 注意，对于带符号右移,若为负数,则在存储时首位表示符号位,其值为1,表示该值是负数的移位
     * 在移位过程中,高位补1,
     * 若符号位是0,表示是正数,在移位过程中高位补零,两者的前提是符号位保持不变:
     * 所以，此计算一个无符号整数中有多少个1的函数，需要将数当做无符号数看待，使用>>>进行无符号移位
     * 否则，-1无论>>多少次，值依然是-1
     * 
     * 注意一个特殊用例，212147483648（10000000 00000000 00000000 00000000），由于java没有无符号整型，所以该数超过了整型的范围
     * 可以用long来保存后强制转换为int，其值被java解释成-212147483648。
     * 特别注意，Math.abs(Integer.MIN_VALUE)=-212147483648，所以对该数使用此方法会输出错误结果0
     * @param a
     * @return
     */
    public static int method4(int a) {
    	int res = 0;
    	int n = (int) a;  	
        while(Math.abs(n)>0 || n==Integer.MIN_VALUE){
             if((n&1)==1)
                 res++;
             n=n>>>1;
        }
        return res;
    }

	
	/**
	 * （平行算法）邻位相加法。首先，每一位两两相加，而后每两位两两相加，再每四位两两相加一直到每16位两两相加
	 * @param i
	 * @return
	 */
	private static int method3(int n) {
		n=(n & 0x55555555) + (n>>>1 & 0x55555555);
		n=(n & 0x33333333) + (n>>>2 & 0x33333333);
		n=(n & 0x0f0f0f0f) + (n>>>4 & 0x0f0f0f0f);
		n=(n & 0x00ff00ff) + (n>>>8 & 0x00ff00ff);
		n=(n & 0x0000ffff) + (n>>>16 & 0x0000ffff);
		return n;
	}

	/**
	 * 快速法，每次位与运算消除整数中最右边的一个1，复杂度为该书中1的个数的线性函数
	 * 此法在java中可以正常工作，因为Integer.MIN_VALUE-1=212147483647,即Integer.MAX_VALUE
	 * @param a
	 * @return
	 */
	public static int method1(int a){
		int count=0;
		for(;Math.abs(a)>0 || a==Integer.MIN_VALUE;count++)
			a=a&(a-1);
		
		return count;
	}
	
	/**
	 * 查表法，此法使用空间换时间的方式，有4bit表或者8bit表或16bit表，表越大速度越快，占用空间越多
	 * 此处以4bit表为示例，表中的数字表示与数组索引相同的数以二进制表示时，1的个数。
	 * @param a
	 * @return
	 */
	public static int method2(int a){
		int[] table={
					0,1,1,2,
					1,2,2,3,
					1,2,2,3,
					2,3,3,4
					};
		int count=0;
		//若使用8bit表，则此处a每次右移8位，与0xff做与操作
		//注意此处也要使用无符号位移
		for(;a>0;a>>>=4)
			count+=table[a&0xf];
		return count;
	}
}
