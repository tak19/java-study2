package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 11660 구간 합 구하기 5
public class etc_02 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] one = br.readLine().split(" ");
		
		int n = Integer.parseInt(one[0]) + 1;
		int t = Integer.parseInt(one[1]);
		
	
		int[][] sum = new int[n][n];
		
		for(int i = 1 ; i < n ; i++) {
			String[] s = br.readLine().split(" ");
			for(int j = 1 ; j < n ; j++) {
				sum[i][j] = sum[i-1][j] +sum[i][j-1] -sum[i-1][j-1] + Integer.parseInt(s[j-1]);
			}
		}
		
			
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < t ; i++) {
			String[] coo = br.readLine().split(" ");
			int x1 = Integer.parseInt(coo[0]);
			int y1 = Integer.parseInt(coo[1]);
			int x2 = Integer.parseInt(coo[2]);
			int y2 = Integer.parseInt(coo[3]);
			
			sb.append(sum[x2][y2] -sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1] + "\n");;
			
		}
		
		
		System.out.println(sb);
	}

}
//		for(int i = 1 ; i < n ; i++) {
//			for(int j = 1 ; j < n ; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println(" ");
//		}
//		
		

