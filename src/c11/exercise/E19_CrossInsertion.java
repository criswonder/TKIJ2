package c11.exercise;

//: c11:E19_CrossInsertion.java
/****************** Exercise 19 *****************
 * Create both an ArrayList and a LinkedList, and
 * fill each using the Collections2.capitals
 * generator. Print each list using an ordinary
 * Iterator, then insert one list into the other
 * using a ListIterator, inserting at every other
 * location. Now perform the insertion starting
 * at the end of the first list and moving
 * backward.
 ***********************************************/
import com.bruceeckel.util.*;
import java.util.*;

public class E19_CrossInsertion {
	public static void main(String args[]) {
		ArrayList al = new ArrayList();
		LinkedList ll = new LinkedList();
		Collections2.fill(al, Collections2.capitals, 3);
		Collections2.fill(ll, Collections2.capitals, 3);
		for (Iterator it = al.iterator(); it.hasNext();)
			System.out.println(it.next());
		System.out.println("********");
		for (Iterator it = ll.iterator(); it.hasNext();)
			System.out.println(it.next());
		System.out.println("********");
		int alindex = 0;
		for (ListIterator lit2 = ll.listIterator(); lit2.hasNext();) {
			al.add(alindex, lit2.next());
			alindex += 2;
		}
		System.out.println("al = " + al);
		System.out.println("********");
		alindex = 0;
		// Start at the end:
		for (ListIterator lit2 = ll.listIterator(ll.size()); lit2.hasPrevious();) {
			al.add(alindex, lit2.previous());
			alindex += 2;
		}
		System.out.println("al = " + al);
	}
} // /:~