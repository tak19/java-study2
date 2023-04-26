package kakao;

import java.util.ArrayDeque;
import java.util.Queue;

public class level2_2022 {
	static Queue<Integer> Q1;
	static Queue<Integer> Q2;
	static int[] queue1 = {1, 2, 1, 2};
	static int[] queue2 = {1, 10, 1, 2};
	public static void main(String[] args) {
		int answer = -2;
		//큐 한쪽의 길이 --> 최대 시행 횟수가 될껄? 아닐 수도
		int len = queue1.length;
		
		Q1 = new ArrayDeque<>();
		Q2 = new ArrayDeque<>();
		//각 원소의 합을 계산함
		long q1 = 0;
		long q2 = 0;
		long sum = 0; //모든 배열의 합
		for(int i = 0 ; i < len ; i++){
			//전체합과 각각의 큐의 합을 계산함
			sum += queue1[i] + queue2[i];
			q1 += queue1[i];
			q2 += queue2[i];
			Q1.offer(queue1[i]);
			Q2.offer(queue2[i]);
		}
		//합이 홀수라면 합을 같게 나눌 수 없는 경우임
		if( sum % 2 == 1 ){
			System.out.println(-1);
		}

		int cnt = 0; //시행 횟수
		int tem = 0;
		//일단 반으로 나눌 수 있는 확률이 존재 - 짝수
		while(cnt < (len*3) ){
			
			//양쪽값이 같아진다면 종료
			if( q1 == q2 ){
				break;
			}
			//q1값이 더크다면 q2로 하나 보냄
			if( q1 > q2 ){
				tem = Q1.poll();
				q2 += tem;
				q1 -= tem;
				Q2.offer(tem);
			}else{
				tem = Q2.poll();
				q1 += tem;
				q2 -= tem;
				Q1.offer(tem);
			}
			cnt++;

		}

		answer = cnt == (len*3) ? -1 : cnt;

		System.out.println(answer);
	}


}

