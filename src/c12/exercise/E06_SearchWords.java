package c12.exercise;

//: c12:E06_SearchWords.java
//{Args: java util main Reader}
/****************** Exercise 6 ******************
 * Modify DirList.java so that the FilenameFilter
 * actually opens each file and accepts the file
 * based on whether any of the trailing arguments
 * on the command line exist in that file.
 ***********************************************/
import java.io.*;
import java.util.*;
import com.bruceeckel.util.*;
/*
 * 这一题，从commandline 读取2个以上的参数，第一个是文件的名字，后面的是文件中要搜索的词，
 * 默认是搜索.
 * DirFilter 实现了FilenameFilter接口，所以File dir=new File("."P)
 * 然后 List list=dir.list(new DirFilter())就可以 把DirFilter accept函数返回true的加入到list中去
 */
class DirFilter implements FilenameFilter {
	String afn;
	Set words = new HashSet();

	DirFilter(String afn, String[] args) {
		this.afn = afn;
		for (int i = 1; i < args.length; i++)
			words.add(args[i]);
	}

	public boolean accept(File dir, String name) {// Strip path information:
		String f = new File(name).getName();
		// Only investigate files containing afn:
		if (f.indexOf(afn) == -1)
			return false;
		System.out.println("** " + f);
		// Modified from E05_FindWords.java:
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			String s;
			while ((s = in.readLine()) != null) {
				Iterator it = words.iterator();
				while (it.hasNext()) {
					String candidate = (String) it.next();
					if (s.indexOf(candidate) != -1) {
						// Print the line that first matched:
						System.out.println(s);
						return true;
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return false;
	}
}

public class E06_SearchWords {
	public static void main(String[] args) {
		File path = new File(".");
		String[] list;
		list = path.list(new DirFilter(args[0], args));
		Arrays.sort(list, new AlphabeticComparator());
		System.out.println(">> Resulting List <<");
		for (int i = 0; i < list.length; i++)
			System.out.println(list[i]);
	}
} // /:~