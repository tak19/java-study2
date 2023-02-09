package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ect_18 {
	private static StringBuilder sb = new StringBuilder();
	static int n, result;
	static int[] dan,sin;
	static int sSum,dSum;
	static boolean[] isSelected; //각 원소가 부분집합의 구성에 포함되었는지 여부
	
	public static void main(String[] args) throws Exception{
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); //음식 수
		sin = new int[n];
		dan = new int[n];
		isSelected = new boolean[n];

		for(int i = 0 ; i < n ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sin[i] = Integer.parseInt(st.nextToken()); //신맛
			dan[i] = Integer.parseInt(st.nextToken()); //쓴맛
		}

		result = Integer.MAX_VALUE;
		//재료 선택 부분집합
		subsetS(0);
		System.out.println(result);

	}
	//신맛은 곱, 쓴맛은 합
	private static void subsetS(int cnt) {
		if( cnt == n ) {
			sSum = 1;
			dSum = 0;
			boolean ck = false;
			//신맛 음식 곱 구하기
			for(int i = 0 ; i < n ; i++) {
				if( isSelected[i] ) {
					ck = true;
					sSum *= sin[i];
					dSum += dan[i];
				}
			}
			if( !ck ) {
				return;
			}
			result = Math.min(result, Math.abs(dSum - sSum) );
			return;
		}

		isSelected[cnt] = true;
		subsetS(cnt+1);
		isSelected[cnt] = false;
		subsetS(cnt+1);
	}
}

