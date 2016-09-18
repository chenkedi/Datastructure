package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序
 * 由于归并排序总要将排序的序列差分到单个元素再组合，而且组合的过程不受序列是否排序的影响，所以归并排序是稳定的排序
 *
 * @author chenkedi
 *
 */
@SuppressWarnings("rawtypes")
public class MergeSort extends Sort{
	InsertionSort insertion = new InsertionSort();
	
	public MergeSort(){
		sortType = "归并排序";
	}
	
	@Override
	public void sort(Comparable[] a) {
		sort(a, 0, a.length-1);
		
	}
	private void sort(Comparable[] a, int low, int high){
		if(low < high){
			int mid = (low + high)/2;
			sort(a, low, mid);
			sort(a, mid+1, high);
			merge(a, low, mid, high);
		}
	}
	
	/**
	 * 使用插入排序优化归并排序归并小数组时的速度
	 * @param a
	 */
	public void sortOptimized(Comparable[] a){
		sortType = "使用插入排序优化的归并排序";
		sortWithInsertion(a, 0, a.length-1);
	}
	private void sortWithInsertion(Comparable[] a, int low, int high){
		if(low < high){
			if(high - low <= 15){
				insertion.sort(a, low, high);
			}else{
				int mid = (low + high)/2;
				sort(a, low, mid);
				sort(a, mid+1, high);
				merge(a, low, mid, high);
			}
			
		}
	}
	
	/**
	 * 合并两个有序子数组，使用算法一书中创建一次临时数组的方式
	 * @param a
	 * @param low
	 * @param mid
	 * @param high
	 */
	private void merge(Comparable[] a, int low, int mid, int high){
		int i = 0, j = mid - low + 1;
		
		Comparable[] aux = (Comparable[]) new Comparable[high - low +1];
		
		for(int k = 0; k < aux.length; k++)
			aux[k] = a[low + k];
		
		for(int k = low; k <= high; k++){
			if(i > mid - low)
				a[k] = aux[j++];
			else if(j > high - low)
				a[k] = aux[i++];
			else if(less(aux[i], aux[j]))
				a[k] = aux[i++];
			else 
				a[k] = aux[j++];
		}		
	}
	
	/**
	 * 合并两个子数组，使用算法导论一书中创建两个临时数组，并使用哨兵的方式
	 * 由于面向实现comparable接口的类编程，所以无法设置大于所有类的哨兵,此方法无法实现
	 * @param a
	 * @param low
	 * @param mid
	 * @param high
	 */
	@SuppressWarnings("unused")
	private void merge2(Comparable[] a, int low, int mid, int high){
		int n1 = mid - low + 1;
		int n2 = high - mid;
		
		Comparable[] left = (Comparable[] )new Comparable[n1+1];
		Comparable[]  right = (Comparable[]) new Comparable[n2+1];
		
		for(int i = 0; i < n1; i++)
			left[i] = a[i+low];
		for(int j = 0; j < n2; j++)
			right[j] = a[j+mid+1];
		
	}
	
	/**
	 * 合并两个子数组，使用算法导论一书中创建两个临时数组，并不使用哨兵的方式
	 * @param a
	 * @param low
	 * @param mid
	 * @param high
	 */
	@SuppressWarnings("unused")
	private void merge2NoGuard(Comparable[] a, int low, int mid, int high){
		int n1 = mid - low + 1;
		int n2 = high - mid;
		
		Comparable[] left = (Comparable[] )new Comparable[n1];
		Comparable[]  right = (Comparable[]) new Comparable[n2];
		
		for(int i = 0; i < n1; i++)
			left[i] = a[i+low];
		for(int j = 0; j < n2; j++)
			right[j] = a[j+mid+1];
		
		int i = 0, j = 0;
		for(int k = low; k <= high; k++){
			if(i < n1 && j < n2){
				if(less(left[i], right[j]))
					a[k] = left[i++];
				else
					a[k] = right[j++];
			}else if(i >= n1)
				a[k] = right[j++];
			else
				a[k] = left[i++];
		}
	}
	

	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
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
		sort3(set,0,N-1);
		long end = System.currentTimeMillis();
		System.out.println("算法导论归并耗时：" + (end-begin));
		
		begin = System.currentTimeMillis();
		sort2(set2,0,N-1);
		end = System.currentTimeMillis();
		System.out.println("算法归并耗时：" + (end-begin));
		
