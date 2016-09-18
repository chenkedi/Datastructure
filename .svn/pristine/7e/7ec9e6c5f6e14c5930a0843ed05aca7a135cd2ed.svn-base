package sort;

/**
 * 插入排序，将当前的数字插入到已经排好序的A[1...i-1]的子序列中，由于使用数组，插入之前，先将要插入的位置腾出空位，所以要先移动该位置后面一直到i-1位置的元素一格
 * @author chenkedi
 * 插入排序是一种不稳定的排序算法，在最好的情况下（序列已经排好序），其复杂度为Θ(n);而最坏的情况（数组反序排列），则为Θ(n2)。平均来讲，也是平方级别的
 */
@SuppressWarnings("rawtypes")
public class InsertionSort extends Sort
{
	public InsertionSort(){
		sortType = "插入排序";
	}
	
	@Override
	public void sort(Comparable[] a){
		for(int i = 1; i < a.length; i++){
			Comparable key = a[i];
			
			//由于j需要在循环外使用，所以此处使用while写法
			int j = i-1;
			//当key<a[j]时，将比key大的移动到其右边，所以是升序或等序排列
			//当key>a[j]时，将比key小的移动到其右边，所以是降序或等序排列
			while(j >= 0 && less(key,a[j])){
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = key;
		}
	}
	
	/**
	 * 将数组的一部分进行排序的插入排序算法
	 * 主要用于与归并排序结合，优化归并排序递归到小数组后的性能
	 * @param a
	 * @param from
	 * @param to
	 */
	public void sort(Comparable[] a, int from, int to){
		for(int i = from+1; i <= to; i++){
			Comparable key = a[i];
			
			int j = i-1;
			while(j >= from && less(key,a[j])){
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = key;
		}
	}

	
//	public static int[] sort(int[] set){
//		for(int i=1;i<set.length;i++){
//			int key=set[i];
//			
//			//由于j需要在循环外使用，所以此处使用while写法
//			int j=i-1;
//			//当set[j]>key时，将比key大的移动到其右边，所以是升序或等序排列
//			//当set[j]<key时，将比key小的移动到其右边，所以是降序或等序排列
//			while(j>=0 && set[j]>key){
//				set[j+1]=set[j];
//				j--;
//			}
//			set[j+1]=key;
//		}
//		return set;
//	}
//	
//	public static void sort(int[] set, int from, int to){
//		for(int i=from+1; i<=to; i++){
//			int key = set[i];
//			
//			int j = i-1;
//			while(j >= from && set[j]>key){
//				set[j+1] = set[j];
//				j--;
//			}
//			set[j+1] = key;	
//		}
//	}
}
