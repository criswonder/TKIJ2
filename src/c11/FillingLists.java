package c11;

//: c11:FillingLists.java
//The Collections.fill() method.
import com.bruceeckel.simpletest.*;
import java.util.*;

public class FillingLists {
	private static Test monitor = new Test();

	public static void main(String[] args) {
		List list = new ArrayList();
		for (int i = 0; i < 10; i++)
			list.add("");
		System.out.println(list);
		Collections.fill(list, "Hello");
		System.out.println(list);
		monitor.expect(new String[] { "[Hello, Hello, Hello, Hello, Hello, "
				+ "Hello, Hello, Hello, Hello, Hello]" });
	}
} // /:~