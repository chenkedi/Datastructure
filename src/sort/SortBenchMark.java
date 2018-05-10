package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 测试所有排序算法在各种不同情况下的性能
 * @author chenkedi
 *
 */
@SuppressWarnings("rawtypes")
public class SortBenchMark {

	public static void main(String[] args) {

		Sort insertionSort = new InsertionSort();
		Sort mergeSort = new MergeSort();
		Sort quickSort = new QuickSort();
		Sort heapSort = new HeapSort();
		Sort selectSort = new SelectSort();
		Sort bubbleSort = new BubbleSort();
		
		//对于个人计算机，6000万是极限，快速排序耗时要45秒，系统自带的三向切分需要50秒，内存耗用2G多
		//使用插入排序优化的归并排序耗时199秒其他排序的时间未知
		int N = 6000000;
		Integer[] set= new Integer[N];
		int element;
		Random ra = new Random();
		for(int i=0;i<N;i++){
			 element = ra.nextInt(20000000);
			set[i]=element;
		}
		Integer[] set2 = set.clone();
		Integer[] set3 = set.clone();
		Integer[] set4 = set.clone();
		Integer[] set5 = set.clone();
		Integer[] set6 = set.clone();
		Integer[] set7 = set.clone();
		Integer[] set8 = set.clone();
		Integer[] set9 = set.clone();
		Integer[] set10 = set.clone();
		System.out.println("数据准备完毕！");
		
		timeBenchMarkSystemSort(set);
		timeBenchMark(mergeSort, set2);
		timeBenchMarkWithOptimize(mergeSort, set3);
		timeBenchMark(quickSort, set4);
		timeBenchMarkWithOptimize(quickSort, set5);
		timeBenchMark(heapSort, set6);
//		timeBenchMark(insertionSort, set7);
//		timeBenchMark(selectSort, set8);
//		timeBenchMark(bubbleSort, set9);
//		timeBenchMarkWithOptimize(bubbleSort, set10);

	}
	
	
	public static void timeBenchMark(Sort sort, Comparable[] a){
		long begin = System.currentTimeMillis();
		sort.sort(a);
		long end = System.currentTimeMillis();
		sort.sortType();
		System.out.println((end-begin) + "ms");
		System.out.println("排序"+ (Sort.isIncreasingSorted(a)?"正确":"错误"));
	}
	
	public static void timeBenchMarkWithOptimize(Sort sort, Comparable[] a){
		long begin = System.currentTimeMillis();
		sort.sortOptimized(a);
		long end = System.currentTimeMillis();
		sort.sortType();
		System.out.println((end-begin) + "ms");
		System.out.println("排序"+ (Sort.isIncreasingSorted(a)?"正确":"错误"));
	}
	
	public static void timeBenchMarkSystemSort(Comparable[] a){
		long begin = System.currentTimeMillis();
		Arrays.sort(a);
		long end = System.currentTimeMillis();
		System.out.print("系统自带快排(三段切分)：");
		System.out.println((end-begin) + "ms");
		System.out.println("排序"+ (Sort.isIncreasingSorted(a)?"正确":"错误"));
	}

}
