package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bruteforce_02 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][2];
		for(int i = 0 ; i < n ; i++) {
			String[] s = br.readLine().split(" ");
			arr[i][0] = Integer.parseInt(s[0]);
			arr[i][1] = Integer.parseInt(s[1]);
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < n ; i++) {
			int total = 0;
			for(int j = 0 ; j < n ; j++) {
				if( arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) { //자기보다 큰 사람이 있으면 덩치 수 더함
					total++;
				}
			}
			sb.append(++total + " ");
		}
		
		System.out.println(sb);
		
		
	}

}
