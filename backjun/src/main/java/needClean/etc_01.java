package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class etc_01 {
private static StringBuilder sb = new StringBuilder();

public static void main(String[] args) throws Exception {
	/*
	 * 1. 입력파일 읽어 들이기
	 */
	//System.setIn(new FileInputStream("res/17478_input.txt"));
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	for(int test_case = 1 ; test_case <= 10 ; test_case++) {
		sb.append("#" + test_case + " ");
		//원본 암호문 길이
		
		int N = Integer.parseInt(br.readLine());
		String[] split = br.readLine().split(" ");
		
		List<String> list = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			list.add(split[i]);
		}
		
		
		
		//명령어 개수
		int test = Integer.parseInt(br.readLine());
		split = br.readLine().split(" ");
		
		int cursor = -1;

		for(int i = 0 ; i < test; i++) {
			String type = list.get(++cursor);
			switch (type) {
			case "I":
				int x = Integer.parseInt(list.get(++cursor));
				int y = Integer.parseInt(list.get(++cursor));
				++cursor;
				List<String> subList = list.subList(cursor, cursor + y);
				list.addAll(x, subList);
				cursor += y -1;
			}
		}
		for(int i = 0 ; i < 10 ; i++) {
			sb.append(list.get(i)).append(" ");
		}
		sb.append("\n");
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
