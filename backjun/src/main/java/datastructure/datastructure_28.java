package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class datastructure_28 {
	static StringBuilder sb = new StringBuilder();
	static int A,B;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		//집합 A,B 갯수 입력받음
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		//집합 A,B 선언
		Set<Integer> aSet = new HashSet<>();
		Set<Integer> bSet = new HashSet<>();
		//집합 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < A ; i++) {
			aSet.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < B ; i++) {
			bSet.add(Integer.parseInt(st.nextToken()));
		}
		//결과 리스트를 선언하여 해당 값이 있는지 판단
		List<Integer> result = new ArrayList<>();
		for(Integer i : aSet) {
			if( !bSet.contains(i) ) {
				result.add(i);
			}
		}
		sb.append(result.size()).append("\n");
		if( result.size() > 0 ) {
			Collections.sort(result);
			for(Integer i : result) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb);
		
	}
}