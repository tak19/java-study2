package d5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class d5_1248 {
	static boolean visit[];
	static int size;
	static node[] n;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1 ; t <= test ; t++) {
			sb.append("#" + t + " ");
			
			String[] info = br.readLine().split(" ");
			int v = Integer.parseInt(info[0]); //정점 개수
			int e = Integer.parseInt(info[1]); //간선 수
			int node1 = Integer.parseInt(info[2]); //정점1
			int node2 = Integer.parseInt(info[3]); //정점2
			
			String[] s = br.readLine().split(" ");
			int parent = 0;
			int child = 0;
			
			n = new node[v+1];
			for (int i = 1; i <= v; i++) {
                n[i] = new node(i);
            }
			
			
			//List<Integer> list[] = new ArrayList<Integer>()[v];
			//간선 입력받음
			for(int i = 0 ; i < e*2 ; i+=2) {
				parent = Integer.parseInt(s[i]);
				child = Integer.parseInt(s[i+1]);
				if(n[parent].left == 0) {
					n[parent].left = child;
				}else {
					n[parent].right = child;
				}
				n[child].parent = parent;
			}
			
			visit = new boolean[v+1];
			//루트 노드는 방문 처리함
			visit[1] = true;
			//node1의 부모 노드 저장함... 최종 부모 노드는 1
			int a = n[node1].parent;
			while(a != 1) {
				visit[a] = true;
				a = n[a].parent;
			}
			
			int b = n[node2].parent;
			while(!visit[b]) {
				b = n[b].parent;
			}
			
			//서브 노드 수 파악
			size = 0;
			size(n[b]);
			
			sb.append(b + " " + size + "\n");
			
		}
		System.out.println(sb);
		
	}
	//서브 노드 개수구함
	static void size(node nod) {
		//size는 총 노드 개수로 자기 자신만 체크하고 자식이 있으면 재귀해서 개수 파악
		size++;
		if( nod.left != 0 ) {
			size(n[nod.left]);
		}
		if(nod.right != 0) {
			size(n[nod.right]);
		}
	}

}
class node{
	int info;
	int parent;
	int left;
	int right;
	
	node(int info){
		this.info = info;
		this.parent = 0;
		this.left = 0;
		this.right =0;
	}
	
	
	
}

