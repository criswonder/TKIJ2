package c11.exercise;

//: c11:E15_MovieNameGenerator.java
/****************** Exercise 15 *****************
 * Create a generator that produces character
 * names from your favorite movies (you can use
 * Snow White or Star Wars as a fallback), and
 * loops around to the beginning when it runs out
 * of names. Use the utilities in
 * com.bruceeckel.util to fill an array, an
 * ArrayList, a LinkedList, and both types of Set,
 * then print each container.
 ***********************************************/
import com.bruceeckel.util.*;
import java.util.*;

class MovieNameGenerator implements Generator {
	String[] characters = { "Grumpy", "Happy", "Sleepy", "Dopey", "Doc",
			"Sneezy", "Bashful", "Snow White", "Witch Queen", "Prince" };
	int next = 0;

	public Object next() {
		String r = characters[next];
		next = (next + 1) % characters.length;//2%18 是2，商0余2
		return r;
	}
}

public class E15_MovieNameGenerator {
	public static void main(String args[]) {
		MovieNameGenerator mng = new MovieNameGenerator();
		String[] sa = new String[5];
		Arrays2.fill(sa, mng);
		System.out.println("sa = " + Arrays.asList(sa));
		ArrayList al = new ArrayList();
		Collections2.fill(al, mng, 5);
		System.out.println("al = " + al);
		LinkedList ll = new LinkedList();
		Collections2.fill(ll, mng, 5);
		System.out.println("ll = " + ll);
		HashSet hs = new HashSet();
		Collections2.fill(hs, mng, 5);
		System.out.println("hs = " + hs);
		TreeSet ts = new TreeSet();
		Collections2.fill(ts, mng, 5);
		System.out.println("ts = " + ts);
	}
} // /:~