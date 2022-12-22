package prob3;

public class Sparrow extends Bird{

	@Override
	public void fly() {
		System.out.println("참새(짹짹)가 날아다닙니다.");
		
	}

	@Override
	public void sing() {
		System.out.println("참새(" + name + ")가 소리내어 웁니다");
		
	}

	@Override
	public String toString() {
		//오리의 이름은 꽥꽥이 입니다.
		String s = "참새의 이름은 " + name + "입니다.";
		return s;
		
	}

}
