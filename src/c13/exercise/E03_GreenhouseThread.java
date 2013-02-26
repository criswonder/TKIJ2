package c13.exercise;

//: c13:E03_GreenhouseThread.java
/******************
 * Exercise 3 ****************** In Chapter 8, locate the
 * GreenhouseController.java example, which consists of four files. In
 * Event.java, the class Event is based on watching the time. Change Event so
 * that it is a Thread, and change the rest of the design so that it works with
 * this new Thread-based Event.
 ***********************************************/
/****************************************
 * add by andy mao
 * 相对于第八章的，不用把所有的event都放到一个list里面去，然后也不用去判断一个event是不是ready等等，确实
 * 方便了很多了。
 ****************************************/
abstract class Event extends Thread {
	private long delay;

	public Event(long delayTime) {
		delay = delayTime;
		start();
	}

	public void run() {
		try {
			sleep(delay);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException = " + e);
		}
		action();
		System.out.println(this);
	}

	abstract public void action();
}

class GreenhouseControls {
	// List events = new ArrayList();
	private boolean light = false;
	private boolean water = false;
	private String thermostat = "Day";
	/*
	 * 是用private右边的Outline图像会有红色的点
	 */
	private class LightOn extends Event {
		public LightOn(long delayTime) {
			super(delayTime);
		}

		public void action() {
			// Put hardware control code here to
			// physically turn on the light.
			light = true;
		}

		public String toString() {
			return "Light is on";
		}
	}

	private class LightOff extends Event {
		public LightOff(long delayTime) {
			super(delayTime);
		}

		public void action() {
			// Put hardware control code here to
			// physically turn off the light.
			light = false;
		}

		public String toString() {
			return "Light is off";
		}
	}

	private class WaterOn extends Event {
		public WaterOn(long delayTime) {
			super(delayTime);
		}

		public void action() {
			// Put hardware control code here
			water = true;
		}

		public String toString() {
			return "Greenhouse water is on";
		}
	}

	private class WaterOff extends Event {
		public WaterOff(long delayTime) {
			super(delayTime);
		}

		public void action() {
			// Put hardware control code here
			water = false;
		}

		public String toString() {
			return "Greenhouse water is off";
		}
	}

	private class ThermostatNight extends Event {
		public ThermostatNight(long delayTime) {
			super(delayTime);
		}

		public void action() {
			// Put hardware control code here
			thermostat = "Night";
		}

		public String toString() {
			return "Thermostat on night setting";
		}
	}

	private class ThermostatDay extends Event {
		public ThermostatDay(long delayTime) {
			super(delayTime);
		}

		public void action() {
			// Put hardware control code here
			thermostat = "Day";
		}

		public String toString() {
			return "Thermostat on day setting";
		}
	}

	// An example of an action() that inserts a
	// new one of itself into the event list:
	private int rings;

	private class Bell extends Event {
		public Bell(long delayTime) {
			super(delayTime);
		}

		public void action() {
			// Ring every 2 seconds, 'rings' times:
			System.out.println("Bing!");
			if (--rings > 0)
				new Bell(200);
		}

		public String toString() {
			return "Ring bell";
		}
	}

	static int repeats = 3;

	class Restart extends Event {
		public Restart(long delayTime) {
			super(delayTime);
		}

		public void action() {
			if (--repeats < 0)
				return;
			rings = 5;
			new ThermostatNight(0);
			new LightOn(100);
			new LightOff(200);
			new WaterOn(300);
			new WaterOff(800);
			new Bell(900);
			new ThermostatDay(1000);
			// Can even add a Restart object!
			new Restart(2000);
		}

		public String toString() {
			return "Restarting system";
		}
	}
}

public class E03_GreenhouseThread {
	public static void main(String[] args) {
		GreenhouseControls gc = new GreenhouseControls();
		gc.new Restart(1000);
	}
} // /:~