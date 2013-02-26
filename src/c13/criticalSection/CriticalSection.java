package c13.criticalSection;

import java.util.Timer;
import java.util.TimerTask;

public class CriticalSection {
	public static void main(String[] args) {
		// Test the two different approaches:
		final PairManipulator pm1 = new PairManipulator(new PairManager1()), pm2 = new PairManipulator(
				new PairManager2());
		new Timer(true).schedule(new TimerTask() {
			public void run() {
				System.out.println("pm1: " + pm1);
				System.out.println("pm2: " + pm2);
				System.exit(0);
			}
		}, 500); // run() after 500 milliseconds
	}
} // /:~ 