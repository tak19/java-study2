package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class etc_03 {
private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//기둥 높이를 배열에 저장
		int[] arr = new int[n+1];
		int[] result = new int[n+1];
		for(int i = 1 ; i <= n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> s = new Stack<>(); //임시저장
		
		for(int i = n ; i >= 1 ; i--) {
			//작은 기둥저장
			if( !s.isEmpty() ) {
				
				if( s.peek() < arr[i] ) { //비어있지 않고, 앞전 기둥보다 지금 기둥이 더 높다면 
					
					int cnt = 1;
					while(!s.isEmpty() && s.peek() < arr[i] ) {
						if(result[i+cnt] == 0) {
							result[i+cnt] = i;
						}else {
							cnt++;
							continue;
						}
						cnt++;
						s.pop(); // 하나 뺴주고
					}
					
					s.add(arr[i]);
					
				}
				else { //현재 기둥보다 더 작다면 스택에 추가함
					s.add(arr[i]);
				}
				
				
				
				
			}
			else { //기둥 비어있으면 스택에 저장함
				s.add(arr[i]);
			}
		}
		
		int size = s.size();
		System.out.println(size);
		for(int i = 0 ; i < size; i++) {
			System.out.println(s.pop());
			result[i] = 0;
		}
//		for(int i = 0 ; i < size; i++) {
//			sb.append(re.pop()).append(" ");
//		}
		for(int i = 1 ; i <= n; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb);
		
		
	}
	
}

