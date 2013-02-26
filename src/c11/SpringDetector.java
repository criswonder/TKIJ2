package c11;

//: c11:SpringDetector.java 
//What will the weather be? 
import com.bruceeckel.simpletest.*;
import java.util.*;
import java.lang.reflect.*;

@SuppressWarnings("rawtypes")
public class SpringDetector {
	private static Test monitor = new Test();

	// Uses a Groundhog or class derived from Groundhog:
	@SuppressWarnings("unchecked")
	public static void detectSpring(Class groundHogClass) throws Exception {
		Constructor ghog = groundHogClass
				.getConstructor(new Class[] { int.class });
		Map map = new HashMap();
		for (int i = 0; i < 10; i++)
			map.put(ghog.newInstance(new Object[] { new Integer(i) }),
					new Prediction());
		System.out.println("map = " + map + "\n");
		Groundhog gh = (Groundhog) ghog.newInstance(new Object[] { new Integer(
				3) });
		System.out.println("Looking up prediction for " + gh);
		if (map.containsKey(gh))
			System.out.println((Prediction) map.get(gh));
		else
			System.out.println("Key not found: " + gh);
	}

	public static void main(String[] args) throws Exception {
		detectSpring(Groundhog.class);
		detectSpring(Groundhog2.class);
		monitor.expect(new String[] {
				"%% map = \\{(Groundhog #\\d="
						+ "(Early Spring!|Six more weeks of Winter!)"
						+ "(, )?){10}\\}", "",
				"Looking up prediction for Groundhog #3",
				"Key not found: Groundhog #3" });
	}
} // /:~
