package d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d3_1873 {
	static int r,c;
	static char[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;

		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			Pos tank = null;
			//지도 입력 받아요
			map = new char[r][c];
			for(int i = 0 ; i < r; i++) {
				char[] tem = br.readLine().toCharArray();
				for(int j = 0; j < c; j++) {
					map[i][j] = tem[j];
					if( map[i][j] == '<' || map[i][j] == '^' || map[i][j] == '>' || map[i][j] == 'v') {
						tank = new Pos(i,j,map[i][j]); //탱크 정보 저장
					}
				}
			}
			int len = Integer.parseInt(br.readLine()) - 1;
			char[] order = br.readLine().toCharArray();
			int a = 0;
			//명령 개수만큼
			for(int i = 0 ; i < order.length ; i++) {
				
				switch (order[i]) {
				case 'U': {
					tank.c = '^'; //방향 바꾸고, 이동가능하면 이동함
					map[tank.x][tank.y] = '^';
					if( canR(tank.x - 1)) {
					if( map[tank.x - 1][tank.y] == '.' ) {
						
							map[ tank.x - 1][tank.y] = '^';
							map[tank.x][tank.y] = '.';
							tank.x = tank.x - 1;
							tank.y = tank.y;
						}
					}
					break;
				}case 'D': {
					tank.c = 'v'; //밑으로~
					map[tank.x][tank.y] = 'v';
					if(!canR(tank.x + 1)) {
						break;
					}
					if( map[tank.x + 1][tank.y] == '.' ) {
						map[ tank.x + 1][tank.y] = 'v';
						map[tank.x][tank.y] = '.';
						tank.x = tank.x + 1;
						tank.y = tank.y;
					}
					break;
				}case 'L': {
					tank.c = '<'; //왼쪽으로~
					map[tank.x][tank.y] = '<';
					if(!canC(tank.y-1)) {
						break;
					}
					if( map[tank.x][tank.y-1] == '.' ) {
						map[ tank.x][tank.y - 1] = '<';
						map[tank.x][tank.y] = '.';
						tank.x = tank.x;
						tank.y = tank.y - 1;
					}
					break;
				}case 'R': {
					tank.c = '>'; //오른쪽으로~
					map[tank.x][tank.y] = '>';
					if(!canC(tank.y+1)) {
						break;
					}
					if( map[tank.x][tank.y+1] == '.' ) {
						map[ tank.x][tank.y+1] = '>';
						map[tank.x][tank.y] = '.';
						tank.x = tank.x;
						tank.y = tank.y+1;
					}
					break;
				}case 'S': {
					shot(tank.c,tank.x,tank.y);
					break;
				}
				}
			}
			
			for(int q = 0 ; q < r; q++) {
				for(int w = 0; w < c; w++) {
					sb.append(map[q][w]);
				}
				sb.append("\n");
			}

		}
		System.out.println(sb);
	}
	
	
	private static void shot(char way, int x, int y) {
		switch (way) {
		case '^': { //위로 포탄 쏨
			for(int k = 1 ; k < r ; k++) {
				if(canR(x-k)) {
					if (map[x-k][y] == '*' || map[x-k][y] == '#' ) {
						if(map[x-k][y] == '*') {
							map[x-k][y] = '.';
							
						}
						break; //벽에 닿으면 종료
					}
				}

			}
			break;
		}case 'v': {
			for(int k = 1 ; k < r ; k++) {
				if(canR(x+k)) {
					if (map[x+k][y] == '*' || map[x+k][y] == '#' ) {
						if(map[x+k][y] == '*') {
							map[x+k][y] = '.';
						}
						break; //벽에 닿으면 종료
					}
				}

			}
			break;
		}case '<': {
			for(int k = 1 ; k < c ; k++) {
				if(canC(y-k)) {
					if (map[x][y-k] == '*' || map[x][y-k] == '#' ) {
						if(map[x][y-k] == '*') {
							map[x][y-k] = '.';
						}
						break; //벽에 닿으면 종료
					}
				}

			}
			break;
		}case '>': {
			for(int i = 1 ; i < c ; i++) {
				if(canC(y+i)) {
					if (map[x][y+i] == '*' || map[x][y+i] == '#' ) {
						if(map[x][y+i] == '*') {
							map[x][y+i] = '.';
						}
						break; //벽에 닿으면 종료
					}
				}
				
			}
		}

		}


	}
	//열
	private static boolean canC(int i) {
		if( i >= 0 && i < c) {
			return true;
		}
		return false;
	}
	//행
	private static boolean canR(int i) {
		if( i >= 0 && i < r) {
			return true;
		}
		return false;
	}
	static class Pos{
		int x,y;
		char c;
		public Pos(int x, int y, char c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}

	}
}
