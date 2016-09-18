package leetcode;

import java.util.Scanner;

/**
 * 百度4月份2016年实习生笔试第二题
 * 将R23C27这种十进制表示法与Excel中的AA23数字字母表示法相互转换
 * @author chenkedi
 * 输入：
2
R23C27
AA23
 * 输出：
 * AA23
 * R23C27
 *
 */
public class Excel26{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int t=sc.nextInt();
		String[] input = new String[t];
		for(int i=0;i<t;i++)
			input[i]=sc.next();
		
		for(int i=0;i<t;i++){
			//根据输入的字符串判断是何种输入类型.第一个字符是R，且第二个字符是数字的，为十进制输入类型
			if(input[i].charAt(0)=='R' && input[i].charAt(1)<65){
				int index = input[i].indexOf('C');
				int row = Integer.valueOf(input[i].substring(1, index));
				int column =Integer.valueOf(input[i].substring(index+1));
				System.out.println(int2Str(column)+row);
			}else{//否者是字母表示列号的26进制方式
				char[] strArray = input[i].toCharArray();
				int index=0;
				//通过遍历求出字母序列的最后一位的索引。由于是不满足条件才推出循环，所以index会指向字母序列后的第一个数字
				for(int j=0;j<strArray.length;j++){
					if(!(strArray[j]>='A' && strArray[j]<='Z')){
						index=j;
						break;
					}
						
				}
				int row = Integer.valueOf(input[i].substring(index));
				String column = input[i].substring(0, index);
				System.out.println("R"+row+"C"+str2Int(column));
			}
		}
		sc.close();
		
	}
    
	/**
	 * 将一个10进制的数字转换为用字母A~Z表示的26进制字母序列
	 * 如27=》AA
	 * @param column
	 * @return
	 */
	public static String int2Str(int column){
		StringBuilder builder = new StringBuilder();
        while(column>0){
           builder.append((char)(--column%26+'A'));
           column/=26;
        }
        return builder.reverse().toString();
        //还可以使用单条语句递归
        //return column==0?"":int2Str(--column/26)+(char)(column%26+'A');
	}
	
	/**
	 * 将26进制的字母序列转换为10进制数
	 * 如：AA=》27
	 * @param str
	 * @return
	 */
    public static int str2Int(String str){
    	//常规解法，逆向遍历字符串，注意先相加
//        char[] cs = str.toCharArray(); 
//        int res = 0;
//        for(int i = cs.length-1;i>=0;i--){
//        	res+=(cs[i]-'A'+1)*Math.pow(26,cs.length-1-i);
//        }
//    	  return res;
    	
    	 //正向遍历字符串，注意先相乘
		char[] cs = str.toCharArray();
		int res = 0;
		for(int i=0;i<cs.length;i++)
		    res=res*26+(cs[i]-'A'+1);
		return res;
        
        
        
        //递归解法，正向递归遍历字符串
        //return str.length()==0?0:(str.charAt(str.length()-1)-'A'-1)+26*str2Int(str.substring(0,str.length()-1));
    }
}
