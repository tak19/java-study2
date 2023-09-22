package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class etc_01 {
private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int people = Integer.parseInt(st.nextToken());
		int party = Integer.parseInt(st.nextToken());

		List<Integer> list = new ArrayList<>();
		//신뢰하는 사람 입력받기
		st = new StringTokenizer(br.readLine());
		int trust = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < trust ; i++) {
			list.add( Integer.parseInt(st.nextToken()));
		}
		
		//파티진행
		for(int i = 0 ; i < party ; i++) {
			
		}
		
		
		
		
		System.out.println(sb);
		
		
	}
	
}

