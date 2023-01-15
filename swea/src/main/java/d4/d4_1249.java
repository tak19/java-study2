package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class d4_1249 {
	static int[][] arr; //도로 상황 저장
	static int[][] visit; //누적값저장
	static int n; //배열 크기
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		StringBuilder  sb = new StringBuilder();
		for(int test = 1 ; test <= t ; test++) {
			sb.append("#" + test + " ");
			n = Integer.parseInt(br.readLine());
			
			arr = new int[n][n];
			visit = new int[n][n];
			for(int i = 0 ; i < n ; i++) {
				char[] c = br.readLine().toCharArray();
				for(int j = 0 ; j < n ; j++) {
					arr[i][j] = Character.getNumericValue(c[j]);
					visit[i][j] = Integer.MAX_VALUE; //누적값 중 최소값을 찾아야하기 때문에 Integer 최대값으로 초기화
				}
			}
			visit[0][0] = arr[0][0];
			search();
			sb.append(visit[n-1][n-1] + "\n");
			
		}
		System.out.println(sb);
	}

	private static void search() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {0,0}); //0,0에서 출발함...
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			//상하좌우 이동
			for(int i = 0 ; i <4 ; i++) {
				int gox = now[0] + dx[i];
				int goy = now[1] + dy[i];
				
				//이동이 가능한지 확인
				if( gox >= 0 && gox < n && goy >= 0 && goy < n  ) {
					//도착지 누적값이 현재누적 + 이동값 보다 크다면 누적값 변경함
					if( visit[gox][goy] > visit[now[0]][now[1]] + arr[gox][goy] ) {
						visit[gox][goy] = visit[now[0]][now[1]] + arr[gox][goy];
						q.offer(new int[] {gox,goy}); //이동 위치를 큐에 넣음
					}
				}
				
			}
		}
		
	}

	
	
	

}
