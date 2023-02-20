package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class etc_05 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static char[][] map;
	static int n,m;
	static boolean[][] visit;
	static int result;
	static boolean ck;
	static Queue<Pos> suQue;
	static Queue<Pos> devilQue;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			map = new char[n][m];
			visit = new boolean[n][m];

			suQue = new ArrayDeque<>();
			devilQue = new ArrayDeque<>();

			//지도 입력받음
			for(int i = 0 ; i < n ; i++) {
				char[] c = br.readLine().toCharArray();
				for(int j = 0 ; j < m ; j++) {
					map[i][j] = c[j];
					if(map[i][j] == 'S') { //수연이 위치
						suQue.add(new Pos(i,j));
						map[i][j] = '.'; //수연이 정보 받고 그냥 .으로 바꿈
					}else if(map[i][j] == '*') { //악마의 손아귀 위치
						devilQue.add(new Pos(i,j));
					}
				}
			}
			result = 0;
			ck = false;
			bfs();
			if(ck) {
				sb.append(result).append("\n");
			}else {
				sb.append("GAME OVER").append("\n");
			}

		}
		System.out.println(sb);

	}
	private static void bfs() {
		while( !suQue.isEmpty() ) {
			result++; //시간 증가
			//악마 퍼트림
			int devilSize = devilQue.size();
			for(int i = 0 ; i < devilSize; i++) {

				Pos pos = devilQue.poll(); //악마 한턴 진행한다.
				int x = pos.x;
				int y = pos.y;
				visit[x][y] = true;
				
				for(int go = 0 ; go < 4; go++) {
					int gox = x + dx[go];
					int goy = y + dy[go];
					if( cnaGo(gox,goy) && map[gox][goy] == '.'  ) { //범위안 + 악마가 갈 수 있다면
						if( !visit[gox][goy] ) { //악마 손길 안 닿은곳이라면
							visit[gox][goy] = true;
							map[gox][goy] = '*';
							devilQue.add(new Pos(gox,goy));
						}
					}
				}
			}
			//수연이도 이동하자
			int suSize = suQue.size();
			for(int i = 0 ; i < suSize; i++) {

				Pos pos = suQue.poll(); //악마 한턴 진행한다.
				int x = pos.x;
				int y = pos.y;
				visit[x][y] = true;
				for(int go = 0 ; go < 4; go++) {
					int gox = x + dx[go];
					int goy = y + dy[go];
					if( cnaGo(gox,goy) && map[gox][goy] != '*' && map[gox][goy] != 'X' ) { //범위안 + 돌x + 악마 x
						if( !visit[gox][goy] ) { //악마 손길 안 닿은곳이라면
							if( map[gox][goy] == 'D') { //여신을 만난다면!!
								ck = true;
								break;
							}
							visit[gox][goy] = true;
							suQue.add(new Pos(gox,goy));
						}

					}
				}
			}
			if(ck) {
				break;
			}

		}

	}
	//범위 안에 있는가?
	private static boolean cnaGo(int gox, int goy) {
		if( gox >= 0 && gox < n && goy >=0 && goy < m) {
			return true;
		}
		return false;
	}
	static class Pos{
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
