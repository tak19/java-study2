package basic;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * 입력: NxN 지도, M개의 나무, K년 
 * 봄: 자기 나이만큼 양분먹고, 나이+1(나이어린 나무부터) , 여름: 봄에 죽은나무가 양분으로변함- 나이를 2로 나눈값만큼
 * 가을: 나무가 번식함 -- 나이는 5의배수, 팔방으로 번식      겨울: 양분을 추가함 --입력으로 주어진 칸만큼 
 */
public class test2 {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int N,MaxCnt,AllCnt,result;
	static int[][] map;
	static ArrayList<Pos> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					MaxCnt = Math.max(MaxCnt, map[i][j]);
				}
			}
			result = 10000;
			// x,y,d,cnt,eat
			search(0,0,0,0,0);
			//프로세스 연결 해보기
			//dfs(0,0,0);
			sb.append(result).append("\n");
		}
		
		
		System.out.println(sb);
	}
	private static void search(int r, int c, int d, int cnt, int eat) {
		//basis part
		if( MaxCnt == eat ) {
			result = Math.min(result, cnt);
			return;
		}
		
		
		
	}
	//x,y 좌표와 연결 개수, 길이를 매개변수로 가짐
	private static void dfs(int index, int cnt, int len) {
		//System.out.println(x + " " + y);
		// 다 탐색 했다면
		if( index >= list.size() ) {
			//System.out.println("다 방문함: " + cnt + " " + len);
			if( MaxCnt < cnt ) {
				MaxCnt = cnt;
				result = len;
			}else if ( MaxCnt == cnt ) {
				result = Math.min(result, len);
			}
			//System.exit(1);
			return;
		}
		if (  cnt  + ( AllCnt - index ) >=  MaxCnt)  {

			//프로세를 만남 - 4방향 연결할 수 있는 방향은 체크함
			for(int i = 0 ; i < 4 ; i++) {
				Pos pos = list.get(index);
				int x = pos.x;
				int y = pos.y;
				if( ckDir(x,y,i) ) {
					//색칠하고, 길이 증가, cnt++
					int plusLen = make(x,y,i);
					//System.out.println("만듬");
					//print();
					dfs(index+1,cnt+1,len+plusLen);
					remove(x,y,i);
					//System.out.println("없앰");
					//print();
				}else {
					dfs(index+1,cnt,len);
				}
			}
		}
		
		
	}
	//전선 제거 작업
	private static void remove(int x, int y, int dir) {
		int gox = x;
		int goy = y;
		for(int i = 0 ; i < N ; i++) {
			gox += dx[dir];
			goy += dy[dir];
			if( canGo(gox,goy) ) {
				map[gox][goy] = 0;
			}else {
				//범위 벗어나면 끝까지 탐색한거임
				break;
			}
		}
	}
	//설치함 - 설치 경로 변경하고 길이를 리턴함
	private static int make(int x, int y, int dir) {
		int len = 0;
		int gox = x;
		int goy = y;
		for(int i = 0 ; i < N ; i++) {
			gox += dx[dir];
			goy += dy[dir];
			if( canGo(gox,goy) ) {
				len++;
				map[gox][goy] = 2;
			}else {
				//범위 벗어나면 끝까지 탐색한거임
				break;
			}
		}
		return len;
	}
	//방해물이 없는지 판단한다. - 설치 가능하면 true 반환함
	private static boolean ckDir(int x, int y, int dir) {
		int gox = x;
		int goy = y;
		for(int i = 0 ; i < N ; i++) {
			gox += dx[dir];
			goy += dy[dir];
			if( canGo(gox,goy) ) {
				if( map[gox][goy] != 0 ) {
					return false;
				}
			}else {
				//범위 벗어나면 끝까지 탐색한거임
				break;
			}
		}
		return true;
	}
	//범위 안인지 판단함
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}




