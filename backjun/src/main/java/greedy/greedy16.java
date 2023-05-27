package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class greedy16 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 필요 기타 줄 개수
		int M = Integer.parseInt(st.nextToken()); // 브랜드 개수
		
		int[] pack = new int[M];
		int[] ones = new int[M];
		//6줄 패키지 or 한줄 낱개
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			pack[i] = Integer.parseInt(st.nextToken());
			ones[i] = Integer.parseInt(st.nextToken());
		}
		// 내림차순 정렬 --> 각 브랜드별로 제일 싼놈만 찾아서 고르면됨
		Arrays.sort(pack);
		Arrays.sort(ones);
		
		int result = 0;
		int packCnt = N / 6; //6개 묶음식 살때의 필요 패키지 수
		//패키지 별로 묶음으로 살때 혹은 낱개로 살때 이득인지 판단하여 선택
		result = packCnt * (pack[0] <= (ones[0] * 6) ? pack[0] : ones[0] * 6);
		
		//남은 기타줄 계산
		int subCnt = N % 6;
		result += pack[0] <= (ones[0] * subCnt) ? pack[0] : ones[0] * subCnt;
		
		System.out.println(result);
		
	}
}