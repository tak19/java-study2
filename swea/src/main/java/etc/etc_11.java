package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class etc_11 {
	static int D,W,K,result,min;
	static int[] output,theme;
	static boolean[][] map,origin;
	static boolean ck;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); //두께
			W = Integer.parseInt(st.nextToken()); //가로크기
			K = Integer.parseInt(st.nextToken()); //합격기준

			origin = new boolean[D][W];
			map = new boolean[D][W];
			//필름 입력받음
			for(int i = 0 ; i < D ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < W ; j++) {
					if( Integer.parseInt(st.nextToken()) == 0 ) {
						//A 라면 true로 처리해줌
						origin[i][j] = true;
					}
				}
			}

//			result = 0; //바꾼 자리 수다!
			initMap();
//
//			//열을 뽑는 부분집합과 그안에서 순열을 통해 약품을 정함
//			if( !ck() ) {
//				while( !ck() ) {
//					result++;
//					output = new int[result];
//					ck = false;
//					combi(0,0);
//					if( ck ) {
//						break;
//					}
//				}
//			}
//			sb.append(result).append("\n");

			//dfs 풀이방식
			min = Integer.MAX_VALUE;
			ck = false;
			dfs(0,0);
			sb.append(min).append("\n");
	
		}
		System.out.println(sb);

	}
	//분할 정복느낌--> row는 행 번호,, cnt는 약품 사용횟수
	private static void dfs(int row, int cnt) {
		//최소 약품횟수를 초과 했다면 나머지 경우는 필요없음!
		if( cnt > min) {
			return;
		}
		//모든 행을 다 방문했다면
		if( row == D ) {
			for(int j = 0 ; j < W ; j++) {

				int A = 0; //연속된 A 수
				int B = 0; //연속된 B 수

				for(int i = 0 ; i < D ; i++) {
					if( map[i][j] ) {
						//true라면 A층 하나 증가
						A++;
						B=0; //다른 특성은 연속 개수를 초기화 시킴
					}else {
						B++;
						A=0;
					}
					//중간에 A나 B의 성능 기준이 통과 했다면 다음 행 탐색
					if( A == K || B == K ) {
						break;
					}
				}
				//탐색 다 돌았는데 둘다 조건 만족 x라면
				if( A != K && B != K ) {
					return; //조건 실패함
				}
			}
			//조건 만족 시에 min값 갱신
			if( min > cnt) {
				min = cnt;
			}
			return;
		}
		//약품 처리 대상 행 복사해 놓음
		boolean[] tem = map[row].clone();

		dfs(row+1,cnt);

		//대상 행 A로 약품 처리
		Arrays.fill(map[row], false);
		dfs(row+1,cnt+1);

		//대상 행 B로 약품처리
		Arrays.fill(map[row], true);
		dfs(row+1,cnt+1);

		map[row] = tem;

	}
	//약품 처리할 행 뽑음
	private static void combi(int cnt,int start) {
		if( cnt == result ) {
			//System.out.println(Arrays.toString(output));
			theme = new int[result];
			//무슨 약품 뿌릴 지 확인
			pec(0);
			if( ck ) {
				return;
			}
			return;
		}

		//행 방향 만큼 뽑음
		for(int i = start ; i < D ; i++) {
			output[cnt] = i;
			combi(cnt+1, i+1);
		}

	}
	//무슨 약을 뿌릴지 결정
	private static void pec(int cnt) {
		//약 순서 다 뽑음
		if( cnt == result ) {
			initMap();
			updateMap();
			if(ck()) {
				ck = true;
			}
			return;
		}

		for( int i = 0; i < 2 ; i++ ) {
			theme[cnt] = i;
			pec(cnt+1);
		}
	}

	//약품처리
	private static void updateMap() {
		//바꿀 열 만큼
		for(int i = 0 ; i < result ; i++) {
			if( theme[i] == 1 ) {
				process(i,false);
			}else {
				process(i,true);
			}
		}

	}
	private static void process(int col, boolean b) {
		for(int i = 0 ; i < W ; i++) {
			map[output[col]][i] = b;
		}
	}
	//원본 배열 복사
	private static void initMap() {
		for(int i = 0 ; i < D ; i++) {
			for(int j = 0 ; j < W ; j++) {
				map[i][j] = origin[i][j];
			}
		}
	}
	//성능 검사 가능한지 봄
	private static boolean ck() {
		//행 탐색
		for(int j = 0 ; j < W ; j++) {

			int A = 0; //연속된 A 수
			int B = 0; //연속된 B 수

			for(int i = 0 ; i < D ; i++) {
				if( map[i][j] ) {
					//true라면 A층 하나 증가
					A++;
					B=0; //다른 특성은 연속 개수를 초기화 시킴
				}else {
					B++;
					A=0;
				}
				//중간에 A나 B의 성능 기준이 통과 했다면 다음 행 탐색
				if( A == K || B == K ) {
					break;
				}
			}
			//탐색 다 돌았는데 둘다 조건 만족 x라면
			if( A != K && B != K ) {
				return false; //조건 실패함
			}
		}
		return true;
	}
}
