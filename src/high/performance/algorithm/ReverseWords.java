package high.performance.algorithm;


public class ReverseWords{
 public static void main(String[] args){
	 String a="You are a student";
	 String b="Fuck you!";
	 System.out.println(reverseStr(a));
	 System.out.println(reverseStr(b));
 }

 public static String reverseStr (String str){
	 str=reverse(str);
	 System.out.println(str);
	 String[] strArray = str.split(" ");
	 str="";
	 for(int i=0;i<strArray.length;i++){
		 str+=reverse(strArray[i]);
		 if(i!=strArray.length)
			 str+=" ";
	 }
	 return str;
	 
 }
 
private static String reverse(String str){
	char[] a=str.toCharArray();
	int n=a.length-1;
	for(int i=0;i<=n/2;i++){
		char temp = a[i];
		a[i]=a[n-i];
		a[n-i]=temp;
	}
	return String.valueOf(a);
}

}
