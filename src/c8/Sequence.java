package c8;

import com.bruceeckel.simpletest.*;

interface Selector {
	boolean end();

	Object current();

	void next();
}

public class Sequence {
	private static Test monitor = new Test();
	private Object[] objects;
	private int next = 0;

	public Sequence(int size) {
		objects = new Object[size];
	}

	public void add(Object x) {
		if (next < objects.length)
			objects[next++] = x;
	}

	private class SSelector implements Selector {
		private int i = 0;

		public boolean end() {
			return i == objects.length;
		}

		public Object current() {
			return objects[i];
		}

		public void next() {
			if (i < objects.length)
				i++;
		}
	}

	public Selector getSelector() {
		return new SSelector();
	}

	public static void main(String[] args) {
		Sequence sequence = new Sequence(10);
		for (int i = 0; i < 10; i++)
			sequence.add(Integer.toString(i));
		Selector selector = sequence.getSelector();
		while (!selector.end()) {
			System.out.println(selector.current());
			selector.next();
		}
		monitor.expect(new String[] { "0", "1", "2", "3", "4", "5", "6", "7",
				"8", "9" });
	}
} // /:~
