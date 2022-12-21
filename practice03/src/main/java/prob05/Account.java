package prob05;

public class Account {
	private String accountNo;
	private int balance;
	
	public Account(String accountNo) {
		this.accountNo = accountNo;
		System.out.println(accountNo + " 계좌가 개설되었습니다.");
	}
	

	//입금
	public void save(int balance) {
		
		this.balance += balance;
		System.out.println(accountNo + " 계좌에 " + balance + "만원이 입금되었습니다.");
		
	}

	//출금
	public void deposit(int balance) {
		
		this.balance -= balance;
		System.out.println(accountNo + " 계좌에 " + balance + "만원이 출금되었습니다.");
		
	}
	
	public String getAccountNo() {
		
		return this.accountNo;
	}
	
	public int getBalance() {
		return this.balance;
	}

	
}
