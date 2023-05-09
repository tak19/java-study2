package study;

import java.util.Arrays;
import java.util.PriorityQueue;

public class etc_01 {
	
	public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->{
            return o2 - o1;
        });
        int ch = 1;
        //정렬함
        Arrays.sort(tangerine);
        for(int i = 0 ; i < tangerine.length-1; i++){
            //다른 귤이라면
            if( tangerine[i] - tangerine[i+1] < 0 ){
                pq.offer(ch);
                ch = 1;
            }else{
                ch++;
            }
        }
        //마지막 귤까지 넣어
        pq.offer(ch);
        
        //하나씩 빼면서 무게 확인함
        while( !pq.isEmpty() ){
            int tem = pq.poll();
            k -= tem;
            answer++;
            //다 담았으면 수고
            if(k <= 0){
                break;
            }
        }
        
        return answer;
    }
	
}
