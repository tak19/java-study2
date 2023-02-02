package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class etc_10 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		int[] arr = new int[n]; //숫자저장
		StringTokenizer st  = new StringTokenizer(br.readLine());
		//수열 입력받음
		for(int i = 0; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] result = new int[n]; //숫자저장
		//하나씩 비교하기 위한 스택 --> 인덱스 정보를 저장할꺼야!!
		Stack<Integer> s = new Stack(); 
		s.push(0); //인덱스를 저장해줌 - 반복조건 때문에 첫벗째 원소만 추가
		
		for(int i = 1; i < n ; i++) { //나머지 원소만큼 반복
			while( !s.isEmpty()  && arr[s.peek()] < arr[i] ) { //담겨져 있는 스택 원소보다 큰값이 나오면 출력
				result[s.pop()] = arr[i];
			}
			s.push(i);
		}
		while( !s.isEmpty() ) {
			result[s.pop()] = -1;
		}
		
		for(int i = 0; i < n ; i++) {
			sb.append(result[i] + " ");
		}
		
		
		System.out.println(sb);
		
		
	}
}
