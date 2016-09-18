package basic.concepts;

/**
 * 循环实现和尾递归实现二分查找
 * @author chenkedi
 *
 */
public class BinarySearch
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int loopSearch(int[] list,int x,int head,int tail){
		while(head<=tail){
			int mid=(head+tail)/2;
			if(list[mid]<x)
				head=mid+1;
			else if(list[mid]>x)
				tail=mid-1;
			else
				return mid;
		}
		return -1;
	}
	
	public int tailRecursiveSearch(int[] list,int x,int head,int tail){
		if(head>tail) return -1;
		
		int mid=(head+tail)/2;
		if(list[mid]<x)
			return tailRecursiveSearch(list, x, mid+1, tail);
		if(list[mid]>x)
			return tailRecursiveSearch(list, x, head, mid-1);
		return mid;
	}

}
