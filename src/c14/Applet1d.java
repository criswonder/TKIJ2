//: c14:Applet1d.java
// Console runs applets from the command line.
// <applet code=Applet1d width=100 height=50></applet>
package c14;
import javax.swing.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class Applet1d extends JApplet {
	public void init() {
		getContentPane().add(new JLabel("Applet!"));
	}

	public static void main(String[] args) {
		Console.run(new Applet1d(), 100, 300);
	}
} // /:~