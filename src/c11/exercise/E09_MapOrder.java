package c11.exercise;

import com.bruceeckel.util.*;
import java.util.*;

public class E09_MapOrder {
	public static void main(String[] args) {
		Map m1 = new HashMap();
		Collections2.fill(m1, Collections2.geography, 10);
		System.out.println(m1);
		Object keys[] = m1.keySet().toArray();
		Arrays.sort(keys);
		Map m2 = new LinkedHashMap();
		for (int i = 0; i < keys.length; i++)
			m2.put(keys[i], m1.get(keys[i]));
		System.out.println(m2);
	}
} // /:~