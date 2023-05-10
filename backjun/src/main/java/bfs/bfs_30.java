package bfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
//9,876,543,210
public class bfs_30 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int N,K,T,cnt;
	static int[][] map;
	static PriorityQueue<Pos> pq;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//지도 크기
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); //바이러스 번호
		map = new int[N][N];
		//낮은 종류의 바이러스 먼저 증식하게끔 PQ선언
		pq = new PriorityQueue<Pos>((o1,o2) -> {
			return o1.num - o2.num;
		});

		//지도 입력받기
		for(int i = 0 ; i < N ; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//바이러스가 있다면
				if(map[i][j] > 0) {
					cnt++;
					pq.offer(new Pos(i,j,map[i][j]));
				}
			}
		}
		if( cnt == 0 ) {
			System.out.println(0);
		}else {


			st = new StringTokenizer(br.readLine());
			//시간과 궁금한 좌표 입력받음
			T = Integer.parseInt(st.nextToken());
			int rX = Integer.parseInt(st.nextToken());
			int rY = Integer.parseInt(st.nextToken());
			int maxCnt = (N+1)*(N+1);

			Queue<Pos> q = new ArrayDeque<>();
			//해당시간만큼 실행
			for(int time = 0 ; time < T ; time++) {
				//세균증식을 시간안에 맞췄다면
				if( cnt == maxCnt ) {
					break;
				}
				int size = pq.size();
				for(int i = 0; i < size ; i++) {
					Pos pos = pq.poll();
					int x = pos.x;
					int y = pos.y;
					//4방 탐색
					for(int go = 0 ; go < 4 ; go++) {
						int gox = x + dx[go];
						int goy = y + dy[go];
						//범위 안인지 체크
						if( canGo(gox,goy) ) {
							//세균증식이 안된곳이라면 증식 함
							if( map[gox][goy] == 0 ) {
								//증식 표시해주고 큐에 값 넣음
								map[gox][goy] = map[x][y];
								q.offer(new Pos(gox,goy,map[x][y]));
								cnt++; //세균 크기 증가 시키기
							}
						}
					}
				}
				//큐를 다시 PQ에 넣어줌
				while( !q.isEmpty() ) {
					pq.offer(q.poll());
				}
			}
			System.out.println(map[rX-1][rY-1]);
		}
	}
	// 범위 안인지
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}
	//좌표정보 저장
	static class Pos{
		int x,y,num;

		public Pos(int x, int y,int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}

	}
}