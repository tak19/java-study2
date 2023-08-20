package string;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class string_06 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int result = 0;
		for(int test = 0 ; test < T ; test++) {
			char[] arr = br.readLine().toCharArray();
			boolean[] visit = new boolean[26]; //방문배열
			boolean ck = true;
			//단어 길이만큼 탐색함
			for(int i = 0 ; i < arr.length-1 ; i++) {
				//다음 나오는 문자가 현재문자와 다르다면 방문 체크함
				if( arr[i] != arr[i+1] ) {
					//이전에 나온적이 있다면 확인용 false로 변환
					if( visit[arr[i] - 'a'] ) {
						ck = false;
						break;
					}
					visit[arr[i] - 'a'] = true;
				}
			}
			if( visit[arr[arr.length-1] -'a'] ) {
				ck = false;
			}
			if( ck ) {
				result++;
			}
		}
		System.out.println(result);
	}
}