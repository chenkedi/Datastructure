package algorithm.exercise;

/**
 * 筛法求素数的常规方法和线性方法
 * @author chenkedi
 *
 */
public class PrimeNubmer{
	public static void main(String[] args){
		final int N = 1000000000;
		boolean[] n = new boolean[N+1];
		for(int i=0;i<=N;i++)
			n[i]=true;
		long begin=System.currentTimeMillis();
		prime(n);//10亿以内的素数，需要16s;
		primeLinear(n);//10亿以内的素数，只需要10秒;
		long end=System.currentTimeMillis();
		System.out.println(end-begin);
//		for(int i=0;i<=N;i++){
//			if(n[i]==true)
//				System.out.print(i+",");
//			if(i%150==0 && i!=0)
//				System.out.println();
//		}
	}

	private static void primeLinear(boolean[] n) {
		n[0]=n[1]=false;
		int sqrt=(int) Math.ceil(Math.sqrt((double)n.length));
		for(int p=2;p<sqrt;p++){
			if(n[p]){
				for(int q=p;q*p<n.length;q++){
					if(n[q]){
						for(int k=p*q;k<n.length;k*=p)
							if(k<0)
								break;//当数组太长时，最内层for循环k=p*q可能会溢出2^31次方，由于负数用补码表示，所以可以通过判断K是否小于0来确认是否溢出
							else
							n[k]=false;
					}
				}
			}
		}
	}

	private static void prime(boolean[] n) {
		n[0]=n[1]=false;
		for(int i=2;i<n.length;i++)
			if(n[i]){
				for(int j=i*i;j<n.length;j+=i)
					if(j<0)
						break;//同上
					else
						n[j]=false;
			}
		
	}
	

	
	
}
