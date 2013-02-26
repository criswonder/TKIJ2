package c13.criticalSection;

import java.util.ArrayList;
import java.util.List;

//Protect a Pair inside a thread-safe class: 
abstract class PairManager {
	protected Pair p = new Pair();
	private List storage = new ArrayList();

	public synchronized Pair getPair() {
		// Make a copy to keep the original safe:
		return new Pair(p.getX(), p.getY());
	}

	protected void store() {
		storage.add(getPair());
	}

	// A "template method":
	/**
	 * 增加pair(x,y) x,y的值
	 */
	public abstract void doTask();
}

// Synchronize the entire method:
class PairManager1 extends PairManager {
	public synchronized void doTask() {
		p.incrementX();
		p.incrementY();
		store();
	}
}

// Use a critical section:
class PairManager2 extends PairManager {
	public void doTask() {
		synchronized (this) {
			p.incrementX();
			p.incrementY();
		}
		store();
	}
}

class PairManipulator extends Thread {
	private PairManager pm;
	private int checkCounter = 0;

	private class PairChecker extends Thread {
		PairChecker() {
			start();
		}

		public void run() {
			while (true) {
				checkCounter++;
				pm.getPair().checkState();
			}
		}
	}

	public PairManipulator(PairManager pm) {
		this.pm = pm;
		start();
		new PairChecker();
	}

	public void run() {
		while (true) {
			pm.doTask();
		}
	}

	public String toString() {
		return "Pair: " + pm.getPair() + " checkCounter = " + checkCounter;
	}
}


