package algorithm.exercise;

/**
 * 筛法求素数的常规方法和线性方法
 * 筛法的重点是外层循环只要遍历0..根号n，然后从素数2开始，将2的倍数过滤掉；然后从素数3开始，将3的倍数过滤掉，一直到将所有素数的倍数过滤掉即可
 * 注意处理n为0或n为1的特殊情况，往往是该问题考察的关键
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
		if(n.length >= 2) {
			n[0]=n[1]=false;
		} else if (n.length == 1) {
			n[0] = false;
		}
		int sqrt=(int) Math.ceil(Math.sqrt(n.length));
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

	private static void primeLinear2(boolean[] isPrime) {
	    int[] prime = new int[isPrime.length]; // 从小到大存储找到的素数
	    int count = 0;//初始化没有素数
		// 注意处理极端边界情况
		if(isPrime.length >= 2) {
			isPrime[0]=isPrime[1]=false;
		} else if (isPrime.length == 1) {
			isPrime[0] = false;
		}

		// 遍历数组，将最大因数为i的合数标记为false
		for(int i = 0; i < isPrime.length; i++) {
			if (isPrime[i])
				prime[count++] = i; // 将标记为素数的数放入到prime中，并将素数计数加1
			// 遍历已知素数表中，比i的最小素因数还小的素数, 这些素数乘以i得到的合数，标记为false，保证筛选合数时不会重复
			for (int j = 0; j < count && prime[j] * i < isPrime.length; j++) {
				isPrime[prime[j] * i] = false;
				// 要达到"遍历已知素数表中比i的最小素因数还小的素数"这个条件，则只要i 除以素数表中第一个最小的素数，余数为0，则表示找到了i的最小素因数
				if(i % prime[j] == 0)
					break;
			}
		}

	}

	/**
	 *
	 * @param n
	 */
	private static void prime(boolean[] n) {
		if(n.length >= 2) {
			n[0]=n[1]=false;
		} else if (n.length == 1) {
			n[0] = false;
		}
		for(int i=2;i < Math.sqrt(n.length);i++)
			if(n[i]){
				for(int j=i*i;j<n.length;j+=i)
					if(j<0)
						break;//同上
					else
						n[j]=false;
			}
		
	}

	/**
	 * 还可以换一种思路，将boolean数组定义为标记不是素数的数组，这样，初始化数组时，认为所有的数字均为素数，可以利用java默认将boolean数组初始化为false的特性
	 * @param notPrime
	 */
	private static void prime2(boolean[] notPrime) {
		if(notPrime.length >= 2) {
			notPrime[0]=notPrime[1]=true;
		} else if (notPrime.length == 1) {
			notPrime[0] = true;
		}

		for(int i = 2; i < Math.sqrt(notPrime.length); i++) {
			if(!notPrime[i]) {
				for(int k = i*i; k < notPrime.length; k+=i)
					notPrime[k] = true;
			}
		}

	}



	
	
}
