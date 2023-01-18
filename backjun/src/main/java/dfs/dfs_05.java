package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dfs_05 {
	static int[] dx = { 0, 0, -1, 1};
	static int[] dy = { 1, -1, 0, 0};
	static int[][] arr;
	static boolean[][] visit;
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];

		// 높이를 입력 받았지 - 1 <= 높이 <= 100
		int min = 101;
		int max = 0;
		for (int i = 0; i < n; i++) {
			String[] info = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(info[j]);
				if (min > arr[i][j]) {
					min = arr[i][j];
				}
				if (max < arr[i][j]) {
					max = arr[i][j];
				}

			}
		}
		int maxArea = 1;
		//물높이가 min에서 올라간다! 최대 높이보다 커지면 종료함!
		while(min <= max) {
			int area = 0; //안전 영역
			visit = new boolean[n][n]; //방문 초기화함
			update(min); //물높이에 따른 잠긴 위치 초기화

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(!visit[i][j]) { //잠기거나 방문하지 않았다면!!
						dfs(i,j);
						area++;
					}
				}
			}
			maxArea = Math.max(maxArea, area);
			min++;
		}
		System.out.println(maxArea);



	}
	private static void dfs(int i, int j) {
		if(visit[i][j]) { //잠겼거나 방문했다면 나옴
			return;
		}else {
			visit[i][j] = true;
			for(int go = 0; go < 4; go++) {
				int nx = i + dx[go];
				int ny = j + dy[go];
				//범위내 이동가능해야해!
				if( nx >= 0 && nx < n && ny >= 0 && ny < n ) {
					if( !visit[nx][ny]  ) {
						dfs(nx,ny);
					}
				}
				
			}
			
		}
		
		
		
	}
	//높이에 따라 잠긴 부분 초기화
	public static void update(int high) {
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if( arr[i][j] <= high) {
					visit[i][j] = true;
				}
			}
		}
	}


}












