package c11;

//: c11:Statistics.java 
//Simple demonstration of HashMap. 
import java.util.*;

class Counter {
	int i = 1;

	public String toString() {
		return Integer.toString(i);
	}
}

public class Statistics {
	private static Random rand = new Random();

	public static void main(String[] args) {
//		original();
		tryToUseInteger();
	}

	private static void tryToUseInteger() {
//		Map hm = new HashMap();
//		for(int i=0; i<1000; i++){
//			Integer randInt = new Integer(rand.nextInt(20));
//			if(hm.containsKey(randInt)){
//				Integer randInt2 = (Integer)hm.get(randInt)+1;
//				hm.
//			}
//			hm.put(randInt, arg1)
//		}
	}

	private static void original() {
		Map hm = new HashMap();
		for (int i = 0; i < 10000; i++) {
			// Produce a number between 0 and 20:
			Integer r = new Integer(rand.nextInt(20));
			if (hm.containsKey(r))
				((Counter) hm.get(r)).i++;
			else
				hm.put(r, new Counter());
		}
		System.out.println(hm);
	}
} // /:~ 