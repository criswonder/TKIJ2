package c11.exercise;

//: c11:E33_ListPerformance2.java
//{RunByHand} (Takes too long during the build process)
/******************** Exercise 33 ************************
 * Create a FastTraversalLinkedList that internally uses a
 * LinkedList for rapid insertions and removals, and an
 * ArrayList for rapid traversals and get() operations.
 * Test it by modifying ListPerformance.java.
 *********************************************************/
import com.bruceeckel.util.*;
import java.util.*;

class FastTraversalLinkedList extends AbstractList {
	private class FlaggedArrayList {
		private FlaggedLinkedList companion;
		boolean changed = false;
		private ArrayList list = new ArrayList();

		public void addCompanion(FlaggedLinkedList other) {
			companion = other;
		}

		private void synchronize() {
			if (companion.changed) {
				list = new ArrayList(companion.list);
				companion.changed = false;
			}
		}

		public Object get(int index) {
			synchronize();
			return list.get(index);
		}

		public Object remove(int index) {
			synchronize();
			changed = true;
			return list.remove(index);
		}

		public boolean remove(Object item) {
			synchronize();
			changed = true;
			return list.remove(item);
		}

		public int size() {
			synchronize();
			return list.size();
		}

		public Iterator iterator() {
			return list.iterator();
		}
	}

	private class FlaggedLinkedList {
		private FlaggedArrayList companion;
		boolean changed = false;
		LinkedList list = new LinkedList();

		public void addCompanion(FlaggedArrayList other) {
			companion = other;
		}

		private void synchronize() {
			if (companion.changed) {
				list = new LinkedList(companion.list);
				companion.changed = false;
			}
		}

		public void add(int index, Object item) {
			synchronize();
			changed = true;
			list.add(index, item);
		}

		public boolean add(Object item) {
			synchronize();
			changed = true;
			return list.add(item);
		}
	}

	private FlaggedArrayList aList = new FlaggedArrayList();
	private FlaggedLinkedList lList = new FlaggedLinkedList();
	// Connect the two so they can synchronize:
	{
		aList.addCompanion(lList);
		lList.addCompanion(aList);
	}

	public int size() {
		return aList.size();
	}

	public Object get(int arg) {
		return aList.get(arg);
	}

	public void add(int index, Object item) {
		lList.add(index, item);
	}

	public boolean add(Object item) {
		return lList.add(item);
	}

	// Through testing, we discover that the ArrayList is
	// actually much faster for removals than the LinkedList:
	public Object remove(int index) {
		return aList.remove(index);
	}

	public boolean remove(Object item) {
		return aList.remove(item);
	}

	public Iterator iterator() {
		return aList.iterator();
	}
}

public class E33_ListPerformance2 {
	private static int reps = 10000;
	private static int quantity = reps / 10;

	private abstract static class Tester {
		private String name;

		Tester(String name) {
			this.name = name;
		}

		abstract void test(List a);
	}

	private static Tester[] tests = { new Tester("get") {
		void test(List a) {
			for (int i = 0; i < reps; i++) {
				for (int j = 0; j < quantity; j++)
					a.get(j);
			}
		}
	}, new Tester("iteration") {
		void test(List a) {
			for (int i = 0; i < reps; i++) {
				Iterator it = a.iterator();
				while (it.hasNext())
					it.next(); // Produces value
			}
		}
	},
			// Modified so it doesn't use the iterator:
			new Tester("insert") {
				void test(List a) {
					int half = a.size() / 2;
					String s = "test";
					for (int i = 0; i < reps * 10; i++)
						a.add(half, s);
				}
			},
			// Modified so it doesn't use the iterator:
			new Tester("remove at location") {
				void test(List a) {
					int half = a.size() / 2;
					for (int loc = a.size() / 2; loc < a.size(); loc++)
						a.remove(loc);
				}
			}, new Tester("remove by object") {
				void test(List a) {
					int half = a.size() / 2;
					for (int loc = a.size() / 2; loc < a.size(); loc++) {
						Object item = a.get(loc);
						a.remove(item);
					}
				}
			}, };

	public static void test(List a) {
		// Strip qualifiers from class name:
		System.out.println("Testing "
				+ a.getClass().getName().replaceAll("\\w+\\.", ""));
		for (int i = 0; i < tests.length; i++) {
			Collections2.fill(a, Collections2.countries.reset(), quantity);
			System.out.print(tests[i].name);
			long t1 = System.currentTimeMillis();
			tests[i].test(a);
			long t2 = System.currentTimeMillis();
			System.out.println(": " + (t2 - t1));
		}
	}

	public static void main(String[] args) {
		// Choose a different number of
		// repetitions via the command line:
		if (args.length > 0)
			reps = Integer.parseInt(args[0]);
		System.out.println(reps + " repetitions");
		test(new ArrayList());
		test(new LinkedList());
		test(new FastTraversalLinkedList());
	}
} // /:~