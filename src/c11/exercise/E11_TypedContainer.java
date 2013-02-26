package c11.exercise;

//: c11:E11_TypedContainer.java
/****************** Exercise 11 *****************
 * Create a new type of container that uses a
 * private ArrayList to hold the objects. Using a class
 * reference, Capture the type of the first object you put
 * in it, and then allow the user to insert objects of
 * only that type from then on.
 ***********************************************/
import java.util.*;

class TypedContainer extends AbstractList {
	private ArrayList list = new ArrayList();
	Class type = null;

	public boolean add(Object o) {
		if (type == null)
			type = o.getClass();
		if (type.isAssignableFrom(o.getClass())) {
			list.add(o);
			return true;
		}
		return false;
	}

	public void add(int index, Object o) {
		if (type == null)
			type = o.getClass();
		if (type.isAssignableFrom(o.getClass()))
			list.add(index, o);
	}

	public Object set(int index, Object o) {
		if (type == null)
			type = o.getClass();
		if (type.isAssignableFrom(o.getClass()))
			return list.set(index, o);
		return list.get(index);
	}

	public Object get(int index) {
		return list.get(index);
	}

	public int size() {
		return list.size();
	}

	public Object remove(int index) {
		return list.remove(index);
	}

	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	public void clear() {
		list.clear();
	}

	public boolean addAll(int index, Collection c) {
		return list.addAll(index, c);
	}

	public Iterator iterator() {
		return list.iterator();
	}

	public ListIterator listIterator() {
		return list.listIterator();
	}

	public ListIterator listIterator(int index) {
		return list.listIterator(index);
	}

	public List subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	public boolean equals(Object o) {
		return list.equals(o);
	}
}

class X {
	public String toString() {
		return "X";
	}
}

class Y {
	public String toString() {
		return "Y";
	}
}

class Z extends X {
	public String toString() {
		return "Z";
	}
}

public class E11_TypedContainer {
	public static void main(String args[]) {
		TypedContainer tc = new TypedContainer();
		for (int i = 0; i < 10; i++) {
			tc.add(new X());
			tc.add(new Y());
		}
		System.out.println("tc = " + tc);
		TypedContainer tc2 = new TypedContainer();
		for (int i = 0; i < 10; i++) {
			tc2.add(new Y());
			tc2.add(new X());
		}
		System.out.println("tc2 = " + tc2);
		TypedContainer tc3 = new TypedContainer();
		for (int i = 0; i < 10; i++) {
			tc3.add(new X());
			tc3.add(new Z()); // Allows adding subclass of X
		}
		System.out.println("tc3 = " + tc3);
		TypedContainer tc4 = new TypedContainer();
		for (int i = 0; i < 10; i++) {
			tc4.add(new Z());
			tc4.add(new X()); // Type X not allowed
		}
		System.out.println("tc4 = " + tc4);
	}
} // /:~
