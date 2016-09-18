package basic.concepts;

/**
 * 使用普通计算方法和提公因式法计算多项式的值
 * 1 + X + 1/2X2 + ... + 1/100X100
 * @author chenkedi
 *
 */

class Polynomials{
	
	public double normal(double x,int n){
		double sum=1.0;
		for(int i=1;i<=n;i++)
			sum+=Math.pow(x,i)/i;
		return sum;
	}
	
	public double commonFactor(double x,int n){
		double sum=1.0/n;
		for(int i=n;i>=2;i--)
			sum=(sum)*x+(1.0/(i-1));
		return (sum*x)+1;
	}
	
}




public class Polynomial
{
	public static int iterate=100000000;
	public static void main(String[] args){
		
		Polynomials poly = new Polynomials();
		
//		long begin1=System.currentTimeMillis();
//		for(int i=1;i<=iterate;i++)
//			poly.normal(1.1,100);
//		long end1=System.currentTimeMillis();
//		System.out.println("Duration of normal method："+(end1-begin1)+"ms");
		
		long begin2=System.currentTimeMillis();
		for(int i=1;i<=iterate;i++)
			poly.commonFactor(1.1,100);
		long end2=System.currentTimeMillis();
		System.out.println("Duration of common factor method："+(end2-begin2)+"ms");
	}
	
}
