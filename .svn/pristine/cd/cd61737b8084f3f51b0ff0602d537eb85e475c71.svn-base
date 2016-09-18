package linear.list;

/**
 * 数组类型的线性表
 * @author chenkedi
 *
 * @param <T>
 */
class ArrayList{
	
	private final int MAX=100;
	Object[] data = new Object[MAX];
	int last = -1;
		
	/**
	 * 查找给定元素在线性表中的索引，没有找到返回-1
	 * @param index
	 * @return
	 */
	public int find(Object element){
		int i=0;
		for(i=0;i<=last;i++){
			if(data[i]==element){
				return i;
			}
		}
		return -1;		
	}
	
	/**
	 * 插入操作
	 * @param index 此参数为形象化，从1开始,对应数组的0号位置
	 * @param element
	 */
	public void insert(int index,Object element){
		if(last==MAX-1) 
			System.out.println("线性表已满！");//表是否已满？
		else if(index<1 || index>last+2){		//给定的位置是否合法？
			System.out.println("给定的位置不合法！");
		}
		else{
			for(int i=last;i>=index-1;i--)
				data[i+1]=data[i];
			
			data[index-1]=element;
			last++;			
		}
		
	}
	
	/**
	 * 删除指定位置的元素
	 * @param index,index是从1计数，对应数组中的0位置
	 */
	public void delete(int index){
		if(last==-1){
			System.out.println("线性表为空，无法删除！");
		}else if(index<1 || index>last+1){
			System.out.println("给定的位置不合法！");
		}
		else{
			for(int i=index-1;i<last;i++){
				data[i]=data[i+1];
			}
			last--;
		}
	}
	
	/**
	 * 打印线性表中的所有元素
	 */
	public void print(){
		if(last==-1){
			System.out.println("线性表为空！");
		}else{
			for(int i=0;i<=last;i++){
				System.out.print(data[i]+ " ");
			}
			System.out.println("\nlast="+last);			
		}
	}	
}

public class ArrayLists{
	public static void main(String[] args){
		ArrayList list=new ArrayList();
		list.insert(1, 1);
		list.insert(2, 2);
		list.insert(3, 3);
		list.insert(4, 4);
		list.insert(5, 5);
		list.insert(6, 6);
		list.print();
		
		list.delete(3);
		list.print();
		System.out.println(list.find(4));
	}
}
