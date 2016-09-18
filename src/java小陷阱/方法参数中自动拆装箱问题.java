package java小陷阱;


public class 方法参数中自动拆装箱问题 {
	    public void add(Byte b)
	    {
	        b = b++;
	    }
	    public void test()
	    {
	        Byte a = 127;
	        Byte b = 127;
	        add(++a);
	        System.out.print(a + " ");
	        add(b);
	        System.out.print(b + "");
	    }
	    
	   public static void main(String[] args){
		   方法参数中自动拆装箱问题 obj = new 方法参数中自动拆装箱问题();
		   obj.test();
	   }
}
