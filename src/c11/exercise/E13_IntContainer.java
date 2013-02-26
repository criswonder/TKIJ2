package c11.exercise;

//: c11:E13_IntContainer.java
/****************** Exercise 13 *****************
 * Repeat Exercise 12 for a container of int, and
 * compare the performance to an ArrayList
 * holding Integer objects. In your performance
 * comparison, include the process of
 * incrementing each object in the container.
 ***********************************************/
import java.util.*;

class IntContainer {
	private int[] array;
	private int index = 0;
	private static final int INCR = 255;

	public IntContainer() {
		array = new int[13];
	}

	public IntContainer(int sz) {
		array = new int[sz];
	}

	public void add(int s) {
		if (index >= array.length) {
			System.out.println("Resizing");
			int[] tmp = new int[array.length + INCR];
			for (int i = 0; i < array.length; i++)
				tmp[i] = array[i];
			index = array.length;
			array = tmp;
		}
		array[index++] = s;
	}

	public int get(int i) {
		if (i < index && i >= 0)
			return array[i];
		else
			throw new RuntimeException("Bad Index");
	}

	public void set(int i, int val) {
		if (i < index && i >= 0)
			array[i] = val;
		else
			throw new RuntimeException("Bad Index");
	}

	public int size() {
		return index;
	}
}

public class E13_IntContainer {
	public static void main(String args[]) {
		IntContainer ic = new IntContainer(5);
		for (int i = 0; i < 10; i++)
			ic.add(i);
		for (int i = 0; i < ic.size(); i++)
			System.out.print(ic.get(i) + " ");
		long t1 = System.currentTimeMillis();
		IntContainer ic2 = new IntContainer(100000);
		for (int i = 0; i < 100000; i++) {
			ic2.add(i);
			ic2.set(i, ic2.get(i) + 1);//从list取出一个数，然后加一放进去。
		}
		long t2 = System.currentTimeMillis();
		System.out.println("IntContainer: " + (t2 - t1));
		t1 = System.currentTimeMillis();
		ArrayList lst = new ArrayList(100000);
		for (int i = 0; i < 100000; i++) {
			lst.add(new Integer(i));
			lst.set(i, new Integer(((Integer) lst.get(i)).intValue() + 1));
		}
		t2 = System.currentTimeMillis();
		System.out.println("ArrayList: " + (t2 - t1));
	}
} // /:~