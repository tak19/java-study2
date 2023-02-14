package simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class simul_01 {
	static int[][] arr;
	static int n,m;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); //행
		m = Integer.parseInt(st.nextToken()); //열
		int r = Integer.parseInt(st.nextToken()); //화전

		//배열 입력받음
		arr = new int[n][m];
		
		for(int i = 0; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < m ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//돌려야 할 사각형 개수
		int rectCnt = Math.min(n, m) / 2;
		
		for(int k = 0 ; k < r ; k++) {
			//사각형 개수만큼
			for(int j = 0 ; j < rectCnt; j++) {
				int startX = 0 + j;
				int startY = 0 + j;
				
				int endX = n -1 -j;
				int endY = m -1 -j;
				//좌측상단 값 빼둠
				int tem = arr[startX][startY];
				// 윗변 옮기기
				for(int i = startY ; i < endY ; i++) {
					arr[startX][i] = arr[startX][i+1];
				}
				//오른쪽
				for(int i = startX ; i < endX ; i++) {
					arr[i][endY] = arr[i+1][endY];
				}
				//아래
				for(int i = endY ; i > startY ; i--) {
					arr[endX][i] = arr[endX][i-1];
				}
				//왼쪽
				for(int i = endX ; i > startX ; i--) {
					arr[i][startY] = arr[i-1][startY];
				}
				arr[startX + 1][startY] = tem;
						
			}
		
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

}


