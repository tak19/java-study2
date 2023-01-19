package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_04 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];
		int[][] sum = new int[n][m];

		//토마토 상태 입력 받음 단, -1 즉 토마토가 들어있지 않은 칸은 -1로 똑같이 초기화 했음!!
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < m ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					sum[i][j] = -1;
				}
			}
		}
		
		
		//0에서 탐색할꺼임 1을 만날때까지 -- BFS
		for(int i = 0 ; i < n ; i++) {
			for(int j=0; j < m ; j++) {
				if(map[i][j] == 0) {
					//방문 배열을 초기화 시킴
					boolean[][] visit = new boolean[n][m];
					Queue<Pos> q = new LinkedList<>();
					q.add(new Pos(i,j));
					int min = 0; //해당 값에 토마토와 거리를 담아서 sum에 저장하자!!
					while(q.isEmpty()) { //큐가 빌때까지 했는데 1을 만나면 종료문을 눌때없음 요기 두야지
						Pos p = q.poll();
						int x = p.x;
						int y = p.y;
						visit[x][y] = true; //방문 표시
						//4방 탐색
						for(int go = 0 ; go < 4 ; go++) {
							int gox = x + dx[go];
							int goy = y + dy[go];
							//범위 내라면...
							if( gox >= 0 && gox < n && goy >= 0 && goy < m) {
								if( !visit[gox][goy] && map[gox][goy] != -1 ) { //방문한적이 없고 + -1이 아니고!! 
									min += map[gox][goy] + 1;
									
								}
									
							}
							
						}
						
						
					}
					
					
					
				}
			}

		}


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


/*

0을 탐색한다...!!
0을 탐색하면서 1을 만나면 끝냄 --> 해당 일수 + 자기 누적 일수 --> 더해가다가 1을 만나면 해당 누적값을 최소값으로 할당하면 되지않나? static?
누적값 저장 배열에 값이 들어있다면 그 값을 활용함 --> 메모제이션! DP? --> map을 변경하면 원본이 바뀌기 때문에 조심. 다른방법을 찾아
해당 수들의 최대값과 최소값을 구함... 
최대값이 답이 될것이고 최소값이 0이라면 만날수 없는 밭임
-1의 경우 0일 텐데 이는 입력 받을때 -1은 누적값 배열에 -1로 저장함

나머지 0을 탐색하면서 숫자를 만나면 해당 숫자에 자기번호 더하면 시간 단축도 되지않을까??

______________________________________________________________
1에서 시작해서 값을 저장하는게 더 편하지 않을까???
1에서 누적해서 가다 저장하고 다른 1 탐색시 둘 중 최소값을 저장한다면..?




 */
