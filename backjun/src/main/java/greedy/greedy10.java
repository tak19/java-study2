package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class greedy10 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		
		
		long[] distance = new long[(int) (n-1)];
		long[] city = new long[(int) n];
		
		String[] dis = br.readLine().split(" ");
		String[] cit = br.readLine().split(" ");
		//거리값
		for(int i = 0 ; i < distance.length ; i++) {
			distance[i] = Long.parseLong(dis[i]);
		}
		
		//주유비
		for(int i = 0 ; i < city.length ; i++) {
			city[i] = Long.parseLong(cit[i]);
		}
		
		//첫번째 도시부터 2번째 도시까지는 변동x
		//int total = city[0] * distance[0];
		long total = 0;
		//현재 기름 값을 저장할꺼야
		long min_price = city[0];
		
		for(int i = 0 ; i < dis.length; i++) {
			if( min_price > city[i] ) {
				min_price = city[i];
			}
			total += min_price * distance[i];
			
		}	
		
		System.out.println(total);
		
	}
}