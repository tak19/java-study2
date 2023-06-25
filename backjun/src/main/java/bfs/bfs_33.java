package bfs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.crypto.CipherInputStream;

public class bfs_33 {
	//물통 이동 정보 저장 -- A->B,C || B->A,C || C ->A,B
	static int[]   send   = { 0, 0, 1, 1, 2, 2 };
	static int[] receiver = { 1, 2, 0, 2, 0, 1 };
	static int[] arr;
	static int maxA,maxB,maxC;
	static boolean[][] visit;
	static List<Integer> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //각 물통에 담을 수 있는 최대값 저장
        maxA = Integer.parseInt(st.nextToken());
        maxB = Integer.parseInt(st.nextToken());
        maxC = Integer.parseInt(st.nextToken());
        arr = new int[]{maxA, maxB, maxC};
        
        list = new ArrayList<>();
        bfs();
        //정렬 수행
        Collections.sort(list);
        
        StringBuilder sb = new StringBuilder();
        for( Integer i : list ) {
        	sb.append(i).append(" ");
        }
        System.out.println(sb);
        
    }
    //탐색 시작함
    private static void bfs() {
    	Queue<water> q = new ArrayDeque<>();
    	//A,B 물통의 양만 확인하면됨
    	visit = new boolean[maxA+1][maxB+1];
    	//초기 상태 저장 후 방문처리
    	q.add(new water(0,0));
    	visit[0][0] = true;
    	list.add(maxC);
    	
		//큐가 비어있지 않다면
    	while( !q.isEmpty() ) {
    		water w = q.poll();
    		int a = w.a;
    		int b = w.b;
    		int c = maxC - (a + b);
    		
    		//a,b 물통에 물 부어
    		for(int i = 0 ; i < 6 ; i++) {
    			int[] next = { a , b , c }; //현재 물통 정보를 담은 배열
    			//받을 물통에 물을 일단 다 따른다.
    			next[receiver[i]] += next[send[i]];
    			next[send[i]] = 0;
    			//물이 넘친다면
    			if( next[receiver[i]] > arr[receiver[i]] ) {
    				//해당 크기만큼만 따르고 나머지는 따랐던 통에 다시 넣어줌 - 순서 중요
    				next[send[i]] = next[receiver[i]] - arr[receiver[i]]; 
    				next[receiver[i]] = arr[receiver[i]];
    			}
    			//해당 물병 경험이 없다면
    			if( !visit[next[0]][next[1]] ) {
    				visit[next[0]][next[1]] = true;
    				q.add(new water(next[0], next[1]));
    				if( next[0] == 0 ) {
    					list.add(next[2]);
    				}
    			}
    			
    		}
    	}
	}
	//물통 정보 저장
    static class water{
    	int a,b;

		public water(int a, int b) {
			this.a = a;
			this.b = b;
		}
    }
}