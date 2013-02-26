package c11.exercise;

//: c11:E34_SlowMapTest.java
/****************** Exercise 34 *****************
 * Apply the tests in Map1.java to SlowMap to
 * verify that it works. Fix anything in SlowMap
 * that doesn't work correctly.
 ***********************************************/
import java.util.*;

import c11.MPair;

import com.bruceeckel.util.*;

class SlowMap2 extends AbstractMap {
	private ArrayList keys = new ArrayList(), values = new ArrayList();

	public Object put(Object key, Object value) {
		Object result = get(key);
		if (!keys.contains(key)) {
			keys.add(key);
			values.add(value);
		} else
			values.set(keys.indexOf(key), value);
		return result;
	}

	public Object get(Object key) {
		if (!keys.contains(key))
			return null;
		return values.get(keys.indexOf(key));
	}

	public Set entrySet() {
		Set entries = new HashSet();
		Iterator ki = keys.iterator(), vi = values.iterator();
		while (ki.hasNext())
			entries.add(new MPair(ki.next(), vi.next()));
		return entries;
	}
}

public class E34_SlowMapTest {
	static Collections2.StringPairGenerator geo = Collections2.geography;
	static Collections2.RandStringPairGenerator rsp = Collections2.rsp;

	// Producing a Set of the keys:
	public static void printKeys(Map m) {
		System.out.print("Size = " + m.size() + ", ");
		System.out.print("Keys: ");
		System.out.println(m.keySet());
	}

	// Producing a Collection of the values:
	public static void printValues(Map m) {
		System.out.print("Values: ");
		System.out.println(m.values());
	}

	public static void test(Map m) {
		Collections2.fill(m, geo, 25);
		// Map has 'Set' behavior for keys:
		Collections2.fill(m, geo.reset(), 25);
		printKeys(m);
		printValues(m);
		System.out.println(m);
		String key = CountryCapitals.pairs[4][0];
		String value = CountryCapitals.pairs[4][1];
		System.out.println("m.containsKey(\"" + key + "\"): "
				+ m.containsKey(key));
		System.out.println("m.get(\"" + key + "\"): " + m.get(key));
		System.out.println("m.containsValue(\"" + value + "\"): "
				+ m.containsValue(value));
		Map m2 = new TreeMap();
		Collections2.fill(m2, rsp, 25);
		m.putAll(m2);
		printKeys(m);
		key = m.keySet().iterator().next().toString();
		System.out.println("First key in map: " + key);
		m.remove(key);
		printKeys(m);
		m.clear();
		System.out.println("m.isEmpty(): " + m.isEmpty());
		Collections2.fill(m, geo.reset(), 25);
		// Operations on the Set change the Map:
		m.keySet().removeAll(m.keySet());
		System.out.println("m.isEmpty(): " + m.isEmpty());
	}

	public static void main(String[] args) {
		System.out.println("Testing SlowMap2");
		test(new SlowMap2());
	}
} // /:~