package c13.exercise;

//: c13:E08_ThreadCooperation.java
/******************
 * Exercise 8 ****************** Create two Thread subclasses, one with a run()
 * that starts up and then calls wait(). The other class should capture the
 * reference of the first Thread object. Its run() should call notifyAll() for
 * the first thread after some number of seconds have passed, so the first
 * thread can print a message.
 ***********************************************/
class Coop1 extends Thread {
	public Coop1() {
		System.out.println("Constructed Coop1");
		start();
	}

	public void run() {
		System.out.println("Coop1 going into wait");
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		System.out.println("Coop1 exited wait");
	}
}

class Coop2 extends Thread {
	Coop1 otherThread;

	public Coop2(Coop1 otherThread) {
		this.otherThread = otherThread;
		System.out.println("Constructed Coop2");
		start();
	}

	public void run() {
		System.out.println("Coop2 pausing 5 secs");
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Coop2 calling notifyAll");
		synchronized (otherThread) {
			otherThread.notify();
		}
	}
}

public class E08_ThreadCooperation {
	public static void main(String args[]) {
		new Coop2(new Coop1());
	}
} // /:~
