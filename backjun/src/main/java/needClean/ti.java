package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 테두리 한바퀴 싸이클 돌리고 찾은 열쇠가 있다면 다음 배열에 찾을 열쇠값이 있다고 표시함
 * 다음 연산때 다른 열쇠를 찾은 적이 없다면 종료하고 값이 있다면 다음탐색도 계속 진행함
 * 대문자에서 65를 빼주면 0인덱스부터 시작.. 소문자는 97을 빼주면 0인덱스부터 시작
 */

public class ti {
	static int N,startX;
	static long ans,result;
	static int[] input;
	static int[][] map;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		//연결정보 입력받음
		map = new int[N][N];
		StringTokenizer st = null;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//출발점을 바꿔가며 진행한다.
		ans = 0;
		for(int i = 0 ; i < N ; i++) {
			visit = new boolean[N];
			visit[i] = true;
			result = 0;
			startX = i;
			dp(i,0);
			ans = Math.min(ans, result);
		}
		
		System.out.println(ans);
	}
	//탐색을 시작한다. index는 자신 행의 인덱스를 나타냄
	private static void dp(int index, int cnt) {
		//System.out.println(result + " " + ans + " " + cnt);
		if( ans > 0 && ans < result ) {
			return;
		}
		//다 뽑았다면
		if( cnt >= (N-1) ) {
			//System.out.println("다뽑음");
			//마지막 도시도 출발 도시랑 붙어 있어야한다.
			if( map[index][startX] > 0 ) {
				result += map[index][startX];
				if( ans == 0 ) {
					//System.out.println("ㅇㅇㅇ");
					ans = result;
				}else {
					
					//System.out.println("dfkmasfke");
					ans = Math.min(ans, result);
				}
			}
			return;
		}

		int min = Integer.MAX_VALUE;
		int nextRow = -1;
		//다음 방문할 도시를 선택함 --> 가중치가 가장 낮은놈을 선택함
		for(int i = 0; i < N ; i++) {
			//방문하지 않았다면 + 자기 자신의 인덱스가 아니라면
			if( !visit[i] && index != i ) {
				//자신의 도시와 연결되어있는 도시중에 가장 작은 도시를 선택한다.
				if( map[index][i] != 0 ) {
					if( min > map[index][i] ) {
						min = map[index][i];
						nextRow = i;
					}
				}
			}
		}
		
		if( nextRow == -1 ) {
			result += Integer.MAX_VALUE;
			return;
		}else {
			visit[nextRow] = true;
			result += min;
			dp(nextRow,cnt+1);
		}

	}
}