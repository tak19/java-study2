package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class greedy4 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());
		
		StringBuffer sb = new StringBuffer();
		//TeseCase 실행
		for(int i = 0 ; i < t ; i ++) {
			//System.out.println(i+"번재 테스트 시작");
			
			int n = Integer.parseInt(bf.readLine()); // 인원 수
			int[][] arr = new int [n][2];
			
			//사원의 성적 입력받음
			for(int j = 0 ; j < n ; j ++) {
				String[] s = bf.readLine().split(" ");
				arr[j][0] = Integer.parseInt(s[0]);
				arr[j][1] = Integer.parseInt(s[1]);
			}
			
			Arrays.sort(arr,(o1, o2) -> {
			    return o1[0]-o2[0]; // 첫번째 숫자 기준 오름차순
			});
			
			int total = 1 ;
			//첫번째 사람의 두번째 시험점수
			int min = arr[0][1];
			
			for(int j = 1 ; j < n ; j ++) {
				//각 사람별 기준을 잡고 반복 실행
				if( min > arr[j][1]) {
					total++;
					min = arr[j][1];;
				}
				
				
			}
			sb.append(total + "\n");
			
		}
		System.out.println(sb);
	}

}
