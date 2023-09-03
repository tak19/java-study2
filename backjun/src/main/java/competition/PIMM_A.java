package competition;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class PIMM_A {
	static StringBuilder sb = new StringBuilder();
	//static int K,N;
	//static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String s = br.readLine();
		int cnt = 0; //과목 수
		double sum = 0;
		
		for(int i = 0; i < s.length() ; i++) {
			char c = s.charAt(i);
			//plus면 제외
			if( c == '+' ) {
				continue;
			}
			boolean plus = false;
			//plus 확인
			if( i != s.length() -1 ) {
				if( s.charAt(i+1) == '+' ) {
					plus = true;					
				}
			}
			double point = 0;
			switch (c) {
			case 'A':
				point = 4;
				break;
			case 'B':
				point = 3;
				break;
			case 'C':
				point = 2;
				break;
			case 'D':
				point = 1;
				break;
			case 'F':
				point = 0;
				break;
			}
			if( plus ) {
				point += 0.5;
			}
			cnt++;
			sum += point;
		}
		System.out.printf("%.5f",sum/cnt);

	}
}