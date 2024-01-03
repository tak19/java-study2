package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class datastructure_30 {
	static StringBuilder sb = new StringBuilder();
	static String s = "";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 처음에 입력된 문자를 담기 위한 스택
		Stack stL = new Stack<>();
		// 문자를 스택에 옮겨 담음
		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			stL.push(str.charAt(i));
		}
		// 나중에 출력할 스택
		Stack stR = new Stack<>();
		// 명령 실행
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			switch (cmd) {
			case "L":
				if (!stL.empty()) {
					stR.push(stL.pop());
				}
				break;
			case "D":
				if (!stR.empty()) {
					stL.push(stR.pop());
				}
				break;
			case "B":
				if (!stL.empty()) {
					stL.pop();
				}
				break;
			case "P":
				stL.push(st.nextToken());
				break;
			}
		}
		while (!stL.empty()) {
			stR.push(stL.pop());
		}

		while (!stR.empty()) {
			sb.append(stR.pop());
		}
		System.out.println(sb);

	}
}