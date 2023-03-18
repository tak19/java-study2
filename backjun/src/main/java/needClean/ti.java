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
	static int[][] map,numCk; 
	//static PriorityQueue<Shark> q;
	static Queue<Shark> q;
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
		shark = new Shark[M]; //상어 배열 초기화
		for(int i = 0 ; i < N ; i++){
			Arrays.fill(numCk[i], Integer.MAX_VALUE);
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				//상어가 나타났다! ==> 좌표정보를 저장한다. + 현재 상어 번호
				if( map[i][j] > 0 ) {
					shark[map[i][j]-1] = new Shark(i, j, map[i][j]);
					map[i][j] = 0; //맵 초기화
				}
			}
		}
		//번호가 작은 상어부터 꺼냄
//		q = new PriorityQueue<Shark>( (o1,o2) -> {
//			return o1.num - o2.num;
//		});
		q = new ArrayDeque<>();
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


		System.out.println(result);
	}
	private static void moveShark() {
		//1번상어가 남을때까지
		while( q.size() != 1 ) {
			//100초가 넘는다면 탐색 종료
			if( result > 1000 ) {
				result = -1;
				break;
			}
			//현재 상어 수만큼 이동 시킨다
			int size = q.size();
			for(int i = 0 ; i < size ; i++) {
				Shark s = q.poll();
				for(int k = 0 ; k < 4 ; k++) {
					int realDir = s.priorityDir[s.dir][k];
					int gox = s.x + dx[realDir];
					int goy = s.y + dy[realDir];
					//범위 안인지
					if( canGO(gox,goy) ) {
						//주변에 냄새 없는곳이 있다면
						if( map[gox][goy] == 0 ) {
							//목적지에 자기 번호보다 다른 상어가 있다면다른 상어가 있다면!!
							if( numCk[gox][goy] < s.num ) {
								System.out.println(s.num + " 삭제대상");
								break;
							}
							map[s.x][s.y] = K; //지도에 표시함
							numCk[s.x][s.y] = s.num; //어떤 상어의 체취가 있는지 확인함 
							//상어의 정보 변경
							s.x = gox;
							s.y = goy;
							s.dir = realDir;
							q.offer(s);
							break;
						}
					}
					//다 봤는데도 갈 곳이 없다면 돌아가야해
					if( k == 3 ) {
						for (int j = 0; j < 4; j++) {
							gox = s.x + dx[j];
							goy = s.y + dy[j];
							//범위 안이면서 자신의 채취가 있는곳이라면
							if( canGO(gox,goy) && numCk[gox][goy] == s.num ) {
								map[s.x][s.y] = K; //지도에 표시함
								s.x = gox;
								s.y = goy;
								s.dir = j;
								q.offer(s);
								break;
							}
						}
					}

				}

			}
			print(result);
			goSec();
			
			result++;
			
			if( result == 4 ) {
				break;
			}
		}
	}
	private static void print(int result2) {
		System.out.println( result2 + "회차 ");
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print(map[i][j] + " ");
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
	//1초가 감
	private static void goSec() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if( map[i][j] > 0 ) {
					map[i][j]--;
					//1을 뺐는데 채취가 사라졌다면 번호를 부여하는 배열에서도 제거해야함 
					if( map[i][j] == 0 ) {
						numCk[i][j] = 0;
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