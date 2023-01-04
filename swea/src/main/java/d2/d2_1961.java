package d2;

import java.util.Scanner;

public class d2_1961 {

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		StringBuffer sb = new StringBuffer();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			//n x n배열의 크기
            int n = sc.nextInt();
            
            
            int[][] arr = new int[n][n];
            for(int i = 0 ; i < n ; i ++) {
            	for(int j = 0 ; j < n ; j ++) {
            		arr[i][j] = sc.nextInt();
            	}
            }
            
            
            int len = n-1;
            int one = 0 ; //90도
            int two = len ; //180, 270도
            
            sb.append("#" + test_case + "\n");
            
            for(int i = 0 ; i < n ; i ++) {
            	//90도 한 싸이클 진행
            	for(int j = len ; j >= 0 ; j --) {
            		sb.append(arr[j][one]);
            	}
            	sb.append(" ");
            	
            	//180도 한 싸이클 진행
            	for(int j = len ; j >= 0 ; j --) {
            		sb.append(arr[two][j]);
            	}
            	sb.append(" ");
            	//270도 한 싸이클 진행
            	for(int j = 0 ; j <= len ; j ++) {
            		sb.append(arr[j][two]);
            	}
            	sb.append("\n");
            	
            	one++;
            	two--;
            }
            
		}
		System.out.println(sb);
	}
}