package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class etc_03 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = new int[6][3]; //6개국 승,무,패
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t = 0 ; t < 4; t ++) {
			st = new StringTokenizer(br.readLine());
			
			boolean ck = true;
			int all = 0;
			for(int i = 0 ; i < 6 ; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken()); //승
				arr[i][1] = Integer.parseInt(st.nextToken()); //무
				arr[i][2] = Integer.parseInt(st.nextToken()); //패
				all = arr[i][0] + arr[i][1] + arr[i][2];
				if(all != 5) {
					ck = false;
				}
			}
			
			//승을 기준으로 오름차순 정렬함 - 승리 수가 같다면 패배로 정렬
			Arrays.sort(arr, (o1,o2)->{
				return  o1[0]!=o2[0] ? o1[0] - o2[0] : o2[2] - o1[2];
			});
			
			//승패 판단 -> 역순으로 방문.. 6-가장 승이많음
			for(int i = 5 ; i >= 0 ; i--) {
				//가장 스
				for(int j = 0 ; j < 6 ; j++) {
					if( j == i ) {
						continue; //자기나라는 제외
					}
					//승리와 패배 맞교환
					if( arr[i][0] > 0 && arr[j][2] > 0 ) { 
						arr[i][0]--;
						arr[j][2]--;
					}
					//무승부도 제거하자!
					if( arr[i][1] > 0 && arr[j][1] > 0 ) {
						arr[i][1]--;
						arr[j][1]--;
					}
				}
				//다 돌았는데 남은 승수나 무승부 수가 있다면 말이앙된다
				if( arr[i][0] + arr[i][1] != 0 ) {
					ck = false;
					break;
				}
			}
			if(ck) {
				for(int j = 0 ; j < 6 ; j++) {
					if( arr[j][2] == 0) {
						continue;
					}else {
						ck = false;
						break;
					}
				}
			}
			if(ck) {
				sb.append(1).append(" ");
			}else {
				sb.append(0).append(" ");
			}
		}
		System.out.println(sb);
		
		
	}
}