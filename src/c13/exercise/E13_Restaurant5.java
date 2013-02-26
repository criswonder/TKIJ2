package c13.exercise;

//: c13:E13_Restaurant5.java
//{Args: 40}
/*******************
 * Exercise 13 ************************* Modify the previous exercise to add
 * Customer objects that are also threads. The Customers will place order
 * requests with WaitPersons, who give the requests to the Chefs, who fulfill
 * the orders and notify the appropriate WaitPerson, who gives it to the
 * appropriate Customer.
 *********************************************************/
class Order4 {
	private static int i = 0;
	private int count = i++;
	private Customer customer;
	private WaitPerson4 waiter;

	public Order4(Customer origin) {
		customer = origin; // Customer that placed the order
	}

	public void setWaiter(WaitPerson4 waiter) {
		this.waiter = waiter;
	}

	// Who should deliver this meal?
	public WaitPerson4 getWaiter() {
		return waiter;
	}

	// Who does the meal go to?
	public Customer getCustomer() {
		return customer;
	}

	public String toString() {
		return "Order " + count + ", waiter " + waiter + ", customer "
				+ customer;
	}

	public int number() {
		return count;
	}
}

class Customer extends Thread {
	private static int i = 0;
	private int count = i++;
	private E13_Restaurant5 restaurant;
	private Order4 meal;

	public Customer(E13_Restaurant5 r) {
		restaurant = r;
		start();
	}

	public void run() {
		while (!isInterrupted()) {
			// wait()s until a waiter is available:
			WaitPerson4 waiter = restaurant.getWaiter();
			waiter.giveOrder(new Order4(this));
			// Waits until the meal shows up:
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					return; // Quit
				}
			}
			System.out.println(this + " eats " + meal);
			meal = null;
		}
		System.out.println(this + " quitting");
	}

	public synchronized void putMeal(Order4 order) {
		if (meal != null)
			throw new RuntimeException("Already have a meal!");
		meal = order;
		notify();
	}

	public String toString() {
		return "Customer " + count;
	}
}

class WaitPerson4 extends Thread {
	private static int i = 0;
	private int count = i++;
	private E13_Restaurant5 restaurant;
	private TQueue meals = new TQueue();

	public WaitPerson4(E13_Restaurant5 r) {
		restaurant = r;
		start();
	}

	public void run() {
		while (!isInterrupted()) {
			// Make yourself available to customers:
			restaurant.waiterAvailable(this);
			// Wait for chef to finish a meal:
			Order4 order = (Order4) meals.get();
			if (order == null) // get() returned via interrupt
				break; // Out of while loop
			System.out.println(this + " delivers " + order);
			order.getCustomer().putMeal(order);
		}
		System.out.println(this + " quitting");
	}

	public synchronized void giveOrder(Order4 order) {
		order.setWaiter(this);
		restaurant.putOrder(order);
	}

	public void putMeal(Order4 order) {
		meals.put(order);
	}

	public String toString() {
		return "WaitPerson " + count;
	}
}

class Chef4 extends Thread {
	private static int i = 0;
	private int count = i++;
	private E13_Restaurant5 restaurant;

	public Chef4(E13_Restaurant5 r) {
		restaurant = r;
		start();
	}

	public void run() {
		while (!isInterrupted()) {
			// TQueue waits until there's an order:
			Order4 order = restaurant.getOrder();
			if (order == null) // get() returned via interrupt
				break; // Out of while loop
			System.out.println(this + " got " + order);
			// Create the meal
			// ...
			// Put the order back on the queue of the waiter
			// that placed it:
			order.getWaiter().putMeal(order);
		}
		System.out.println(this + " quitting");
	}

	public String toString() {
		return "Chef " + count;
	}
}

public class E13_Restaurant5 {
	private TQueue orders = new TQueue();
	private TQueue waiterQueue = new TQueue();
	private Chef4[] chefs = new Chef4[3];
	private WaitPerson4[] waiters = new WaitPerson4[6];
	private Customer[] customers = new Customer[12];
	private int maxOrders;

	public E13_Restaurant5(int maxOrders) {
		this.maxOrders = maxOrders;
		for (int i = 0; i < chefs.length; i++)
			chefs[i] = new Chef4(this);
		for (int i = 0; i < waiters.length; i++)
			waiters[i] = new WaitPerson4(this);
		for (int i = 0; i < customers.length; i++)
			customers[i] = new Customer(this);
	}

	public void waiterAvailable(WaitPerson4 waiter) {
		waiterQueue.put(waiter);
	}

	public WaitPerson4 getWaiter() {
		return (WaitPerson4) waiterQueue.get();
	}

	public synchronized void putOrder(Order4 newOrder) {
		if (newOrder.number() > maxOrders)
			shutdown();
		else
			orders.put(newOrder);
	}

	public Order4 getOrder() {
		return (Order4) orders.get();
	}

	public synchronized void shutdown() {
		for (int i = 0; i < chefs.length; i++)
			chefs[i].interrupt();
		for (int i = 0; i < waiters.length; i++)
			waiters[i].interrupt();
		for (int i = 0; i < customers.length; i++)
			customers[i].interrupt();
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("argument: number of meals");
			System.exit(1);
		}
		new E13_Restaurant5(Integer.parseInt(args[0]));
	}
} // /:~