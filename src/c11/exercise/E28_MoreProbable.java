package c11.exercise;

//: c11:E28_MoreProbable.java 
/****************** Exercise 28 ***************** 
 * Starting with Statistics.java, create a 
 * program that runs the test repeatedly and 
 * looks to see if any one number tends to appear 
 * more than the others in the results. 
 ***********************************************/
import java.util.*;

class Counter {
	int i = 1;

	public String toString() {
		return Integer.toString(i);
	}
}

class HistoUnit implements Comparable {
	Counter counter;
	Integer val;

	public HistoUnit(Counter counter, Integer val) {
		this.counter = counter;
		this.val = val;
	}

	public int compareTo(Object o) {
		int lv = ((HistoUnit) o).counter.i;
		int rv = counter.i;
		return (lv < rv ? -1 : (lv == rv ? 0 : 1));
	}

	public String toString() {
		return "Value = " + val + ", Occurrences = " + counter.i + "\n";
	}
}

public class E28_MoreProbable {
	public static void main(String[] args) {
		HashMap hm = new HashMap();
		for (int i = 0; i < 10000000; i++) {
			// Produce a number between 0 and 100:
			Integer r = new Integer((int) (Math.random() * 100));
			if (hm.containsKey(r))
				((Counter) hm.get(r)).i++;
			else
				hm.put(r, new Counter());
		}
		// Make a histogram:
		List lst = new ArrayList();
		Iterator it = hm.keySet().iterator();
		while (it.hasNext()) {
			Integer key = (Integer) it.next();
			lst.add(new HistoUnit((Counter) hm.get(key), key));
		}
		Collections.sort(lst);
		System.out.println("lst = " + lst);
	}
} // /:~
