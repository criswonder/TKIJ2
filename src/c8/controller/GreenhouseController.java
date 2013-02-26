//: c08:GreenhouseController.java
// Configure and execute the greenhouse system.
// {Args: 5000}
package c8.controller;
import c8.controller.*;
/*
 * 这几个类的关系是Event 是例如 Bell, LightOn, LightOff等等的基类
 * Controller是GreenhouseController的基类，有addEvent方法
 * GreenhouseController包含了很多Event的类
 */
public class GreenhouseController {
	public static void main(String[] args) {
		GreenhouseControls gc = new GreenhouseControls();
		// Instead of hard-wiring, you could parse
		// configuration information from a text file here:
		gc.addEvent(gc.new Bell(900));
//		gc.addEvent(gc.new  LightOn(900));
		Event[] eventList = { gc.new ThermostatNight(0), gc.new LightOn(200),
				gc.new LightOff(400), gc.new WaterOn(600),
				gc.new WaterOff(800), gc.new ThermostatDay(1400) };
//		gc.addEvent(gc.new Restart(2000, eventList));
		if (true)
			gc.addEvent(gc.new Terminate(9000));
		gc.run();
	}
} // /:~