package prob2;

public class SmartPhone extends MusicPhone{

	@Override
	public void execute(String function) {
		// super.execute(function);
		if (function.equals("음악")) {
			downMusic();
			
		} else if(function.equals("앱")){
			goApp();
		}else {
			super.execute(function);
		}
	}

	private void goApp() {
		System.out.println("앱실행");
		
	}

	private void downMusic() {
		// TODO Auto-generated method stub
		System.out.println("다운로드해서 음악재생");
	}
	
}
