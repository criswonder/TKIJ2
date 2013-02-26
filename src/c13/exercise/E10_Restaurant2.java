package c13.exercise;

//: c13:E10_Restaurant2.java
/*******************
 * Exercise 10 ************************* Modify Restaurant.java to use
 * notifyAll() and observe any difference in behavior.
 *********************************************************/
class Order {
	private static int i = 0;
	private int count = i++;

	public Order() {
		if (count == 10) {
			System.out.println("Out of food, closing");
			System.exit(0);
		}
	}

	public String toString() {
		return "Order " + count;
	}
}

class WaitPerson extends Thread {
	private E10_Restaurant2 restaurant;

	public WaitPerson(E10_Restaurant2 r) {
		restaurant = r;
		start();
	}

	public void run() {
		while (true) {
			while (restaurant.order == null)
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			System.out.println("Waitperson got " + restaurant.order);
			restaurant.order = null;
		}
	}
}

class Chef extends Thread {
	private E10_Restaurant2 restaurant;
	private WaitPerson waitPerson;

	public Chef(E10_Restaurant2 r, WaitPerson w) {
		restaurant = r;
		waitPerson = w;
		start();
	}

	public void run() {
		while (true) {
			if (restaurant.order == null) {
				restaurant.order = new Order();
				System.out.print("Order up! ");
				synchronized (waitPerson) {
					waitPerson.notifyAll();
				}
			}
			try {
				sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}

public class E10_Restaurant2 {
	Order order; // Package access

	public static void main(String[] args) {
		E10_Restaurant2 restaurant = new E10_Restaurant2();
		WaitPerson waitPerson = new WaitPerson(restaurant);
		Chef chef = new Chef(restaurant, waitPerson);
	}
} // /:~