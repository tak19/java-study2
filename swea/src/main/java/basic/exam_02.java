package basic;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class exam_02 {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int N,R,C,result;
	static int[][] map;
	static boolean[][] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visit = new boolean[N][N];
			
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if( map[i][j] == 2) {
						R = i;
						C = j;
					}
				}
			}
			result = 0;
			go(R,C,0);
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void go(int r, int c, int cnt) {
		//3번 움직였으면 끛ㅌ
		if( cnt == 3 ) {
			return;
		}
		
		for(int i = 0 ; i < 4 ; i++) {
			int idx = 1;
			boolean first = false;
			boolean second = false;
			while(true) {
				int gox = r + dx[i] * idx;
				int goy = c + dy[i] * idx;
				//이동 범위 안이고 2번재 이상 안넘었다면
				if( canGo(gox,goy) && !second ) {
					//말 등장
					if( map[gox][goy] == 1 ) {
						if( !first ) {
							first = true;
						}else {
							second = true;
							if( !visit[gox][goy] ) {
								visit[gox][goy] = true;
								result++;
							}
							map[gox][goy] = 0;
							go(gox,goy,cnt+1);
							map[gox][goy] = 1;
							break;
						}
					}else {
						//장기말이 아니라면
						if( first && !second ) {
							go(gox,goy,cnt+1);
						}
					}
				}else {
					break;
				}
				idx++;
			}
		}
		
		
	}

	//범위 안인지
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}
}



