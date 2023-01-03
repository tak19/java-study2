package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class greedy4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		//TeseCase 실행
		for(int i = 0 ; i < t ; i ++) {
			System.out.println(i+"번재 테스트 시작");
			
			int n = sc.nextInt(); // 인원 수
			int[][] arr = new int [n][2];
			
			//사원의 성적 입력받음
			for(int j = 0 ; j < n ; j ++) {
				arr[j][0] = sc.nextInt();
				arr[j][1] = sc.nextInt();
			}
			
			Arrays.sort(arr,(o1, o2) -> {
			    return o1[0]-o2[0]; // 첫번째 숫자 기준 오름차순
			});
			
			int total = 0 ;
			for(int j = 0 ; j < n ; j ++) {
				//각 사람별 기준을 잡고 반복 실행
				int second = arr[j][1];
				int cnt = 1;
				//j인덱스 보다 작은 경우 = 2번째 시험 점수가 나보다 높아야함
				for(int z = 0 ; z < j ; z ++) {
					if(second < arr[z][1]) {
						//System.out.println("2번째 시험점수가 나보다 높음");
						cnt++;
					}
				}
				//j인덱스 보다 큰 경우 = 2번째 시험 점수가 나보다 낮아야함
				for (int z = j; z < n; z++) {
					if(second > arr[z][1]) {
						//System.out.println("2번째 시험점수가 나보다 낮음");
						cnt++;
					}
				}
				total = Math.max(total, cnt);
			}
			System.out.println(total);
			
			
			
			
			
		}
		
	}

}
