package simul;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 테트르모노가 모둑 4개로 구성됨
 * 방향전환은 4번을 넘지않음 + 4칸안에 가는 모든 경우에 해당함
 * 단, ㅗ 모양은 예외처리가 필요해보임
 */
public class simul_20 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int[][] anoX = { {0,0,1,0}, {0,-1,-1,-2}, {0, 0,-1, 0}, {0,-1,-1,-2} };
	static int[][] anoY = { {0,1,1,2}, {0, 0,-1, 0}, {0,-1,-1,-2}, {0, 0, 1, 0} };
	static int[][] map;
	static boolean[][] visit;
	static int N,M,result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		//행과 열 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//지도 입력받기
		map = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit = new boolean[N][M]; //방문 배열 초기화ㅏ
		//탐색시작
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				//자신 방문처리후 해당 경우 탐색이 끝나다면 다음 탐색을 위해 방문처리 초기화
				//해당 경우는 'ㅗ' 모양을 제외한 나머지 4가지 경우를 검사함
				visit[i][j] = true;
				dfs(i,j,map[i][j],1);
				visit[i][j] = false;
				//'ㅗ' 모양 점검
				plusShape(i,j);
			}
		}
		System.out.println(result);

	}
	private static void plusShape(int r, int c) {
		//4가지 도형
		for(int i = 0 ; i < 4 ; i++) {
			boolean ck = false;
			int sum = 0;
			//방향에 따른 도형검사
			for(int j = 0 ; j < 4 ; j++) {
				int gox = r + anoX[i][j];
				int goy = c + anoY[i][j];
				//범위 안인지
				if( canGo(gox,goy) ) {
					sum += map[gox][goy];
				}else {
					ck = true;
					break;
				}
			}
			//다 방문했다면 값 체크
			if( !ck ) {
				result = Math.max(result, sum);
			}
		}
	}
	//팀색 시작
	private static void dfs(int r, int c, int point, int cnt){
		//탐색이 끝났다면 - point 계산함
		if( cnt >= 4 ) {
			result = Math.max(result, point);
			return;
		}
		//4방 탐색
		for(int i = 0 ; i < 4 ; i++) {
			int gox = r + dx[i];
			int goy = c + dy[i];
			//범위 안이고 방문하지 않은 곳이라면
			if( canGo(gox,goy) && !visit[gox][goy] ) {
				//해당 경우 방문하고 점수와 이동횟수 더하고 다음 탐색시작
				visit[gox][goy] = true;
				dfs(gox,goy,point+map[gox][goy],cnt+1);
				visit[gox][goy] = false;
			}
		}
		
		
		
	}
	//범위 안인지 체크
	private static boolean canGo(int gox, int goy) {
		if( gox < N && gox >= 0 && goy >= 0 && goy < M ) {
			return true;
		}
		return false;
	}
}