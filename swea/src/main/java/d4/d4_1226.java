package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class d4_1226 {
	static int[][] arr = new int[16][16];
	static boolean[][] flag;
	static int[] row = {0, 0, -1, 1}; //상하좌우 이동시 사용
	static int[] col = {-1, 1, 0, 0};
	static int result;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			sb.append("#" + test_case + " ");
			int nnnn = Integer.parseInt(br.readLine());
			
			flag = new boolean[16][16];
			int startX = 0;
			int startY = 0;
			//111 111 111 111 111 1
			for(int i = 0 ; i < 16 ; i ++) {
				char[] c = br.readLine().toCharArray();

				for(int j = 0 ; j < 16 ; j ++) {
					arr[i][j] = Character.getNumericValue(c[j]);
					if( arr[i][j] == 2 ) { //시작 좌표 저장해둠
						startX = i;
						startY = j;
					}
				}
			}
			result = 0;
			Bruteforce(startX,startY);
			
			sb.append(result + "\n");
			
			
        }
		System.out.println(sb);
	}
	private static void Bruteforce(int x, int y) {
		if(arr[x][y] == 1 || flag[x][y] ) {
			return; //길이 막혔어!!! or 들렸던 길이야!!!
		}
		if(arr[x][y] == 0 || arr[x][y] ==2) {
			flag[x][y] = true;
			//길이 있다면 다음길을 탐색할꺼야
			for(int i = 0 ; i < 4 ; i++) {
				int nextX = x + row[i];
				int nextY = y + col[i];
				
				if( nextX < 0 || nextY < 0 || nextX >= 16 || nextY >= 16 ) {
					continue;
				}
				Bruteforce(nextX,nextY);
			}

		}
		if(arr[x][y] == 3) {
			result = 1;
			return;
		}
		
	}
}

