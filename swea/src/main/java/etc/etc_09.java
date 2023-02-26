package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class etc_09 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int[][] map;
	static boolean[][] visit;
	static int n,m,k;
	static Queue<Pos> q;
	static PriorityQueue<Pos> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // Row
			m = Integer.parseInt(st.nextToken()); // Col
			k = Integer.parseInt(st.nextToken()); // Cnt
			
			int center = 175 - (Math.max(n, m) / 2) ; //중앙 인덱스
			// 생명 주기가 큰 순서부터 뺌
			pq = new PriorityQueue<Pos>( (o1,o2) -> {
				return o2.origin - o1.origin;
			});
			q = new ArrayDeque<>();
			
			//지도 입력받기
			map = new int[351][351]; //n,m의 최대 크기가 50이기 때문
			visit = new boolean[351][351]; //n,m의 최대 크기가 50이기 때문
			for(int i = center ; i < center + n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = center ; j < center + m ; j++) {
					int num = Integer.parseInt(st.nextToken());
					if( num != 0 ) { //세포가 있다면
						map[i][j] = num;
						pq.offer(new Pos(i,j,num,num*2));
						visit[i][j] = true;
					}
				}
			}
			bfs();
			sb.append(pq.size()).append("\n");

		}
		System.out.println(sb);

	}
	private static void bfs() {
		
		for(int cnt = 0 ; cnt < k ; cnt++) {
			//모든 세포 한번씩 돌리기
			while( !pq.isEmpty() ) {
				Pos pos = pq.poll();
				pos.cur -= 1; //한주기 돌면 -1
				//cur을 2배로 잡아서 origin이 더 커지면 활성화된다.. (-1씩 감소했기 때문)
				if( pos.origin > pos.cur ) {
					for(int i = 0 ; i < 4 ; i++) {
						int gox = pos.x + dx[i];
						int goy = pos.y + dy[i];
						//갈수 있나염?
						if( canGo(gox,goy) ) {
							if( !visit[gox][goy] ) { //방문한적이 없나염?
								visit[gox][goy] = true;
								q.add(new Pos(gox,goy,pos.origin,pos.origin*2)); //cur은 값이 변하기 때문에 origin으로
							}
						}
					}
				}
				if( pos.cur != 0 ) { //죽은세포가 아니라면
					q.add(new Pos(pos.x,pos.y,pos.origin,pos.cur));
				}
				
			}
			while( !q.isEmpty() ) { //활성 & 비활성 세포 옮기기
				pq.offer(q.poll());
			}
		}
	}
	
	//범위 안에 있는지 판단한다요
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < 351 && goy >= 0 && goy < 351) {
			return true;
		}
		return false;
	}
	static class Pos{
		int x,y,cur,origin;

		public Pos(int x, int y, int origin, int cur) {
			this.x = x;
			this.y = y;
			this.cur = cur;
			this.origin = origin;
		}
		
	}
}
