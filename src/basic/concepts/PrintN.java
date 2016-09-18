package basic.concepts;

/**
 * 测试循环打印与递归打印从1到N的不同
 * @author chenkedi
 *
 */
class PrintNs{
	
	
	public void loopPrint(int N){
		for(int i=1;i<=N;i++){
			System.out.println(i);
		}
	}
	
	public void recursivePrint(int N){
		if(N>0){
			recursivePrint(N-1);
			System.out.println(N);			
		}
	}
}



public class PrintN
{
	public static final int N = 10000;
	
	public static void main(String[] args) {
		
		PrintNs printN = new PrintNs();
		
		printN.loopPrint(N);
		//printN.recursivePrint(N);

	}

}
