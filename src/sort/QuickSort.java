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


	/**
	 * 与i,j分别从数组两端进行扫描，分别找大于key和小于key的位置的实现不同
	 * 此实现只有一个j指针从数组左边开始扫描，且只寻找比key小的元素，找到后，先将加i指针右移一位后（这也是为什么指针要初始化为p-1)，将其指向的元素与j指向的元素交换
	 * 这样,当j指针移动到最后一个元素的前一个元素时（最后一个作为key),i指针指向的元素之前的为小于key的，其他的为大于key的
	 * 但是由于key为最后一个元素，所以要将第i+1个元素（即右子数组的第一个元素与最后一个元素交换
	 * @param a
	 * @param p
	 * @param q
	 * @return
	 */
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


    /**
     * i,j指针分别从数组两端开始扫描，i碰到比key大的，i便停止移动；此时j开始从右开始扫描，碰到比key小的停止移动
     * 此时交换i,j指向的元素，如此往复，直至 i >= j结束
     * 由于我们选择数组的第一个元素作为key，所以需要将切分好的左子数组最右边的元素（此时应该是a[j]）与第一个元素进行交换即可完成切分
     * @param a
     * @param lo
     * @param hi
     * @return
     */
	private int partition_2(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Comparable key = a[lo];
		while(true) {
		    while(less(a[++i], key)) if(i == hi) break;
		    while(less(key, a[--j])) if(j == lo) break;
		    if (i >= j)
		        break;
		    exchange(a, i, j);
        }
        exchange(a, lo, j);
		return j;
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
