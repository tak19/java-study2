package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bruteforce_03 {
	static int n;
	static int[] arr;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		result = 0;
		arr = new int[n];

		searchRow(0);
		System.out.println(result);
	}

	// 탐색 시작하는데 row는 행값이 --> 행이 n까지 가면 탐색완료
	private static void searchRow(int queen) {
		
		if (queen == n) {
			result++;
			return;
		}
		//n까지 가면서 해당 열에 차례대로 하나씩 집어넣음
		for(int i = 0 ; i < n ; i++) {
			arr[queen] = i;
			if( backTraking(queen)) {
				searchRow(queen + 1);
			}
		}
		

	}

	// 인덱스 안에 있는 값 중복 확인으로 같은 행에 놓였는지 판단.
	// 두 행과 열의 차이가 일치하면 대각선 상에 놓여있는 것이기 때문에 이를 이용해 판단
	private static boolean backTraking(int queen) {
		
		for(int i = 0 ; i < queen ; i++) {
			//같은 행에 퀸이 놓여졌는지 판단함-- 있으면 false 리턴해서 검색 x
			if(arr[i] == arr[queen]) {
				return false;
			}
			//대각선에 같이 있는지 확인함
			else if( Math.abs(arr[i] - arr[queen]) == Math.abs(i - queen)  ) {
					return false;
			}
		}
		
		
		
		return true;
	}
}

