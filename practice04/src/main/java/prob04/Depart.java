package prob04;

public class Depart extends Employee {
	
	private String job;

	public Depart(String name, int salary, String job) {
		super(name,salary);
		this.job = job;
	}
	
	@Override
	public void getInformation() {
		//super.getInformation() 으로 간단하게 가능하지만 출력 형식 맞춤
		System.out.println("이름: " + super.getName() +" "+ "연봉: " +super.getSalary()+" " + "부서: "+ this.job);
	}

}
