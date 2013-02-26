package c11.exercise;

//: c11:E05_CountryList.java
/****************** Exercise 5 ******************
 * Create a List (try both ArrayList and
 * LinkedList) and fill it using
 * Collections2.countries. Sort the list and
 * print it, then apply Collections.shuffle() to
 * the list repeatedly, printing it each time so
 * that you can see how the shuffle() method
 * randomizes the list differently each time.
 ***********************************************/
import com.bruceeckel.util.*;
import java.util.*;

public class E05_CountryList {
	public static void main(String args[]) {
		List lst = new LinkedList();
		Collections2.fill(lst, Collections2.countries, 8);
		Collections.sort(lst);
		System.out.println(lst);
		for (int i = 0; i < 5; i++) {
			Collections.shuffle(lst);
			System.out.println(lst);
		}
	}
} // /:~