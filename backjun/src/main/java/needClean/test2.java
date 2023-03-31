package needClean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 3차원 맵을 써야할것 같음
 * 열쇠를 하나 찾으면 해당 열쇠를 가지고 있는지 여부에 따라 다음 차원 방문 여부 확인함
 * 
 */
public class test2 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int R,C,result;
	static char[][] map;
	static boolean[][][] visit;
	static int[][] key;
	static ArrayList<int[]> List;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		int startX = 0;
		int startY = 0;

		List = new ArrayList<>();
		//지도 입력받기
		map = new char[R][C];
		for(int i = 0 ; i < R ; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j = 0 ; j < C; j++) {
				map[i][j] = c[j];
				if( map[i][j] == '0' ) {
					//현재 위치 저장용
					startX = i;
					startY = j;
					map[i][j] = '.'; //자기 자리 빈칸으로 변경한다요
				}else if( map[i][j] == 1 ) {
					//요가 바로 그다! 달!!
					List.add( new int[] {i,j} );
				}
			}
		}
		//오빠랑 달보러 가자
		bfs(startX,startY);
		result = result == 0 ? -1 : result;
		System.out.println(result);
	}
	private static void bfs(int startX, int startY) {
		//System.out.println("dddd  " + startX + " " + startY);
		Queue<Pos> q = new ArrayDeque();
		q.offer(new Pos(startX,startY,0,0));

		//알파벳을 찾은 수에 따른 차원 변경 필요
		visit = new boolean[R][C][26];
		key = new int[26][26]; //키를 가지고 있는지 여부를 확인해유

		//찾은 열쇠가 있다면 탐색을 계속 진행한다.
		while( !q.isEmpty() ) {
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			if( map[x][y] == '1' ) {
				//System.out.println("찾았나염?");
				result = p.dis;
				break;//바로 중단해도 상관없겠지?
			}
			int alpa = p.alpa;
			visit[x][y][alpa] = true;
			System.out.println(x + " " + y + " " + alpa);
			
			for(int i = 0; i < 4; i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				//갈수 있나염? = 범위 안이면서 벽인지 판단함
				if( canGo(gox,goy) && map[gox][goy] != '#' ) {
					char now = map[gox][goy]; //현재 자리는 뭐지?
					//방문한적이 없는 곳이라믄!! - 키다
					if( !visit[gox][goy][alpa] ) {
						if( ckLower( now ) ) {
							visit[gox][goy][alpa] = true;
							//열쇠만나면 해당 열쇠를 가지고 있나 판단하고 차원 늘려줌
							if( !ckKey( now , alpa) ) {
								key[++alpa][now -97]++; //열쇠 찾으면 다음 차원으로 이동함
								p.alpa++;
							}
							visit[gox][goy][alpa] = true;
							//방문처리하고 큐에 넣는다.

							q.offer(new Pos(gox,goy,p.alpa,p.dis+1));

						}else if( ckUpper(now)) {
							System.out.println(now + " 발견");
							//문이라면 -> 열쇠있는 확인하고 있다면 계속 탐색
							visit[gox][goy][alpa] = true;
							if( ck( now , alpa) ) {
								visit[gox][goy][alpa] = true;
								q.offer(new Pos(gox,goy,alpa,p.dis+1));
							}
						}else {
							//그냥 벽이라면 방문처리하고 계속 탐색
							visit[gox][goy][alpa] = true;
							q.offer(new Pos(gox,goy,alpa,p.dis+1));
						}
					}

				}


			}

		}

	}
	//차원에 키가 있는지 없는지 판단함
	private static boolean ckKey(char now, int alpa) {
		//키가 있다면 true 반환함
		for(int i = 0 ; i <= alpa ; i++) {
			if( key[i][now - 97] > 0 ) {
				return true;
			}
		}
		return false;
	}
	//키 있나염?
	private static boolean ck(char door, int alpa) {
		for(int i = 0 ; i <= alpa ; i++) {
			if( key[i][door - 65] > 0 ) {
				return true;
			}
		}
		return false;
	}
	//대문자인지 확인한다.
	private static boolean ckUpper(char d) {
		if( 'A' <= d && d <= 'Z' ) {
			return true;
		}
		return false;
	}
	//소문자인지 확인한다.
	private static boolean ckLower(char d) {
		if( 'a' <= d && d <= 'z' ) {
			return true;
		}
		return false;
	}
	//범위 안인지 확인
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < R && goy >= 0 && goy < C ) {
			return true;
		}
		return false;
	}
	//좌표저장
	static class Pos{
		int x,y,alpa,dis;

		public Pos(int x, int y, int alpa,int dis) {
			this.x = x;
			this.y = y;
			this.alpa = alpa;
			this.dis = dis;
		}

	}
}

