package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_18 {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
	
		char[][] map = new char[R][C]; //지도 저장 배열
		Queue<Pos> gosum = new LinkedList();
		Queue<Pos> water = new LinkedList();
		
		boolean[][] visit = new boolean[R][C]; //물의 방문배열
		boolean[][] visitGosum = new boolean[R][C]; //고슴도치의 방문배열
		// 지도 정보 입력 --> 수집 정보: 고슴도치와 물의 위치
		for(int i = 0 ; i < R ; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j = 0 ; j < C ; j++) {
				map[i][j] = c[j];
				if( c[j] == 'S') {
					gosum.add(new Pos(i,j)); //고슴도치 위치정보 저장
					visitGosum[i][j] = true;
				}else if(c[j] == '*') {
					water.add(new Pos(i,j)); //물 위치저장
					visit[i][j] = true;
				}
			}
		}
		
		
		int time = 0;
		
		//BFS 탐색시작-> 고슴도치가 이동 불가할때까지 반복
		//각 큐의 사이즈를 미리 입력받고 그만큼만 반복하면 됨
		//for문 안에서 사이즈 돌리면 바뀌기 때문
		while( !gosum.isEmpty() ) {
			int size = water.size();
			//물 한턴 이동
			for(int i  = 0; i < size ; i++) {
				Pos pos = water.poll();
				int x = pos.x;
				int y = pos.y;
				
				for(int go = 0 ; go < 4 ; go++) {
					int gox = x + dx[go];
					int goy = y + dy[go];
					
					if( gox >= 0 && gox < R && goy >=0 && goy < C && map[gox][goy] != 'X') {// 범위 안이면서 벽이 아니라면
						if( map[gox][goy] != 'D' && !visit[gox][goy]) { //돌이 아니면서 비버집 아니면
							water.add(new Pos(gox,goy));
							visit[gox][goy] = true;
						}
					}
					
				}
			}
			size = gosum.size();
			//물 한턴 이동
			for(int i  = 0; i < size ; i++) {
				Pos pos = gosum.poll();
				int x = pos.x;
				int y = pos.y;
				if( map[x][y] == 'D') { //비버집 발견하면 종료
					System.out.println(time);
					return;
				}
				
				for(int go = 0 ; go < 4 ; go++) {
					int gox = x + dx[go];
					int goy = y + dy[go];
					
					if( gox >= 0 && gox < R && goy >=0 && goy < C && map[gox][goy] != 'X') {// 범위 안이면서 벽이 아니라면
						if( !visitGosum[gox][goy] && !visit[gox][goy]) { //물이 아니고 고슴도치가 가본적 없으면
							gosum.add(new Pos(gox,goy));
							visitGosum[gox][goy] = true;
						}
					}
					
				}
				
			}
			time += 1; //한턴 종료 후 시간 추가
			
			
		}
		System.out.println("KAKTUS");
		
		
	}
	static class Pos{
		int x;
		int y;
		
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
