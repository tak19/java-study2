package etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class etc_33 {
	static int N;
	static int[][] tree;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringBuilder sb = new StringBuilder();
		//노드 개수 입력받기
		N = Integer.parseInt(br.readLine());
		tree = new int[26][2];
		//트리 노드 정보입력받음
		for(int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int node = st.nextToken().charAt(0)-'A';
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			//연결정보 입력
			if( left == '.' ) {
				tree[node][0] = -1;
			}else {
				tree[node][0] = left - 'A';
			}
			if( right == '.' ) {
				tree[node][1] = -1;
			}else {
				tree[node][1] = right - 'A';
			}
		}
		Pre(0);
		sb.append("\n");
		In(0);
		sb.append("\n");
		Post(0);
		
		System.out.println(sb);
		
	}
	//전
	private static void Pre(int now) {
		if( now == -1 ) {
			return;
		}
		sb.append( (char)(now + 'A'));
		Pre(tree[now][0]);
		Pre(tree[now][1]);
	}
	//중
	private static void In(int now) {
		if( now == -1 ) {
			return;
		}
		In(tree[now][0]);
		sb.append( (char)(now + 'A'));
		In(tree[now][1]);
	}
	//후
	private static void Post(int now) {
		if( now == -1 ) {
			return;
		}
		Post(tree[now][0]);
		Post(tree[now][1]);
		sb.append( (char)(now + 'A'));
	}

}