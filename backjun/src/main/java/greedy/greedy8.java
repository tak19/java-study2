package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class greedy8 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		Arrays.sort(arr);
		int[] cnt = new int[26];
		//대문자 A의 아스키 코드는 65.. 대문자 한정이기 때문에 알파벳의 아스키 코드에 65를 빼서 알파벳 등장횟수 파악
		for (char c : arr) {
			cnt[c-65]++;
		}
		
		//문자열 길이가 짝수일때와 홀수 일 때를 구분
		//문자열 길이가 짝수이면 해당 문자열의 각 문자개수가 짝수여야지 팰린드롬 성립가능
		if(arr.length % 2 == 0) {
			for(int i = 0 ; i < 26 ; i++) {
				if(cnt[i] % 2 == 1) {
					print();
					return;
				}else {//짝수이면 데칼코마디처럼하면되기 때문에 /2해줌
					cnt[i] /= 2;
				}
			}
			//요기에 짝수의 경우 구현 -> 차례대로 한문자씩 뺀다... 다 빼고나면 해당문자 reverse 해서 붙일거임
			System.out.println(print(cnt));
			
			
		}else {
			int odd = 0;
			int oddIndex=-1;
			//문자열 길이가 홀수라면 하나를 제외한 문자열의 개수가 짝수여야지 팰린드롬 가능
			for(int i = 0 ; i < 26 ; i++) {
				if(cnt[i] % 2 == 1) {
					if(cnt[i] == 1) { //홀수이면서 등장횟수가 1이라면
						odd++; //홀수 개수파악 만약 1개 이상이면 데칼코마니 불가함
						oddIndex = i; //홀수가 나오는 문자열 Index 파악해둠
						cnt[i] = 0; //하나만 등자하는 수는 따로 처리할꺼임..
					}else {
						odd++; //홀수 개수파악 만약 1개 이상이면 데칼코마니 불가함
						oddIndex = i; //홀수가 나오는 문자열 Index 파악해둠
						cnt[i] /= 2;
					}
					
					if(odd == 2) {
						print();
						return;
					}
				}else {
					cnt[i] /= 2;
				}
				
			}
			//요기에 홀수의 경우 구현... AAABB 이런경우를 생각못함
			String s = print(cnt);
			StringBuffer sb = new  StringBuffer(s);
			sb.insert(s.length()/2, (char) (oddIndex + 65));
			System.out.println(sb);
		}
		
		
	}
	//한번씩 더 사용할 문자열이 남아 있기때문에 차례대로 다 출력해주면됨(이유: 사전순)
	public static String print( int[] cnt) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 26; i++) {
			while(cnt[i] != 0) {
				char c = (char) (i + 65);
				sb.append(c);
				cnt[i]--; // 해당 문자 1개 차감
			}
		}
		String s = sb.toString();
		s += sb.reverse();
		return s;
		
	}
	
	public static void print() {
		System.out.println("I'm Sorry Hansoo");
	}

}
