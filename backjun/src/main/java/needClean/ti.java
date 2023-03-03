package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class ti {
	//사방탐색이요
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int R,C,T,cleanRow;
	static int[][] map,origin;
	static boolean[][] visit;
	static Queue<Pos> q = new ArrayDeque<>();
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // Row 
		C = Integer.parseInt(st.nextToken()); // Col
		T = Integer.parseInt(st.nextToken()); // Time

		//지도크기와 정보 입력받기
		map = new int[R][C];
		origin = new int[R][C];
		for(int i = 0 ; i < R ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < C ; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
				//공기 청정기라면
				if ( origin[i][j] == -1 ) {
					cleanRow = i; //공기청정기 위치 저장
				}else {
					q.offer(new Pos(i,j)); //먼지 저장할께
				}
			}
		}
		//확산합니데이
		while(T --> 0) {
			updateMap();
			diffusion();
			//print();
			windReverseClockwise(cleanRow);
			windClockwise(cleanRow);
			//print();
			ckdust();
			originUpdate();

		}
		int result = 0;
		while( !q.isEmpty() ) {
			Pos p = q.poll();
			result += map[p.x][p.y];
		}

		System.out.println(result);


	}
	private static void originUpdate() {
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				origin[i][j] = map[i][j]; 
			}
		}
	}
	//지도 복사함
	private static void updateMap() {
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				map[i][j] = origin[i][j];
			}
		}
	}
	private static void print() {
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("++++++++++++++++++++++++++++++++");
		System.out.println();
	}
	//먼지 다시 큐에 넣어줌
	private static void ckdust() {
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				if(map[i][j] > 0 ) {
					q.offer(new Pos(i,j));
				}
			}
		}
	}
	//공기 청정 On - 반시계방향 -- 좌상우하 순
	//빼둘지 말고 청정기 칸을 0으로 바꾸자!
	private static void windReverseClockwise(int CRow) {
		int temp = map[0][0]; //하나 빼둠
		//좌로 이동
		for(int i = 0 ; i < C - 1 ; i++) {
			map[0][i] = map[0][i+1];
		}
		//상으로 이동 -- 행이 바뀜
		for(int i = 0 ; i < CRow -1 ; i++) {
			map[i][C-1] = map[i+1][C-1]; 
		}
		//우
		for(int i = C - 1 ; i > 0 ; i--) {
			map[CRow-1][i] = map[CRow-1][i-1];
		}
		//하 - 청정기랑 만남
		for(int i = CRow - 2 ; i > 0 ; i--) {
			map[i][0] = map[i-1][0]; 
		}
		map[CRow-1][0] = -1; //돌리고 청정기 쪽은 -1로 초기화함!
		map[CRow-1][1] = 0; //청정기에서 나온거
		map[1][0] = temp; //빼둔거 저장함

	}
	//공기 청정 On - 시계방향 -- 상좌하우 순
	private static void windClockwise(int CRow) {
		int temp = map[R-1][0]; //좌하단

		//상으로 이동
		for(int i = CRow - 1 ; i < R - 1 ; i++) {
			map[i][0] = map[i+1][0]; 
		}
		//좌로 이동
		for(int i = 0 ; i < C - 1 ; i++) {
			map[R-1][i] = map[R-1][i+1];
		}
		//하 - 청정기랑 만남 (열 고정)
		for(int i = R - 1 ; i > CRow - 1 ; i--) {
			map[i][C-1] = map[i-1][C-1]; 
		}
		//우 (행 고정)
		for(int i = C - 1 ; i > 0 ; i--) {
			map[CRow][i] = map[CRow][i-1];
		}

		map[CRow][0] = -1; //돌리고 청정기 쪽은 -1로 초기화함!
		map[CRow][1] = 0; //청정기에서 나온거
		map[R-2][0] = temp; //빼둔거 저장함

	}

	//미세친구 확산
	private static void diffusion() {
		int size = q.size();
		//미세먼지 만큼 반복할꺼임
		while( size --> 0 ) {

			Pos pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			int diffusionSize = 0; //확산크기
			//주변 공간에 확산함
			for(int i = 0 ; i < 4 ;i++) {
				int gox = x + dx[i];
				int goy = y + dy[i];
				//범위 안이면서 공기 청정기가 없다면~~
				if( canGo(gox,goy) && map[gox][goy] != -1 ) {
					diffusionSize++; 
					map[gox][goy] += (origin[x][y] / 5); //확산미세먼지 더함
				}
			}
			map[x][y] -= ((origin[x][y] / 5) * diffusionSize); //확산된거 빼줌

		}

	}
	//범위 안인지 체크해유~
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < R && goy >= 0 && goy < C) {
			return true;
		}
		return false;
	}

	// 좌표 정보
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
