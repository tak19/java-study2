package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;


public class datastructure_06 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeMap<String,Double> tMap = new TreeMap<>();
		StringBuilder sb = new StringBuilder();
		
		int index = 0;
		while( true ) {
			String s = br.readLine();
			if( s == null || s.length() == 0 ) {
				break;
			}
			index++;
			//나무 이름을 기준으로 키값을 저장하고 나올때마다 +1 증가시켜줌
			tMap.put(s, tMap.getOrDefault(s, 0.0) + 1 );
		}
		
		//Ket값을 통한 순회
		// 키 오름차순 정렬 
//		Object[] keys = tMap.keySet().toArray();
//		Arrays.sort(keys);
//		
//		for(Object key : keys) {
//			String keyStr = (String) key;
//			double per = tMap.get(keyStr) / index * 100;
//			
//			sb.append(keyStr + " " + String.format("%.4f", per) + "\n");	// 소수점 4번 째 자리까지 출력 
//		}
		
		//Entry를 통한 순회
		for( Entry<String, Double> tem : tMap.entrySet() ) {
			if( tem == null ) {
				break;
			}
			double fer = (tem.getValue() / index) * 100;
			sb.append(tem.getKey()).append(" ").append(String.format("%.4f", fer)).append("\n");
		}
		System.out.println(sb);
	}
}