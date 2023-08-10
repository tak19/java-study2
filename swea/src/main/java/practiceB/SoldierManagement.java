package practiceB;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SoldierManagement {
	private final static int CMD_INIT				= 1;
	private final static int CMD_HIRE				= 2;
	private final static int CMD_FIRE				= 3;
	private final static int CMD_UPDATE_SOLDIER		= 4;
	private final static int CMD_UPDATE_TEAM		= 5;
	private final static int CMD_BEST_SOLDIER		= 6;
	private final static UserSolution usersolution = new UserSolution();

	private static boolean run(BufferedReader br) throws Exception
	{
		StringTokenizer st;

		int numQuery;

		int mID, mTeam, mScore, mChangeScore;

		int userAns, ans;

		boolean isCorrect = false;

		numQuery = Integer.parseInt(br.readLine());

		for (int q = 0; q < numQuery; ++q)
		{
			st = new StringTokenizer(br.readLine(), " ");

			int cmd;
			cmd = Integer.parseInt(st.nextToken());

			switch(cmd)
			{
			case CMD_INIT:
				usersolution.init();
				isCorrect = true;
				break;
			case CMD_HIRE:
				mID = Integer.parseInt(st.nextToken());
				mTeam = Integer.parseInt(st.nextToken());
				mScore = Integer.parseInt(st.nextToken());
				usersolution.hire(mID, mTeam, mScore);
				break;
			case CMD_FIRE:
				mID = Integer.parseInt(st.nextToken());
				usersolution.fire(mID);
				break;
			case CMD_UPDATE_SOLDIER:
				mID = Integer.parseInt(st.nextToken());
				mScore = Integer.parseInt(st.nextToken());
				usersolution.updateSoldier(mID, mScore);
				break;
			case CMD_UPDATE_TEAM:
				mTeam = Integer.parseInt(st.nextToken());
				mChangeScore = Integer.parseInt(st.nextToken());
				usersolution.updateTeam(mTeam, mChangeScore);
				break;
			case CMD_BEST_SOLDIER:
				mTeam = Integer.parseInt(st.nextToken());
				userAns = usersolution.bestSoldier(mTeam);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans) {
					isCorrect = false;
				}
				break;
			default:
				isCorrect = false;
				break;
			}
		}

		return isCorrect;
	}

	public static void main(String[] args) throws Exception
	{
		int TC, MARK;

		System.setIn(new java.io.FileInputStream("res/sample_25_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase)
		{
			int score = run(br) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
	static class UserSolution
	{
		LinkedList[] list = new LinkedList[6];
		public void init()
		{
			for(int i =1 ; i <= 5 ; i++) {
				list[i] = new LinkedList();
			}
		}

		public void hire(int mID, int mTeam, int mScore)
		{
			list[mTeam].getNewNode(mID, mScore);
		}

		public void fire(int mID)
		{
			for(int i = 1 ; i <= 5 ; i++) {
				list[i].fire(mID);
			}
		}

		public void updateSoldier(int mID, int mScore)
		{
			for(int i = 1 ; i <= 5 ; i++) {
				list[i].udpateSoldier(mID, mScore);
			}
		}

		public void updateTeam(int mTeam, int mChangeScore)
		{
			list[mTeam].updateTeam(mChangeScore);
		}

		public int bestSoldier(int mTeam)
		{
			return list[mTeam].bestSoldier();
		}
	}
	// 정점을 표현
	static class Node{
		int mID,mScore;
		Node next;
		//데이터를 기반으로 추가하면 데이터를 입력하고 다음 노드는 null값줌
		public Node(int mID, int mScore) {
			this.mID = mID;
			this.mScore = mScore;
			this.next = null;
		}
	}
	// 연결 리스트 구현
	static class LinkedList{
		Node head;
		Node tail;
		Node[] nodeArr;
		int maxScore,maxID;
		int nodeCnt;

		//링크드 리스크 초기화
		public LinkedList() {
			head = null;
			nodeArr = new Node[100000];
			nodeCnt = 0;
		}
		// 병사 추가
		void getNewNode(int mID, int mScore) {
			nodeArr[nodeCnt++] = new Node(mID,mScore);
			//최대값 갱신해줌 -- 스코어가 같다면 ID값이 높은 놈이 입력됨
			updateMaxScore(mID,mScore);
			//return nodeArr[nodeCnt++];
		}
		// 최고점수 갱신
		private void updateMaxScore(int mID, int mScore) {
			if( maxScore < mScore ) {
				maxID = mID;
				maxScore = mScore;
			}else if( maxScore == mScore ) {
				maxID = (maxID < mID ? mID : maxID);
			}
		}
		// 병사 점수 변경
		void udpateSoldier(int mID, int mScore) {

			for( Node node = head; node != null; node = node.next ) {
				//해당 아이디의 노드라면..
				if( node.mID == mID ) {
					node.mScore = mScore;
					//해당 아이디의 병사가 최고점 병사였다면
					if( node.mID == maxID ) {
						maxScore = 0;
						updateMaxID();
					}
					break;
				}
			}
		}
		//최고점 병사 다시 찾기
		private void updateMaxID() {
			for( Node node = head; node != null; node = node.next ) {
				updateMaxScore(node.mID,node.mScore);
			}
		}
		// 해당 팀 전부 점수 변경
		void updateTeam(int mScore) {
			maxScore = 0;
			for( Node node = head; node != null; node = node.next ) {
				int point = node.mScore + mScore;
				if( point > 5 ) {
					point = 5;
				}else if( point < 1 ) {
					point = 1;
				}
				node.mScore = point;
			}
			updateMaxID();
		}

		// 해당 팀 전부 점수 변경
		void fire(int mID) {
			Node preNode = null;
			// mID이전 노드를 fireID 해당하는 다음 노드와 연결 시켜줌
			for( Node node = head; node != null; node = node.next ) {
				if( node.mID == mID ) {
					preNode.next = node.next;
					break;
				}
				preNode = node;
			}
			if( mID == maxID ) {
				updateMaxID();
			}
		}
		// 평판 좋은 병사 붙임
		int bestSoldier() {
			return maxID;
		}
	}
}