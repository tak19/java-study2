package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Floyd_04 {
	static int N,M;
	static int[][] distance;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		//StringTokenizer st = null;
		
		//도시 수와 관계 수를 입력받음
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//친구 관계배열 초기화
		distance = new int[N+1][N+1];
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				distance[i][j] = 500;
			}
		}
		
		//친구 관계 입력받음 - 해당 경우는 관계가 1인 경우임
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			distance[a][b] = 1;
			distance[b][a] = 1;
			
		}
		
		//플로이드 알고리즘 수행
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				for(int k = 1 ; k <= N ; k++) {
					if(  distance[j][k] > distance[j][i] + distance[i][k] ) {
						distance[j][k] = distance[j][i] + distance[i][k];
					}
				}
			}
		}
		
		int minRelation = Integer.MAX_VALUE;
		int minIndex = 1;
		for(int i = 1 ; i <= N ; i++) {
			int relationValue = 0;
			//관계수 다 더하기
			for(int j = 1 ; j <= N ; j++) {
				relationValue += distance[i][j];
			}
			//최소값보다 작다면 해당값 초기화함
			if( minRelation > relationValue ) {
				minRelation = relationValue;
				minIndex = i;
			}
		}
		System.out.println(minIndex);

	}
}