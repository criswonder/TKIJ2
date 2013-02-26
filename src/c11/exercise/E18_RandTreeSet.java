package c11.exercise;

//: c11:E18_RandTreeSet.java
/****************** Exercise 18 *****************
 * Use Arrays2.RandStringGenerator to fill a
 * TreeSet but using alphabetic ordering. Print
 * the TreeSet to verify the sort order.
 ***********************************************/
import com.bruceeckel.util.*;
import java.util.*;

public class E18_RandTreeSet {
	static Arrays2.RandStringGenerator gen = new Arrays2.RandStringGenerator(7);

	public static void main(String args[]) {
		TreeSet ts = new TreeSet(new AlphabeticComparator());
		Collections2.fill(ts, gen, 10);
		System.out.println("ts = " + ts);
	}
} // /:~