package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
 * 입력: NxN 지도, M개의 나무, K년 
 * 봄: 자기 나이만큼 양분먹고, 나이+1(나이어린 나무부터) , 여름: 봄에 죽은나무가 양분으로변함- 나이를 2로 나눈값만큼
 * 가을: 나무가 번식함 -- 나이는 5의배수, 팔방으로 번식      겨울: 양분을 추가함 --입력으로 주어진 칸만큼 
 */
public class datastructure_11 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		HashSet<String> hSet = new HashSet<>();
		for(int i = 0 ; i < n ; i++) {
			hSet.add(br.readLine());
		}
		StringBuilder sb = new StringBuilder();
		ArrayList<String> result = new ArrayList<>();
		for(int i = 0 ; i < m ; i++) {
			String s = br.readLine();
			if(hSet.contains(s)) {
				result.add(s);
			}
		}
		
		Collections.sort(result);
		sb.append(result.size()).append("\n");
		for(int i = 0 ; i < result.size() ; i++) {
			sb.append(result.get(i)).append("\n");
		}
		System.out.println(sb);
	}
}