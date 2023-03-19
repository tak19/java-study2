package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 25크기의 배열 선언하고, 25C7 구해서 조건에 맞는지 판단 + 연결되어 있는지 확인 후 ++
 */
public class ti {
	//상하좌우
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int N,M,K,result;
	static int[][] map,realMap,numCk; 
	static PriorityQueue<Shark> q;
	static Queue<Shark> queue;
	static Shark[] shark;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 지도크기
		M = Integer.parseInt(st.nextToken()); // 상어 수
		K = Integer.parseInt(st.nextToken()); // 냄새 시간

		//지도 입력 받기
		map = new int[N][N]; //상어의 남아있는 수 K 확인용
		numCk = new int[N][N]; //상어 종류 확인용
		realMap = new int[N][N];
		shark = new Shark[M];
		for(int i = 0 ; i < N ; i++){
			Arrays.fill(numCk[i], Integer.MAX_VALUE);
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++){
				realMap[i][j] = Integer.parseInt(st.nextToken());
				//상어가 나타났다! ==> 좌표정보를 저장한다. + 현재 상어 번호
				if( realMap[i][j] > 0 ) {
					shark[realMap[i][j]-1] = new Shark(i, j, realMap[i][j]);
					realMap[i][j] = 0; //맵 초기화
				}
			}
		}
		//번호가 작은 상어부터 꺼냄
		q = new PriorityQueue<Shark>( (o1,o2) -> {
			return o1.num - o2.num;
		});
		queue = new ArrayDeque<>();
		//각 상어마다 초기 방향을 저장함
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			shark[i].dir = (Integer.parseInt(st.nextToken())-1);
			q.offer(shark[i]); //상어를 큐에 넣어준당!
		}

		//상어 별로 방향 우선 순위 입력받음
		for(int i = 0 ; i < M ; i++) {
			for(int j = 0 ; j < 4 ; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0 ; k < 4 ; k++) {
					shark[i].priorityDir[j][k] = (Integer.parseInt(st.nextToken()) -1);
				}
			}
		}

		moveShark();

		System.out.println(result == 1001 ? -1 : result);
	}
	private static void moveShark() {
		result = 0;
		//1000초가 넘는다면 탐색 종료
		while( result++ < 1000 ) {

			//현재 상어 수만큼 이동 시킨다
			next: while( !q.isEmpty() ) {
				Shark s = q.poll();

				realMap[s.x][s.y] = K; //지도에 표시함
				numCk[s.x][s.y] = s.num; //상어 냄시 저장
				//탐색 시작
				for(int k = 0 ; k < 4 ; k++) {
					int realDir = s.priorityDir[s.dir][k];
					int gox = s.x + dx[realDir];
					int goy = s.y + dy[realDir];
					//범위 안인지
					if( canGO(gox,goy) ) {
						//주변에 냄새 없는곳이 있다면
						if( realMap[gox][goy] == 0 ) {
							//목적지에 자기 번호보다 다른 상어가 있다면다른 상어가 있다면!!
							if( numCk[gox][goy] < s.num ) {
								continue next;
							}else {
								//상어의 정보 변경
								s.x = gox;
								s.y = goy;
								s.dir = realDir;
								numCk[gox][goy] = s.num; //상어 냄시 저장
								queue.offer(s);
								continue next;
							}
						}
					}
				}
				//다 봤는데도 갈 곳이 없다면 돌아가야해
				for (int j = 0; j < 4; j++) {
					int realDir = s.priorityDir[s.dir][j];
					int gox = s.x + dx[realDir];
					int goy = s.y + dy[realDir];
					//범위 안이면서 자신의 채취가 있는곳이라면
					if( canGO(gox,goy) && numCk[gox][goy] == s.num ) {
						s.x = gox;
						s.y = goy;
						s.dir = realDir;
						numCk[gox][goy] = s.num; //상어 냄시 저장
						queue.offer(s);
						continue next;
					}
					if( j == 3 ) {
						//s.dir = realDir;
						queue.offer(s);
						continue next;
					}
				}
			}
			copyQ();
			//print(result);
			goSec();
			//1번상어만 남았다면
			if( q.size() == 1 ) {
				break;
			}
		}
	}
	//큐 복사
	private static void copyQ() {
		while( !queue.isEmpty() ) {
			q.offer(queue.poll());
		}
	}
	//1초가 감
	private static void goSec() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if( realMap[i][j] > 0 ) {
					realMap[i][j]--;
					//1을 뺐는데 채취가 사라졌다면 번호를 부여하는 배열에서도 제거해야함 
					if( realMap[i][j] == 0 ) {
						numCk[i][j] = Integer.MAX_VALUE;
					}
				}
			}
		}
	}
	//범위 안인지
	private static boolean canGO(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N) {
			return true;
		}
		return false;
	}
	private static void print(int result2) {
		System.out.println( result2 + "회차 ");

		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print(realMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(" ");
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print(numCk[i][j] + " ");
			}
			System.out.println();
		}

	}
	//상어 클래스
	static class Shark{
		int x,y,num,dir;
		int[][] priorityDir = new int[4][4]; //방향 우선순위 정할꺼야
		//좌표정보만 저장
		public Shark(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
}