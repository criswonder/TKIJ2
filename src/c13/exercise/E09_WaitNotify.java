package c13.exercise;

import c13.Timeout;

//: c13:E09_WaitNotify.java
//The second version using wait().
public class E09_WaitNotify {
	public static void main(String[] args) throws Exception {
		new Timeout(20000, "Timed Out");
		Thread t = new Thread() {
			public void run() {
				while (true) {
					try {
						sleep(4000);
						synchronized (this) {
							notify();
						}
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		};
		t.start();
		while (true) {
			synchronized (t) {
				t.wait();
			}
			System.out.println("Cycled");
		}
	}
} // /:~
