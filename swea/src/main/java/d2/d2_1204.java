package d2;

import java.util.Scanner;

public class d2_1204 {

	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		StringBuffer sb = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + sc.nextInt() + " ");
			
			int[] arr = new int[101]; //0~100점 까지 저장하기 위한 배열 크기
			for(int i = 0 ; i < 1000; i ++) {
				arr[sc.nextInt()]++; //입력받은 점수가 나온다면 해당 인덱스의 배열 +1
			}
			//배열을 돌면서 최대값 출력
			int max = 0;
			for(int i = 0 ; i < 101; i ++) {
				if( max <= arr[i]) {
					max = arr[i];
				}
			}
			//최대값에 해당하는 가장 큰수 확인
			for(int i = 100 ; i >= 0; i --) {
				if( max == arr[i]) {
					sb.append(i + "\n");
					break;
				}
			}
			
		}
		System.out.println(sb);
	}
}