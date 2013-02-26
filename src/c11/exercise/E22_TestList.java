package c11.exercise;

//: c11:E22_TestList.java
/****************** Exercise 22 *****************
 * Create a class, then make an initialized array
 * of objects of your class. Fill a List from
 * your array. Create a subset of your List using
 * subList(), and then remove this subset from
 * your List using removeAll().
 ***********************************************/
import java.util.*;

class IDClass {
	private static int counter = 0;
	private int count = counter++;

	public String toString() {
		return "IDClass " + count;
	}
}


public class E22_TestList {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]) {
		IDClass[] idc = new IDClass[10];
		for (int i = 0; i < idc.length; i++)
			idc[i] = new IDClass();
		List lst = new ArrayList(Arrays.asList(idc));
//		List lst =Arrays.asList(idc);
		System.out.println("lst = " + lst);
		List subSet = new ArrayList(lst.subList(lst.size() / 4, lst.size() / 2));
//		List subSet = lst.subList(lst.size() / 4, lst.size() / 2);
		System.out.println("subSet = " + subSet);
		lst.removeAll(subSet);
		System.out.println("lst = " + lst);
	}
} // /:~