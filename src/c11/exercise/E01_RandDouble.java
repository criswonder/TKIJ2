package c11.exercise;

//: c11:E01_RandDouble.java
/****************** Exercise 1 ******************
 * Create an array of double and fill() it using
 * RandDoubleGenerator. Print the results.
 ***********************************************/
import com.bruceeckel.util.Arrays2;

public class E01_RandDouble {
	public static void main(String args[]) {
		double[] da = new double[10];
		Arrays2.fill(da, new Arrays2.RandDoubleGenerator());
		System.out.println(Arrays2.toString(da));
	}
} // /:~