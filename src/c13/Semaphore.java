package c13;

//: c13:Semaphore.java
//A simple threading flag
/**
 * 　信号量(Semaphore)，有时被称为信号灯
 * @author MS
 */
public class Semaphore implements Invariant {
	private volatile int semaphore = 0;

	public boolean available() {
		return semaphore == 0;
	}

	public void acquire() {
		++semaphore;
	}

	public void release() {
		--semaphore;
	}

	public InvariantState invariant() {
		int val = semaphore;
		if (val == 0 || val == 1)
			return new InvariantOK();
		else
			return new InvariantFailure(new Integer(val));
	}
} // /:~