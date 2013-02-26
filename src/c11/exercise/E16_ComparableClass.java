package c11.exercise;

//: c11:E16_ComparableClass.java
/****************** Exercise 16 *****************
 * Create a class containing two String objects,
 * and make it Comparable so that the comparison
 * only cares about the first String. Fill an
 * array and an ArrayList with objects of your
 * class, using the geography generator.
 * Demonstrate that sorting works properly. Now
 * make a Comparator that only cares about the
 * second String and demonstrate that sorting
 * works properly; also perform a binary search
 * using your Comparator.
 ***********************************************/
import com.bruceeckel.util.*;
import java.util.*;

class TwoString implements Comparable {
	String s1, s2;

	public TwoString(String s1i, String s2i) {
		s1 = s1i;
		s2 = s2i;
	}

	public String toString() {
		return "[s1 = " + s1 + ", s2 = " + s2 + "]";
	}

	public int compareTo(Object rv) {
		String rvi = ((TwoString) rv).s1;
		return s1.compareTo(rvi);
	}

	private static Arrays2.RandStringGenerator gen = new Arrays2.RandStringGenerator(
			7);

	public static Generator generator() {
		return new Generator() {
			public Object next() {
				return new TwoString((String) gen.next(), (String) gen.next());
			}
		};
	}
}

class CompareSecond implements Comparator {
	public int compare(Object o1, Object o2) {
		TwoString sc1 = (TwoString) o1;
		TwoString sc2 = (TwoString) o2;
		return sc1.s2.compareTo(sc2.s2);
	}
}

public class E16_ComparableClass {
	public static void main(String[] args) {
		TwoString[] array = new TwoString[4];
		Arrays2.fill(array, TwoString.generator());
		System.out.println("before sorting, array = " + Arrays.asList(array));
		Arrays.sort(array);
		System.out.println("\nafter sorting, array = " + Arrays.asList(array));
		Arrays.sort(array, new CompareSecond());
		System.out.println("\nafter sorting with CompareSecond, array = "
				+ Arrays.asList(array));
		ArrayList list = new ArrayList();
		Collections2.fill(list, TwoString.generator(), 4);
		TwoString zeroth = (TwoString) list.get(0);
		System.out.println("\nbefore sorting, list = " + list);
		Collections.sort(list);
		System.out.println("\nafter sorting, list = " + list);
		Comparator comp = new CompareSecond();
		Collections.sort(list, comp);
		System.out
				.println("\nafter sorting with CompareSecond, list = " + list);
		int index = Collections.binarySearch(list, zeroth, comp);
		System.out.println("\nFormer zeroth element " + zeroth
				+ " now located at " + index);
	}
} // /:~