package c11.exercise;

//: c11:E30_MapOrder2.java
/******************* Exercise 30 **************************
 * Fill a LinkedHashMap with String keys and objects of
 * your choice. Now extract the pairs, sort them based on
 * the keys, and re-insert them into the Map.
 *********************************************************/
import com.bruceeckel.util.*;
import java.util.*;

public class E30_MapOrder2 {
	public static void main(String[] args) {
		Map m1 = new LinkedHashMap();
		Collections2.fill(m1, Collections2.geography, 25);
		System.out.println(m1);
		Object keys[] = m1.keySet().toArray();
		Arrays.sort(keys);
		Map m2 = new LinkedHashMap();
		for (int i = 0; i < keys.length; i++)
			m2.put(keys[i], m1.get(keys[i]));
		System.out.println(m2);
	}
} // /:~