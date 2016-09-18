package sort;

import java.util.Arrays;

@SuppressWarnings("rawtypes")
public class BubbleSort extends Sort{
	
	public BubbleSort(){
		sortType = "冒泡排序";
	}
	
	
	@Override
	public void sort(Comparable[] a){
		int N = a.length;
		for(int i = 0; i < N; i++){
			for(int j = 1; j < N - i; j++)
				if(less(a[j], a[j-1])){
					exchange(a, j, j-1);
				}
					
		}
	}
	
	@Override
	public void sortOptimized(Comparable[] a){
		sortType = "使用flag优化的冒泡排序";
		//如果有100个数的数组，仅前面10个无序，后面90个都已排好序且都大于前面10个数字，那么在第一趟遍历后，最后发生交换的位置必定小于10，且这个位置之后的数据必定已经有序了，
		//记录下这位置，第二次只要从数组头部遍历到这个位置就可以了。
		//benchmark测试无作用，反而会增加运行时间
		int flag,k;
		flag = a.length;
		while(flag > 0){
			k = flag;
			flag = 0;
			for(int j = 1; j < k; j++)
				if(less(a[j], a[j-1])){
					exchange(a, j, j-1);
					flag=j;
				}
		}
	}
	
	
	
	public static void main(String[] args) {
		int[] set={5,2,4,6,1,3};
		sortOptimized(set);
		
		System.out.println(Arrays.toString(set));

	}
	
	public static void sort(int[] set){
		//加入标志变量，如果前一趟没有发生交换，则即使没有到达for的结束条件也可以结束循环，因为此时数组已经有序
		boolean flag =true;
		while(flag){
			flag=false;
			int n = set.length;
			for(int i=0;i<n;i++){
				for(int j=1;j<n-i;j++){
					if(set[j-1]>set[j]){
						int tmp=set[j-1];
						set[j-1]=set[j];
						set[j]=tmp;
						flag=true;
					}
				}
		}
		
		}
	}
	
	public static void sortOptimized(int[] set){
		//如果有100个数的数组，仅前面10个无序，后面90个都已排好序且都大于前面10个数字，那么在第一趟遍历后，最后发生交换的位置必定小于10，且这个位置之后的数据必定已经有序了，
		//记录下这位置，第二次只要从数组头部遍历到这个位置就可以了。
		int flag,k;
		flag = set.length;
		while(flag>0){
			k=flag;
			flag=0;
			for(int j=1;j<k;j++){
				if(set[j-1]>set[j]){
					int tmp=set[j-1];
					set[j-1]=set[j];
					set[j]=tmp;
					flag=j;
				}
			}
		}
	}
}
