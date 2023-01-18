package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class etc_07 {
	static int[] ckArr;
	static int[] myArr;
	static int four;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int all = Integer.parseInt(info[0]); //DNA문자열 길이
		int pw = Integer.parseInt(info[1]); //PW 문자열 길이

		char[] minho = br.readLine().toCharArray(); // 실제 주어진 문자열

		//4가지 경우 모두 만족한다면 비밀번호 성립이 가능하다 -> 조건 1개당 1씩 증가 시킬꺼야
		four = 0;
		//각 문자열이 조건에 충족했는지 확인하기 위함!
		ckArr = new int[4];
		myArr = new int[4];
		String[] must = br.readLine().split(" "); //{A, C, G, T}
		for(int i = 0 ; i < must.length; i++) {
			ckArr[i] = Integer.parseInt(must[i]);
			if(ckArr[i] == 0) {
				four++; //필요 요소 하나 없으면 충족된걸로 봄
			}
		}
		//PW 문자열 길이만큼 문자 더해줌
		for(int i = 0; i < pw ; i++) {
			add(minho[i]);
		}
		//만약 부분 문자열길이 만큼 초기화 했을때 만족한다면 1증가후 탐색시작
		int result=0;
		if(four == 4) {
			result++;
		}

		//부분 문자열  탐색 -> 부분 문자열의 끝 길이부터 시작함.. 즉, 끝부분은 추가 첫부분은 제거하는 형식
		for(int i = pw; i < all ; i++) {
			int j = i - pw;
			//부분 문자 길의 끝부분은 추가하고 첫글자는 뺌
			add(minho[i]);
			remove(minho[j]);
			
			if(four == 4) {
				result++;
			}
			
		}
		System.out.println(result);





	}
	private static void remove(char c) {
		switch (c) {
		case 'A':
			if(myArr[0] == ckArr[0]) {
				four--;
			}
			myArr[0]--;
			break;
		case 'C':
			if(myArr[1] == ckArr[1]) {
				four--;
			}
			myArr[1]--;
			break;
		case 'G':
			if(myArr[2] == ckArr[2]) {
				four--;
			}
			myArr[2]--;
			break;
		case 'T':
			if(myArr[3] == ckArr[3]) {
				four--;
			}
			myArr[3]--;
			break;
		}
		
	}
	//myArr -> 차례대로 A C G T 순으로 본다.
	private static void add(char c) {
		switch (c) {
		case 'A':
			myArr[0]++;
			if(myArr[0] == ckArr[0]) {
				four++;
			}
			break;
		case 'C':
			myArr[1]++;
			if(myArr[1] == ckArr[1]) {
				four++;
			}
			break;
		case 'G':
			myArr[2]++;
			if(myArr[2] == ckArr[2]) {
				four++;
			}
			break;
		case 'T':
			myArr[3]++;
			if(myArr[3] == ckArr[3]) {
				four++;
			}
			break;
		}


	}

}
