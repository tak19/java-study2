package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_16 {
	static int N,limit,result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //배열크기
			limit = Integer.parseInt(st.nextToken()); //경사로 길이

			int[][] map = new int[N][N];
			int[][] reverMap = new int[N][N]; //행과 열을 반전한 배열
			for( int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					reverMap[j][i] = map[i][j]; //행 열 반대 입력
				}
			}
			result = 0;
			building(map);
			building(reverMap);
			sb.append(result).append("\n");
		}
		System.out.println(sb);

	}
	private static void building(int[][] tmap) {

		//탐색시작 -- 첫행부터
		for(int i = 0 ; i < N ; i++) {
			int flat = 1; //초기 자기 자신값 포함
			boolean ck = true;

			for(int j = 1 ; j < N ; j++) {
				//높이 차이가 난다면
				if( tmap[i][j-1] != tmap[i][j] ) {
					if( tmap[i][j-1] == tmap[i][j] - 1 ) {
						//더 높은 경우를 만났을때
						if( flat < limit ) {
							//건설 공간이 부족하다면
							ck = false;
							break;
						}
						flat = 1; //공간 있다면 평평한 공간을 다시 초기화
					}else if( tmap[i][j-1] == tmap[i][j] + 1 ) {
						//더 낮은 경우를 만났을때
						if( !ckDown(tmap,i,j,tmap[i][j]) ) {
							ck = false;
							break;
						}
						//건설 가능하다면.. 해당 경우로 점프
						flat = 0;
						j += limit - 1;
						
					}else {
						//차이가 1 이상인 경우
						ck = false;
						flat = 1;
						break;
					}
				}
				//높이가 같다면
				else {
					flat++;
				}
			}
			if(ck) {
				result++;
			}
		}
	}

	//내릴 공간이 있나욤? -> 경사로 길이만큼 같은 값이 나온다면 true 리턴
	private static boolean ckDown(int[][] tmap, int x, int y, int same) {
		//범위 벗어난다면 실패 -> 자기 자신 포함 하기때문에 -1
		if( !canGo(y + limit-1) ) {
			return false;
		}
		//경사로 길이만큼 공간 있는지 확인쓰
		for(int i = 0 ; i < limit ; i++) {
			if( tmap[x][y+i] != same ) {
				return false;
			}
		}
		return true;
	}
	//범위 안인가?
	private static boolean canGo(int i) {
		if( i < N ) {
			return true;
		}
		return false;
	}
}
