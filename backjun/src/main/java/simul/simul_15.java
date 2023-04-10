package simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class simul_15 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int N,M,result;
	static char[][] map,subMap;
	static Queue<Pos> q;
	static ArrayList<char[][]> mapList = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken()); //반복 시간
		//지도 입력받음
		map = new char[N][M];
		subMap = new char[N][M];
		for(int i = 0 ; i < N ; i++) {
			char [] arr = br.readLine().toCharArray();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = arr[j];
			}
		}

		q = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();

		//1이면 그대로 출력
		if( T == 1 ) {
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < M ; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}else {
			//1초 후
			mapList.add(copy());
			
			//2조 후
			ckBoom();
			readyBoom();
			mapList.add(copy());
			
			//3초후
			playBoom();
			mapList.add(copy());
			
			//4초후
			ckBoom();
			readyBoom();
			mapList.add(copy());
			
			//5초 후
			playBoom();
			mapList.add(copy());

			char[][] re = new char[N][M];
			if (T == 1) {
				re = mapList.get(0);
			}
			// 시간이 2, 4, 6, 8, 10
			else if (T % 2 == 0) {
				re = mapList.get(1);
			}
			// 시간이 3, 7, 11, 15 ...
			else if (T % 4 == 3) {
				re = mapList.get(2);
			}
			// 시간이 5, 9 ...
			else if (T % 4 == 1) {
				re = mapList.get(4);
			}

			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < M ; j++) {
					sb.append(re[i][j]);
				}
				sb.append("\n");
			}


		}

		System.out.println(sb);
	}
	private static char[][] copy() {
		char[][] tempMap = new char[N][M];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				tempMap[i][j] = map[i][j];
			}
		}

		return tempMap;
	}

	private static void readyBoom() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = 'O';
			}
		}
	}

	//폭탄 위치 저장한다!!
	private static void ckBoom() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if( map[i][j] == 'O' ) {
					q.offer(new Pos(i,j));
				}
			}
		}
	}

	//폭탄 트춘다
	private static void playBoom() {
		while( !q.isEmpty() ) {
			Pos pos = q.poll();
			map[pos.x][pos.y] = '.';
			for(int i = 0 ; i < 4 ; i++) {
				int gox = pos.x + dx[i];
				int goy = pos.y + dy[i];
				//범위 안인교? 그럼 바꾼다잉
				if( canGo(gox,goy)) {
					map[gox][goy] = '.';
				}
			}
		}
	}


	//범위 안인가요?
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < M ) {
			return true;
		}
		return false;
	}
	//범위 내인지 판단
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}