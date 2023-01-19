package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class etc_08 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//split 과 StringTokenizer속도차이 -StringToken이 더빠름..
		String[] info = br.readLine().split(" ");
		//StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(info[0]); //n 수열크기
		int l = Integer.parseInt(info[1]); //길이 차이값!

		StringBuilder sb = new StringBuilder();

		String[] arr = br.readLine().split(" "); //수열을 입력받음...
		//st = new StringTokenizer(br.readLine()); //token에다가 수열 입력받음
		
		//첫번째 값은 수동으로 할당 
		Deque<Pos> dq = new ArrayDeque<>();
		Pos pos = new Pos(0, Integer.parseInt(arr[0]));
		dq.addLast(pos);
		sb.append(dq.peekFirst().data + " ");
		
		for(int i = 1 ; i < n; i++) {
			//차례로 입력됨.. 인덱스로 활용 , 즉, 인덱스와 데이터를 같이 넣음
			pos = new Pos(i, Integer.parseInt(arr[i]));

			//가장 앞에 있는 데이터의 인덱스와 자신의 인덱스 비교하여 범위 확인
			if( dq.peekFirst().index  < i - l + 1 ) {
				dq.removeFirst(); //인덱스 범위 초과 시 제거함
			}

			//데이터를 넣을때 값 비교 후 자기보다 크면 데이터 제거. 자기보다 작은 곳 뒤에 들어감
			/*
			//while 문을 쓰면? -- 2328ms
			//for문에서 while로 전환하자
			int now = dq.size()-1;
			while(now >= 0) {
				if ( dq.peekLast().data > pos.data ) {
					dq.removeLast(); 
				}else {
					break;
				}
				now--;
			}
			*/
			
			//for문 쓰면 - 2304ms
			for(int now = dq.size()-1; now >= 0; now--) {
				//데큐 끝에 존재하는 데이터가 현재 데이터 보다 크다면 제거 
				if ( dq.peekLast().data > pos.data ) {
					dq.removeLast(); 
				}else {
					break;
				}
			}
			
			//값을 제거 했기 때문에 이제 추가해준다.
			dq.addLast(pos);
			//추가한 데이터가 들어왔다면 제일 앞에 있는 데크의 데이터를 출력하면된다!
			sb.append(dq.peekFirst().data + " ");
			
		}
		
		
		System.out.println(sb);
	}

	static class Pos{
		int index;
		int data;

		Pos(int index, int data){
			this.index = index;
			this.data = data;
		}

	}
}