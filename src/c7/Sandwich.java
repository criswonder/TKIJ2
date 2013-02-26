package c7;

//: c07:Sandwich.java
//Order of constructor calls.
import com.bruceeckel.simpletest.*;

class Meal {
	Meal() {
		System.out.println("Meal()");
	}
}

class Bread {
	Bread() {
		System.out.println("Bread()");
	}
}

class Cheese {
	Cheese() {
		System.out.println("Cheese()");
	}
}

class Lettuce {
	Lettuce() {
		System.out.println("Lettuce()");
	}
}

class Lunch extends Meal {
	private static Test monitor = new Test();
	Lunch() {
		System.out.println("Lunch()");
	}
}

class PortableLunch extends Lunch {
	PortableLunch() {
		System.out.println("PortableLunch()");
	}
}

public class Sandwich extends PortableLunch {
	private static Test monitor = new Test();
	private Bread b = new Bread();
	private Cheese c = new Cheese();
	private Lettuce l = new Lettuce();
	private Lunch m=new Lunch();

	public Sandwich() {
		System.out.println("Sandwich()");
	}

	public static void main(String[] args) {
		new Sandwich();
		monitor.expect(new String[] { "Meal()", "Lunch()", "PortableLunch()",
				"Bread()", "Cheese()", "Lettuce()", "Sandwich()" });
	}
} // /:~