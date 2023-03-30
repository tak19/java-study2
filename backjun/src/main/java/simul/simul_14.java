package simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 테두리 한바퀴 싸이클 돌리고 찾은 열쇠가 있다면 다음 배열에 찾을 열쇠값이 있다고 표시함
 * 다음 연산때 다른 열쇠를 찾은 적이 없다면 종료하고 값이 있다면 다음탐색도 계속 진행함
 * 대문자에서 65를 빼주면 0인덱스부터 시작.. 소문자는 97을 빼주면 0인덱스부터 시작
 */

public class simul_14 {
	//상하좌우
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int test,h,w,result,hear;
	static int[] key,search;
	static char[][] map; 
	static boolean[][] visit,ckPaper;


	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		test = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		//테스트 케이스 수만큼 반복
		for(int T = 0 ; T < test ; T++) {
			st = new StringTokenizer(br.readLine());

			h = Integer.parseInt(st.nextToken()); // 높이
			w = Integer.parseInt(st.nextToken()); // 넓이

			//지도 입력 받기		
			map = new char[h][w];

			for(int i = 0 ; i < h ; i++){
				char[] tem = br.readLine().toCharArray();
				for(int j = 0 ; j < w ; j++){
					map[i] = tem;
				}
			}

			key = new int[26];
			search = new int[26];

			//현재 가지고 있는 열쇠 -> '-'연산을 통해 인덱스로 활용하여 사용
			String s = br.readLine();
			for(int i = 0 ; i < s.length() ; i++) {
				int index = s.charAt(i) - 97;
				if( index >= 0 ) {
					key[index]++;
				}
			}
			//최초 한번은 무조건 탐색함 -> 열쇠없는 시작 방지
			search[0] = 1; 
			result = 0;
			hear = 0;

			//현재 열쇠를 돌고 있다면
			while( search[hear++] >= 1 ) {
				visit = new boolean[h][w];
				letPaper();
			}
			sb.append(result).append("\n");

		}
		System.out.println(sb);

	}

	//서류찾기 시작함 -> 끝에서 들어갈 수 있기 때문에 탐색 신경
	private static void letPaper() {
		for(int i = 0 ; i < h ; i++){
			for(int j = 0 ; j < w ; j++){

				//들어갈 수 있는 곳이라면 가장자리인지 확인하고 탐색한다.
				if( map[i][j] != '*' ) {
					//제일 위에 변이라면~!!
					if( i == 0 ) {
						searchPaper(i,j,map[i][j]);
					}else if( j == 0 || j == (w-1) ) {
						//양 사이드 탐색
						searchPaper(i,j,map[i][j]);
					}else if( i == (h-1) ) {
						//제일 아래변
						searchPaper(i,j,map[i][j]);
					}
				}
			}
		}

	}

	private static void searchPaper(int i, int j, char c) {
		visit[i][j] = true;
		//시작 좌표에 문서가 있다면 개수 추가하고 빈 공간으로 바꾸자!!
		if( map[i][j] == '$' ) {
			result++;
			map[i][j] = '.';
		}
		//시작 좌표가 대문자라면 -- 즉, 문이라면
		if( ckUpper(map[i][j]) ) {
			//해당 문을 열 수 있는 열쇠가 있는지 확인합니다.
			int index = (map[i][j] - 65);
			//열쇠가 있다면 해당 문을 제거함
			if( key[index] > 0 ) {
				map[i][j] = '.'; 
			}else {
				//해당문을 못 열면 종료
				return;
			}
		}
		//소문자(열쇠)라면
		if( ckLower(map[i][j]) ) {
			int index = map[i][j] - 97;
			//없었던 키라면!! 해당 키 소유 표시하고 다음탐색을 처리를 위한 표시
			map[i][j] = '.';
			//			search[hear]++;
			if( key[index] == 0 ) {
				key[index]++;
				search[hear]++;
			}
		}
		//4방탐색
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(i,j));

		//반복 시작점!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		while( !queue.isEmpty() ) {
			Pos pos = queue.poll();
			int x = pos.x;
			int y = pos.y;

			for(int k = 0 ; k < 4 ; k++) {
				int gox = x + dx[k];
				int goy = y + dy[k];
				//범위 안이면서 벽이 아니라면 + 방문이력이 없는 곳이라면
				if( canGo(gox,goy) && map[gox][goy] != '*' ) {
					if( !visit[gox][goy] ) {
						//다음 방문할 칸에 문서가 있다면!! 바꾸고 방문함
						if( map[gox][goy] == '$' ) {
							result++;
							map[gox][goy] = '.';
							visit[gox][goy] = true;
							queue.offer(new Pos(gox,goy));
							continue;
						}

						//문이 나왔다?
						else if( ckUpper(map[gox][goy]) ) {

							//해당 문을 열 수 있는 열쇠가 있는지 확인합니다.
							int index = (map[gox][goy] - 65);
							//열쇠가 있다면 해당 문을 제거하고 방문함
							if( key[index] > 0 ) {
								map[gox][goy] = '.';
								visit[gox][goy] = true;
								queue.offer(new Pos(gox,goy));
							}


						}else if( ckLower(map[gox][goy]) ){
							int index = (map[gox][goy] - 97);
							//없었던 키라면!! 해당 키 소유 표시하고 다음탐색을 처리를 위한 표시
							if( key[index] == 0 ) {
								key[index]++;
							}
							search[hear]++;
							map[gox][goy] = '.';
							visit[gox][goy] = true;
							queue.add(new Pos(gox,goy));
						}else {
							//그냥 빈 공간이라면 방문 or 문서라면?
							visit[gox][goy] = true;
							queue.add(new Pos(gox,goy));
						}
					}
				}

			}

		}
	}

	//소문자라면 true 반환
	private static boolean ckLower(char c) {
		if( 'a' <= c && c <= 'z' ) {
			return true;
		}
		return false;
	}

	//대분자라면 true 반환
	private static boolean ckUpper(char c) {
		if( 'A' <= c && c <= 'Z' ) {
			return true;
		}
		return false;
	}
	//범위 안인지
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < h && goy >= 0 && goy < w) {
			return true;
		}
		return false;
	}
	//좌표정보 저장
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}