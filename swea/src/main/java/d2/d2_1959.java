package d2;

import java.util.Scanner;

public class d2_1959 {

	static int max;
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			int[] arr1 = new int[n];
			int[] arr2 = new int[m];
			
			for(int i = 0 ; i < n ; i ++) {
				arr1[i] = sc.nextInt();
			}
			for(int i = 0 ; i < m ; i ++) {
				arr2[i] = sc.nextInt();
			}
			
			max = 0;
			int sub = Math.abs(n-m); //두 n,m의 차(절대값)를 통해 이동해야 하는 횟수를 확인
			if(n > m) {
				cal1(arr1,arr2,sub);
			}else {
				cal2(arr1,arr2,sub);
			}
			System.out.println("#"+ test_case +" " + max);
			
			
		}
	}
	//n이 더 클경우 즉 arr1이 더큰 배열
	private static void cal1(int[] arr1, int[] arr2, int sub) {
		//큰 배열은 차이 만큼 이동할꺼임 -- 반복은 작은 배열의 길이만큼 해야함
		for(int j = 0 ; j <= sub ; j ++) {
			int total=0;
			for(int i = 0 ; i < arr2.length ; i++) {
				total += (arr1[i+j] * arr2[i]);
			}
			max = Math.max(total, max);
		}

	}
	//m이 더 클경우 즉 arr2가 더큰 배열
	private static void cal2(int[] arr1, int[] arr2, int sub) {
		for(int j = 0 ; j <= sub ; j ++) {
			int total=0;
			for(int i = 0 ; i < arr1.length ; i++) {
				total += (arr1[i] * arr2[i+j]);
			}
			max = Math.max(total, max);
		}
	}
}