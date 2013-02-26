package c11.exercise;
public class Mouse2 {
	private int mouseNumber;
	Mouse2(int i) { mouseNumber = i; }
	// Override Object.toString():
	public String toString() {
	return "This is Mouse #" + mouseNumber;
	}
	public int getNumber() {
	return mouseNumber;
	}
	} ///:~