package etc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_01 {
	static int[][] arr; // 음식 전체 정보야!!
	static int[] listA,listB; //각각 조합으로 뽑은 음식의 경우의 수가 들어감 -> 요기서 2개씩 뽑음 
	static boolean[] visit;
	static boolean[] Avisit,Bvisit; // 음식 2개씩 고를때 순열 중복체크
	static int[] Anum,Bnum; // 2개씩 뽑은 음식 순열 요기 들어감
	static int result; 
	static int Asum,Bsum; // 한 턴당 음식 시너지 합 --> 요기서 뽑은 음식 시너지로 차를 구한는거야 
	static int n;
	private static StringBuilder sb = new StringBuilder();
 
	public static void main(String[] args) throws Exception {
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			sb.append("#" + test_case + " ");
			n = Integer.parseInt(br.readLine());

			StringTokenizer st;
			arr = new int[n][n]; 
			//시너지 입력받음
			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < n ; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = Integer.MAX_VALUE;

			//음식수 만큼
			listA = new int[n/2];
			listB = new int[n/2];
			visit = new boolean[n];

			//최종 음식저장
			Anum= new int[n/2];
			Bnum= new int[n/2];
			//뽑은 개수와 시작값
			//A 음식을 조합으로 뽑음 --> 이거 뽑으면 자동으로 B 알수 있어!
			combi(0,0);

			sb.append(result).append("\n");
		}

		System.out.println(sb);

	}
	//A의 음식 조합
	private static void combi(int cnt, int start) {
		//다 뽑았으면
		if( cnt == (n/2) ) {
			//A 조합이 완료된상태
			Asum = 0;
			Bsum = 0;

			//나머지 식재료로 B음식 세팅함
			setListB();

			Avisit = new boolean[n/2];
			Bvisit = new boolean[n/2];
			//각각 식재료 목록에서 2개 뽑아서 전체 합  -> calA랑 claB 로직은 똑같은데 배열값이 다름

			//조합으로 합 구하기
			for(int i = 0 ; i < listB.length ; i++ ) {
				for(int j = 0 ; j < listB.length ; j++ ) {
					Bsum += arr[ listB[i] ][ listB[j] ];
					Asum += arr[ listA[i] ][ listA[j] ];
				}
			}

			//			calA(0);
			//			calB(0);
			result = Math.min(result, Math.abs( Bsum - Asum ));
			return;
		}
		//조합 계산 - visit쓰느 이유는 남은 걸로 B음식 처리하려고
		for(int i = start; i < n; i ++) {
			listA[cnt] = i;
			visit[i] = true;
			combi(cnt+1,i+1);
			visit[i] = false;
		}
	}



	//A에서 안쓴음식 B에 넣음
	private static void setListB() {
		int cntB = 0;
		for(int i = 0; i < n ; i ++) {
			if( !visit[i] ) {
				listB[cntB] = i;
				cntB++;
			}

		}
	}

	//	private static void calA(int cnt) {
	//	if( cnt == 2) {
	//		Asum += arr[ Anum[0] ][ Anum[1] ];
	//	}
	//	//listA의 순열계산
	//	for(int i = 0 ; i < listA.length ; i++ ) {
	//		//선택된 수이면 다음으로 넘어감
	//		if( Avisit[i] ) {
	//			continue;
	//		}
	//		//선택죄이 않았다면 input 배열에서 수 하나를 뽑는다.
	//		Anum[cnt] = listA[i];
	//		Avisit[i] = true;
	//		//다음 수 뽑으러 가기
	//		calA( cnt + 1 );
	//		// 사용했던 수에 대한 선택 되돌리기
	//		Avisit[i] = false;
	//	}
	//
	//}

	//	private static void calB(int cnt) {
	//	if( cnt == 2) { //2개 다 뽑혔으면!!
	//		Bsum += arr[ Bnum[0] ][ Bnum[1] ];
	//	}
	//	//listA의 순열계산
	//	for(int i = 0 ; i < Bnum.length ; i++ ) {
	//		//선택된 수이면 다음으로 넘어감
	//		if( Bvisit[i] ) {
	//			continue;
	//		}
	//		//선택죄이 않았다면 input 배열에서 수 하나를 뽑는다.
	//		Bnum[cnt] = listB[i];
	//		Bvisit[i] = true;
	//		//다음 수 뽑으러 가기
	//		calB( cnt + 1 );
	//		// 사용했던 수에 대한 선택 되돌리기
	//		Bvisit[i] = false;
	//	}
	//}


}