package java小陷阱;


public class AutoUnbox {
	    public void add(Byte b) {
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
		   AutoUnbox obj = new AutoUnbox();
		   obj.test();
	   }
}
