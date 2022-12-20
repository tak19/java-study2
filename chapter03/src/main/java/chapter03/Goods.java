package chapter03;

public class Goods {
	private String name;
	private int price;
	private int countStork;
	private int countSold;
	
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
