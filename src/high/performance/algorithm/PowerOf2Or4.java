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
	   return (a & 0x55555555)!=0;//再判断该数是否为1之后跟了偶数个0(即1是否在奇数位，4的次幂每增加一次，1向左移动两位，4 = 100，所以1一定会在奇数位)
       // 经过第二个if，该数一定为2的次幂，所以一定是1后面所有位均为0的形式
	   //0x55555555=0101 0101 0101 0101 0101 0101 0101 0101，与奇数位为1的数进行与运算，则计算结果不会为0.
       //而与偶数为1的数进行与运算，则结果为0，这样就将2的次幂中不是4的次幂的数排除了(如8，32等)
   }
   
}
