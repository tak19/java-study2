package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class dfs_01 {
	static boolean[] arr;
	static List<Integer>[] list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); //컴퓨터 수
		int e = Integer.parseInt(br.readLine()); //간선 수
		
		arr = new boolean[n+1];
		list = new ArrayList[n+1];
		for(int i = 0 ; i < list.length ; i ++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < e ; i++) {
			String[] s = br.readLine().split(" ");
			int par = Integer.parseInt(s[0]);
			int chi = Integer.parseInt(s[1]);
			
			if( !list[par].contains(chi) ) {
				list[par].add(chi);
			}
			if( !list[chi].contains(par) ) {
				list[chi].add(par);
			}
		}
		 bfs(1);
		 
		 int total=0;
		 //1번인 자기 자신은 제외하기 때문에 2부터 시작
		 for(int i = 2 ; i < arr.length; i++) {
			 if(arr[i]) {
				 total++;
			 }
		 }
		 System.out.println(total);
	}
	private static void bfs(int a) {
		if(arr[a]) {
			return;
		}else {
			arr[a] = true;
			for(int i = 0 ; i < list[a].size(); i++) {
				bfs(list[a].get(i));
			}			
		}
	}
}