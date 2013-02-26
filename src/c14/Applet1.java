//: c14:Applet1.java
// Very simple applet.
package c14;

import javax.swing.*;
import java.awt.*;

public class Applet1 extends JApplet {
	public void init() {
		getContentPane().add(new JLabel("Applet!"));
	}
} // /:~