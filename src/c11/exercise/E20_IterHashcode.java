package c11.exercise;

//: c11:E20_IterHashcode.java
/****************** Exercise 20 *****************
 * Write a method that uses an Iterator to step
 * * through a Collection and print the hashCode()
 * of each object in the container. Fill all the
 * different types of Collections with objects
 * and apply your method to each container.
 ***********************************************/
import java.util.*;
import com.bruceeckel.util.*;

public class E20_IterHashcode {
	public static void PrintHashes(Iterator it) {
		while (it.hasNext())
			System.out.println(it.next().hashCode());
	}

	public static void main(String args[]) {
		Collection[] ca = { new ArrayList(), new LinkedList(), new HashSet(),
				new TreeSet(), };
		for (int i = 0; i < ca.length; i++)
			Collections2.fill(ca[i], Collections2.capitals, 10);
		for (int i = 0; i < ca.length; i++)
			PrintHashes(ca[i].iterator());
	}
} // /:~
