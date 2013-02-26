package c8.controller;

//: c08:GreenhouseControls.java
//This produces a specific application of the
//control system, all in a single class. Inner
//classes allow you to encapsulate different
//functionality for each type of event.
//import com.bruceeckel.simpletest.*;
import c8.controller.*;

public class GreenhouseControls extends Controller {
//	private static Test monitor = new Test();
	private boolean light = false;

	public class LightOn extends Event {
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

	public class LightOff extends Event {
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

	private boolean water = false;

	public class WaterOn extends Event {
		public WaterOn(long delayTime) {
			super(delayTime);
		}

		public void action() {
			// Put hardware control code here.
			water = true;
		}

		public String toString() {
			return "Greenhouse water is on";
		}
	}

	public class WaterOff extends Event {
		public WaterOff(long delayTime) {
			super(delayTime);
		}

		public void action() {
			// Put hardware control code here.
			water = false;
		}

		public String toString() {
			return "Greenhouse water is off";
		}
	}

	private String thermostat = "Day";

	public class ThermostatNight extends Event {
		public ThermostatNight(long delayTime) {
			super(delayTime);
		}

		public void action() {
			// Put hardware control code here.
			thermostat = "Night";
		}

		public String toString() {
			return "Thermostat on night setting";
		}
	}

	public class ThermostatDay extends Event {
		public ThermostatDay(long delayTime) {
			super(delayTime);
		}

		public void action() {
			// Put hardware control code here.
			thermostat = "Day";
		}

		public String toString() {
			return "Thermostat on day setting";
		}
	}

	// An example of an action() that inserts a
	// new one of itself into the event list:
	/*
	 * 你可以注释掉GreenhouseController.java line16, 然后把terminate的时间调长t1
	 * 你会发现Bing! 会一直打印，打印的次数是t1/900, 只要没用terminate,  就有机会Bing!
	 * 打印的Bing! 并不是action 做的，而是Controller的run（） 打印的. action做的是一直往
	 * eventList 不停的增加Bell, 以同样的delaytime
	 * 这里值得学习的是 继承封装了 基类的东西，
	 * 1.例如addEvent, 在GreenhouseController.java到处可以用
	 * 而且内嵌的对象也可以用。但是eventList只能是通过addEvent来调用，因为它是私有的。
	 */
	public class Bell extends Event {
		public Bell(long delayTime) {
			super(delayTime);
		}

		public void action() {
			addEvent(new Bell(delayTime));
		}

		public String toString() {
			return "Bing!";
		}
	}

	public class Restart extends Event {
		private Event[] eventList;

		public Restart(long delayTime, Event[] eventList) {
			super(delayTime);
			this.eventList = eventList;
			for (int i = 0; i < eventList.length; i++)
				addEvent(eventList[i]);
		}

		public void action() {
			for (int i = 0; i < eventList.length; i++) {
				eventList[i].start(); // Rerun each event
				addEvent(eventList[i]);
			}
			start(); // Rerun this Event
			addEvent(this);
		}

		public String toString() {
			return "Restarting system";
		}
	}

	public class Terminate extends Event {
		public Terminate(long delayTime) {
			super(delayTime);
		}

		public void action() {
			System.exit(0);
		}

		public String toString() {
			return "Terminating";
		}
	}
} // /:~