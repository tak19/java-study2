package simul;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 비어있는 칸 중에 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 이동한다.
 * -> 인접 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
 *  -> 행의 번호가 가장 작은 칸으로, 열의 번호가 가장 작은 칸으로 자리 정한다. -- 델타로 해결
 */

public class simul_16 {
	//인접한지 확인하기 위해
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	static int N;
	static int[][] map;
	static Student[] student;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //반 크기

		student = new Student[N*N];
		StringTokenizer st = null;
		//학생들 우선 순위 입력받음
		for(int i = 0 ; i < N*N ; i++) {
			student[i] = new Student();
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			//힉생 번호와 관심있는 학생 정보를 정장함
			student[i].num = num;
			student[i].inter[0] = Integer.parseInt(st.nextToken());
			student[i].inter[1] = Integer.parseInt(st.nextToken());
			student[i].inter[2] = Integer.parseInt(st.nextToken());
			student[i].inter[3] = Integer.parseInt(st.nextToken());
		}

		map = new int[N][N];
		//자리 배치함
		for(int stu = 0 ; stu < N*N ; stu++) { 
			//interCnt, emptyCnt는 0이 아닌 -1로 초기화 시켜줘야함 -> 주변이 0인경우도 있기때문
			//해당 설정을 초기에 0으로 해서 문제 틀림
			int interCnt = -1;
			int emptyCnt = -1;
			int indexX = 0;
			int indexY = 0;
			//탐색 시작
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					int interTem = -1;
					int emptyTem = -1;
					//내가 앉을 수 있는 자리라면
					if( map[i][j] == 0 ) {
						int[] tem = interSearch(i,j,stu);
						interTem = tem[0];
						emptyTem = tem[1];
						//현재 자리가 기존자리와 관심있는 친구 수가 크다면
						if( interTem > interCnt ) {
							interCnt = interTem; // 인접 친구 갱신
							emptyCnt = emptyTem; // 빈자리 갱신
							indexX = i; 
							indexY = j;

						}else if( interTem == interCnt ) {
							//기존자리와 관심있는 친구 수가 동일 + 기존자리와 빈 자리수가 크다면 변경
							if( emptyTem > emptyCnt ) {
								//빈자리 수 갱신하고 좌표값 할당함
								emptyCnt = emptyTem;
								indexX = i; 
								indexY = j;
							}
						}
					}

				}
			}
			//탐색 끝나고 나면 indexX,indexY에 해당 친구의 자리 좌표가 저장되어있음
			map[indexX][indexY] = student[stu].num;
			student[stu].x = indexX;
			student[stu].y = indexY;
		}

		System.out.println(calPoint());

	}

	//점수 계산하기
	private static int calPoint() {
		int result = 0;
		//자리 확인해본다
		for(int stu = 0 ; stu < N*N ; stu++) {
			int cnt = 0;
			for(int i = 0 ; i < 4 ; i++) {
				int gox = student[stu].x + dx[i];
				int goy = student[stu].y + dy[i];

				if( canGo(gox, goy) ) {
					for(int ck = 0 ; ck < 4 ; ck++) {
						if( map[gox][goy] == student[stu].inter[ck] ) {
							cnt++;
						}
					}
				}
			}
			result += (int)Math.pow(10, cnt-1);
		}
		return result;
	}

	//주변에 관심 있는 친구들이 있는지 확인
	private static int[] interSearch(int x, int y, int stu) {
		int cnt = 0;
		int ecnt = 0;
		//주위 자리에 관심 친구가 있는지 확인함
		for(int i = 0 ; i < 4 ; i++) {
			int gox = x + dx[i];
			int goy = y + dy[i];
			//범위체크
			if( canGo(gox,goy) ) {
				//현재 관심 목록에 있는 친구들인 지확인한다.
				for(int ck = 0 ; ck < 4 ; ck++) {
					if( map[gox][goy] == student[stu].inter[ck] ) {
						cnt++;
					}
				}
				//빈자리라면
				if( map[gox][goy] == 0 ) {
					ecnt++;
				}
			}

		}
		int[] re = {cnt,ecnt};
		return re;
	}
	//범위 체크
	private static boolean canGo(int gox, int goy) {
		if( gox >= 0 && gox < N && goy >= 0 && goy < N ) {
			return true;
		}
		return false;
	}
	//학생 정보를 저장
	static class Student{

		int num,x,y;
		int[] inter = new int[4];

		public Student() {
			// TODO Auto-generated constructor stub
		}
		public Student(int num, int[] inter) {
			this.num = num;
			this.inter = inter;
		}
	}
}
