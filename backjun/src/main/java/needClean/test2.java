package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class test2 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T --> 0) {
			String s = br.readLine(); 
			sb.append(s.charAt(0)).append(s.charAt(s.length()-1)).append("\n");
		}
		System.out.println(sb);
	}
}




