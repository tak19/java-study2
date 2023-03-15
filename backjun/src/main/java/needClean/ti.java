package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class ti {
	//하,좌,우만 탐색 -- 중복 방지
	static int[] dx = {0,0,1};
	static int[] dy = {1,-1,0};
	static char[][] map;
	static boolean[][] visit;
	static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//지도 입력받기
		map = new char[5][5];
		for(int i = 0 ; i < 5 ; i++) {
			char[] tem = br.readLine().toCharArray();
			map[i] = tem;
		}
		/*
		 * 25크기의 배열 선언하고, 25C7 구해서 조건에 맞는지 판단 + 연결되어 있는지 확인 후 ++
		 */
		
		
		visit = new boolean[5][5];
		//X,Y 좌표, 전체 뽑은 수,다솜파,도연파
		for(int i = 0 ; i < 5 ; i++) {
			for(int j = 0 ; j < 5 ; j++) {
				if( map[i][j] == 'Y' ) {
					//시작점이 도연파라면 방문 처리 후 원복구
					visit[i][j] = true;
					dfs(i,j,1,0,1);
					visit[i][j] = false;
				}else {
					//시작점이 다솜파라면
					visit[i][j] = true;
					dfs(i,j,1,1,0);
					visit[i][j] = false;
				}

			}
		}
		System.out.println(result);
	}
	//탐색하기 -- dfs로 안되는 문제인가..
	private static void dfs(int x, int y, int allCnt, int som, int yean) {
		//도연파가 3명 이상이면 조건 x -> (이다솜파 4명이상)
		if( yean > 3 ) {
			return;
		}
		
		//칠공주 결성 완료
		if( allCnt >= 7 ) {
			System.out.println(x + " " + y);
			result++;
			return;
		}

		//3방탐색한다
		for(int i = 0 ; i < 3 ; i++) {
			int gox = x + dx[i];
			int goy = y + dy[i];
			//범위 안이면서 방문한 이력이 없다면
			if( canGo(gox,goy) && !visit[gox][goy] ) {
				if( map[gox][goy] == 'Y' ) {
					//도연파라면
					visit[gox][goy] = true;
					dfs(gox,goy,allCnt+1,som,yean+1);
					visit[gox][goy] = false;
				}else {
					//다솜파라면
					visit[gox][goy] = true;
					dfs(gox,goy,allCnt+1,som+1,yean);
					visit[gox][goy] = false;
				}
			}
		}


	}

	//범위안인지 판단
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < 5 && goy >= 0 && goy < 5 ) {
			return true;
		}
		return false;
	}
}

