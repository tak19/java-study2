package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_07 {
	private static StringBuilder sb = new StringBuilder();
	//상우하좌
	static int[] dx = {0,  0, 1, 0, -1};
	static int[] dy = {0, -1, 0, 1, 0};
	static int m,bcnt;
	static int[] aMove,bMove;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			sb.append("#" + test_case + " ");

			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken()); //총 이동시간
			bcnt = Integer.parseInt(st.nextToken()); //배터리 개수
			aMove = new int [m+1];
			bMove = new int [m+1];
			//A의 움직임
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= m ; i++) {
				aMove[i] = Integer.parseInt(st.nextToken());
			}
			//B의 움직임
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= m ; i++) {
				bMove[i] = Integer.parseInt(st.nextToken());
			}

			//BC 정보는 좌표(X, Y), 충전 범위(C), 처리량(P)로 구성
			Pos[] p = new Pos[bcnt];
			for(int i = 0 ; i < bcnt; i++) {
				st = new StringTokenizer(br.readLine());
				p[i] = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			Pos a = new Pos(1,1);
			Pos b = new Pos(10,10);

			int result = 0;
			//이동 횟수만큼
			for(int cnt = 0 ; cnt <= m ; cnt++) {
				//A 움직임 추적
				a.x += + dx[aMove[cnt]];
				a.y += + dy[aMove[cnt]];
				//b 움직임 추적
				b.x += + dx[bMove[cnt]];
				b.y += + dy[bMove[cnt]];
				//System.out.println(a.x + " " +  a.y + " " + b.x + " " + b.y);

				boolean[] visitA = new boolean[bcnt];
				boolean[] visitB = new boolean[bcnt];

				for(int k = 0 ; k < bcnt; k++) {
					//A,B와 배터리 거리
					int disA = Math.abs(p[k].x - a.x) + Math.abs(p[k].y - a.y);
					int disB = Math.abs(p[k].x - b.x) + Math.abs(p[k].y - b.y);

					if( disA <= p[k].c) {
						visitA[k] = true;
					}
					if( disB <= p[k].c) {
						visitB[k] = true;
					}

				}
				int max = 0;
				//각각 방문한 경우의 수를 모두 탐색하며 최대로 충전 가능한 경우를 찾음
				for(int i = 0 ; i < bcnt ; i++) {
					for(int j = 0 ; j < bcnt; j++) {
						int sum = 0;
						//같은곳에 있다면
						if( i==j && visitA[i] && visitB[j] ) {
							sum = p[j].p;
						}else {
							if( visitA[i] ) {
								sum += p[i].p;
							}
							if( visitB[j] ) {
								sum += p[j].p;
							}
						}
						max = Math.max(max, sum);
						
					}
				}
				result += max;



			}
			sb.append(result).append("\n");
		}

		System.out.println(sb);

	}
	//BC 정보는 좌표(X, Y), 충전 범위(C), 처리량(P)로 구성
	static class Pos{
		int x,y,c,p;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Pos(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
}

