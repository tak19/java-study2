package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dfs_07 {
	//사방이요~~
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[][] map;
    static boolean[][] visit;
    static int area;
    static int n,m;

    public static void main(String[] args) throws Exception
    {
       	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //도화지 크기
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
        int cnt=0;
        visit = new boolean[n][m];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if( map[i][j] == 1 && !visit[i][j] ){
                    area = 0;
                    cnt++; //그림 개수임
                    dfs(i , j);
                    result = Math.max(area,result);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append("\n").append(result);
        System.out.println(sb);
        


    }
    static void dfs(int x, int y){
        area += 1;
        visit[x][y] = true;
        for(int i = 0 ; i < 4 ; i++){
            int gox = x + dx[i];
            int goy = y + dy[i];

            if( canGo(gox,goy) && map[gox][goy] == 1 ){
                if( !visit[gox][goy] ){
                	visit[gox][goy] = true;
                    dfs(gox,goy);
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