package chapter03;

public class Goods {
	
	public static int countOfGoods=0;
	
	private String name;
	private int price;
	private int countStork;
	private int countSold;

	public Goods() {
		Goods.countOfGoods = Goods.countOfGoods + 1;
	}
	
	public int calcDiscountPrice(float discountRate) {
		return (int) (price*discountRate);
		
	}
	
	public void printInfo() {
		 System.out.println( name+ " " +  price + " " + countStork + " " + countSold );
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		//변수 보호 -- 정확히는 메소드에서 예외처리를 할 수 있음
		if(price < 0) {
			price = 0;
		}
		this.price = price;
	}
	public int getCountStork() {
		return countStork;
	}
	public void setCountStork(int countStork) {
		this.countStork = countStork;
	}
	public int getCountSold() {
		return countSold;
	}
	public void setCountSold(int countSold) {
		this.countSold = countSold;
	}
	
	
}
