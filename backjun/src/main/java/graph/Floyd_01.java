package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 플로이드 와샬 알고리즘 적용함
 * 
 */
public class Floyd_01 {
	static int[][] D;
	static int INF = 9999999;
	static int N;
	static String result;
	static boolean[] visit;
	static Pos[] pos;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); //테케 수
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = null;
		for(int test = 0 ; test < T ; test++) {
			N = Integer.parseInt(br.readLine()) + 2; //편의점 수

			//각 좌표를 입력받음			
			pos = new Pos[N];
			int x = 0;
			int y = 0;
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				pos[i]  = new Pos(x,y);
			}
			D = new int[N][N];
			//각 지점별로 거리를 측정함
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					D[i][j] =  Math.abs(pos[i].x - pos[j].x) + Math.abs(pos[i].y - pos[j].y);
					if( D[i][j] > 1000 ) {
						D[i][j] = INF;
					}
				}
			}
			//경출도
			for(int k = 0 ; k < N ; k++) {
				for(int i = 0 ; i < N ; i++) {
					
					if( k == i ) {
						continue;
					}
					for(int j = 0 ; j < N ; j++) {
						if( k == j || i == j ) {
							continue;
						}
						if( D[i][j] > D[i][k] + D[k][j] ) {
							D[i][j] = D[i][k] + D[k][j];
							
						}
					}
				}
			}
			//print();
			
			sb.append(D[0][N-1] == INF ? "sad" : "happy").append("\n");
		}
		System.out.println(sb);
	}

	private static void print() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print(D[i][j] + "\t");
			}				
			System.out.println();
		}
	}
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}