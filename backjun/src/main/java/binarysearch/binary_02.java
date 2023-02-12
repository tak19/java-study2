package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class binary_02 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //수열 크기
		int blue = Integer.parseInt(st.nextToken()); //블루레이 개수

		int start = 0;
		int end = 0;
		//비디오 입력받음
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if( start < arr[i] ) { //최대값 인덱스 저장해둠
				start = arr[i];
			}
			end = end + arr[i]; //초기 입력값의 전체 합 저장함
		}
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int sum = 0;
			int count = 0;
			//중간값에 얼마나 저장할 수 있는지 확인
			for(int i = 0; i < n ; i++) {
				if(sum + arr[i] > mid) { //현재 누적값을 저장 못한다면!
					count++; //블루레이 갯수 증가시키고 누적합 초기화
					sum = 0;
				}
				sum += arr[i];
			}
			//마지막 블루레이 개수 증가
			if(sum != 0) {
				count++;
			}
			//필요 개수가 더 크다면 --> 시작 인덱스 증가 범뮈 넓힘
			if(count > blue) {
				start = mid +1;
			}else { //저장가능하면 범위를 더 좁힘
				end = mid - 1;
			}

		}
		System.out.println(start);


	}
}
