package c11.exercise;

//: c11:E02_Gerbil.java
/****************** Exercise 2 ******************
 * Create a new class called Gerbil with an int
 * gerbilNumber that's initialized in the
 * constructor (similar to the Mouse example in
 * this chapter). Give it a method called hop()
 * that prints out which gerbil number this is,
 * and that it's hopping. Create an ArrayList and
 * add a bunch of Gerbil objects to the List. Now
 * use the get() method to move through the List
 * and call hop() for each Gerbil.
 ***********************************************/
import java.util.*;

class Gerbil2 {
	private static int gerbilCounter = 0;
	private int gerbilNumber = ++gerbilCounter;

	public String toString() {
		return "gerbil " + gerbilNumber;
	}

	public void hop() {
		//这里使用头toString（）因为object有
		System.out.println(toString() + " is hopping");
	}
}

public class E02_Gerbil {
	public static void main(String args[]) {
		ArrayList gerbils = new ArrayList<Gerbil2>();
		for (int i = 0; i < 10; i++)
			gerbils.add(new Gerbil2());
		for (int i = 0; i < gerbils.size(); i++)
			((Gerbil2) gerbils.get(i)).hop();
	}
} // /:~