package d2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class d2_14510 {
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			sb.append("#" + test_case + " ");
			
			int n = Integer.parseInt(br.readLine()); //식물 수
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[n];
			//식물 키 저장했다
			for(int i = 0 ; i < n ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			//하루에 한 나무에 물을 줄 수 있다, 그리고 짝수는 2 홀수는 1만큼 나무가 큰다.
			Arrays.sort(arr);
			int sub = 0;
			int two = 0;
			int one = 0;
			//가장 높은 나무에서 모든 나무의 길이를 뺀다
			for(int i = 0 ; i < n-1; i++) {
				sub = arr[n-1] - arr[i];
				one += sub % 2;
				two += sub / 2;
			}
			int temp = Math.max(two - one, 0) / 3;
			one += temp * 2;
			two -= temp;
			int Min = Math.min(one,two);
			int result = Min * 2 + Math.max( (one - Min) * 2 -1, 0) + (two - Min) / 2 * 3 + (two - Min) % 2 *2;
			
			
			
			
			
			sb.append(result).append("\n");
			
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