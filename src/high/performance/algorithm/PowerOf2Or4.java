package high.performance.algorithm;

/**
 * 判断一个数是否是2的幂或者4的幂
 * @author chenkedi
 *
 */
public class PowerOf2Or4{
   public static void main(String args[]){
	   
	   for(int i =1;i<=18;i++){
		   //System.out.println(i+":"+powerOf4(i));
		   //System.out.println(powerOf4(32));
		   //System.out.println(i+":"+powerOf2(i));
	   }
		   
   }
   
   public static boolean powerOf2(int a){
	   //1是任何数的乘方
	   return a>=1 && (a & (a-1))==0;//一个数是2的幂则其二进制位1后面所有位为0，与自身减一后的数与运算后只能得到0.注意1的情况要特殊考虑
   }
   
   public static boolean powerOf4(int a){
	   if(a<4) return false;
	   if((a&(a-1))!=0) return false;//不是2的幂一定也不是4的幂
	   return (a & 0x55555555)!=0;//再判断这个数是否是1后面是否跟了偶数个0(即1是否在奇数位)，零的个数通过&0x55555555来计算，&1与%2等价
	   //0x55555555=0101 0101 0101 0101 0101，与奇数位为1的数进行与运算会得0.由于已经把非2幂的数已经去掉了，所以剩下的数的二进制
	   //形式都是1后面跟着0，所以可以用第三个条件来判断剩下的数是不是4的幂
   }
   
}
