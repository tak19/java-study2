package competition;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MatKorCup1_A {
	
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int[] index = new int[N];
		String[] card = {"swimming","bowling","soccer"};
		//자기가 뽑은카드 출력
		for(int i = 0 ; i < N ; i++) {
			int ren = (int) (Math.random()*10) % 3; //0~2까지 난수 생성
			index[i] = ren;
			sb.append(card[ren]).append(" ");
			
		}
		System.out.println(sb);
		System.out.flush();
		//이제 컴터가 하나 뽑아줌 내꺼 제외하고
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder result = new StringBuilder();
		//컴퓨터가 준거를 확인하고 기존꺼랑 비교하고 값을 바꾸던지 결정
		for(int i = 0 ; i < N ; i++) {
			String com = st.nextToken();
			for(int j = 0 ; j < 3 ; j++) {
				//컴퓨터가 뽑은 카드가 아니라면
				if( !card[j].equals(com) && index[i] != j ) {
					result.append(card[j]).append(" ");
					break;
				}
			}
		}
		System.out.println(result);
		
	}
}