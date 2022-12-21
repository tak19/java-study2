package prob03;

public class CurrencyConverter {
	private static double rate;

	public static void setRate(double rate) {
		CurrencyConverter.rate = rate;
	}

	public static double toDollar(double d) {
		// TODO Auto-generated method stub
		return d / 1285.55;
	}

	public static double toKRW(double d) {
		// TODO Auto-generated method stub
		return d*1285.55;
	}

}
