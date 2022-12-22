package prob02;
//1이면 “재고있음”으로 0이면 “대여중"
public class Book {
	private int bookNo;
	private int stateCode;
	private String title;
	private String author;
	
	public Book(int bookNo,String title,String author) {
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.stateCode = 1;
	}
	
	public void rent() {
		if(this.stateCode == 0) {
			System.out.println("대여 불가");
			return;
		}
		this.stateCode = 0;
		System.out.println(this.title +" 이(가) 대여 됐습니다");
	}
	public void print() {
		System.out.print("책 제목: "+ this.title + ", 작가: "+ this.author +", 대여 유무: " );
		System.out.println((this.stateCode==1 )? "재고있음" :"대여중");
	}
	

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
}
