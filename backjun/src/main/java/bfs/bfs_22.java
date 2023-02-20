package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_22 {
	 //팔방이요~~
    static int[] dx = {0,0,-1,1,-1,-1,1,1 };
    static int[] dy = {1,-1,0,0,-1,1,-1,1 };
    static int[][] map;
    static boolean[][] visit;
    static int n,m;
    static Queue<Pos> q = new ArrayDeque<>();
    public static void main(String[] args) throws Exception
    {
       	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //좌표값
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = 0;
        visit = new boolean[n][m];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if( map[i][j] == 1 && !visit[i][j] ){
                    q.add(new Pos(i,j));
                    bfs();
                    result++;
                }
            }
        }
        System.out.println(result);
        


    }
    static void bfs(){
        while( !q.isEmpty() ){
            Pos pos = q.poll();
            int x = pos.x;
            int y = pos.y;

            for(int i = 0 ; i < 8 ; i++){
                int gox = x + dx[i];
                int goy = y + dy[i];

                if( canGo(gox,goy) && map[gox][goy] == 1){ //범위안이믄서 글자가?
                    if( !visit[gox][goy] ){ //방문안한 글자가?
                        visit[gox][goy] = true;
                        q.add(new Pos(gox,goy));
                    }
                }

            }


        }
    }
    //범위안에 들읏나?
    static boolean canGo(int x, int y){
        if( x >= 0 && x < n && y >= 0 && y < m){
            return true;
        }
        return false;
    }

    static class Pos{
        int x;
        int y;
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}