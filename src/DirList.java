import java.io.*;
import java.util.*;
import java.util.regex.*;

public class DirList {
	public static void main(String[] args) {
		String[] arg = { "src" };
		args = arg;
		File path = new File(".");
		String[] list;
		if (args.length == 0)
			list = path.list();
		else
			list = path.list(new DirFilter(args[0]));

		for (int i = 0; i < list.length; i++)
			System.out.println(list[i]);
	}
}

class DirFilter implements FilenameFilter {
	private Pattern pattern;

	public DirFilter(String regex) {
		pattern = Pattern.compile(regex);
	}

	public boolean accept(File dir, String name) {
		// Strip path information, search for regex:
		return pattern.matcher(new File(name).getName()).matches();
	}
} // /:~
