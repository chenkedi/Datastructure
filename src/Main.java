import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        System.out.print(centerExpand(str));
        
        in.close();
    }
    //使用中心扩展法，时间较短，Manercher算法风险太大
	private static int centerExpand(String str) {
		if(str.length()==0)
			return 0;
		int longest=0;
		int candidate=0;
		char[] strArray=str.toCharArray();
		int n=strArray.length;
		
		//开始枚举中心位置，同时向两边展开，看两边的字符串是否相等
		for(int i=0;i<n;i++){

			//对于以i为中心，长度为奇数的回文字符串进行扫描
			for(int j=0;(i-j)>=0 && (i+j)<n;j++){
				if(strArray[i-j]!=strArray[i+j])
					break;
				candidate=2*j+1;
			}
			
			if(candidate>longest){
				longest=candidate;		
			}
			
			//对于以i为中心，长度为偶数的的回文字符串进行扫描
			for(int j=0;(i-j)>=0 && (i+j+1)<n;j++){
				if(strArray[i-j]!=strArray[i+j+1])
					break;
				candidate=2*j+2;
			}
			if(candidate>longest){
				longest=candidate;
			}			
		}
		return longest;
		
	}

}