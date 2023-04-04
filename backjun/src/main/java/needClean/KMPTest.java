package needClean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class KMPTest {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] T = br.readLine().toCharArray(); //본문
		char[] P = br.readLine().toCharArray(); //패턴
		
		int tLength = T.length;
		int pLength = P.length;
		
		//1단계: 부분 일치 테이블 만들기
		int[] pi = new int[pLength];
		
		// i: 접미사 포인터
		// j: 접두사 포인터
		for(int i = 1, j = 0 ; i < pLength ; i++) {

			//일치하지 않는경우
			while( j > 0 && P[i] != P[j] ) {
				// j-1 위치까지는 일치했으므로 일치했던 곳의 인덱스 번호를 가져옴
				j = pi[j-1];
			}
			
			//같은경우
			if( P[i] == P[j] ) {
				pi[i] = ++j;
			}
			else {
				pi[i] = 0;
			}
		}
		
		//2단계: 부분일치 테이블을 활용하여 본문과 패턴 비교하기
		int cnt = 0; //찾은 단어 개수
		List<Integer> list = new ArrayList<>(); //찾은 단어의 시작 인덱스 번호 모음
		
		// i 본문 포인터
		// j 패턴 포인터
		for(int i = 0, j = 0 ; i < tLength ; i++) {
			//본문 패턴이 불일치하면
			while( j > 0 && T[i] != P[j] ) {
				// ㅓ-1일 떄까지는 일치 했으므로 인덱스 j를 이전 일치했던 상태로 돌림
				j = pi[j-1];
			}
			
			//일치하면
			if( T[i] == P[j] ) {
				
				//j가 패턴의 마지막 인덱스라면 패턴과 일치하는 단어를 찾은경우
				if( j == pLength -1 ) {
					cnt++; //찾은 단어 수 증가
					list.add(i-j); // 찾은 단어의 첫 인덱스 번호를 저장
					
					// j위치까지는 일치 했으므로 일치했던 j 인덱스 번호 사용
					j = pi[j];
					
				}else {
					j++;
				}
				
			}
			
		}
		System.out.println(list.size());
		System.out.println(list);
	}
}





