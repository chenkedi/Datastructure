package sort;

import java.util.Arrays;
import java.util.Random;

@SuppressWarnings("rawtypes")
public class QuickSort extends Sort{
	
	static Random ra = new Random();
	
	public QuickSort(){
		sortType  = "快速排序";
	}
	
	
	@Override
	public void sort(Comparable[] a) {
		sort(a, 0, a.length-1);
		
	}
	private void sort(Comparable[] a, int p, int q){
		if(p < q){
			int mid = partition(a, p, q);
			sort(a, p, mid - 1);
			sort(a, mid + 1, q);
		}
	}
	
	/**
	 * 使用随机切分优化的快排，保证各种情况下的稳定性
	 */
	@Override 
	public void sortOptimized(Comparable[] a){
		sortType = "使用随机优化的快排";
		sortWithRandomPartition(a, 0, a.length - 1);
	}
	private void sortWithRandomPartition(Comparable[] a, int p, int q){
		if(p < q){
			int mid = randomPartition(a, p, q);
			sort(a, p, mid - 1);
			sort(a, mid + 1, q);
		}
	}
	
	
	private int partition(Comparable[] a, int p, int q){
		Comparable key = a[q];
		int i = p - 1;
		for(int j = p; j < q; j++){
			if(less(a[j],key)){
				exchange(a, j, ++i);
			}
		}
		exchange(a, q, i+1);
		//注意，如果分区值不进行这种处理的话，当数组中的元素全部相同时会造成无限的堆栈开销，最终StackOverFlow
		//这是由于所有元素相同时，每次 i+1 都会等于 p，造成递归无法正常收敛
		return i+1;
	}
	
	private int randomPartition(Comparable[] a, int p, int q){
		int rand = ra.nextInt(q - p) + p;
		Sort.exchange(a, rand, q);
		return partition(a, p, q);
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
//		int[] set={5,2,4,6,1,3};
		
		int N = 6000000;
//		int[] set={5,2,4,6,1,3};
		int[] set= new int[N];
		
		int element;
		Random ra = new Random();
		for(int i=0;i<N;i++){
			 element = ra.nextInt(20000000);
			set[i]=element;
		}
		int[] set2 = set.clone();
		int[] set3 = set.clone();
		
		long begin = System.currentTimeMillis();		
		sort(set,0,N-1);
		long end = System.currentTimeMillis();
		System.out.println("普通快排：" + (end-begin));
		
		begin = System.currentTimeMillis();
		sort2(set2,0,N-1);
		end = System.currentTimeMillis();
		System.out.println("随机快排：" + (end-begin));

		begin = System.currentTimeMillis();
		Arrays.sort(set3);
		end = System.currentTimeMillis();
		System.out.println("系统自带排序：" + (end-begin));
		
//		System.out.println(Arrays.toString(set));

	}
	
	public static void sort(int[] set,int p, int r){
		if(p<r){
		int q=partition(set,p,r);
		sort(set,p,q-1);
		sort(set,q+1,r);
		}
	}
	public static void sort2(int[] set, int p, int r){
		if(p < r){
			int q = randomPartition(set, p, r);
			sort2(set, p, q-1);
			sort2(set, q+1, r);
		}
	}

	public static int partition(int[] set, int p, int r) {
		int x = set[r];
		int i = p-1;
		for(int j=p;j<r;j++){
			if(set[j]<=x){
				i++;
				exchange(set,j,i);
			}
		}
		exchange(set,r,i+1);
		//防止当需要排序的数组里面的元素相同时，返回错误值。如果相同会返回r，而我们需要其返回(p+r)/2
		return i+1==r?(p+r)/2:i+1;
	}
	
	/**
	 * 注意，这里不使用临时变量的交换方式，不能正确处理对于两个引用指向同一个元素的交换，会产生交换后该值恒等于0的bug
	 * 所以，仅当max!=i时作交换，max=i时不用做任何处理，因为他们就是本身
	 * @param set
	 * @param max
	 * @param i
	 */
	public static void exchange(int[] set, int max, int i) {
		if(max!=i){
		set[max]=set[max]^set[i];
		set[i]=set[max]^set[i];
		set[max]=set[max]^set[i];
		}
	}
	
	public static int randomPartition(int[] set, int p, int r){
		int rand = ra.nextInt(r-p)+p;
		exchange(set, r, rand);
		return partition(set, p, r);
	}

	
}
