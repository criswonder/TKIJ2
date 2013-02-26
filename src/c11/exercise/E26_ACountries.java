package c11.exercise;

//: c11:E26_ACountries.java 
/****************** Exercise 26 ***************** 
 * Produce a Map and a Set containing all the 
 * countries that begin with 'A.' 
 ***********************************************/
import com.bruceeckel.util.*;
import java.util.*;

public class E26_ACountries {
	public static void main(String args[]) {
		TreeMap map = new TreeMap();
		TreeSet set = new TreeSet();
		Collections2.fill(map, Collections2.geography,
				CountryCapitals.pairs.length);
		Collections2.fill(set, Collections2.countries,
				CountryCapitals.pairs.length);
		Iterator it = map.keySet().iterator();
		String b = null;
		while (it.hasNext()) {
			String s = (String) it.next();
			if (s.startsWith("B")) {
				b = s;
				break;
			}
		}
		Map aMap = map.headMap(b);
		Set aSet = set.headSet(b);
		System.out.println("aMap = " + aMap);
		System.out.println("aSet = " + aSet);
	}
} // /:~
