package c11.exercise;

//: c11:E32_SlowSet.java 
/****************** Exercise 32 ***************** 
 * Using SlowMap.java for inspiration, create a 
 * SlowSet. 
 ***********************************************/
import java.util.*;
import com.bruceeckel.util.*;

class SlowSet extends AbstractSet {
	private ArrayList keys = new ArrayList();

	public boolean add(Object key) {
		if (!contains(key)) {
			keys.add(key);
			return true;
		} else
			return false;
	}

	public boolean contains(Object key) {
		return keys.contains(key);
	}

	public Iterator iterator() {
		return keys.iterator();
	}

	public int size() {
		return keys.size();
	}
}

public class E32_SlowSet {
	public static void main(String[] args) {
		SlowSet ss = new SlowSet();
		Collections2.fill(ss, Collections2.countries.reset(), 10);
		Collections2.fill(ss, Collections2.countries.reset(), 10);
		Collections2.fill(ss, Collections2.countries.reset(), 10);
		System.out.println(ss);
	}
} // /:~
