package datastructure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.TreeMap;
//9,876,543,210
public class datastructure_09 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		TreeMap<Long,Integer> tMap = new TreeMap<>();
		int maxCnt = 0;
		Long result = 0L;
		//각 키를 입력받으면서 트리에 값을 넣음
		for(int i = 0 ; i < N ; i++) {
			Long num = Long.parseLong(br.readLine());
			//값이 있다면 그값을 아니면 0을 반환
			tMap.put(num, tMap.getOrDefault(num, 0) + 1 );
			//해당 개수가 더 많이 나왔다면
			if( maxCnt < tMap.get(num) ) {
				maxCnt = tMap.get(num);
				result = num;
			}else if ( maxCnt == tMap.get(num) ) {
				//개수가 같은경우
				result = Math.min(result, num);
			}
		}
		System.out.println(result);

	}

}