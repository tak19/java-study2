package prob05;


//여길 고쳐야해!!!!
public class MyBase extends Base {

	@Override
	public void day() {
		//super.day();
		System.out.println("낮에는 열심히 일해야지");
	}

	@Override
	public void service(String state) {
		if(!state.equals("오후")) {
			super.service(state);
		}
		else {
			System.out.println("오후도 낮과 마찬가지로 일해야 합니다");
		}
		
	}

}
