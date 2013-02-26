package c13.exercise;

//: c13:E02_Daemons2.java
//{Args: 5}
/*******************
 * Exercise 2 ************************ Experiment with different sleep times in
 * Daemons.java to see what happens.
 *******************************************************/
class Daemon extends Thread {
	private Thread[] t = new Thread[10];

	public Daemon() {
//		setDaemon(true);
		start();
	}

	public void run() {
		for (int i = 0; i < t.length; i++)
			t[i] = new DaemonSpawn(i);
		for (int i = 0; i < t.length; i++)
			System.out.println("t[" + i + "].isDaemon() = " + t[i].isDaemon());
		while (true)
			yield();
	}
}

class DaemonSpawn extends Thread {
	public DaemonSpawn(int i) {
		start();
		System.out.println("DaemonSpawn " + i + " started");
	}

	public void run() {
		while (true)
			yield();
	}
}

public class E02_Daemons2 {
	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.out.println("Usage: java E02_Daemons2 <sleep time>");
			System.exit(1);
		}
		Thread d = new Daemon();
		System.out.println("d.isDaemon() = " + d.isDaemon());
		Thread.sleep(Integer.parseInt(args[0]));
	}
} // /:~