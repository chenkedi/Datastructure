package sort;


import java.util.Arrays;

/**
 * 注意，堆结构中，数组的第0个元素是不使用的
 * @author chenkedi
 *
 */
@SuppressWarnings("rawtypes")
public class HeapSort extends Sort{
	
	public HeapSort(){
		sortType = "堆排序";
	}
	
	
	
	public void  sort(Comparable[] a){
		int N = a.length;
		//先将数组变为堆有序
		//完全二叉树的所有叶子节点都可以看做是只有一个节点的堆，所以我们只要从倒数第一个非叶子节点(2/N)开始扫描并下沉，直到第一个元素，即可保证堆有序
		for(int k = N/2; k >= 1; k--)
			sink(a, k, N);
		while(N > 1){
			//将堆顶元素与当前堆的最后一个元素交换后，再将堆大小减一（因为堆顶元素就是数组中最大的元素）
			swap(a, 1, N--);
			//在下沉这个新交换的元素，维护堆的有序性，同时将堆中最大的元素推到堆顶
			sink(a, 1, N);
		}
	}
	
	/**
	 * 用于维护堆结构的上浮操作
	 * @param k
	 */
	public void swim(Comparable[] a, int k){
		while(k > 1 && less(a[k/2], a[k])){
			exchange(a, k/2, k);
			k /= 2;
		}
	}
	
	/**
	 * 用于维护堆结构的下沉操作
	 * 由于数组的第一个元素不使用，所以此处是小于等于a的长度
	 * @param k
	 */
	public void sink(Comparable[] a, int k, int N){
		while(2 * k <= N){
			int j = 2*k;
			//如果节点k的两个子节点中，右子节点比左子节点大，则j+1
			if(j < N && less(a, j, j+1))
				j++;
			//如果k大于或等于自己的子节点中最大的那个，则循环可以结束，下沉到此为止
			if(!less(a, k, j))
				break;
			//否则交换k与j两个节点
			swap(a, k, j);
			k = j;
		}
	}
	
	/**
	 * 定义自己的less方法，因为堆结构一般不使用数组第0个元素，所有我们要修改less的实现，将其索引减1，以实现a[0]至a[N-1]的堆排序
	 * @param a
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean less(Comparable[] a, int v, int w){
		return a[v-1].compareTo(a[w-1]) < 0;
	}
	
	/**
	 * 实现自己的swap方法，因为堆结构一般不使用数组第0个元素，所有我们要修改less的实现，将其索引减1，以实现a[0]至a[N-1]的堆排序
	 * @param a
	 * @param i
	 * @param j
	 */
	public void swap(Comparable[] a, int i, int j){
		Comparable tmp = a[i-1];
		a[i-1] = a[j-1];
		a[j-1] = tmp;
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		int[] set={5,2,4,6,1,3,11,25,48,9,7,8,32,12,14,16,18};
		sort(set);
		System.out.println(Arrays.toString(set));
		
	}
	
	/**
	 * 堆排序算法逻辑主体
	 * 先建立最大堆。注意，建立最大堆的过程是从堆底部的非叶子结点开始的，慢慢往上保证每个非叶子结点都比其子堆的元素大，这样直至最顶部即建立了一个最大堆
	 * 而后，一个for循环，每次将数组中的第一个元素（即堆顶）与i位置的元素置换，保证每次维护堆后的最大结点均在数组最后或者倒数第几的位置，并每次减小堆的大小，直至最后堆中只有最后一个元素
	 * 则此时该元素虽为堆顶，但实际是最小的元素，堆排序完成
	 * 注意，由于真实程序中的数组是从0开始索引，而不是算法中所假设的从1开始的，所以exchange等都要与A[0]交换，维护堆也要以A[0]为堆的顶点
	 * @param set
	 */
	public static void sort(int[] set){
		buildMaxHeap(set);
		System.out.println(Arrays.toString(set));
		int heapSize=set.length;
		for(int i=set.length-1;i>=1;i--){
			exchange(set,i,0);
			//System.out.println(Arrays.toString(set));
			heapSize--;
			maxHeapiFy(set,0,heapSize);
		}
	}
	
	/**
	 * 建立最大堆
	 * 由于Math.floor(n/2+1)及以后的节点都是堆的子节点，所以只要从堆的下发开始，对n/2~0中的每个元素进行MaxHeapFy过程，即可保证每个结点
	 * 都是子堆的最大根
	 * @param set
	 */
	public static void buildMaxHeap(int[] set){
		int heapSize = set.length;
		for(int i=heapSize/2-1;i>=0;i--){
			maxHeapFy(set,i,set.length);
		}
	}
	
	/**
	 * 对最大堆的维护方法，复杂度O(lgn)
	 * @param set
	 * @param i
	 */
	public static void maxHeapFy(int[] set,int i,int heapSize){
		int left = left(i);
		int right = right(i);
		int largest= i;
		if(left<=heapSize-1 && set[left]>set[i])
			largest=left;
			
		if(right<=heapSize-1 && set[right]>set[largest])
			largest=right;
					
		if(largest!=i){
			exchange(set,largest,i);
			maxHeapFy(set,largest,heapSize);
		}
			
	}
	/**
	 * 为了避免某些编译器对尾递归优化不好，可以使用如下的循环版本
	 * @param set
	 * @param i
	 * @param heapSize
	 */
	public static void maxHeapiFy(int[] set, int i, int heapSize){
		int left=left(i);
		int right=right(i);
		int largest=i;
		while(left<=heapSize-1 || right<=heapSize-1){
			
			if(left<=heapSize-1 && set[left]>set[i])
				largest=left;
				
			if(right<=heapSize-1 && set[right]>set[largest])
				largest=right;
			
			if(largest!=i){
				exchange(set,largest,i);
				i=largest;
				left=left(i);
				right=right(i);
			}else{
				break;
			}
			
		}
	}
	
	/**
	 * 交换数组中的两个元素
	 * @param set
	 * @param max
	 * @param i
	 */
	private static void exchange(int[] set, int max, int i) {
		if(max!=i){
		set[max]=set[max]^set[i];
		set[i]=set[max]^set[i];
		set[max]=set[max]^set[i];
		}
	}

	/**
	 * 求节点的左子节点，二叉堆为当前下标的两倍
	 * 由于算法假设第一个元素下标为1，而实际数组下标从0开始，所以要加一
	 * @param i
	 * @return
	 */
	public static int left(int i){
		return (2*i)+1;
	}
	
	/**
	 * 求节点的右子节点，二叉堆为当前下标的两倍加一
	 * @param i
	 * @return
	 */
	public static int right(int i){
		return (2*i+1)+1;
	}
	
}
