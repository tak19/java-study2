package bronze;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bronze_18 {
	private static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String string = br.readLine();

			// 중복 제거
			StringBuilder sb = new StringBuilder();
			sb.append(string.charAt(0));  // 첫 번째 문자는 그대로 저장
			for (int j = 1; j < string.length(); j++) {
				if (string.charAt(j) != string.charAt(j-1)) {
					sb.append(string.charAt(j));
				}
			}

			System.out.println(sb.toString());
		}

	}
}