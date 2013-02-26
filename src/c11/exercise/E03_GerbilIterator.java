package c11.exercise;

//: c11:E03_GerbilIterator.java
/****************** Exercise 3 ******************
 * Modify Exercise 2 so you use an Iterator to
 * move through the List while calling hop().
 ***********************************************/
import java.util.*;
public class E03_GerbilIterator {
	public static void main(String args[]) {
		ArrayList gerbils = new ArrayList();
		for(int i = 0; i < 10; i++)
			gerbils.add(new Gerbil2());
		for(Iterator it = gerbils.iterator();it.hasNext();)
			((Gerbil2)it.next()).hop();
	}
} ///:~