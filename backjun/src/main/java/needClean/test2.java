package needClean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * BFS 탐색후 Prim 알고리즘 적용함
 * 
 */
public class test2 {
	static int R;
    static int C;
    static int N;
    
    static int[][] map;
    
    static int[] d_r = {-1, 0, 1, 0};
    static int[] d_c = {0, 1, 0, -1};
    
    static ArrayList<int[][]> mapList = new ArrayList<>();
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        map = new int[R][C];
        
        for(int i = 0; i < R; i++) {
            String inputs = br.readLine();
            for(int j = 0; j < C; j++) {
                if(inputs.charAt(j) == 'O') {
                    map[i][j] = 3;
                }
                else {
                    map[i][j] = -1;
                }
            }
        }
        if(N == 1) {
            result(map);
        }else {
            for(int t = 1; t <= 5; t++) {
                countDown();
                if(t % 2 == 0) {
                    setBomb();
                }
                bomb();
                if(t >= 2) {
                    mapList.add(copy());
                }
            }
            result(mapList.get((N-2)%4));
        }
        
        
    }
    
    private static int[][] copy() {
        int[][] tempMap = new int[R][C];
        
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                tempMap[i][j] = map[i][j];
            }
        }
        
        return tempMap;
    }

    private static void setBomb() {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == -1) {
                    map[i][j] = 3;
                }
            }
        }
    }

    private static void bomb() {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == 0) {//폭발
                    map[i][j] = -1;
                    for(int n = 0; n < 4; n++) {
                        int n_r = i + d_r[n];
                        int n_c = j + d_c[n];
                        
                        if(rangeCheck(n_r, n_c)) {
                            map[n_r][n_c] = -1;
                        }
                    }
                }
            }
        }
    }

    private static boolean rangeCheck(int n_r, int n_c) {
        if(0<=n_r && 0<= n_c && n_r < R && n_c < C) {
            if(map[n_r][n_c] != 0) {
                return true;
            }
        }
        return false;
    }

    private static void countDown() {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] > 0) {
                    map[i][j]--;
                }
            }
        }        
    }

    private static void result(int[][] tempMap) {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(tempMap[i][j] == -1) {
                    System.out.print('.');
                }else {
                    System.out.print('O');
                }
            }
            System.out.println();
        }
    }
}