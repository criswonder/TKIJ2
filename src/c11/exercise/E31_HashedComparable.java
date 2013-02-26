package c11.exercise;

//: c11:E31_HashedComparable.java 
/****************** Exercise 31 ***************** 
 * Modify the class in Exercise 16 so that it 
 * will work with HashSets and as a key in 
 * HashMaps. 
 ***********************************************/
import com.bruceeckel.util.*;
import java.util.*;

class TwoString2 implements Comparable {
	String s1, s2;

	public TwoString2(String s1i, String s2i) {
		s1 = s1i;
		s2 = s2i;
	}

	public String toString() {
		return "\n[s1 = " + s1 + ", s2 = " + s2 + "]";
	}

	public int compareTo(Object rv) {
		String rvi = ((TwoString2) rv).s1;
		return s1.compareTo(rvi);
	}

	public int hashCode() {
		// Since the comparisons are based on s1,
		// use s1's hashCode:
		return s1.hashCode();
	}

	public boolean equals(Object obj) {
		return obj instanceof TwoString2 && ((TwoString2) obj).s1.equals(s1);
	}

	private static Arrays2.RandStringGenerator gen = new Arrays2.RandStringGenerator(
			7);

	public static Generator generator() {
		return new Generator() {
			public Object next() {
				return new TwoString2((String) gen.next(), (String) gen.next());
			}
		};
	}
}

public class E31_HashedComparable {
	public static void main(String args[]) {
		HashSet hs = new HashSet();
		HashMap hm = new HashMap();
		Generator gen = TwoString2.generator();
		Collections2.fill(hs, gen, 20);
		for (int i = 0; i < 20; i++)
			hm.put(gen.next(), new Integer(i));
		System.out.println("hs = " + hs);
		System.out.println("hm = " + hm);
	}
} // /:~ 