package huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();
		char[] inputSplit = input.toCharArray();
		StringBuilder out = new StringBuilder();
		char last = inputSplit[0];
		int value = 1;
		for(int i=1; i<inputSplit.length; i++){
			if(inputSplit[i] == last){
				value++;
			}else{
				out.append(String.valueOf(last)+value);
				last = inputSplit[i];
				value = 1;
			}
		}

		System.out.println(out.append(String.valueOf(last)+value));
	}
	

}
