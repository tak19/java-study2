package dp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DP_12 {
	static int N,result;
	static int[] arr,dis;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		//미로 배열 입력받기
		arr = new int[N+1];
		dis = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dis[i] = Integer.MAX_VALUE;
		}
		dis[1] = 0;
		for(int i = 1 ; i <= N ; i++) {
			//갈 수 없는 경우 제외
			if( dis[i] >= Integer.MAX_VALUE ) {
				continue;
			}
			//기존의 cnt와 이전에서 온 cnt+1의 최소값중 선택
			for(int j = 1 ; j <= arr[i] ; j++) {
				if( i + j <= N ) {
					dis[i+j] = Math.min(dis[i+j], dis[i] + 1);
				}else {
					dis[N] = Math.min(dis[N], dis[i] + 1);
				}
				
			}
		}
		
		result = dis[N] == Integer.MAX_VALUE ? -1 : dis[N];
		System.out.println(result);
	}

}