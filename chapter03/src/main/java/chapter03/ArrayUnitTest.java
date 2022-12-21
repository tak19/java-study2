package chapter03;

import java.util.Arrays;

public class ArrayUnitTest {

	public static void main(String[] args) {
		int[] a = {10, 20 ,30 ,40 };
		//int[] a = new int[5];
//		a[0] = 1;
//		a[1] = 2;
		
		double[] d = ArrayUtil.intToDouble(new int[] {10,20,30,40});
		System.out.println(Arrays.toString(d));
		
		//int[] i1 = ArrayUtil.doubleToInt(new double[] {10.0, 11.1, 22.2, 33.3});
		//System.out.println(Arrays.toString(i1));
		
		//int[] i2 = ArrayUtil.concat(new int[] {1,2,3},{2,3,4});
		//System.out.println(Arrays.toString(i1));
		
	}

}
