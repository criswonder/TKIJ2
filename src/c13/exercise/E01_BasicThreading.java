package c13.exercise;

//: c13:E01_BasicThreading.java
/******************
 * Exercise 1 ****************** Inherit a class from Thread and override the
 * run() method. Inside run(), print a message, and then call sleep(). Repeat
 * this three times, then return from run(). Put a start-up message in the
 * constructor and override finalize() to print a shut-down message. Make a
 * separate thread class that calls System.gc() and System.runFinalization()
 * inside run(), printing a message as it does so. Make several thread objects
 * of both types and run them to see what happens.
 ***********************************************/
class Cleaner extends Thread { // The "separate" class.
	static int counter = 0;
	int id = counter++;

	public Cleaner() {
		start();
	}

	public void run() {
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Cleaner " + id + " run");
		System.gc();
		System.runFinalization();
	}
}

public class E01_BasicThreading extends Thread {
	static int counter = 0;
	int id = counter++;

	public E01_BasicThreading() {
		System.out.println("Constructing thread " + id);
		start();
	}

	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("Basic Thread " + id + " Loop " + i);
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	protected void finalize() {
		System.out.println("Basic Thread " + id + " Finalize");
	}

	public static void main(String args[]) throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			new E01_BasicThreading();
			new Cleaner();
			sleep(5000);
		}
	}
} // /:~
