//: c12:ChangeSystemOut.java
// Turn System.out into a PrintWriter.
import com.bruceeckel.simpletest.*;
import java.io.*;

public class ChangeSystemOut {
	private static Test monitor = new Test();

	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out, true);
		out.println("Hello, world");
		System.out.println("Hello, world");
		monitor.expect(new String[] { "Hello, world" });
	}
} // /:~