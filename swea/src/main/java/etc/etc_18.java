package etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * A,B 문 둘중에 하나씩 파악하면됨
 * 둘중 나가는 값이 큰값이 정답 가능성이 있는 값이다
 */
public class etc_18 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int N,size,result;
	static int[][] map;
	static List<Pos> person;
	static List<Pos> door;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			//배열 크기 입력받음
			N = Integer.parseInt(br.readLine());

			person = new ArrayList<>();
			door = new ArrayList<>();
			//지도입력받음
			map = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if( map[i][j] == 1 ) {
						//사람이라면
						person.add(new Pos(i,j));
					}else if( map[i][j] >= 2 ) {
						//문이라면
						door.add(new Pos(i,j,map[i][j]));
					}
				}
			}
			//사람 수
			int size = person.size();
			//System.out.println(person.size() + " " + door.size());
			result = Integer.MAX_VALUE;
			subSet(size);
			sb.append(result).append("\n");

		}
		System.out.println(sb);

	}
	//부분집합으로 구한다.
	private static void subSet(int size) {
		PriorityQueue<Integer> pqA = new PriorityQueue<>();
		PriorityQueue<Integer> pqB = new PriorityQueue<>();
		//부분집합
		for(int i = 0 ; i < (1 << size) ; i++) {
			//부분집합 구한놈 계산
			for(int j = 0; j < size; j++) {
				Pos pos = person.get(j);
				int dis = 0;
				int doorX = 0;
				int doorY = 0;
				//j번째 사람이 선택된 경우 - 첫번쨰 문으로감 - index = 0
				if( ( i & (1<<j)) != 0 ) {
					doorX = door.get(0).x;
					doorY = door.get(0).y;
					//첫번쨰 문과 멘헤튼거리 측정
					dis = Math.abs( doorX - pos.x ) + Math.abs( doorY - pos.y );
					pqA.add(dis);
				}else {
					//두번째 문으로감 - index = 1
					doorX = door.get(1).x;
					doorY = door.get(1).y;
					//두번쨰 문과 멘헤튼거리 측정
					dis = Math.abs( doorX - pos.x ) + Math.abs( doorY - pos.y );
					pqB.add(dis);
				}
			}
			//구한 부분집합으로 시뮬레이션해봄

			//A,B 계단을 따로 계산
			int remain = size; //현재 맵에 남아있는 사용자
			int[] Astairs = new int[3];
			int[] Bstairs = new int[3];
			int time = 0; //내려가는데 걸리는 시간
			int Alen = door.get(0).num;
			int Blen = door.get(1).num;
			while(true) {
				if( remain == 0 ) {
					boolean ck = true;
					for (int k = 0; k < 3; k++) {
						if ( Astairs[k] != 0) {
							ck = false;
							break;
						}
						if (Bstairs[k] != 0) {
							ck = false;
							break;
						}
					}
					if (ck) {
						break;
					}
				}
				//제한된 3 크기만큼 확인
				for(int k = 0 ; k < 3 ; k++) {
					//A출입에 들어갈 수 있고, 남은 인원이 있다면
					if( Astairs[k] == 0 ) {
						if( !pqA.isEmpty() ) {
							if( pqA.peek() <= time ) {
								//남은 인원 수 감소하고, 계단에 남은시간을 표시
								remain--;
								Astairs[k] = Alen;
								pqA.poll();
							}
						}
					}else {
						//계단을 이용중이라면
						Astairs[k]--;
						//내리고 다시 태울 수 있다면
						if( Astairs[k] == 0 ) {
							if( !pqA.isEmpty() ) {
								if( pqA.peek() <= time ) {
									remain--;
									Astairs[k] = Alen;
									pqA.poll();
								}
							}
						}
					}
					//B계단 인원 들어갈 수 있다면
					if( Bstairs[k] == 0 ) {
						if( !pqB.isEmpty() ) {
							if( pqB.peek() <= time ) {
								remain--;
								Bstairs[k] = Blen;
								pqB.poll();
							}
						}

					}else {
						//인원 차있다면
						Bstairs[k]--;

						if( Bstairs[k] == 0 ) {
							if( !pqB.isEmpty() ) {
								if( pqB.peek() <= time ) {
									remain--;
									Bstairs[k] = Blen;
									pqB.poll();
								}
							}
						}
					}


				}
				//for문 3회 반복후 time 증가
				time++;
			}

			if( result > time ) {
				result = time;
			}


		}

	}
	//좌표정보
	static class Pos implements Comparable<Pos>{
		int x,y,num;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Pos(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}

		@Override
		public int compareTo(Pos o) {
			return num - o.num;
		}

	}
}
