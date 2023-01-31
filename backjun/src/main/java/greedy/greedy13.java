package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class greedy13 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); //크레인 수
		int[] crain = new int[n];
		int[] cnt = new int[n];

		if (n == 0) {
			System.out.println(-1);
			return;
		}
		StringTokenizer st =new StringTokenizer(br.readLine());
		for(int i = 0 ; i< n ; i ++) {
			crain[i] = Integer.parseInt(st.nextToken());
		}
		//들 수 있는 무게 순으로 정렬
		Arrays.sort(crain);

		int boxCnt = Integer.parseInt(br.readLine()); //화물 수

		st = new StringTokenizer(br.readLine());
		//박스 무게 입력받음
		for(int i = 0 ; i< boxCnt ; i ++) {
			int weight = Integer.parseInt(st.nextToken());
			for(int j = 0 ; j < n ; j++) {
				if( crain[j] >= weight ) { //해당 크레인으로 화물의 무게를 들 수 있다면
					cnt[j]++;
					break; //해당 크레인에 담고 반복종료 다음박스 찾음
				}else {
					if( j == n - 1) {
						System.out.println(-1);
						return;
					}
					continue;
				}
			}
		}
		int max = 0;
		for(int i = 0 ; i < n ; i++) {
			max = Math.max(max, cnt[i]);
		}
		//최대값 찾고 그 이후 인덱스를 n 빵치면 됨!!!
		int sum = 0;
		int sub = 0;
		int index = 0;
		boolean ck = false;
		for(int i = 0 ; i < n ; i++) {
			if( cnt[i] == max ) {
				index = i;
				ck = true; // 최대값이 나왔다면 인덱스를 기억
			}
			//최대값이 나온 이후로 모든 값을 더하고 나눌 값도 계산함
			if( ck  ) {
				sum += cnt[i];
				sub++;
			}
		}
		
		
		
		int result = (int) Math.ceil( ((double)sum / (double)sub));
		
		for(int i = 0 ; i < index ; i++) {
			result = Math.max(result, cnt[i]);
		}
		
		System.out.println( result  );





	}
}
