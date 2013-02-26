package c11.exercise;
//: c11:E25_TreeStatistics.java 
/****************** Exercise 25 ***************** 
 * Use a TreeMap in Statistics.java. Now add code 
 * that tests the performance difference between 
 * HashMap and TreeMap in that program. 
 ***********************************************/
import java.util.*;

class Counter3 {
	int i = 1;

	public String toString() {
		return Integer.toString(i);
	}
}

public class E25_TreeStatistics {
	public static void test(String type, Map m) {
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			// Produce a number between 0 and 20:
			Integer r = new Integer((int) (Math.random() * 20));
			if (m.containsKey(r))
				((Counter3) m.get(r)).i++;
			else
				m.put(r, new Counter3());
		}
		long t2 = System.currentTimeMillis();
		System.out.println(m);
		System.out.println(type + ": " + (t2 - t1));
	}

	public static void main(String[] args) {
		test("HashMap", new LinkedHashMap());
		test("TreeMap", new TreeMap());
	}
} // /:~ 