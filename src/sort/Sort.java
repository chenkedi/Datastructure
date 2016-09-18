package sort;

import java.util.Arrays;

/**
 * 排序库的抽象父类
 * 封装基本的比较方法，测试方法以及数组的展示方法
 * @author chenkedi
 *
 */

@SuppressWarnings("rawtypes")
public abstract class Sort {
	String sortType;
	
	/**
	 * 具体的排序算法，由具体的子类实现
	 */
	public abstract void sort(Comparable[] a);
	
	public void sortOptimized(Comparable[] a){
		
	}
	
	/**
	 * 检测元素v是否小于元素w
	 * @param v
	 * @param w
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	
	/**
	 * 检测元素v是否大于元素w
	 * @param v
	 * @param w
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean more(Comparable v, Comparable w){
		return v.compareTo(w) > 0;
	}
	
	/**
	 * 交换数组中的两个值
	 * @param a
	 * @param i
	 * @param j
	 */
	public static void exchange(Comparable[] a, int i, int j){
		Comparable tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	/**
	 * 打印数组
	 * @param a
	 */
	public static void show(Comparable[] a){
		System.out.println(Arrays.toString(a));
	}
	
	/**
	 * 检测数组是否按非降序排列
	 * @param a
	 * @return
	 */
	public static boolean isIncreasingSorted(Comparable[] a){
		for(int i = 1; i < a.length; i++)
			if(less(a[i], a[i-1])){
				show(a);
				return false;
			}
				
		return true;
	}
	
	/**
	 * 检测数组是否按照非降序排列
	 * @param a
	 * @return
	 */
	public static boolean isDecresingSorted(Comparable[] a){
		for(int i = 1; i < a.length; i++)
			if(more(a[i], a[i-1])){
				show(a);
				return false;
			}
				
		return true;
	}
	
	public void sortType(){
		System.out.print(sortType + "耗时：");
	}
	
	
}
