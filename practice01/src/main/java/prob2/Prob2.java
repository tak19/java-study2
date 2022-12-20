package prob2;

public class Prob2 {
	public static void main(String[] args) {
		
		for(int i = 1 ; i < 10 ; i++) {
			for(int j = i ; j < 10 + i ; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
}
