package c11.exercise;

//: c11:E14_PrimitiveArrays.java
/****************** Exercise 14 *****************
 * Using the utilities in com.bruceeckel.util,
 * create an array of each primitive type and of
 * String, then fill each array using an
 * appropriate generator, and print each array
 * using the appropriate toString() method.
 ***********************************************/
import com.bruceeckel.util.*;
import java.util.*;

public class E14_PrimitiveArrays {
	public static void main(String[] args) {
		int size = 6;
		// Or get the size from the command line:
		if (args.length != 0)
			size = Integer.parseInt(args[0]);
		boolean[] a1 = new boolean[size];
		byte[] a2 = new byte[size];
		char[] a3 = new char[size];
		short[] a4 = new short[size];
		int[] a5 = new int[size];
		long[] a6 = new long[size];
		float[] a7 = new float[size];
		double[] a8 = new double[size];
		String[] a9 = new String[size];
		Arrays2.fill(a1, new Arrays2.RandBooleanGenerator());
		System.out.println("a1 = " + Arrays2.toString(a1));
		Arrays2.fill(a2, new Arrays2.RandByteGenerator());
		System.out.println("a2 = " + Arrays2.toString(a2));
		Arrays2.fill(a3, new Arrays2.RandCharGenerator());
		System.out.println("a3 = " + Arrays2.toString(a3));
		Arrays2.fill(a4, new Arrays2.RandShortGenerator());
		System.out.println("a4 = " + Arrays2.toString(a4));
		Arrays2.fill(a5, new Arrays2.RandIntGenerator());
		System.out.println("a5 = " + Arrays2.toString(a5));
		Arrays2.fill(a6, new Arrays2.RandLongGenerator());
		System.out.println("a6 = " + Arrays2.toString(a6));
		Arrays2.fill(a7, new Arrays2.RandFloatGenerator());
		System.out.println("a7 = " + Arrays2.toString(a7));
		Arrays2.fill(a8, new Arrays2.RandDoubleGenerator());
		System.out.println("a8 = " + Arrays2.toString(a8));
		Arrays2.fill(a9, new Arrays2.RandStringGenerator(7));
		System.out.println("a9 = " + Arrays.asList(a9));
	}
} // /:~