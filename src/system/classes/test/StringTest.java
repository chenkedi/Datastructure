package system.classes.test;

public class StringTest{
	public static void main(String args[]){
		//字符串常量池的测试
        

//		String str1=new StringBuilder("ja").append("va").toString();
//		System.out.println(str1.intern()==str1);
//		
//		String str2=new StringBuilder("ja").append("va").toString();
//		System.out.println(str2.intern()==str2);
		

		String str1=new String("abc");
		String str2=new String("abc");
		String str3="abc";
		String str4="abc";
		System.out.println(str1==str2);
		System.out.println(str1==str3);
		System.out.println(str2==str3);
		System.out.println("abc".intern()==str3);
		System.out.println(str4==str3);
        
        
        //字符串的运行时与编译时,以及常量池显示调用intern方法的测试
//        String s4 = "abcd";  
//        String s5 = "abcd";  
//        String s6 = (s4 + s5);
//        String s7=s4+s5;
//        //String s7 = s6.intern();
//        s6.intern();
//        String s8 = "abcd" + "abcd";  
////        String s6 = (s4 + s5);
////        String s9 = "ab" + "cdabcd";
//        System.out.println("字符串的运行时与编译时,以及常量池显示调用intern方法的测试");
//        System.out.println("s6==s8:"+(s6 == s8));
////        System.out.println("s6.intern() == s8:"+(s6.intern() == s8));
//        System.out.println("s6.intern()==s6:"+(s6.intern()==s6));
//        System.out.println("s6.intern()==s6:"+(s7==s6));
////        System.out.println("s8.intern()==s8:"+(s8.intern()==s8));
////        System.out.println("s8==s9:"+(s8 == s9)); 
		
		 /*  JAVA编译器对string + 基本类型/常量 是当成常量表达式直接求值来优化的。 
         *  运行期的两个string相加，会产生新的对象的，存储在堆(heap)中 
         */  
        String str6 = "b";  
        String str7 = "a" + str6;  
        String str67 = "ab";  
        System.out.println("str7 = str67 : "+ (str7 == str67));  
        //↑str6为变量，在运行期才会被解析。  
        final String str8 = "b";  
        String str9 = "a" + str8;  
        String str89 = "ab";  
        System.out.println("str9 = str89 : "+ (str9 == str89.intern()));  
        //↑str8为常量变量，编译期会被优化  

	}
}
