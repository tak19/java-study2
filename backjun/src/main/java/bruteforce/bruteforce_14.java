package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class bruteforce_14 {
	static int N,M,result;
	static boolean[] visit;
	static int[] map,cntSum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int [101];
		cntSum = new int[101];
		Arrays.fill(cntSum, Integer.MAX_VALUE);

		//사다리
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int str = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[str] = end;
		}
		//뱀
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int str = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[str] = end;
		}
		result = Integer.MAX_VALUE;
		//게임시작
		start(1,0);

		System.out.println(result);
	}
	//게임시작함
	private static void start(int index, int cnt) {
		//System.out.println(index + " 방문  " + cnt + " 번쨰에");
		if( cnt >= result ) {
			return;
		}
		if( index == 100 ) {
			result = Math.min(result, cnt);
			return;
		}

		if( index >= 95 ) {
			result = Math.min(result, cnt+1);
			return;
		}
		for(int i = 6 ; i >= 1 ; i--) {
			//사다리나 뱀이 있다면 해당 칸으로 방문했는지 확인하고
			if( map[index+i] != 0 ) {
				//이동좌표
				int go = map[index+i];
				//System.out.println(index+i + "에서 사다리 탐: " + map[index+1]  + " 로 이동");
				//해당 이동좌표에 표시된 이동횟수보다 덜 이동했다면 걸로 이동함
				if( cntSum[go] >= cnt+1 ) {
					cntSum[go] = cnt+1;
					start(go, cnt+1);
				}
				
			}else {
				//아니면 그냥 주사위 나온칸으로감 + 횟수 작아야 이동
				if( cntSum[index+1] >= cnt+1 ) {
					cntSum[index+1] = cnt+1; 
					start(index+i, cnt+1);
				}


			}

		}

	}



}