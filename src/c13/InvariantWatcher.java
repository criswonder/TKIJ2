package c13;

//: c13:InvariantWatcher.java
//Repeatedly checks to ensure invariant is not violated
public class InvariantWatcher extends Thread {
	private Invariant invariant;

	public InvariantWatcher(Invariant invariant) {
		this.invariant = invariant;
		setDaemon(true);
		start();
	}

	// Stop everything after awhile:
	public InvariantWatcher(Invariant invariant, final int timeOut) {
		this(invariant);
		new Timeout(timeOut, "Timed out without violating invariant");
	}

	public void run() {
		while (true) {
			InvariantState state = invariant.invariant();
			if (state instanceof InvariantFailure) {
				System.out.println("Invariant violated: "
						+ ((InvariantFailure) state).value);
				System.exit(0);
			}
		}
	}
} // /:~