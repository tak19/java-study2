package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class etc_11 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
//		//Comparator 오버라이딩함
//		PriorityQueue q = new PriorityQueue<Integer>(new Comparator<Integer>()  {
//
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				//절대값이 같지 않다면 오름차순
//				return Math.abs(o1) != Math.abs(o2) ? Math.abs(o1) -Math.abs(o2) : o1 - o2; 
//				//if( Math.abs(o1) == Math.abs(o2))
//			}
//			
//		});
		
		//람다 표현식 활용
		PriorityQueue<Integer> q = new PriorityQueue<>( (o1,o2) -> {
			return Math.abs(o1) != Math.abs(o2) ? Math.abs(o1) -Math.abs(o2) : o1 - o2; 
		} );		
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < n ; i++) {
			int tem = Integer.parseInt(br.readLine());
			
			if(  tem == 0 && !q.isEmpty() ) {
				sb.append(q.poll() + "\n");
				continue;
			}else if(tem == 0 && q.isEmpty() ) {
				sb.append(0 + "\n");
				continue;
			}
			q.add(tem);
			
		}
		System.out.println(sb);
	}
}
