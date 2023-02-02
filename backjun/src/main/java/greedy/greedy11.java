package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class greedy11 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); //크레인 수
		List<Integer> crain = new ArrayList<>();

		StringTokenizer st =new StringTokenizer(br.readLine());
		for(int i = 0 ; i< n ; i ++) {
			crain.add(Integer.parseInt(st.nextToken()));
		}
		
		int boxCnt = Integer.parseInt(br.readLine()); //화물 수
		st = new StringTokenizer(br.readLine());
		List<Integer> weight = new ArrayList<>();
		
		//박스 무게 입력받음
		for(int i = 0 ; i< boxCnt ; i ++) {
			weight.add(Integer.parseInt(st.nextToken())) ;
		}
		
		//크레인과 화물을 내림차순으로 정렬함!!
		crain.sort(Collections.reverseOrder());
		weight.sort(Collections.reverseOrder());
		
		//가장 무거운 화물을 가장 큰 크레인이 못들면 바로 종료
		if(crain.get(0)<weight.get(0)){
            System.out.println(-1);
            return;
        }

        int day = 0; //날짜 초기화
        while(!weight.isEmpty()){ //화물을 무거운 순서대로 하나씩 뺀다.
            int boxIdx = 0, crainIdx = 0;

            //크레인을 하나씩 방문
            while( crainIdx < n ){ // 크레인 인덱스가 크레인 수보다 작을때까지, 즉 크레인만큼 
                if(boxIdx == weight.size()) //현재 화물인덱스와 화물의 수랑 같아지면-- 화물 모두 탐색
                    break;
                else if(crain.get(crainIdx) >= weight.get(boxIdx)){ //크레인에 화물 적재 가능하다면 적재함
                	weight.remove(boxIdx);
                    crainIdx++;
                }
                else
                    boxIdx++;
            }

            day++;
        }

        System.out.println(day);



	}
}
