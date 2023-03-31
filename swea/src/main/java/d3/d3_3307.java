package d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class d3_3307 {
	private static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			sb.append("#" + test_case + " ");
			
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			int[] C = new int[N];
			
			String[] split = br.readLine().split(" ");
			for(int i = 0 ; i < N ; i++) {
				arr[i] = Integer.parseInt(split[i]);
			}
			
			int size = 0;
			for(int i = 0 ; i < N ; i++) {
				int pos = Arrays.binarySearch(C, 0, size, arr[i]);
				
				if( pos >= 0 ) {
					continue;
				}
				
				int insertPos = Math.abs(pos) - 1; //삽입 위치 인덱스 값 계산
				C[insertPos] = arr[i];
				//삽입 위치의 인덱스와 크기가 같다면 마지막 삽입위치라는 뜻으로 size 1증가
				if(insertPos == size) {
					size++;
				}
			}
			sb.append(size).append("\n");
		}
		System.out.println(sb);
	}
}