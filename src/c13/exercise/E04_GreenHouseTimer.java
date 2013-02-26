package c13.exercise;

//: c13:E04_GreenHouseTimer.java
/****************** Exercise 4 ******************
 * Modify the previous exercise so that the
 * java.util.Timer class is used to run the
 * system.
 ***********************************************/
import java.util.*;

abstract class Event_T extends TimerTask {
	private long delayTime;

	public Event_T(long eventDelay) {
		delayTime = eventDelay;
		// Causes NullPointerException since a TimerTask may
		// be scheduled before it is completely construted.
		// new Timer().schedule(this, delayTime);
	}

	public void schedule() {
		new Timer().schedule(this, delayTime);
	}

	// abstract public void action();
	// Replace "action" with TimerTask's run()::
	public void run() {
		System.out.println(this);
	}
}

class GreenhouseControls_T {
	private boolean light = false;
	private boolean water = false;
	private String thermostat = "Day";

	private class LightOn extends Event_T {
		public LightOn(long eventDelay) {
			super(eventDelay);
		}

		public void run() {
			// Put hardware control code here to
			// physically turn on the light.
			light = true;
			super.run();
		}

		public String toString() {
			return "Light is on";
		}
	}

	private class LightOff extends Event_T {
		public LightOff(long eventDelay) {
			super(eventDelay);
		}

		public void run() {
			// Put hardware control code here to
			// physically turn off the light.
			light = false;
			super.run();
		}

		public String toString() {
			return "Light is off";
		}
	}

	private class WaterOn extends Event_T {
		public WaterOn(long eventDelay) {
			super(eventDelay);
		}

		public void run() {
			// Put hardware control code here
			water = true;
			super.run();
		}

		public String toString() {
			return "Greenhouse water is on";
		}
	}

	private class WaterOff extends Event_T {
		public WaterOff(long eventDelay) {
			super(eventDelay);
		}

		public void run() {
			// Put hardware control code here
			water = false;
			super.run();
		}

		public String toString() {
			return "Greenhouse water is off";
		}
	}

	private class ThermostatNight extends Event_T {
		public ThermostatNight(long eventDelay) {
			super(eventDelay);
		}

		public void run() {
			// Put hardware control code here
			thermostat = "Night";
			super.run();
		}

		public String toString() {
			return "Thermostat on night setting";
		}
	}

	private class ThermostatDay extends Event_T {
		public ThermostatDay(long eventDelay) {
			super(eventDelay);
		}

		public void run() {
			// Put hardware control code here
			thermostat = "Day";
			super.run();
		}

		public String toString() {
			return "Thermostat on day setting";
		}
	}

	// An example of an run() that inserts a
	// new one of itself into the event list:
	private int rings;

	private class Bell extends Event_T {
		public Bell(long eventDelay) {
			super(eventDelay);
		}

		public void run() {
			// Ring every 2 seconds, 'rings' times:
			System.out.println("Bing!");
			if (--rings > 0)
				new Bell(200).schedule();
			super.run();
		}

		public String toString() {
			return "Ring bell";
		}
	}

	static int repeats = 3;

	class Restart extends Event_T {
		public Restart(long eventDelay) {
			super(eventDelay);
		}

		public void run() {
			super.run();
			if (--repeats < 0) {
				System.out.println("Finished");
				// Must call this or threads keep the
				// program running:
				System.exit(0);
			}
			rings = 5;
			new ThermostatNight(0).schedule();
			new LightOn(100).schedule();
			new LightOff(200).schedule();
			new WaterOn(300).schedule();
			new WaterOff(800).schedule();
			new Bell(900).schedule();
			new ThermostatDay(1000).schedule();
			// Can even add a Restart object!
			new Restart(2000).schedule();
		}

		public String toString() {
			return "Restarting system";
		}
	}
}

public class E04_GreenHouseTimer {
	public static void main(String[] args) {
		GreenhouseControls_T gc = new GreenhouseControls_T();
		gc.new Restart(0).schedule();
	}
} // /:~