package linear.list;

import java.util.Iterator;

/**
 * 背包类
 * 不能删除集合中元素，且迭代访问集合元素时与元素加入的顺序无关
 * 一个纯粹的包含多个元素的集合类
 * @author chenkedi
 *
 */
public class Bag<Item> implements Iterable<Item>{
	private int N = 0;
	private final int defaultSize = 10;	
	private Item[] a;

	
	@SuppressWarnings("unchecked")
	public Bag(){
		a = (Item[]) new Object[defaultSize];
	}
	@SuppressWarnings("unchecked")
	public Bag(int initCapacity){
		a = (Item[]) new Object[initCapacity];
	}
	
	public boolean isEmpty(){
		return N==0;
	}
	
	public int size(){
		return N;
	}
	
	private void resize(int max){
		@SuppressWarnings("unchecked")
		Item[] temp = (Item[]) new Object[max];
		for(int i = 0; i < N; i++)
			temp[i] = a[i];
		a=temp;		
	}
	
	public void add(Item item){
		if(N==a.length)
			resize(2*N);
		a[N++] = item;
	}
	
	@Override
	public Iterator<Item> iterator(){
		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator<Item>{
		private int n=N;

		@Override
		public boolean hasNext() {
			return n>0;
		}
		
		/**
		 * 通过切换两个return来决定是顺序返回数组元素还是逆序返回
		 */
		@Override
		public Item next() {
			//return a[N-n--];
			return a[--n];
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

	}
}
