package chapter03;

public class ArrayUtil {

	public static double[] intToDouble(int[] is) {
		
		double[] result = new double[is.length];
		for(int i = 0 ; i < is.length; i++) {
			result[i] = is[i];
		}
		
		return result;
	}

	public static int[] doubleToInt(double[] ds) {
		int[] result = new int[ds.length];
		for(int i = 0 ; i < ds.length; i++) {
			result[i] = (int) ds[i];
		}
		return result;
	}

	public static int[] concat(int[] is1, int[] is2) {		
		int[] result = new int[is1.length];
		for(int i = 0 ; i < is1.length; i++) {
			result[i] = is1[i] + is2[i];
		}
		return result;
	}
	
}
