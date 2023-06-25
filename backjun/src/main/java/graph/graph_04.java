package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class graph_04 {
	static int V, E;
    static int[] arr;
    static String result;
    static boolean ck;
    static boolean[] visit;
	static List<Integer>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null; 
        StringBuilder sb = new StringBuilder();
        //해당 TC만큼 실행
        for(int test = 1 ; test <= T ; test++) {
        	st = new StringTokenizer(br.readLine());
        	// 정점과 간선 수
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            
            //정점 초기화 + 방문 배열 초기화
            list = new ArrayList[V+1];
            visit = new boolean[V+1];
            for(int i = 1 ; i <= V; i++) {
            	list[i] = new ArrayList<>();
            }
            //간선 입력받기
            for(int i = 0 ; i < E ; i++) {
            	st = new StringTokenizer(br.readLine());
            	int a = Integer.parseInt(st.nextToken());
            	int b = Integer.parseInt(st.nextToken());
            	//그래프 연결
            	list[a].add(b);
            	list[b].add(a);
            }
            //색상 정보를 확인할 int 배열
            arr = new int[V+1];
            result = "YES\n";
            ck = true; // 중간 탈출 확인용
            //이분 탐색해봄
            for(int i = 1 ; i <= V ; i++) {
            	if( !visit[i] ) { 
            		//System.out.println(test + " 번쨰 테케임: " + " " + i + " cnt:" +  cnt);
            		visit[i] = true;
            		arr[i] = 1;
            		dfs(i);
            	}
            	if( !ck ) {
            		break;
            	}
            }
            
            sb.append(result);
        }

        System.out.println(sb);
    }

    // 노드 탐색시작
    private static void dfs(int index) {
        //연결된 노드확인
        for(int tem : list[index]) {
        	//방문하지 않은 노드라면
        	if( !visit[tem] ) {
        		visit[tem] = true;
        		arr[tem] = (arr[index] + 1) % 2;
        		dfs(tem);
        	}else {
        		//방문 했던 곳이라면 이전값과 현재 방문 값의 색상 정보(0or1)정보가 달라야함
        		if( arr[tem] == arr[index] ) {
        			result = "NO\n";
        			ck = false;
        		}
        	}
        }
    }
}