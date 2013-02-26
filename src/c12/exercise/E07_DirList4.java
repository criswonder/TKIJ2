package c12.exercise;

//: c12:E07_DirList4.java
//{Args: \\w+\.java}
/*********************** Exercise 7 ***********************
 * Modify DirList.java to produce all the file names in the
 * current directory and subdirectories that satisfy the
 * given regular expression. Hint: use recursion to
 * traverse the subdirectories
 * *******************************************************/
import java.io.*;
import java.util.*;
import java.util.regex.*;
import com.bruceeckel.util.*;

public class E07_DirList4 {
	private static void listDir(File dir, final String regEx, String indent) {
		String[] files = dir.list(new FilenameFilter() {
			private Pattern pattern = Pattern.compile(regEx);

			public boolean accept(File dir, String name) {
				File f = new File(name);
				String fullpath=f.getAbsolutePath();
				File ff=new File(fullpath);
				if (ff.isDirectory()){
					
					return true;}
				else 
					return pattern.matcher(ff.getName()).matches();
//				return true;
			}
		});
		Arrays.sort(files, new AlphabeticComparator());
		for (int i = 0; i < files.length; i++) {
			System.out.println(indent + "+--" + files[i]);
			File child = new File(files[i]);
			if (child.isDirectory())
				listDir(child, regEx, indent + " ");
		}
	}

	public static void main(final String[] args) {
		if (args.length < 1) {
			System.out.println("Argument required: <regex>");
			System.exit(1);
		}
		File f = new File("H:/mylib/Projects/BlogTest");
		System.out.println(f.getName());
		listDir(f, args[0], " ");
	}
} // /:~