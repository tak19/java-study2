package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_02 {
	static int[] price = new int[4];
	static int[] month = new int[12];
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			st = new StringTokenizer(br.readLine());
			//입장권 가격 입력받음 -- 1일 / 1달 / 3달 / 1년
			for(int i = 0 ; i < 4 ; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			//이용 횟수 입력받음
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 12 ; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			result = Integer.MAX_VALUE;
			cal(0,0);
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
		
	}
	//최적 값 계산
	private static void cal(int mon, int won) {
		if( mon >= 12) {
			if( result > won ) {
				result = won;
			}
			return;
		}
		//이용권 별로 호출함
		cal(mon+1,  won + (price[0] * month[mon])); //1일권 이용 - 1일가격 * 이용횟수
		cal(mon+1,  won + price[1] ); //1달
		cal(mon+3,  won + price[2] ); //3달
		cal(mon+12,  won + price[3] ); //1년
		
		
	}
}
