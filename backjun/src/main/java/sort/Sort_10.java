package sort;
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

public class Sort_10 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Point> pq = new PriorityQueue<>( (o1,o2) -> {
			return o2.point - o1.point;
		});
		
		int cnt = 1;
		for(int i = 0 ; i < 8 ; i++) {
			int point = Integer.parseInt(br.readLine());
			pq.offer(new Point(point, cnt++));
		}
		
		StringBuilder sb = new StringBuilder();
		int result = 0;
		int[] arr = new int[5];
		for(int i = 0 ; i < 5 ; i++) {
			Point p = pq.poll();
			result += p.point;
			arr[i] = p.cnt;
		}
		Arrays.sort(arr);
		sb.append(result).append("\n");
		for(Integer i : arr) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
		
		
	}
	
	static class Point{
		int point;
		int cnt;
		
		public Point(int point, int cnt) {
			this.point = point;
			this.cnt = cnt;
		}
		
	}
}
	