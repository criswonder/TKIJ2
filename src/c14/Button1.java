package c14;

//: c14:Button1.java
//Putting buttons on an applet.
//<applet code=Button1 width=200 height=50></applet>
import javax.swing.*;
import java.awt.*;
import com.bruceeckel.swing.*;

public class Button1 extends JApplet {
	private JButton b1 = new JButton("Button 1"), b2 = new JButton("Button 2");

	public void init() {
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(b1);
		cp.add(b2);
	}

	public static void main(String[] args) {
		Console.run(new Button1(), 200, 50);
	}
} // /:~