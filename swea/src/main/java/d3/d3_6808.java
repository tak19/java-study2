package d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class d3_6808 {
	static int[] cu = new int[9];
	static int[] in = new int[9];
	static int[] testIn = new int[9];
	static boolean[] visit;
	static boolean[] inVisit = new boolean[9];
	static int win,lose;

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
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			sb.append("#" + test_case + " ");

			visit = new boolean[19]; //1~18까지 확인하기 위해 19로
			//규영이의 고정된 9가지 카드 순서
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 9 ; i++) {
				cu[i] = Integer.parseInt(st.nextToken());
				visit[cu[i]] = true;
			}
			//남은 카드 인영이꼬
			int cnt = 0;
			for(int i = 1 ; i <= 18 ; i++) {
				if( !visit[i] ) {
					in[cnt++] = i;
				}
			}


			//일단 하고 NP 해보자

			//정렬하고 요기선 할 필요 없음 - 인덱스 방문해서 처리하기 떄문
			//Arrays.sort(in);
			win = 0;
			lose = 0;

			//pec(0); //그냥 순열 사용
			
			//NP 사용
			do {
				//System.out.println(Arrays.toString(input));
				startNPGame();

			}while( npPec(in) );
			
			sb.append(win).append(" ").append(lose).append("\n");

		}

		/*
		 * 3. 알고리즘 풀기
		 */

		/*
		 * 4. 정답출력
		 */
		System.out.println(sb);

	}

	
	private static void startNPGame() {
		int cuSum = 0;
		int inSum = 0;

		for(int i = 0 ; i < 9 ; i++) {

			if( cu[i] > in[i] ) { //규영이가 더 커요
				cuSum += cu[i] + in[i];
			}else { //인영이가 더 커요
				inSum += cu[i] + in[i];
			}

		}
		//규영이가 이겼으면!!
		if( cuSum > inSum ) {
			win++;
		}else {
			lose++;
		}
	}


	private static boolean npPec(int[] input) {
		int n = input.length;
		//교환위치 찾아요
		int i = n - 1;
		while( i > 0 && input[i] < input[i-1] ) { //가장 큰값을 찾아유 꼭대기
			i--;
		}
		if( i == 0 ) { //이미 가장 큰 순열이야
			return false;
		}
		//꼭대기 앞칸과 바꿀 위치 찾기
		int j = n - 1;
		while( input[i-1] > input[j] ) {
			j--;
		}
		swap(input, i-1 ,j);
		
		int k = n - 1;
		while( i < k ) {
			swap(input, i++, k--);
		}
		return true;
	}

	private static void swap(int[] input, int i, int j) {
		
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
		
	}


	//	//그냥 순열 썼을때
	//	private static void pec(int cnt) {
	//		if(cnt == 9) {
	//			
	//			if( startGame() ) { //규영이가 이겼다면
	//				win++;
	//			}else { //규영이가 짓다면
	//				
	//				lose++;
	//			}
	//			return;
	//		}
	//		
	//		for(int i = 0 ; i < 9 ; i++) {
	//			if( inVisit[i] ) { //뽑은카드는 안뽑아~
	//				continue;
	//			}
	//			inVisit[i] = true;
	//			testIn[cnt] = in[i];
	//			pec(cnt + 1);
	//			inVisit[i] = false;
	//			
	//		}
	//		
	//	}

	private static boolean startGame() {
		int cuSum = 0;
		int inSum = 0;

		for(int i = 0 ; i < 9 ; i++) {

			if( cu[i] > testIn[i] ) { //규영이가 더 커요
				cuSum += cu[i] + testIn[i];
			}else { //인영이가 더 커요
				inSum += cu[i] + testIn[i];
			}

		}
		//규영이가 이겼으면!!
		if( cuSum > inSum ) {
			return true;
		}else {
			return false;
		}

	}
}

