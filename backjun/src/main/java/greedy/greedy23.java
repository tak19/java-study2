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
		int n = Integer.parseInt(br.readLine());
		
		int result = 0;
		//5로 나눠지면 5로 나누고 아니면 -2
		while( n > 0 ) {

			if( n % 5 == 0 ) {
				result += n / 5;
				n = 0;
			}else {
				result++;
				n -= 2;
			}
		}
		//나누어 떨어지지않는 경우
		if( n != 0 ) {
			result = -1;
		}
		System.out.println(result);
		
	}
}