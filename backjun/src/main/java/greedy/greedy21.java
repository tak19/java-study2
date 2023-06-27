package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.crypto.CipherInputStream;

public class greedy21 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 0 ; t < Test ; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			//배열 입력받기
			for(int i = 0 ; i < N ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			//끝에서부터 탐색시작
			int max = 0;
			long result = 0;
			for(int i = N-1 ; i >= 0 ; i--) {
				//최대 주식값 갱신함
				if( max < arr[i] ) {
					max = arr[i];
					continue;
				}
				result += (max - arr[i]);
				
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}