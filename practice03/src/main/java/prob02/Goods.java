package prob02;

public class Goods {
	
	public Goods(String name, int price, int countStock) {
		this.name = name;
		this.price = price;
		this.countStock = countStock;
	}
	public String getName() {
		return this.name;
	}
	public int getPrice() {
		return this.price;
	}
	public int getCountStock() {
		return this.countStock;
	}
	private String name;
	private int price;
	private int countStock;

}
