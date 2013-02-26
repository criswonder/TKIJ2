package c13.exercise;

import c13.Timeout;

//: c13:E09_BusyWait.java
/******************
 * Exercise 9 *************************** Create an example of a "busy wait."
 * One thread sleeps for awhile and then sets a flag to true. The second thread
 * watches that flag inside a while loop (this is the "busy wait") and when the
 * flag becomes true, sets it back to false and reports the change to the
 * console. Note how much wasted time the program spends inside the "busy wait,"
 * and create a second version of the program that uses wait() instead of the
 * "busy wait."
 ********************************************************/
public class E09_BusyWait {
	private static volatile boolean flag = false;
	private static int spins = 0;

	public static void main(String[] args) {
		new Timeout(1000, "Timed Out");
		Thread t = new Thread() {
			public void run() {
				while (true) {
					try {
						sleep(10);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					flag = true;
				}
			}
		};
		t.start();
		for (;;) {
			while (!flag)
				// The busy-wait
				spins++;
			System.out.println("Spun " + spins + " times");
			spins = 0;
			flag = false;
		}
	}
} // /:~