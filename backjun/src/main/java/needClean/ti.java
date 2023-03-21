package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class ti {
	//상하좌우
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int N,M,K,result;
	static int[][] realMap,numCk; 
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
		realMap = new int[N][N]; //상어 종류 확인용(상어 인덱스가 저장)
		numCk = new int[N][N]; //상어의 남아있는 냄새 K 확인용 - -1씩 감소
		
		int[][][] map = new int[N][N][2];
		shark = new Shark[M+1];
		for(int i = 0 ; i < N ; i++){
			Arrays.fill(realMap[i], Integer.MAX_VALUE);
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++){
				int input = Integer.parseInt(st.nextToken());
				//상어가 나타났다! ==> 좌표정보를 저장한다. + 현재 상어 번호
				if( input != 0 ) {
					shark[input] = new Shark(i, j, input);
					//shark[input] = new Shark(i, j);
					//numCk[i][j] = K;
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
		for(int i = 1 ; i <= M ; i++) {
			shark[i].dir = (Integer.parseInt(st.nextToken())-1);
		}

		//상어 별로 방향 우선 순위 입력받음
		for(int i = 1 ; i <= M ; i++) {
			for(int j = 0 ; j < 4 ; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0 ; k < 4 ; k++) {
					shark[i].priorityDir[j][k] = (Integer.parseInt(st.nextToken()) -1);
				}
			}
			q.offer(shark[i]); //상어를 큐에 넣어준당!
		}
		
		moveShark();
		System.out.println( result == 1001 ? -1 : result );
		
	}
	//상어
	private static void moveShark() {
		
		result = 0;
		//1000초가 넘는다면 탐색 종료
		while( result <= 1000 ) {
			//1번상어만 남았다면
			if( q.size() == 1 ) {
				break;
			}
			//냄새 뿌리기
			smell();
			copyQ(); //큐 복구
			move(); //상어 이동
			copyQ(); //큐 복사
			goSec(); // 방구 감소
			result++;
		}
		
	}
	
	//냄새 뿌리기
	private static void smell() {
		//남은 상어 자기 위치에 냄새 뿌리기 -> K만큼
		while( !q.isEmpty() ) {
			Shark tem = q.poll();
			numCk[tem.x][tem.y] = K; //냄새저장
			realMap[tem.x][tem.y] =  tem.num; //지도에 상어를 표시한다.
			queue.offer(tem); //임시 배열에 저장
		}
	}
	//큐 복사
	private static void copyQ() {
		while( !queue.isEmpty() ) {
			q.offer(queue.poll());
		}
	}
	
	//상어 이동시켜유
	private static void move() {
		//현재 상어 수만큼 이동 시킨다
		next: while( !q.isEmpty() ) {
			Shark s = q.poll();

			//탐색 시작
			for(int k = 0 ; k < 4 ; k++) {
				int realDir = s.priorityDir[s.dir][k];
				int gox = s.x + dx[realDir];
				int goy = s.y + dy[realDir];
				//범위 안인지
				if( canGO(gox,goy) ) {
					//주변에 냄새 없는곳이 있다면
					if( numCk[gox][goy] == 0 ) {
						//목적지에 자기 번호보다 다른 상어가 있다면다른 상어가 있다면!!
						if( realMap[gox][goy] < s.num ) {
							continue next;
						}else{
							//상어의 정보 변경
							s.x = gox;
							s.y = goy;
							s.dir = realDir;
							realMap[gox][goy] = s.num;
							queue.offer(s);
							continue next;
						}
					}
				}
			}
			//다 봤는데도 갈 곳이 없다면 돌아가야해 -자기 자신으로 돌아감
			for (int j = 0; j < 4; j++) {
				int realDir = s.priorityDir[s.dir][j];
				int gox = s.x + dx[realDir];
				int goy = s.y + dy[realDir];
				//범위 안이면서 자신의 채취가 있는곳이라면
				if( canGO(gox,goy)  ) {
					if( realMap[gox][goy] == s.num ) {
						s.x = gox;
						s.y = goy;
						s.dir = realDir;
						queue.offer(s);
						continue next;
					}
				}
			}
		}
	}

	//1초가 감
	private static void goSec() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if( numCk[i][j] > 0 ) {
					numCk[i][j]--;
					//1을 뺐는데 채취가 사라졌다면 번호를 부여하는 배열에서도 제거해야함 
					if( numCk[i][j] == 0 ) {
						realMap[i][j] = Integer.MAX_VALUE ; //채취가 사라지면 방문 자국도 제거
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