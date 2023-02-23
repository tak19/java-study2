package combination;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class combination_07 {
	private static StringBuilder sb = new StringBuilder();
	static int n,m,d;
	static int[][] map,origin;
	static int[] npC,position;
	static int result,count;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); //n,m,d 순서
		n = Integer.parseInt(st.nextToken()); //row
		m = Integer.parseInt(st.nextToken()); //col
		d = Integer.parseInt(st.nextToken()); //dis

		//지도 입력받아욧!
		map = new int[n][m];
		origin = new int[n][m];
		for(int i = 0; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < m ; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//조합 뽑기 위한 배열
		npC = new int[m];
		int a = 3;
		while(a --> 0) {
			npC[m-a-1] = 1;
		}
		result = 0;
		// 조합 드가자~
		do {
			//궁수 자리 저장하기
			int cnt = 0;
			position = new int[3]; //궁수의 열(row)정보
			for(int i = 0 ; i < m ; i++) {
				if(npC[i] == 1) {
					position[cnt++] = i;
				}
			}
			count = 0;
			coppyArray();
			attack();
			result = Math.max(result, count);
		} while (np(npC));

		System.out.println(result);


	}
	//원본배열 복사함
	private static void coppyArray() {
		for(int i = 0; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				map[i][j] = origin[i][j];
			}
		}
	}
	//궁수의 공격이 시작된다! || 궁수 위치는 ( n+1, 고른값 )
	private static void attack() {
		//적이 남아있는지 확인용
		while( ckMap() ) {

			Pos[] p = new Pos[3];

			//궁수 만큼이용
			for(int trio = 0 ; trio < 3; trio++) {
				int minDis = Integer.MAX_VALUE; //최소거리

				//한명 조종사 만들고 떄려잡음 -- 거리 가까운놈 탐색해유~
				for(int i = 0; i < n ; i++) {
					for(int j = 0 ; j < m ; j++) {
						if(map[i][j] == 1) {
							//위치 계산 -- x끼리,, y랑
							int dis = Math.abs(n - i) + Math.abs(position[trio] - j);
							if( dis <= d && minDis > dis ) { //최소거리 갱신함요
								minDis = dis;
								p[trio] = new Pos(i,j); //해당 궁수의 최소값에 넣어줌
							}if( dis <= d &&  minDis == dis ) { //최소거리가 똑같다면!
								if( p[trio].y > j ) { //원래 있던 적이랑 거리가 똑같으면서 왼쪽에 위치한다면
									minDis = dis;
									p[trio] = new Pos(i,j); //최소위치 갱신데스
								}
							}
						}
					}
				}
			}


			for(int i = 0 ; i < 3 ; i ++) {
				//가까운 적 좌표띠
				if(p[i] != null) {
					int x = p[i].x;
					int y = p[i].y;
					if( map[x][y] == 1) {
						count++;
						map[x][y] = 0; //적 제거
					}
				}
			}
			//제거하고 나면 적이 한칸씩 내리온다
			downEnemy();
		}
	}
	//적 내려욧
	private static void downEnemy() {
		
		//마지막 행은 어차피 다 제거됨 -> 마지막 행 먼저 지워줘야함,, 그래야 반영안됨
		for(int j = 0 ; j < m ; j++) {
			map[n-1][j] = 0;
		}
		//마지막 전까지만 진행 -- 밑에서 부터 올라가면서 탐색해야함!!
		for(int i = n-2; i >= 0 ; i--) {
			for(int j = 0 ; j < m ; j++) {
				//적 발견
				if(map[i][j] == 1) {
					map[i][j] = 0;
					map[i+1][j] = 1;
				}
			}
		}

	}
	//적이 한명이라도 있다면 true
	private static boolean  ckMap() {
		for(int i = 0; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				if(map[i][j] == 1) {
					return true;
				}
			}
		}
		return false;
	}
	private static boolean np(int[] npC2) {
		int n = npC2.length;
		int i = n - 1;
		while(i > 0 && npC2[i-1] >= npC2[i] ) {
			i--;
		}
		if(i==0) {
			return false;
		}

		int j = n - 1;
		while(npC2[j] <= npC2[i-1]) {
			j--;
		}
		swap(npC2,i-1,j);

		int k = n - 1;
		while( k >= i) {
			swap(npC2, i++, k--);
		}

		return true;
	}
	//자리 교환
	private static void swap(int[] npC2, int i, int j) {
		int tem = npC2[i];
		npC2[i] = npC2[j];
		npC2[j] = tem;

	}
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}

