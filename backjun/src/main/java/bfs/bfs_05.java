package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_05 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//M x N --> 왼쪽 기준으로 0,0 이다...
		int M = Integer.parseInt(st.nextToken()); 
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		boolean[][] arr = new boolean[M][N];
		
		//k개 방문
		for(int i = 0 ; i < K ; i++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			
//			//좌표를 오름차순으로 정렬하자!
//			if(x1 > x2) { //x1이 더 크다면 x1에 더 작은 값 할당
//				int temp = x1;
//				x1 = x2;
//				x2 = temp;
//			}
//			if(y1 > y2) { //y1이 더 크다면 y1에 더 작은 값 할당
//				int temp = y1;
//				y1 = y2;
//				y2 = temp;
//			}
			
			for(int row = x1; row < x2; row++) {
				for(int col = y1; col < y2; col++) {
					arr[row][col] = true;
				}
			}
			
		}
		int area = 0;
		int size=0;
		
		
		List<Integer> list = new ArrayList<>();
		for(int i = 0 ; i < M ; i++) {
			for(int j = 0 ; j < N ; j++) {
				//BFS시작
				if(!arr[i][j]) {
					//System.out.println("i: " + i +" j: " +  j);
					size = 0;
					area++; //지역 개수 증가 시켜줌
					Queue<Pos> q = new LinkedList<>();
					q.add(new Pos(i,j,1));
					while( !q.isEmpty() ) {
						Pos p = q.poll();
						int x = p.x;
						int y = p.y;
						size += 1;
						arr[x][y] = true; //해당 배열 방문 체크함
						for(int go = 0 ; go < 4; go++) {
							int nx = x + dx[go];
							int ny = y + dy[go];
							if( nx >= 0 && nx < M && ny >= 0 && ny < N ) {
								if( !arr[nx][ny] ) {
									q.add(new Pos(nx,ny,size));
									arr[nx][ny] = true;
								}
							}
							
						}
					}
					list.add(size);
					
				}
			}
		}
		Collections.sort(list);
		System.out.println(area);
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		
		
		
		
	}
	static class Pos{
		int x;
		int y;
		int cnt;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
		Pos(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}

















