package c13.exercise;

//: c13:E06_ManyTimers.java
//{Args: 100}
/****************** Exercise 6 ************************
 * Demonstrate that java.util.Timer scales to large
 * numbers by creating a program that generates many
 * Timer objects that perform some simple task when
 * the timeout completes (if you want to get fancy,
 * you can jump forward to the "Windows and Applets"
 * chapter and use the Timer objects to draw pixels on
 * the screen, but printing to the console is
 * sufficient).
 ******************************************************/
import java.util.*;

public class E06_ManyTimers {
	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.out.println("Usage: java E06_ManyTimers <num of timers>");
		}
		int numOfTimers = Integer.parseInt(args[0]);
		for (int i = 0; i < numOfTimers; i++) {
			new Timer().schedule(new TimerTask() {
				public void run() {
					System.out.println(System.currentTimeMillis());
				}
			}, numOfTimers - i);
		}
		// Wait for timers to expire
		Thread.sleep(2 * numOfTimers);
		System.exit(0);
	}
} // /:~