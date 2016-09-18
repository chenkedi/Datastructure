package netEase;

import java.io.*;
import java.util.*;


public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();//学生数
		int[] capacity = new int[N];
		for(int i=0; i<N; i++){
			capacity[i] = sc.nextInt();
		}
		int K = sc.nextInt();
		int D = sc.nextInt();
		long maxRes =1;
		for(int i=0; i<N; i++){
			int max = capacity[i+D];
			for(int j=i+D; j>=0 && j>=i-D; j--){
				if(capacity[j]>max)
					max = capacity[j];
			}
			
		}
		

	}
	
	
	

//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int N = sc.nextInt();
//		int[] apples = new int[N];
//		int sum = 0;
//		for(int i=0; i<N; i++){
//			int tmp = sc.nextInt();
//			apples[i] = tmp;
//			sum += tmp;
//		}
//		sc.close();
//		if(sum%N!=0){
//			System.out.println(-1);
//		}else{
//			int avg = sum/N;
//			int count = 0;
//			for(int i=0; i<N; i++){
//				int sub = Math.abs(avg-apples[i]);
//				if(sub%2!=0){
//					System.out.println(-1);
//					return;
//				}	
//				count += (sub/2);
//			}
//			System.out.println(count/2);
//		}
//
//	}
}


//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

