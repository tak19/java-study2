package chapter04;

public class ObjebtTest01 {
	public static void main(String[] args) {
		Point point = new Point();
		
		System.out.println(point.getClass());
		System.out.println(point.hashCode()); //address  기반의 해싱값
											  //address x
											  //reflection x
		
		System.out.println(point.toString()); //getClass + "@" + hashCode()
		System.out.println(point);
		

		//Class klass = point.getClass(); //reflection
		//System.out.println(klass);
		//System.out.println(point.getClass());
		
		
	}
}
