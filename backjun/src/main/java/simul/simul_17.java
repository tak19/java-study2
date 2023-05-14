package simul;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 입력: NxN 지도, M개의 나무, K년 
 * 봄: 자기 나이만큼 양분먹고, 나이+1(나이어린 나무부터) , 여름: 봄에 죽은나무가 양분으로변함- 나이를 2로 나눈값만큼
 * 가을: 나무가 번식함 -- 나이는 5의배수, 팔방으로 번식      겨울: 양분을 추가함 --입력으로 주어진 칸만큼 
 */
public class simul_17 {
	static int[] dx = { 0,  0, -1, 1,  1, 1, -1, -1 };
	static int[] dy = { 1, -1,  0, 0, -1, 1,  1, -1 };
	static int N,M,K;
	static int[][] energy,nowEnergy;
	static PriorityQueue<Pos> pq;
	static Queue<Pos> temTree,dieTree;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//지도크기, 나무 수, 년수 입력받음
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		energy = new int[N][N];
		nowEnergy = new int[N][N];
		//양분 지도 입력 받음
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				energy[i][j] = Integer.parseInt(st.nextToken());
				nowEnergy[i][j] = 5;
			}
		}
		// 나이가 어린 나무부터 나오도록 우선순위 큐 선언
		pq = new PriorityQueue<Pos>( (o1,o2) -> {
			return o1.age - o2.age;
		});
		
		//나무 입력받음
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			//큐에 나무 넣어줌
			pq.offer(new Pos(x-1,y-1,age));
		}
		//해당 년수 만큼 계산
		while( K --> 0 ) {
			spring();
			summer();
			fall();
			winter();
			copyQueue();
		}
		System.out.println(pq.size());
		
	}
	//겨울 - 양분 추가됨
	private static void winter() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				nowEnergy[i][j] += energy[i][j];
			}
		}
	}
	//가을 - 번식함
	private static void fall() {
		int size = temTree.size();
		for(int i = 0 ; i < size ; i++) {
			Pos pos = temTree.poll();
			int x = pos.x;
			int y = pos.y;
			int need = pos.age;
			//번식을 못하는 나무라면 다시 넣어준다.
			if( need % 5 != 0 ) {
				temTree.offer(new Pos(x,y,need));
				continue;
			}
			//주위 8칸에 아이들을 낳음
			for(int dir = 0 ; dir < 8 ; dir++) {
				int gox = x + dx[dir];
				int goy = y + dy[dir];
				//범위 안인지 판단
				if( canGo(gox,goy) ) {
					//해당 위치에 나이가 1인 나무가 생성됨
					temTree.offer(new Pos(gox,goy,1));
				}
			}
			//마지막에 자기 자신도 넣어줘야함
			temTree.offer(new Pos(x,y,need));
		}
		
	}

	//여름 - 죽은 나무 양분으로
	private static void summer() {
		//죽은 나무 양분으로
		while( !dieTree.isEmpty() ) {
			Pos pos = dieTree.poll();
			int x = pos.x;
			int y = pos.y;
			int need = pos.age / 2;
			//죽은 나무 위치에 해당 나무의 나이 /2 만큼의 양분을 추가
			nowEnergy[x][y]+= need;
		}
	}
	//봄 - 양분 먹기
	private static void spring() {
		temTree = new ArrayDeque<>();
		dieTree = new ArrayDeque<>();
		//양분 먹기
		while( !pq.isEmpty() ) {
			Pos pos = pq.poll();
			int x = pos.x;
			int y = pos.y;
			int need = pos.age;
			
			//자신의 나이만큼 양분을 먹는다. - 양분이 그만큼 있는지 체크, 부족하면 죽음,,,
			if( need > nowEnergy[x][y] ) {
				dieTree.offer(new Pos(x,y,need));
				continue;
			}
			//해당 땅의 양분을 감소 키기고, 다른 큐에 살아남은 나무를 저장시킴
			nowEnergy[x][y] -= need;
			temTree.offer(new Pos(x,y,need+1));
		}

	}
	
	//tem에 있는 나무들 다시 pq로 옮김
	private static void copyQueue() {
		while( !temTree.isEmpty() ) {
			pq.offer(temTree.poll());
		}
	}
	
	//범위 안인지
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}
	
	//나무 정보를 담을 클래스
	static class Pos{
		int x,y,age;

		public Pos(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
		
	}
}