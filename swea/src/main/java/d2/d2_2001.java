package d2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d2_2001 {
	static int[][] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for(int test = 1; test <= T ; test++) {
			sb.append("#" + test + " ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); //지도 크기
			int m = Integer.parseInt(st.nextToken()); //파리채 크기

			//파리 지도 입력받았어 -> index 그대로 활용
			arr = new int[n+1][n+1];
			for(int i = 1 ; i <= n ; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1 ; j <= n ; j++){
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//반복횟수--> (n-m+1)^2
			int re =  (n-m+1) * (n-m+1) ;
			//시작 좌표값
			int x = 1;
			int y = 1;
			int result = 0; //결과값
			
			
			while( re != 0 ) {
				//인덱스 벗어 난다면
				if(  m+y > n+1 ) { //y인덱스 범위 만나면 밑으로 한칸 내리고 다시 탐색함
					x += 1;
					y = 1;
				}
				//m*m 범위 전체 합 더함 --> 파리 수 겠지?
				int sum = 0;
				for(int i = x ; i < m+x ; i++) {
					for(int j = y ; j < m+y ; j++) {
						sum += arr[i][j];
					}
				}
				//결과 최대값으로 바꾸고, y증가 반복횟수 감소
				result = Math.max(result, sum);
				re--;
				y++;
			}
			sb.append(result).append("\n");

		}
		System.out.println(sb);
	}
}

