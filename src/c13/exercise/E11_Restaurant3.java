package c13.exercise;

//: c13:E11_Restaurant3.java
/******************* Exercise 11 *************************
 * Modify Restaurant.java so that there are multiple
 * WaitPersons, and indicate which one gets each Order.
 *********************************************************/
import java.util.*;

class Order2 {
	private static int i = 0;
	private int count = i++;

	public Order2() {
		if (count == 20) {
			System.out.println("Out of food, closing");
			System.exit(0);
		}
	}

	public String toString() {
		return "Order " + count;
	}
}

class WaitPerson2 extends Thread {
	private int id;
	private E11_Restaurant3 restaurant;

	public WaitPerson2(E11_Restaurant3 r, int id) {
		this.id = id;
		restaurant = r;
		start();
	}

	public void run() {
		while (true) {
			while (restaurant.order == null)
				synchronized (restaurant) {
					try {
						// All threads wait on the same object.
						restaurant.wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			System.out.println(this + " got order " + restaurant.order);
			restaurant.order = null;
		}
	}

	public String toString() {
		return "" + id;
	}
}

class Chef2 extends Thread {
	private E11_Restaurant3 restaurant;
	private Random rand = new Random();

	public Chef2(E11_Restaurant3 r) {
		restaurant = r;
		start();
	}

	public void run() {
		while (true) {
			if (restaurant.order == null) {
				restaurant.order = new Order2();
				System.out.print("Order up! ");
				synchronized (restaurant) {
					restaurant.notify();
					//如果notifyAll的话，很多waitperson的线程都会工作起来，就会几个waitperson拿
					//同一个order的情况
//					restaurant.notifyAll(); // Doesn't work!
				}
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}

public class E11_Restaurant3 {
	volatile Order2 order; // Package access

	public static void main(String[] args) {
		//这里都是同一个restaurant
		E11_Restaurant3 restaurant = new E11_Restaurant3();
		for (int i = 0; i < 5; i++)
			new WaitPerson2(restaurant, i);
		Chef2 chef = new Chef2(restaurant);
	}
} // /:~