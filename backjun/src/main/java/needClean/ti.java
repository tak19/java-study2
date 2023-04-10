package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
<<<<<<< HEAD

public class ti {
	//상우하좌
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int N,M,K;
	static boolean[][] visit;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // map size
		M = Integer.parseInt(st.nextToken()); // player Su
		K = Integer.parseInt(st.nextToken()); // round
		
		//지도 입력받기
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
	}

	//범위 안인지
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}
	
	//좌표정보 - 위치, 기본능력치, 방향, 권총 저장
	static class Pos{
		int x,y,basic,dir,him;

		public Pos(int x, int y, int basic, int dir, int him) {
			this.x = x;
			this.y = y;
			this.basic = basic;
			this.dir = dir;
			this.him = him;
		}

		
		
	}
}
=======
/*
 * 물고기는 큐에 넣고 관리함  --> 나중에 복제를 위해  tem 큐도 같이 만든다.
 * int 배열을 통해 물고기가 몇마리 있는지 확인함
 * 상어가 이동하면 물고기를 가장 많이 제거 할  수있는 위치를 저장해뒀다. 해당위치의 물고기는 더이상 움직이지 않음
 */
public class ti {
	static int M, S;
    static Fish shark;
    static ArrayList<Integer>[][] grid;
    static Deque<Integer>[][] smell;
    static ArrayList<Fish> fishList;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sd = {-1, 2, 0, 6, 4};
    static class Fish{
        int r, c, d;
        Fish(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        grid = new ArrayList[4][4];
        smell = new LinkedList[4][4];
        for(int r=0; r<4; r++) {
            for(int c=0; c<4; c++) {
                grid[r][c] = new ArrayList<Integer>();
                smell[r][c] = new LinkedList<Integer>();
            }
        }
        
        for(int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken())-1;
            int fy = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken())-1;
            
            grid[fx][fy].add(s);
        }
        
        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken())-1;
        int sy = Integer.parseInt(st.nextToken())-1;
        shark = new Fish(sx, sy, -1);
        
        fishList = new ArrayList<Fish>();
        for(int s=0; s<S; s++) {
            copyFish();
            moveFish();
            moveShark(s);
            removeSmell(s);
            completeCopy();
        }
        
        int result = countFish();
        System.out.println(result);
    }
 
    private static void copyFish() {
        fishList.clear();
        for(int r=0; r<4; r++) {
            for(int c=0; c<4; c++) {
                for(int d:grid[r][c]) {
                    fishList.add(new Fish(r, c, d));
                }
            }
        }
        
    }
 
    private static void moveFish() {
        ArrayList<Integer>[][] tempGrid = new ArrayList[4][4];
        for(int r=0; r<4; r++) {
            for(int c=0; c<4; c++) {
                tempGrid[r][c] = new ArrayList<Integer>();
            }
        }
        
        for(int r=0; r<4; r++) {
            for(int c=0; c<4; c++) {
                for(int d:grid[r][c]) {
                    int nr = r, nc = c;
                    
                    boolean isMoved = false; 
                    for(int i=0; i<8; i++) {
                        int nd = ((d-i)+8)%8;
                        nr = r+dx[nd];
                        nc = c+dy[nd];
                        
                        if(nr<0 || nr>=4 || nc<0 || nc>=4) continue;
                        if(smell[nr][nc].size()!=0) continue;
                        if(nr==shark.r && nc==shark.c) continue;
                        
                        tempGrid[nr][nc].add(nd);
                        isMoved = true;
                        break;
                    }
                    if(!isMoved) tempGrid[r][c].add(d);
                }
            }
        }
        grid = tempGrid;        
    }
 
    private static void moveShark(int time) {
        int move = 0, maxMove=0;
        int fishNum = 0, maxFishNum = 0;
        int nr = shark.r, nc = shark.c;
        boolean isThree = false;
        
        boolean[][] visited;
        for(int i=1; i<5; i++) {
            visited = new boolean[4][4];
            
            nr+=dx[sd[i]];
            nc+=dy[sd[i]];
            if(nr<0 || nr>=4 || nc<0 || nc>=4) {
                nr = shark.r;
                nc = shark.c;
                continue;
            }
            
            move = i;
            visited[nr][nc] = true;
            fishNum = grid[nr][nc].size();
            for(int j=1; j<5; j++) {
                nr+=dx[sd[j]];
                nc+=dy[sd[j]];
                if(nr<0 || nr>=4 || nc<0 || nc>=4) {
                    nr-=dx[sd[j]];
                    nc-=dy[sd[j]];
                    continue;
                }
                
                move = (move*10)+j;
                if(!visited[nr][nc]) fishNum += grid[nr][nc].size();
                for(int k=1; k<5; k++) {
                    nr+=dx[sd[k]];
                    nc+=dy[sd[k]];
                    if(nr<0 || nr>=4 || nc<0 || nc>=4) {
                        nr-=dx[sd[k]];
                        nc-=dy[sd[k]];
                        continue;
                    }
                    
                    if(!visited[nr][nc]) fishNum += grid[nr][nc].size();
                    int finalFishNum = fishNum;
                    if(!visited[nr][nc]) fishNum -= grid[nr][nc].size();
                    
                    nr-=dx[sd[k]];
                    nc-=dy[sd[k]];
                    
                    if(isThree && maxFishNum >= finalFishNum) continue;
                    move = (move*10)+k;
                    maxMove = move;
                    maxFishNum = finalFishNum;
                    isThree = true;
                    move /= 10;
                }
                
                if(!visited[nr][nc]) fishNum -= grid[nr][nc].size();
                visited[nr][nc] = false;
                nr-=dx[sd[j]];
                nc-=dy[sd[j]];
                move /= 10;
            }
            
            visited[nr][nc] = false;
            nr = shark.r;
            nc = shark.c;
        }
        
        nr = shark.r;
        nc = shark.c;
        int idx=2;
        while(idx>=0) {
            int base = (int)Math.pow(10, idx--);
            int m = maxMove/base;
            maxMove %= base;
            
            nr+=dx[sd[m]];
            nc+=dy[sd[m]];
            
            if(grid[nr][nc].size()==0) continue;
            smell[nr][nc].add(time+2);
            grid[nr][nc].clear();
        }
        shark.r = nr;
        shark.c = nc;        
    }
 
    private static void removeSmell(int time) {
        for(int r=0; r<4; r++) {
            for(int c=0; c<4; c++) {
                while(!smell[r][c].isEmpty()) {
                    int s = smell[r][c].poll();
                    if(time-s < 0) {
                        smell[r][c].addFirst(s);
                        break;
                    }
                }
            }
        }
    }
 
    private static void completeCopy() {
        for(Fish f:fishList) {
            grid[f.r][f.c].add(f.d);
        }
    }
    
    private static int countFish() {
        int count = 0;
        for(int r=0; r<4; r++) {
            for(int c=0; c<4; c++) {
                count+=grid[r][c].size();
            }
        }
        return count;
    }
}
>>>>>>> ed1ef62377a4c83049234fc5ea67902acf1c919e
