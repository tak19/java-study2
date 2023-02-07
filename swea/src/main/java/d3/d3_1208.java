package d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class d3_1208 {
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/*
		 * 2. 입력파일 객체화
		 */
		for(int test_case = 1 ; test_case <= 10 ; test_case++) {
			sb.append("#" + test_case + " ");

			int max = -1;
			int min = 101;

			//정렬 시키고 끝에서 처음으로 한개씩 줌
			int dumpCnt = Integer.parseInt(br.readLine()); //dump 횟수
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] dumpArray = new int[100];
			for(int i = 0 ; i < 100 ; i++) {
				dumpArray[i] = Integer.parseInt(st.nextToken());
			}
			// min max 차이가 1 이하라면 종료된거임
			// 정렬 시켜서 끝에서 시작점에 한개씩 줌

			while( dumpCnt != 0) {
				Arrays.sort(dumpArray);
				if( dumpArray[99] - dumpArray[0] <= 1 ) {
					sb.append(dumpArray[99] - dumpArray[0]).append("\n");
					break;
				}
				dumpArray[0]++;
				dumpArray[99]--;
				dumpCnt--;
			}
			Arrays.sort(dumpArray);
			if (dumpCnt == 0) {
				sb.append(dumpArray[99] - dumpArray[0]).append("\n");
			}


		}

		/*
		 * 3. 알고리즘 풀기
		 */

		/*
		 * 4. 정답출력
		 */
		System.out.println(sb);

	}
}