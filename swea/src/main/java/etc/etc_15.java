package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_15 {
	static int N,startX,startY,result;
	//시계방향임 -> 우하, 좌하, 좌상, 우상
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	static int[] cafeNum;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			N = Integer.parseInt(br.readLine());
			//지도 정보 입력받음
			map = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = 0;
			cafeNum = new int[101]; // 카페 중복 확인용
			for(int i = 0 ; i < N - 2  ; i++) {
				for(int j = 0 ; j < N - 1 ; j++) {
					//시작 좌표 저장 --> 끝내기 위함
					startX = i;
					startY = j;
					cafeNum[map[i][j]]++; //시작 카페 방문처리
					dfs(i,j,0,1,0);
					cafeNum[map[i][j]]--; //다시 복구		

				}
			}
			sb.append(result == 0 ? -1 : result).append("\n");

		}
		System.out.println(sb);

	}
	//나랑 카페 투어 가자 - 좌표정보와 방향 정보, 들린 카페수와 방향전환 횟수
	private static void dfs(int x, int y, int dir, int cnt, int chageDir) {
		if( chageDir > 3 ) {
			return;
		}
		//시계 방향 탐색
		for(int i = dir ; i <= dir+1 ; i++) {
			int realDir = i % 4; //4로 나눈 나머지로 해서 순환
			//가는 방향
			int gox = x + dx[realDir];
			int goy = y + dy[realDir];
			//시작 방향으로 돌아왔다면
			if( gox == startX && goy == startY ) {
				result = Math.max(result, cnt); //간식수 생신
				return;
			}
			//범위 안이면서 가본적 없는 카페라면 방문함
			if( cango (gox,goy) && cafeNum[map[gox][goy]] == 0 ) {
				cafeNum[map[gox][goy]]++;
				dfs(gox,goy,realDir,cnt+1,dir== realDir ? chageDir : chageDir+1); //실제 가는 방향 최신화 + 방문횟수 증가
				cafeNum[map[gox][goy]]--; //이후 방문은 초기화시킴
			}


		}

	}
	//범위 안인지 확인해요
	private static boolean cango(int i, int j) {
		if( i >= 0 && i < N && j >= 0 && j < N) {
			return true;
		}
		return false;
	}
}
