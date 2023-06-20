package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class datastructure_20 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		//마라토너 입력받음
		Map<String,Integer> hMap = new HashMap<>();
		for(int i = 0 ; i < N ; i++) {
			String key = br.readLine();
			hMap.put( key, hMap.getOrDefault(key, 0) + 1);
		}
		
		//완주한 사람을 구분하고 해당 이름을 지워준다
		for(int i = 0 ; i < N-1 ; i++) {
			String removeKey = br.readLine();
			hMap.put(removeKey,hMap.get(removeKey)-1);
			if( hMap.get(removeKey) == 0 ) {
				hMap.remove(removeKey);
			}
		}
		
		for(String aws : hMap.keySet()) {
			System.out.println(aws);
		}
	}
}