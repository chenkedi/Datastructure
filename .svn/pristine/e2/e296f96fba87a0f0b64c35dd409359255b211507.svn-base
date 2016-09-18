package sort;

/**
 * 选择排序
 * 从A[0]开始，找出A中最小的元素，与A[0]交换；再从A[1]开始，找出A中次小的元素（或者说A[1...n-1]中最小的元素），与A[1]交换；
 * 依次类推，直到倒数第二个元素（由于最后一个元素已经是剩下的A序列中最小的元素，即A全局第n小的元素
 * 选择排序应该是稳定的排序
 * 算法导论2.2-2练习题
 * @author chenkedi
 *
 */
@SuppressWarnings("rawtypes")
public class SelectSort extends Sort{
	
	public SelectSort(){
		sortType = "选择排序";
	}
	
	
	public void sort(Comparable[] a){
		int N = a.length;
		for(int i = 0; i < N; i++){
			int min = i;
			for(int j = i + 1; j < N; j++){
				if(less(a[j], a[min]))
					min = j;
			}
			if(i!=min)
				exchange(a, i, min);
		}
	}
	
	
	

	public static void main(String[] args) {
		int[] set={5,2,4,6,1,3};
		sort(set);
		for(int temp : set)
			System.out.println(temp);

	}
	public static void sort(int[] a){
		for(int i=0;i<a.length-1;i++){
			int minIndex=i;
			
			//从这一句可以看出，选择排序应该是稳定的排序算法
			for(int j=i+1;j<a.length;j++){
				//将这里的小于号改成大于号，即可变为降序和等序排列
				if(a[j]<a[minIndex])
					minIndex=j;
			}
			if(minIndex!=i){
				int min=a[minIndex];
				a[minIndex]=a[i];
				a[i]=min;
			}
			
		}
	}

}
