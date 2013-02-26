package c13.exercise;

//: c13:TQueue.java
//A queue for solving thread synchronization problems.
import java.util.*;

public class TQueue {
	private LinkedList queue = new LinkedList();

	public synchronized void put(Object v) {
		queue.addFirst(v);
		notifyAll();
	}

	public synchronized Object get() {
		while (queue.isEmpty())
			try {
				wait();
			} catch (InterruptedException e) {
				return null; // Says "quit now"
			}
		return queue.removeLast();
	}
} // /:~