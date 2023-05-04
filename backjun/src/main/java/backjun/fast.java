package backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class fast {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		for(int i=0 ; i <t ; i++) {
			String[] s = br.readLine().split(" ");
			sb.append( Integer.parseInt(s[0]) + Integer.parseInt(s[1]) + "\n");
		}
		
 
		System.out.println(sb);
	}

}
