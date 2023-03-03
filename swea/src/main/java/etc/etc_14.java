package etc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class etc_14 {
	static int N,M,C,plus,need,maxX,maxY;
	static int[][] map,sumMap;
	static boolean[] visit;
	static int[] output,sumPart,mulPart;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			sb.append("#" + test_case + " ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //지도크기
			M = Integer.parseInt(st.nextToken()); //통 수 
			C = Integer.parseInt(st.nextToken()); //최대 양 - 크기

			//지도 입력받음 - n*n
			map = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sumMap = new int[N][N-M+1]; //최대값만 저장함
			output = new int[M]; //통 수 만큼 뽑음
			sumPart = new int[N]; //N행만큼의 합이 나옴
			mulPart = new int[N];
			plus = 0;
			need = 0;
			//꿀통 전까지만 --> 조합별 최대값 배열을 만들어서 겹치지 않는 최대값 2부분을 더하면 정답
			for(int i = 0 ; i <= N - M ; i++) {
				visit = new boolean[N+1];
				subSet(0);
				plus++;
			}
			
			for(int i = 0 ; i < N ; i++) {
				System.out.println(Arrays.toString(sumMap[i]));
			}
			
			int need2 = 0;
			//값 출력하깅
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N-M+1 ; j++) {
					//최대값이랑 붙어있으면 제외함
					if( maxX == i && Math.abs( maxY - j ) <= (M - 1)  ) {
						continue;
					}
					need2 = Math.max(need2, sumMap[i][j]);
				}
			}
			sb.append(need+need2).append("\n");
			
		}
		
		System.out.println(sb);

	}
	//뽑는거
	private static void subSet(int cnt) {
		if( cnt == M ) {
			initSumPart();
			//뽑힌 횟수만큼 반복
			for(int i = 0 ; i < M ; i++) {
				//방문한적이 있다면, 행의 모든 부분들의 누적합을 구함
				//전체 행 방문 후 나머지 부분집합 탐색
				if( visit[i]) {
					for(int k = 0 ; k < N ; k++ ) {
						sumPart[k] += map[k][i+plus]; //뽑힌거 합 구함
						mulPart[k] += ( map[k][i+plus] * map[k][i+plus]); //제곱 수 더함

						if( sumPart[k] > C ) {
							mulPart[k] = 0;
							sumPart[k] =0;
						}
					}
				}
			}
	
			//전체합에 최대값만을 저장함
			for(int i = 0 ; i < N ; i++) {
				sumMap[i][plus] = Math.max(sumMap[i][plus], mulPart[i]);
				//최대값 좌표 정보 저장
				if( need < sumMap[i][plus] ) {
					need = sumMap[i][plus];
					maxX = i;
					maxY = plus;
				}
				
			}

			return;
		}
		
		//부분집합 구한다
		visit[cnt] = true;
		subSet(cnt+1);
		visit[cnt] = false;
		subSet(cnt+1);
		
	}
	private static void initSumPart() {
		mulPart = new int[N];
		sumPart = new int[N];
	}

}
