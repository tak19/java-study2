package string;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 플로이드 와샬 알고리즘 적용함
 * 
 */
public class string_01 {
	static int N;
	static String[] s;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		s = new String[N];
		//문자열 입력받음
		for(int i = 0 ; i < N ; i++) {
			s[i] = br.readLine();
		}
		int allSame = 0;
		String one = "";
		String two = "";
		//비교연산 시작
		for(int i = 0 ; i < N ; i++) {
			String now = s[i];
			for(int j = i+1 ; j < N ; j++) {
				String tem = s[j];
				int maxSame = 0;
				int len = Math.min(s[i].length(), s[j].length());
				//하나씩 비교한다. 아니면 나와요
				for(int k = 0 ; k < len ; k++) {
					
					//같은 문자가 나온다면 글자 증가
					if( now.charAt(k) == tem.charAt(k) ) {
						maxSame++;
					}else {
						break;
					}
					//최대 접두사가 나온다면
					if( allSame < maxSame ) {
						allSame = maxSame;
						one = s[i];
						two = s[j];
					}
				}
				
			}
		}
		System.out.println(one);
		System.out.println(two);
	}
}