package c13.exercise;

//: c13:E05_SimpleThreadDaemon.java
/******************
 * Exercise 5 ************************ Modify SimpleThread.java so that all the
 * threads are daemon threads, and verify that the program ends as soon as
 * main() is able to exit.
 ******************************************************/
/*
 * 说说自己对daemon线程的理解
 * daemon是半人半神的精灵，可以理解为后台的守护神。就是不太重要的会放到后台去执行。
 * 当主程序要退出时，如果其它非daemon的线程还在运行的话，则要等这些线程运行完才能
 * 退出。如果其它线程都是daemon线程的话就直接退出。
 */
public class E05_SimpleThreadDaemon extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;

	public E05_SimpleThreadDaemon() {
		super("" + ++threadCount); // Store the thread name
		setDaemon(true);
		start();
	}

	public String toString() {
		return "#" + getName() + ": " + countDown;
	}

	public void run() {
		while (true) {
			System.out.println(this);
			try {
				sleep(100); // Introduce delay.
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			if (--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++)
			new E05_SimpleThreadDaemon();
	}
} // /:~