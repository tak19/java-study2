package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class etc_10 {
	//우,좌,상,하
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n,cnt,maxPro,result;
	static int[][] map;
	static ArrayList<Pos> coreList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			n = Integer.parseInt(br.readLine()); //지도 크기
			coreList = new ArrayList<>();
			map = new int[n][n];
			//지도 정보 저장 -- 중복 순열말고 dfs 사방 탐색으로 하자
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < n ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if( i > 0 && i < n-1 && j > 0 && j < n-1 && map[i][j] == 1) {
						coreList.add(new Pos(i,j));
					}
				}
			}
			maxPro = 0; //최대 연결된 프로세스 수
			result = Integer.MAX_VALUE ; //결과값
			//visit = new boolean[n][n];
			dfs(0,0,0);

			sb.append(result).append("\n");

		}
		System.out.println(sb);

	}

	//전선깔기 - maxPro에 최대 코어 수 저장되어 있음.. coreList - index <= maxPro - 백트래킹
	private static void dfs(int index, int cnt, int len) {

		if( index == coreList.size() ) {

			//최대 연결 프로세스라면
			if( cnt > maxPro ) {
				maxPro = cnt;
				result = len;

			}else if( cnt == maxPro ) {
				result = Math.min(result, len);

			}
			return;
		}
		//최대 코어에 근접할 수 있는가?
		if ( cnt + ( coreList.size() - index ) >= maxPro ) {
			
			int x = coreList.get(index).x;
			int y = coreList.get(index).y;

			for(int dir = 0 ; dir < 4 ; dir++) {

				int gox = x;
				int goy = y;
				int originX = x;
				int originY = y;
				int count = 0;

				while(true ) {
					gox += dx[dir];
					goy += dy[dir];

					if( canGo(gox,goy) ) {
						//전선 연결이 불가능하다면
						if( map[gox][goy] == 1) {
							count = 0;
							break;
						}
						count++;
					}else {
						break;
					}
				}


				for(int i = 0 ; i < count ; i++) {
					originX += dx[dir];
					originY += dy[dir];
					map[originX][originY] = 1;
				}

				//연결 불가 프로세서 - 다음 프로세서 탐색
				if( count == 0 ) {
					dfs(index+1,cnt,len);
				}else {
					//연결 가능 --> 다음 코어, 코어랑 전선 길이 증가
					dfs(index+1,cnt+1,len+count);
					//다음 탐색을 위해 전선 깐거 철수
					originX = x;
					originY = y;
					for(int i = 0 ; i < count ; i++) {

						originX += dx[dir];
						originY += dy[dir];
						map[originX][originY] = 0;
					}

				}

			}
			
		}else {
			//나머지를 실행하더라고 최대코어 수에 안된다면 종료
			return;
		}

	}

	//범위 안인지
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < n && goy >= 0 && goy < n) {
			return true;
		}
		return false;
	}
	//좌표정보
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
