package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class etc_01 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		//index를 그대로 쓰기위해 배열 크기 +1 
		int[] sum = new int[Integer.parseInt(s[0]) + 1];
		int t = Integer.parseInt(s[1]);
		
		//숫자배열 입력받음
		String[] arrs = br.readLine().split(" ");
		for(int i = 1 ; i < sum.length; i++) {
			sum[i] = sum[i-1] + Integer.parseInt(arrs[i-1]);
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < t ; i++) {
			String[] range = br.readLine().split(" ");
			int start = Integer.parseInt(range[0]);
			int end =  Integer.parseInt(range[1]);
			sb.append(sum[end] - sum[start-1]   + " ");
			
		}
		System.out.println(sb + "\n");
	}

}
