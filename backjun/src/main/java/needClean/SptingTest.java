package needClean;

public class SptingTest {
	public static void main(String[] args) {

	}
	public int solution(int[][] lotteries) {
        int answer = 0;
        //당첨자수, 구매사람, 당첨금액 순서
        //구매사람에 나 포함 안되어있음
        int m = lotteries.length; //복권수 
        double max = 0;
        int maxMoney = 0;
        for(int i = 0 ; i < m; i++) {
        	
        	double per = 100;
        	
        	double people = lotteries[i][0]; //당첨자
        	double buy = lotteries[i][1] + 1; //구매사람
        	int money = lotteries[i][2];
        	//확률 계산
        	if( people < buy ) {
        		per = (people / buy) * 100;
        	}
        	System.out.println(max + " " + per);
        	
        	if( max == per ) { //돈으로 따짐
        		if( maxMoney <  money ) {
        			answer = i + 1;
        		}
        	}else if ( max < per ) {
        		max = per; 
        		maxMoney = money;
        		answer = i + 1;
        	}
        	
        }


        return answer;
    }
}
