package bfs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_31 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int[][] arr;
	static boolean[][] visit;
	static int r,c,allCnt,cnt,result;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 행과 열 그리고 쓰레기 개수입력받음
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		allCnt = Integer.parseInt(st.nextToken());
		arr = new int[r][c];
		//쓰레기 위치 입력받음
		for(int i = 0 ; i < allCnt; i++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		
		//탐색
		for(int i = 0 ; i < r ; i++) {
			for(int j = 0 ; j < c ; j++) {
				cnt = 0;
				if( arr[i][j] == 1 ) {
					food(i,j);
				}
				result = Math.max(result,cnt);
			}
		}

		System.out.println(result);
	}
	//인접 쓰레기 찾기
	private static void food(int x, int y) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(x,y));
		arr[x][y] = 0;
		//주변 탐색
		while( !q.isEmpty() ) {
			Pos pos = q.poll();
			cnt += 1;
			for(int i = 0 ; i < 4 ; i++) {
				int gox = pos.x + dx[i];
				int goy = pos.y + dy[i];
				//범위 안인지 체크
				if( canGo(gox,goy) ) {
					//쓰레기여야함
					if( arr[gox][goy] == 1 ) {
						arr[gox][goy] = 0;
						q.offer(new Pos(gox,goy));
					}
				}
			}
		}
		
	}
	//범위체크
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < r && goy >= 0 && goy < c ) {
			return true;
		}
		return false;
	}
	//좌표정보저장
	static class Pos{
		int x,y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}