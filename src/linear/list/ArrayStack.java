package linear.list;

/**
 * 数组实现堆栈，单数组，单堆栈
 * @author chenkedi
 *
 */
public class ArrayStack
{
	private Object[] data;
	private int top;
	private int MAX;
	/**
	 * 构造一个长度为MAX的空堆栈
	 * @param MAX
	 */
	public ArrayStack(int MAX){
		//栈空时top为-1;
		top=-1;
		this.MAX=MAX;
		data=new Object[MAX];
	}
	
	/**
	 * 入栈
	 * @param element
	 */
	public void push(Object element){
		if(!isFull()){
			data[++top]=element;
		}else{
			System.out.println("栈已满！");
		}
	}
	
	public Object pop(){
		if(!isEmpty()){
			return data[top--];
		}else{
			throw new NullPointerException("栈为空！");
		}
	}
	
	public boolean isEmpty(){
		if(top==-1)
			return true;
		else
			return false;
	}
	
	public boolean isFull(){
		if(top==MAX-1)
			return true;
		else
			return false;
	}
	
	public void print(){
		for(int i=top;i>=0;i--){
			if(i==top){
				System.out.println("-------");
				System.out.println("*top->"+data[i]);
			}else{
				System.out.println("-------");
				System.out.println("  "+data[i]);
			}
			
		}
		System.out.println("=======");
	}
	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args){
		ArrayStack stack=new ArrayStack(10);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		
		stack.pop();
		stack.print();
	}
}





/**
 * 一个数组实现两个堆栈
 * @author chenkedi
 *
 */
class Array2Stack{
	
	private Object[] data;
	//以数组左边界为栈底的栈指针
	private int top1;
	//以数组左边界为栈底的栈指针
	private int top2;
	private int MAX;
	
	/**
	 * 构造一个长度为MAX的数组，初始化两个堆栈
	 * @param MAX
	 */
	public Array2Stack(int MAX){
		//左栈空时top1为-1;
		top1=-1;
		//右栈空时top2为MAX;
		top2=MAX;
		this.MAX=MAX;
		data=new Object[MAX];
	}
	
	/**
	 * 入栈
	 * @param element
	 * @param tag 0为左堆栈，1为右堆栈
	 */
	public void push(Object element,int tag){
		if(tag!=0 && tag!=1){
			//为什么抛出Exception需要try catch ，而NullPointerException不需要呢?
			throw new NullPointerException("提供合法的tag!");
		}
		if(!isFull()){
			if(tag==0){
				data[++top1]=element;
			}
			if(tag==1){
				data[--top2]=element;
			}
		}else{
			System.out.println("栈已满！");
		}		
	}
	
	/**
	 * 出栈
	 * @param tag 0为左堆栈，1为右堆栈
	 */
	public Object pop(int tag){
		if(tag!=0 && tag!=1){
			//为什么抛出Exception需要try catch ，而NullPointerException不需要呢?
			throw new NullPointerException("提供合法的tag!");
		}
		if(tag==0){
			if(!isEmpty(tag)){
				return data[top1--];
			}else{
				throw new NullPointerException("栈为空！");
			}
		}else{//tag==1
			if(!isEmpty(tag)){
				return data[top2++];
			}else{
				throw new NullPointerException("栈为空！");
			}
		}		
	}
	
	/**
	 * 判断堆栈是否为空
	 * @param tag 0为左堆栈，1为右堆栈
	 * @return
	 */
	public boolean isEmpty(int tag){
		if(tag!=0 && tag!=1){
			//为什么抛出Exception需要try catch ，而NullPointerException不需要呢?
			throw new NullPointerException("提供合法的tag!");
		}
		if(tag==0){
			if(top1==-1)
				return true;
			else
				return false;
		}else{
			if(top2==MAX)
				return true;
			else
				return false;
		}
		
	}
	
	/**
	 * 判断堆栈是否已满，由于二者公用空间，所以不需要针对每个堆栈判断full
	 * @return
	 */
	public boolean isFull(){
		if(top1+1==top2)
			return true;
		else
			return false;
	}
	
	public void print(){
		for(int i=0;i<=top1;i++){
			if(i==0){
				System.out.println("=======");
			}
			if(i==top1){
				System.out.println("-------");
				System.out.println("*top1->"+data[i]);
			}else{
				System.out.println("-------");
				System.out.println("  "+data[i]);
			}
			
		}
		for(int i=top1+1;i<top2;i++){
			System.out.println("-------");
			System.out.println("  空空      ");
		}
		for(int i=top2;i<MAX;i++){
			if(i==top2){
				System.out.println("-------");
				System.out.println("*top2->"+data[i]);
			}else{
				System.out.println("-------");
				System.out.println("  "+data[i]);
			}
			
		}
		System.out.println("=======");
	}
	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args){
		Array2Stack stacks=new Array2Stack(10);
		
		stacks.push(1, 0);
		stacks.push(2, 0);
		stacks.push(3, 0);
		stacks.push(4, 0);
		stacks.pop(0);
		
		stacks.push(5, 1);
		stacks.push(6, 1);
		stacks.push(7, 1);
		stacks.push(8, 1);
		stacks.pop(1);
		
		System.out.println("============**双堆栈**==================");
		stacks.print();
	}
}
