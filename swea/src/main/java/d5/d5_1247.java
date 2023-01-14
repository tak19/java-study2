package d5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class d5_1247 {
	static int min; //최소거리 저장
	static boolean[] visit; //방문 확인
	static point home,office,costomer[]; //집,회사,고객 좌표
	static int dis; //이동거리 저장
	static int n; //고객 수 저장--> 깊이 계산을 위해 전역으로 선언함.
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1 ; t <= test ; t++) {
			
			sb.append("#" + t + " ");
			
			n = Integer.parseInt(br.readLine());
			String[] s = br.readLine().split(" ");
			//회사랑 집 먼저 입력받았다.
			office = new point(Integer.parseInt(s[0]),Integer.parseInt(s[1]));
			home = new point(Integer.parseInt(s[2]),Integer.parseInt(s[3]));
			
			//고객을 입력받음,,, j는 인덱스용 일회성 변수임
			costomer = new point[n];
			int j=4;
			for(int i = 0 ; i < n ; i++) {
				costomer[i] = new point(Integer.parseInt(s[j]),Integer.parseInt(s[j+1]));
				j+=2;
			}
			
			visit = new boolean[n];
			min = Integer.MAX_VALUE;
			//깊이와 거리는 0으로 초기화.. 회사부터 출발하기 때문에 회사좌표를 전달
			start(0,office,0);
			sb.append(min + "\n");
			
		}
		System.out.println(sb);
	}
	public static void start(int depth, point pre, int dis) {
		// 측정된 거리가 최소값을 넘었다면 해당 루트는 더이상 탐색할 필요가 없기때문에 return함
		if(min <= dis) {
			return;
		}
		
		//고객의 수만큼 방문 했다면 마지막 거리 계산후 최소값 확인하고 반환.. 마지막 거리는 집이기 때문에 집(home)으로 계산
		if( depth == n ) { 
			dis += Math.abs(pre.x - home.x) + Math.abs(pre.y - home.y);
			if(min > dis) {
				min = dis;
			}
		}
		
		//순열 계산 반복 시작
		for(int i = 0 ; i < n ; i++) {
			//방문하지 않았다면
			if(!visit[i]) { 
				visit[i] = true; //방문으로 표시
				start(depth+1, costomer[i], dis + Math.abs(pre.x - costomer[i].x) + Math.abs(pre.y - costomer[i].y) );
				visit[i] = false; //순열을 위해 false로 변경
				
			}
			
		}
		
		
	}
}

//각 좌표를 나타내기위해 point 클래스 만들었다.
class point{
	int x,y;
	point(int x , int y){
		this.x = x;
		this.y = y;
	}
}

