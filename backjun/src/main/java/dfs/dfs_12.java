package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class dfs_12 {
	//우 하 좌 상 순
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int N,appleCnt,dirCnt,index,result;
	static int[] changeCnt;
	static char[] changeDir;
	static List<Pos> list = new ArrayList<>();
	
	static boolean[][] map,visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //map 크기
		appleCnt = Integer.parseInt(br.readLine()); //사과개수 크기
		
		map = new boolean[N][N];
		visit = new boolean[N][N];
		StringTokenizer st = null;
		for(int i = 1 ; i <= appleCnt ; i++) {
			st = new StringTokenizer(br.readLine());
			int appX = Integer.parseInt(st.nextToken());
			int appY = Integer.parseInt(st.nextToken());
			//사과있으면 true
			map[appX-1][appY-1] = true; 
		}
		
		dirCnt = Integer.parseInt(br.readLine()); //방향 횟수
		changeCnt = new int[dirCnt];
		changeDir = new char[dirCnt];
		//방향전환 입력받기
		for(int i = 0 ; i < dirCnt ; i++) {
			st = new StringTokenizer(br.readLine());
			int appCnt = Integer.parseInt(st.nextToken());
			char appD = st.nextToken().charAt(0);
			
			changeCnt[i] = appCnt;
			changeDir[i] = appD;
		}
		list.add(new Pos(0,0)); //첫번째 좌표 저장
		//x,y 좌표와 횟수와 크기
		dfs(0,0,0,1);

	}
	//뱀 출발
	private static void dfs(int x, int y, int dir, int len) {
		//방향전화 해야할 차례라면..
		if( index < dirCnt && result == changeCnt[index] ) {
			if( changeDir[index] == 'D' ) {
				//오른쪽회전
				dir = ( (dir +1) % 4 );
			}
			else {
				//왼쪽회전 -> 
				if( dir == 0 ) {
					dir = 3;
				}else {
					dir -= 1;
				}
			}
			index++; //다음 방향 유무 탐색
		}
		
		int gox = x + dx[dir];
		int goy = y + dy[dir];
		//범위에 벗어나거나 꼬리랑 닿으면
		if( !canGo(gox,goy) || !ckTail(len,gox,goy) ) {
			System.out.println(result+1); //처음 길이 +1 진행
			return;
		}
		
		result++; //이동 회수 증가
		list.add(new Pos(gox,goy));
		if( map[gox][goy] ) {
			//사곽 있다면 길이를 1 증가시킴
			map[gox][goy] = false; //먹은사과 제거
			dfs(gox,goy,dir,len+1);
		}else {
			dfs(gox,goy,dir,len);
		}
		
	}
	
	//꼬리랑 만나나?
	private static boolean ckTail(int len, int gox, int goy) {
		//길이만큼만 확인함
		for(int i = 1 ; i <= len ; i++) {
			Pos p = list.get(list.size() - i);
			//겹치는 부분이 한 곳이라도 있다면
			if( gox == p.x && goy == p.y ) {
				return false;
			}
		}
		return true;
	}
	//범위 안인지
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N) {
			return true;
		}
		return false;
	}
	//좌표 저장 클래스
	static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
}

