package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.crypto.CipherInputStream;

public class greedy23 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		char c = 'a'; //이전 문자를 저장함
		int one = 0;
		int zero = 0;
		for(int i = 0 ; i < s.length() ; i++) {
			
			if( s.charAt(i) != c ) {
				c = s.charAt(i);
				if( c == '1' ) {
					zero++;
				}
				if( c == '0' ) {
					one++;
				}
			}
		}
		System.out.println(zero > one ? one : zero);

	}
}