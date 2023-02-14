package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d4_1233 {
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		for(int test_case = 1 ; test_case <= 10 ; test_case++) {
			sb.append("#" + test_case + " ");
			int n = Integer.parseInt(br.readLine());
			
			StringTokenizer st;
			
			int result = 1;
			for(int i = 0 ; i < n ; i++) {
				
				String[] s  = br.readLine().split(" ");
				if( s.length == 4) {//연산 3개 다 존재
					if( s[1].equals("+") || s[1].equals("-")  || s[1].equals("/") || s[1].equals("*")) {
						
					}else {
						result = 0;
						//break;
					}
					
					//2,3 자리에 숫자오는지도
					if( s[2].equals("+") || s[2].equals("-")  || s[2].equals("/") || s[2].equals("*")) {
						result = 0;
						//break;
					}
					if( s[3].equals("+") || s[3].equals("-")  || s[3].equals("/") || s[3].equals("*")) {
						result = 0;
						//break;
					}
				}else if ( s.length == 2) { //2개만 있음
					if( s[1].equals("+") || s[1].equals("-")  || s[1].equals("/") || s[1].equals("*")) {
						result = 0;
						//break;
					}
				}
		
			}
			
			sb.append(result).append("\n");
		}


		System.out.println(sb);

	}
	static class Pos{
		int left;
		int rigth;
		String data;
		
		Pos( String data, int rigth,int left){
			this.data = data;
			this.left = left;
			this.rigth = rigth;
		}
		
	}

}

