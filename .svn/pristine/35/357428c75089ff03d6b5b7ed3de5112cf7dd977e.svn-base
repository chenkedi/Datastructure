package high.performance.algorithm;

/**
 * 十进制转16进制，类似可以推广到转8进制，4进制等是二的幂次的进制
 * @author chenkedi
 *
 */
public class Ten2Sixteen {

	public static void main(String[] args) {

		System.out.println(transform16(Integer.MIN_VALUE));
		System.out.println(transformNoNegative(Integer.MIN_VALUE));

	}
	/**
	 * 传入的数字存在负数时的情况
	 * @param a
	 * @return
	 */
	public static String transform16(int a){
		String[] map = {"A","B","C","D","E","F"};
		if(a == Integer.MIN_VALUE)
			return "-80000000";
		if(a == 0)
			return "0";
		StringBuilder res = new StringBuilder();
		int b = Math.abs(a);
		while(b>0){
			int tmp = (b & 0xF);
			if(tmp >= 10)
				res.insert(0,map[tmp-10]);
			else
				res.insert(0, String.valueOf(tmp));
			b >>= 4;
		}
		return a>0?res.toString():res.insert(0, "-").toString();
	}
	
	/**
	 * 传入数字按照无符号整数处理时情况
	 * @param a
	 * @return
	 */
	public static String transformNoNegative(int a){
		String[] map = {"A","B","C","D","E","F"};
		if(a == 0)
			return "0";
		
		StringBuilder res = new StringBuilder();
		while(Math.abs(a)>0 || a == Integer.MIN_VALUE){
			int tmp = (a & 0xF);
			if(tmp >= 10)
				res.insert(0,map[tmp-10]);
			else
				res.insert(0, String.valueOf(tmp));
			a >>>= 4;
		}
		return res.toString();
	}

}
