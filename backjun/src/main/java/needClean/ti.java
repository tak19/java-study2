package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class ti {
	//상하좌우
		static int[] dx = {-1,1,0,0};
		static int[] dy = {0,0,-1,1};
		static int test,h,w,result;
		static int[] key;
		static char[][] map; 

		public static void main(String[] args) throws Exception{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			test = Integer.parseInt(br.readLine());
			StringTokenizer st = null;
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
				//현재 가지고 있는 열쇠 -> '-'연산을 통해 인덱스로 활용하여 사용
				String s = br.readLine();
				for(int i = 0 ; i < s.length() ; i++) {
					int index = s.charAt(i) - 97;
					if( index >= 0 ) {
						key[index]++;
					}
				}
				
				
			}
			

		}

		//범위 안인지
		private static boolean canGO(int gox, int goy) {
			if( gox >= 0 && gox < h && goy >= 0 && goy < w) {
				return true;
			}
			return false;
		}

	}