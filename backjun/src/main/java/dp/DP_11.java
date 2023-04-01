package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_11 {
	static int N,result;
	static int[][] map,dp;
	static int INF = 987_654_321;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		//연결정보 입력받음
		map = new int[N][N];
		dp = new int[N][(1<<N)-1]; //비트마스킹을 통한 모든 도시 방문체크 위해
		StringTokenizer st = null;
		for(int i = 0 ; i < N ; i++) {
			Arrays.fill(dp[i],-1);
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println( travel(0,1) );
		
	}
	//순회 시작함 -- 자신의 번호와 방문한 리스트의 비트 마스킹 정보를 매개변수로 전달
	private static int travel(int index, int visitList) {
		//모든 도시를 방문했다면
		if( visitList == ( (1<<N) -1) ) {
			//만약 출발지로 돌아오는 경로가 없다면 큰값을 갱신하여 리스트에서 제외시킴
			if( map[index][0] == 0 ) {
				return INF;
			}
			return map[index][0];
		}
		//이전에 계산한 값이 있다면 해당 최소값을 리턴해줌
		if( dp[index][visitList] != -1 ) {
			return dp[index][visitList];
		}
		
		dp[index][visitList] = INF;
		
		for(int i = 0 ; i < N ; i++) {
			//예전에 방문한적이 있거나 연결되어 있지않은 경우 방문대상에서 제외시킴
			if( map[index][i] == 0 || (visitList & ( 1 << i )) > 0 ) {
				continue;
			}
			//현재 인덱스의 남은 경우의 수와 이전까지의 합 + 현재가중치의 합 중의 작은 경우를 선택함
			dp[index][visitList] = Math.min(dp[index][visitList], travel(i, (visitList | (1 << i)) ) + map[index][i]);
		}
		return dp[index][visitList];
	}
}