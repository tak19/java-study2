package study;

import java.util.Arrays;
import java.util.PriorityQueue;

public class etc_02 {
	static int N,re;
	static int[][] sum;
	public int solution(int[][] land) {
		int answer = 0;
		N = land.length;

		//합을 저장할 DP 배열
		sum = new int[N][4];
		//첫번째 행은 따로 저장 시킴
		sum[0][0] = land[0][0];
		sum[0][1] = land[0][1];
		sum[0][2] = land[0][2];
		sum[0][3] = land[0][3];

		//현재 자신자신의 행,열
		//dfs(1,0,land);
		for(int i = 1 ; i < N; i++){
			sum[i][0] = Math.max(Math.max(sum[i-1][1],sum[i-1][2]),sum[i-1][3]) + land[i][0];
			sum[i][1] = Math.max(Math.max(sum[i-1][0],sum[i-1][2]),sum[i-1][3]) + land[i][1];
			sum[i][2] = Math.max(Math.max(sum[i-1][0],sum[i-1][1]),sum[i-1][3]) + land[i][2];
			sum[i][3] = Math.max(Math.max(sum[i-1][0],sum[i-1][1]),sum[i-1][2]) + land[i][3];
		}
		for(int i = 0 ; i < 4 ; i++){
			if( answer < sum[N-1][i] ){
				answer = sum[N-1][i];
			}
		}  
		return answer;
	} 

	public static void dfs(int row, int col,int[][] land){
		if( row >= N ){
			for(int i = 0 ; i < 4 ; i++){
				if( re < sum[row-1][i] ){
					re = sum[row-1][i];
				}
			}
			return;
		}

		//다음 행 탐색
		if( col >= 4 ){
			dfs(row+1,0,land);
			return;
		}
		int max = -1;
		//행 탐색 가장 큰값 가져갈꺼여
		for(int i = 0 ; i < 4 ; i++){
			//자신의 열은 제외
			if( col == i ){
				continue;
			}
			if( max < sum[row-1][i] ){
				max = sum[row-1][i];
			}
		}
		//합 계산
		sum[row][col] = land[row][col] + max;
		dfs(row, col+1, land);
	}
}