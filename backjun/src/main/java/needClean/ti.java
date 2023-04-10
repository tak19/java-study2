package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class ti {
	//상우하좌
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int N,M,K;
	static boolean[][] visit;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // map size
		M = Integer.parseInt(st.nextToken()); // player Su
		K = Integer.parseInt(st.nextToken()); // round
		
		//지도 입력받기
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
	}

	//범위 안인지
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}
	
	//좌표정보 - 위치, 기본능력치, 방향, 권총 저장
	static class Pos{
		int x,y,basic,dir,him;

		public Pos(int x, int y, int basic, int dir, int him) {
			this.x = x;
			this.y = y;
			this.basic = basic;
			this.dir = dir;
			this.him = him;
		}

		
		
	}
}