package combination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class combination_04 {
	private static StringBuilder sb = new StringBuilder();
	static int[] arr;

	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		//순열의 경우의 수 계산 - 총 20 까지 있음 - 받은 n만큼만 계산
		long[] pec = new long[n+1];
		pec[0] = 1;
		for( int i = 1; i <= n ; i++ ) {
			pec[i] = i * pec[i-1];
		}
		boolean[] isSelect = new boolean[n+1]; 
		arr = new int[n];

		//문제 입력 받아욧
		st = new StringTokenizer(br.readLine());
		int what = Integer.parseInt(st.nextToken()); //원하는 유형의 문제
		long cnt = 0; //20! 이기때문에 long으로 값 받음


		if( what == 1) { //순서 물어보나?
			cnt = Long.parseLong(st.nextToken()); //물어보는 순열의 순서

			for(int i = 0 ; i < n ; i++) {
				for(int j = 1 ; j <= n ; j++) {
					if(isSelect[j]) {
						continue;
					}
					//자릿수 계산함, 팩토리얼 계산값보다 크면 해당 자리수는 아니기때문에 넘어감
					if( cnt > pec[n-i-1] ) {
						cnt -= pec[n-i-1];
					}else {//팩토리얼 계산보다 작다면 해당 자리수에 도착한것임
						arr[i] = j;
						isSelect[j] = true;
						break;
					}

				}
			}
			for(int i = 0 ; i < n ; i++) {
				sb.append(arr[i]).append(" ");
			}


		}else {// 순열 차례 물어보나?
			for(int i = 0 ; i < n ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			//차례를 계산한다
			for(int i = 0 ; i < n ; i++) {
				for(int j = 1 ; j < arr[i] ; j++) { //해당 자리수만큼 순열 수를 계산한다.
					if(isSelect[j]) {
						continue;
					}
					cnt += pec[n-1-i];
				}
				isSelect[arr[i]] = true;
			}
			sb.append(cnt + 1);

		}



		System.out.println(sb);

	}
}
