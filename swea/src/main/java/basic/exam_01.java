package basic;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class exam_01 {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int N,maxApple,result;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			maxApple = 0;
			result = Integer.MAX_VALUE;
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxApple = Math.max(map[i][j], maxApple);
				}
			}
			//현재 좌표, 방향, 먹을사과 번호
			dfs(0,0,0,0,1);

			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	//과일묵자잉
	private static void dfs(int x, int y, int dir,int changeDirCnt, int eatNum) {
		//System.out.println(x+ " " + y);
		if( eatNum > maxApple ) {
			//System.out.println("마지막 사과 먹음: " + eatNum);
			result = Math.min(result, changeDirCnt);
			return;
		}
		//앞으로 갈 방향
		int gox = x + dx[dir];
		int goy = y + dy[dir];
		
		//꺾으면 먹을 수 있나? or 벽이라면 방향전환
		int spin = (dir + 1) % 4;
		if( possibleEat(x,y,spin,eatNum) || !canGo(gox,goy) ) {
			dfs(x, y, spin, changeDirCnt+1, eatNum);
			return;
		}

		//자신이 먹을 사과라면 먹고 사과 개수 올려줌
		if( map[x][y] == eatNum ) {
			//System.out.println("사과 먹음: " + eatNum);
			dfs(x, y, dir, changeDirCnt, eatNum+1);
			return;
		}else {
			dfs(gox, goy, dir, changeDirCnt, eatNum);
		}

	}
	//현재 방향으로 계속가면 먹을 수 있나?
	private static boolean possibleEat(int x, int y, int dir, int eatNum) {
		int gox = x + dx[dir];
		int goy = y + dy[dir];
		while( canGo(gox,goy) ) {
			if( map[gox][goy] == eatNum ) {
				return true;
			}
			gox += dx[dir];
			goy += dy[dir];
		}
		return false;
	}
	//범위 안인지
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}
}



