package linear.list;

public class Queue<T>{
	int head=0,tail=0;
	int MAX_SIZE;
	Object[] queue;
	
	public Queue(int n){
		queue = new Object[n];
		MAX_SIZE=n;
	}

	/**
	 * 出队
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T deQueue() throws Exception{
		if(head==tail)
			throw new Exception("队列为空！");
		else{
			T element = (T)queue[head];
			head=(head+1)%(MAX_SIZE);
			return element;
		}
	}
	
	/**
	 * 入队
	 */
	public void enQueue(T element) throws Exception{
		if((tail+1)%(MAX_SIZE)==head)
			throw new Exception("队列已满！");
		else{
			queue[tail] = element;
			tail=(tail+1)%(MAX_SIZE);
			
		}
	}

	public boolean isEmpty() {
		return queue.length > 0;
	}
	
	public void print(){
		System.out.print("队首");
		for(int i=head;i<tail;i=(i+1)%(MAX_SIZE)){
			System.out.print("|"+queue[i]);
		}
		System.out.println("|队尾");
	}
	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args){
		Queue<Integer> queue = new Queue<>(10);
		try {
			queue.enQueue(20);
			queue.enQueue(20);
			queue.enQueue(30);
			queue.enQueue(30);
			queue.enQueue(30);
			queue.enQueue(30);
			queue.enQueue(30);
			queue.enQueue(30);
			queue.enQueue(30);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		queue.print();
	
	}	
}