package c11.exercise;

//: c11:E24_Deque.java 
/****************** Exercise 24 ***************** 
 * Following the Queue.java example, create a 
 * Deque class and test it. 
 ***********************************************/
import java.util.*;

// A full implementation of Collection: 
class Deque implements Collection {
	private LinkedList list;

	public Deque() {
		list = new LinkedList();
	}

	// Copy from an existing Collection:
	public Deque(Collection c) {
		list = new LinkedList(c);
	}

	public void put_front(Object v) {
		list.addFirst(v);
	}

	public void put_back(Object v) {
		list.addLast(v);
	}

	public Object get_front() {
		return list.removeFirst();
	}

	public Object get_back() {
		return list.removeLast();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public int size() {
		return list.size();
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	public Iterator iterator() {
		return list.iterator();
	}

	public Object[] toArray() {
		return list.toArray();
	}

	public Object[] toArray(Object a[]) {
		return list.toArray(a);
	}

	public boolean add(Object o) {
		list.addLast(o);
		return true;
	}

	public boolean remove(Object o) {
		return list.remove(o);
	}

	public boolean containsAll(Collection c) {
		return list.containsAll(c);
	}

	public boolean addAll(Collection c) {
		return list.addAll(c);
	}

	public boolean removeAll(Collection c) {
		return list.removeAll(c);
	}

	public boolean retainAll(Collection c) {
		return list.retainAll(c);
	}

	public void clear() {
		list.clear();
	}
}

public class E24_Deque {
	public static void main(String[] args) {
		Deque deque1 = new Deque();
		for (int i = 0; i < 5; i++)
			deque1.put_front(Integer.toString(i));
		for (int i = 50; i < 55; i++)
			deque1.put_back(Integer.toString(i));
		Deque deque2 = new Deque(deque1);
		while (!deque1.isEmpty())
			System.out.println(deque1.get_front());
		System.out.println("**********");
		while (!deque2.isEmpty())
			System.out.println(deque2.get_back());
	}
} // /:~ 