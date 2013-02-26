package c13;

//: c13:Timeout.java
//Set a time limit on the execution of a program
import java.util.*;

public class Timeout extends Timer {
	public Timeout(int delay, final String msg) {
		super(true); // Daemon thread
		schedule(new TimerTask() {
			public void run() {
				System.out.println(msg);
				System.exit(0);
			}
		}, delay);
	}
} // /:~