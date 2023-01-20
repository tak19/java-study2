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
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];
		int[][] sum = new int[n][m];

		//토마토 상태 입력 받음 단, -1 즉 토마토가 들어있지 않은 칸은 -1로 똑같이 초기화 했음!!
		Queue<Pos> one = new LinkedList<>();
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < m ; j++) {				
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					one.add(new Pos(i,j)); //1좌표 저장해둠
				}else if(map[i][j] == 0) {
					sum[i][j] = Integer.MAX_VALUE;
				}

			}
		}

		int max = -1;
		//one에서 1의 좌표를 하나씩 불러와서 방문처리 진행
		//설마 원소가 빠지면서 반복이 안도나..?!!!

		int tak = 0;
		boolean[][] visit = new boolean[n][m];
		while( !one.isEmpty() ) {

			Pos p = one.poll();
			int x = p.x;
			int y = p.y;


			//방문 배열을 초기화 시킴

			if(!visit[x][y]) {
				visit[x][y] = true; //방문 표시
				//4방 탐색
				for(int go = 0 ; go < 4 ; go++) {
					int gox = x + dx[go];
					int goy = y + dy[go];
					//범위 내라면...
					if( gox >= 0 && gox < n && goy >= 0 && goy < m) {
						if( !visit[gox][goy] && map[gox][goy] == 0) { //방문한적이 없고 + 벽이(-1)이 아니고!! + 토마토도 아니고
							if( sum[x][y] + 1 <  sum[gox][goy] ) {
								sum[gox][goy] = sum[x][y] + 1; 
								one.add(new Pos(gox,goy));
							}
 
						}
					}
				}
			}

		}

		int cnt = 0;
		for(int i = 0 ; i < n ; i++) {
			for(int j=0; j < m ; j++) {
				max = Math.max(max, sum[i][j]);
				if(max== 2147483647) {
					System.out.println(-1);
					return;
				}
			}
		}
		//탐색 시에 1을 더했으니 출력시 -1함

		System.out.println(max);






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