		begin = System.currentTimeMillis();
		Arrays.sort(set3);
		end = System.currentTimeMillis();
		System.out.println("系统自带排序：" + (end-begin));
//		for(int temp : set)
//			System.out.println(temp);

	}
	/**
	 * 归并排序递归调用过程
	 * @param a
	 * @param low
	 * @param high
	 * @return
	 */
	public static void sort(int[] a,int low,int high){
		if(low<high){
			int mid=(low+high)/2;
			sort(a,low,mid);
			sort(a,mid+1,high);	
			merge(a,low,mid,high);
		}
		
	}
	/**
	 * 算法一书中的实现方法，使用了一整个临时数组，而不是像算法导论的创建两个临时数组，速度在数组大小为6百万是，比sort快200ms，提升不大
	 * 因为算法导论中的方法开销主要在与多创建了一次数组
	 * @param a
	 * @param low
	 * @param high
	 */
	public static void sort2(int[] a, int low, int high){
		if(low < high){
			int mid = (low + high)/2;
			sort2(a, low, mid);
			sort2(a, mid+1, high);
			merge2(a, low, mid, high);
		}
	}
	
	/**
	 * 使用插入排序优化归并排序处理小规模数组时的性能
	 * 只有在数据量达到600万，小数组阈值取15时，该算法比sort2快400ms，在6000万时，快将近一倍
	 * 在阈值取25时，比sort2快一倍
	 * @param a
	 * @param low
	 * @param high
	 */
	public static void sort3(int[] a, int low, int high){
		
		if(low < high){
			if(high - low + 1 <= 20){
				//InsertionSort.sort(a, high, low);
			}else{
				int mid = (low + high)/2;
				sort3(a, low, mid);
				sort3(a, mid + 1, high);
				merge2(a ,low, mid, high);
			}
			
		}
	}
	
	
	/**
	 * 合并已经排好序的两个子序列，使用哨兵（即无穷大）
	 * p<=q<r
	 * @param sub
	 * @param low
	 * @param mid
	 * @param high
	 * @return
	 */
	public static void merge(int[] a,int low,int mid,int high){
		//将长度为r-p+1的子数列拆分为左右两个数组
		int n1=mid-low+1;
		int n2=high-mid;
		
		//左右两个数组各增加一个单元存储哨兵(即无穷大)
		int[] left=new int[n1+1];
		int[] right=new int[n2+1];
		
		//将a中的对应元素复制到left，right
		for(int i=0;i<n1;i++)
			left[i]=a[low+i];
		for(int j=0;j<n2;j++)
			right[j]=a[mid+1+j];
		
		//将left、right最后一个元素设为无穷大
		left[n1]=Integer.MAX_VALUE;
		right[n2]=Integer.MAX_VALUE;
		
		//合并排好序的两个子序列，将其覆盖原a数组的p~r部分
		int i=0,j=0;
		for(int k=low;k<=high;k++){
			//将这里的小于号改为大于号，并将正无穷改为负无穷，即可降序排列
			if(left[i]<=right[j]){
				a[k]=left[i];
				i++;
			}				
			else{
				a[k]=right[j];
				j++;
			}				
		}
	}
	
	/**
	 * 合并已经排好序的两个子序列，不使用哨兵（即无穷大）
	 * 算法导论2.3-2
	 * p<=q<r
	 * @param sub
	 * @param low
	 * @param mid
	 * @param high
	 * @return
	 */
	public static void mergeNo(int[] a,int low,int mid,int high){
		//将长度为r-p+1的子数列拆分为左右两个数组
		int n1=mid-low+1;
		int n2=high-mid;
		
		//左右两个数组各增加一个单元存储哨兵(即无穷大)
		int[] left=new int[n1];
		int[] right=new int[n2];
		
		//将a中的对应元素复制到left，right
		for(int i=0;i<n1;i++)
			left[i]=a[low+i];
		for(int j=0;j<n2;j++)
			right[j]=a[mid+1+j];
		
		//合并排好序的两个子序列，将其覆盖原a数组的p~r部分
		int i=0,j=0;
		for(int k=low;k<=high;k++){
			if(i<n1 && j<n2){
				//将这里的小于号改为大于号，即可降序排列
				if(left[i]<=right[j]){
					a[k]=left[i];
					i++;
				}				
				else{
					a[k]=right[j];
					j++;
				}
			}else if(i>=n1){
				a[k]=right[j];
				j++;
			}else{
				a[k]=left[i];
				i++;
			}
							
		}
	}
	
	public static void merge2(int[] a, int low, int mid, int high){
		int i = 0 ;
		int j = mid - low +1 ;
		int[] aux = new int[high-low+1];
		for(int k = 0; k < aux.length; k++)
			aux[k] = a[low+k];
		for(int k = low; k <= high; k++){
			if(i > mid - low) a[k] = aux[j++];
			else if(j > high-low) a[k] = aux[i++];
			else if(aux[i] < aux[j]) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
	}
	

}
