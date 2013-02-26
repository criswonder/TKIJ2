package c12.exercise;

//: c12:E08_SortedDirList.java
/****************** Exercise 8 ******************
 * Create a class called SortedDirList with a
 * constructor that takes file path information
 * and builds a sorted directory list from the
 * files at that path. Create two overloaded
 * list() methods that will either produce the
 * whole list or a subset of the list based on an
 * argument. Add a size() method that takes a
 * file name and produces the size of that file.
 ***********************************************/
import java.io.*;
import java.util.*;
import com.bruceeckel.util.*;

class SortedDirList {
	File path;

	public SortedDirList() {
		path = new File(".");
	}

	public SortedDirList(String filePath) {
		path = new File(filePath);
	}

	public String[] list() {
		String[] list = path.list(new FilenameFilter() {
			public boolean accept(File dir, String n) {
				// Accept anything:
				return true;
			}
		});
		Arrays.sort(list, new AlphabeticComparator());
		return list;
	}

	public String[] list(final String afn) {
		String[] list = path.list(new FilenameFilter() {
			public boolean accept(File dir, String n) {
				String f = new File(n).getName();
				return f.indexOf(afn) != -1;
			}
		});
		Arrays.sort(list, new AlphabeticComparator());
		return list;
	}

	public long length(final String fileName) {
		File[] files = path.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().indexOf(fileName) != -1;
			}
		});
		if (files.length > 1)
			throw new RuntimeException("More than one match for " + fileName);
		return files[0].length();
	}
}

public class E08_SortedDirList {
	public static void main(String args[]) {
		// Default constructor == current directory
		SortedDirList dir = new SortedDirList();
		System.out.println(Arrays.asList(dir.list()));
		System.out.println(Arrays.asList(dir.list(".java")));
		String f = dir.list(".java")[0];
		System.out.println("Length of " + f + " is " + dir.length(f));
	}
} // /:~