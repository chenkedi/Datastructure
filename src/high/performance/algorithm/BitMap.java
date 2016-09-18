package high.performance.algorithm;


/**
 * 有一千万个电话号码，如何快速找到某个电话号码，考虑优化时间和空间复杂度，同时考虑内存限制
 * Given 2MB memory, we want to store 5 million integers in 0~10million range. These integers are unique.
 *1. How to store these integers?
 *2.Given a target integer i, how to test whether it is existed in these 5 million integers using only 2MB memory?
 * @author chenkedi
 *遍历电话号码簿，将电话号码存入对应的bit位。如第0个bit位是整数0存在与否的标志，第1个bit是是整数1存在与否的标，依次类推直到一千万
 *此例假设已经有存储了0~23整数（号码）的bitMap，其中0存在，1存在，二不存在。。。。23不存在
 *然后使用位运算查找整数给定的整数是否存在
 */
public class BitMap
{
	static byte[] bitMap={(byte) 204,(byte) 204,(byte) 204};
	//{[1100 1100],[1100 1100],[1100 1100]}
	
	public static void main(String[] args){
		for(int i=0;i<=23;i++)
		System.out.println(exist(i));	
	}
	
	public static Boolean exist(int a){
		int div = a/8;
		int remain = a%8;
		return (1<<(8-remain-1) & bitMap[div])==0?false:true;
	}
}
