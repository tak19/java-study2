package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class dfs_06 {
	static List<Integer>[] list;
	static boolean[] visit;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int n = Integer.parseInt(info[0]); // 컴퓨터 수
		int m = Integer.parseInt(info[1]); // 반복 수

		list = new ArrayList[n + 1];
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		// 연결 입력받음
		for (int i = 0; i < m; i++) {
			String[] s = br.readLine().split(" ");
			list[Integer.parseInt(s[1])].add(Integer.parseInt(s[0]));
		}
		int max = 0;
		int[] arr = new int[n + 1];
		for (int i = 1; i < list.length; i++) {
			cnt = 0;
			visit = new boolean[n + 1];
			dfs(i);
			max = Math.max(max, cnt);
			arr[i] = cnt;
		}
		//System.out.println(max);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (max == arr[i]) {
				sb.append(i + " ");
			}
		}
		System.out.println(sb);
	}

	private static void dfs(int num) {

		Queue<Integer> q = new LinkedList<>();
		// 리스트 순회함! --> 1부터 리스트 배열 끝까지..
		visit[num] = true;
		q.offer(num);

		while (!q.isEmpty()) {
			int x = q.poll();
			for (int sea : list[x]) {
				if (!visit[sea]) {
					cnt++;
					visit[sea] = true;
					q.offer(sea);

				}
			}
		}

	}

}
