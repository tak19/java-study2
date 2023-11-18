package kakao;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class level1_2023 {

	public static void main(String[] args) {
		//solution();
	}
	public int[] solution(String today, String[] terms, String[] privacies) {

		//today에서 년,월,일을 빼온다
		StringTokenizer st = new StringTokenizer(today,".");
		int nowYear = Integer.parseInt(st.nextToken());
		int nowMonth = Integer.parseInt(st.nextToken());
		int nowDay = Integer.parseInt(st.nextToken());
		//System.out.println("year: " + year+ " month: "+ month+ " day: "+day);

		//약관 정보를 저장함
		int[] info = new int[26];
		for(int i = 0 ; i < terms.length ; i++){
			//약관 정보 번호
			st = new StringTokenizer(terms[i]);
			info[st.nextToken().charAt(0)-'A'] = Integer.parseInt(st.nextToken());
		}

		//정답을 담을 List
		List<Integer> arrList = new ArrayList<Integer>();

		//약관 기간 파악하기
		for(int i = 0 ; i < privacies.length ; i++){
			st = new StringTokenizer(privacies[i]);
			String dayInfo = st.nextToken(); // 기간
			String tema = st.nextToken(); // 종류

			//현재 탐색하는 약관의 기간
			st = new StringTokenizer(dayInfo,".");
			int year = Integer.parseInt(st.nextToken());
			int month = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());

			//약관 기관이 1일일 경우 그 전 달로 넘겨줘야함!
			day -= 1;
			if( day == 0 ){
				day = 28;
				month -= 1;
				if( month == 0 ){
					year -= 1;
					month = 12;
				}
			}
			month += info[tema.charAt(0) - 'A']; //약관 기간을 더한 월 수
			year += Math.floor((month - 1) / 12);
			month = (month - 1) % 12 + 1; // 1을 더해서 1부터 12까지의 값으로 보정

			//파기해야할 정보인지 확인
			if( year < nowYear ){
				arrList.add(i+1);
				continue;
			}else if( year <= nowYear && month < nowMonth ){
				arrList.add(i+1);
				continue;
			}else if( year <= nowYear && month <= nowMonth && day < nowDay ){
				arrList.add(i+1);
				continue;
			}
		}
		int[] answer = new int[arrList.size()];
		int now = 0;
		for(Integer num : arrList){
			answer[now++] = num;
		}
		return answer;

	}
}