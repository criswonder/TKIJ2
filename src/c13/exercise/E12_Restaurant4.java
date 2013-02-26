package c13.exercise;

//: c13:E12_Restaurant4.java
//{Args: 40}
/*******************
 * Exercise 12 ************************* Modify Restaurant.java so that multiple
 * WaitPersons generate order requests to multiple Chefs, who produce orders and
 * notify the WaitPerson that generated the request. You'll need to use queues
 * for both incoming order requests and outgoing orders.
 *********************************************************/
class Order3 {
	private static int i = 0;
	private int count = i++;
	private WaitPerson3 originator;

	public Order3(WaitPerson3 origin) {
		originator = origin; // Waiter that placed the order
	}

	// Who should deliver this meal?
	public WaitPerson3 destination() {
		return originator;
	}

	public String toString() {
		return "Order " + count;
	}

	public int number() {
		return count;
	}
}

class WaitPerson3 extends Thread {
	private static int i = 0;
	private int count = i++;
	private E12_Restaurant4 restaurant;
	private TQueue meals = new TQueue();

	public WaitPerson3(E12_Restaurant4 r) {
		restaurant = r;
		start();
	}

	public void run() {
		while (!isInterrupted()) {
			// Simplest approach -- one order at a time:
			Order3 order = new Order3(this);
			restaurant.putOrder(order);
			// TQueue waits upon a get() until there's a meal:
			order = (Order3) meals.get();
			if (order == null) // get() returned via interrupt
				break; // Out of while loop
			System.out.println(this + " delivers " + order);
		}
		System.out.println(this + " quitting");
	}

	public void putMeal(Order3 order) {
		meals.put(order);
	}

	public String toString() {
		return "WaitPerson " + count;
	}
}

class Chef3 extends Thread {
	private static int i = 0;
	private int count = i++;
	private E12_Restaurant4 restaurant;

	public Chef3(E12_Restaurant4 r) {
		restaurant = r;
		start();
	}

	public void run() {
		while (!isInterrupted()) {
			// TQueue waits until there's an order:
			Order3 order = restaurant.getOrder();
			if (order == null) // get() returned via interrupt
				break; // Out of while loop
			System.out.println(this + " got " + order);
			// Create the meal
			// ...
			// Put the order back on the queue of the waiter
			// that placed it:
			order.destination().putMeal(order);
		}
		System.out.println(this + " quitting");
	}

	public String toString() {
		return "Chef " + count;
	}
}

public class E12_Restaurant4 {
	private TQueue orders = new TQueue();
	private Chef3[] chefs = new Chef3[3];
	private WaitPerson3[] waiters = new WaitPerson3[6];
	private int maxOrders;

	public E12_Restaurant4(int maxOrders) {
		this.maxOrders = maxOrders;
		for (int i = 0; i < chefs.length; i++)
			chefs[i] = new Chef3(this);
		for (int i = 0; i < waiters.length; i++)
			waiters[i] = new WaitPerson3(this);
	}

	public synchronized void putOrder(Order3 newOrder) {
		if (newOrder.number() > maxOrders)
			shutdown();
		else
			orders.put(newOrder);
	}

	// No need for extra synchronization on this one;
	// get() already provides synchronization:
	public Order3 getOrder() {
		return (Order3) orders.get();
	}

	public synchronized void shutdown() {
		for (int i = 0; i < chefs.length; i++)
			chefs[i].interrupt();
		for (int i = 0; i < waiters.length; i++)
			waiters[i].interrupt();
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("argument: number of meals");
			System.exit(1);
		}
		//Chef 负责deliver meal
		//waitperson 负责生产 order
		//有两个queue,  一个meal，一个order
		/*
		 * chef 从order queue拿东西，如果没有 则等。有的话放到 投递order的那个
		 * waitperson的meals queue里面，每个waitperson都用一个meal queue
		 */
		new E12_Restaurant4(Integer.parseInt(args[0]));
	}
} // /:~
