package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class d3_1213 {

	public static void main(String[] args) throws Exception {
		/*
		 * 입력파일 읽어들이기
		 */
		// 1204_input.txt 파일을 읽어 br에 저장
		// System.setIn(new FileInputStream("1213_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한번에 출력하기 위함
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= 10; test_case++) {
			sb.append("#" + test_case + " ");

			int caseNum = Integer.parseInt(br.readLine()); // test케이스 횟수
			String s = br.readLine();
			String search = " " + br.readLine() + " ";

			String[] re = search.split(s);
			int total = re.length - 1;
			sb.append(total + "\n");

			/*  첫번째 문자를 만났을때 하나씩 보고 푸는 방법
			char[] arr = br.readLine().toCharArray(); // 검색문자
			char[] search = br.readLine().toCharArray(); // 전체문자

			char first = arr[0];

			int total = 0;
			// 끝까지 탐색할 필요없음
			for (int i = 0; i < search.length - arr.length + 1; i++) {
				// 첫글자 같으면 탐색함
				int ck = 1;
				if (search[i] == first) {
					// 문자 탐색
					for (int j = 0; j < arr.length; j++) {
						if (arr[j] != search[i + j]) {
							ck = 1;
							i += j - 1; // 맞는 문자 나오다 중간에 틀리면 해당 구역부터 탐색하기 위함
							break;
						} else {
							ck = 0;
						}
					}

				}
				if (ck != 1) {
					total++;
				}

			}
			sb.append(total + "\n");
			*/

		}
		System.out.println(sb);
	}
}