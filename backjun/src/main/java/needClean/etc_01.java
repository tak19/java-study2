package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
//귀여운 라이언
public class etc_01 {
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); 
		int k = Integer.parseInt(st.nextToken());

		List<Integer> list = new ArrayList<>();

		//인형 입력받음!
		//라이언 인형은 1, 어피치 인형은 2로 표현 --> 라인언 정보는 따로 저장함
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] == 1) {
				list.add(i);
			}
		}

		if( list.size() < k) {
			System.out.println(-1);
			return;
		}
		
		//리스트 기준으로 
		int result = Integer.MAX_VALUE;
		for(int i = 0 ; i <= list.size() - k ; i++) {
			int dis = (list.get(i+k-1) - list.get(i)) +1 ;
			if( result > dis ) {
				result = dis;
			}
		}
		System.out.println(result);
	}

}

