package c11.exercise;

//: c11:E07_MouseListProblem.java
/****************** Exercise 7 ******************
 * Modify MouseList.java so that it inherits from
 * ArrayList instead of using composition.
 * Demonstrate the problem with this approach.
 ***********************************************/
import java.util.*;

class MouseList2 extends ArrayList {
	public void add(Mouse2 m) {
		System.out.println("Adding a mouse");
		super.add(m);
	}

	// Can't override with different return type:
	// ! public Mouse2 get(int i) {
	// ! return (Mouse2)super.get(i);
	// ! }
	// Sidestep:
	public Mouse2 getMouse(int i) {
		return (Mouse2) super.get(i);
	}
}

public class E07_MouseListProblem {
	public static void main(String args[]) {
		MouseList2 mice = new MouseList2();
		for (int i = 0; i < 3; i++)
		{
			mice.add(new Mouse2(i));
			
		}
		for (int i = 0; i < mice.size(); i++)
			System.out.println(mice.getMouse(i));
		// Oops! Can add a non-mouse:
		mice.add(new Object());
	}
} // /:~