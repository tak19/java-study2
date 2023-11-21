package basic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class codingTest_01 {
	private static StringBuilder sb = new StringBuilder();
	static int N,pointGap,result;
	static int[] studentArr;

	public static void main(String[] args) throws Exception {
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/Sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int Test = Integer.parseInt(br.readLine());
		for(int test_case = 1 ; test_case <= Test ; test_case++) {
			sb.append("#" + test_case + " ");
			
			//전체 인원 수와 편차를 입력 받는다.
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			pointGap = Integer.parseInt(st.nextToken());
			
			//학생 배열 입력 받기
			studentArr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				studentArr[i] = Integer.parseInt(st.nextToken());
			}
			
			//정렬
			Arrays.sort(studentArr);
			//System.out.println(Arrays.toString(studentArr));
			result = 0;
			//완전 탐색 시작
			for(int i = 0 ; i < N ; i++) {
				//System.out.println(i+"번째 실행 테케임=>" + "왼쪽으로: " + leftCount(i) + " 오른쪽으로: " + rigthCount(i));
				int sumStudent = rigthCount(i) + 1;
				result = Math.max(result, sumStudent);
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	//오른쪽으로 갈 경우 나오는 수 계산1
	private static int rigthCount(int index) {
		int cnt = 0;
		for(int i = index + 1 ; i < N ; i++ ) {
			if( studentArr[i] - studentArr[index] <= pointGap ) {
				cnt++;
			}else {
				break;
			}
		}
		return cnt;
	}
}