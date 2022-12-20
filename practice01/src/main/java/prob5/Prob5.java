package prob5;

public class Prob5 {

	public static void main(String[] args) {
		String n = "";
		int sum;
		for(int i = 1 ; i < 100 ; i ++) {
			sum = 0;
			n = String.valueOf(i);
			sum += count(n,'3');
			sum += count(n,'6');
			sum += count(n,'9');
			
			if(sum > 0 ) {
				System.out.print(n +" " );
				for(int j=0 ; j < sum ; j++) {
					System.out.print("짝");
				}
				System.out.println();
			}
			
			
		}
	}
	
	public static int count(String s, char c) {
		
		int cnt = 0;
		for(int i = 0 ; i < s.length(); i++) {
			if(s.charAt(i) == c)
				cnt++;
		}
		
		return cnt;
		
	}
}
