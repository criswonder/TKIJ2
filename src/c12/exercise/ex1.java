package c12.exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/*
 * 第十二章的总结
 * 第一题要求反向输出文件的值，是利用了iterator，获得最后的一个iterator然后 用previous往前迭代
 * 而我是用next正向迭代。linkedlist可以很轻松的获得迭代器。
 */
public class ex1 {
	public void openFile(String filename) throws Exception{
		LinkedList lklst=new LinkedList();
		BufferedReader bfrd=new BufferedReader(new FileReader(filename));
		String s;
		while((s=bfrd.readLine())!=null)lklst.add(s);
		bfrd.close();
		ListIterator iterator=lklst.listIterator();
		while(iterator.hasNext())
			System.out.println(iterator.next());
	}
	public static void main(String[] args) throws Exception{
		ex1 ex=new ex1();
		String filename="./test.txt";
//		ex.openFile(filename);
//		ex.manipulateFile(filename);
//		ex.getUpperCase(filename);
		ex.getFuckContent(filename, "main");
	}
	/*
	 * exercise 3,use arrylist
	 */
	public void manipulateFile(String filename) throws Exception{
		ArrayList rl=new ArrayList();
		BufferedReader bfrd=new BufferedReader(new FileReader(filename));
		String s;
		int line=0;
		while((s=bfrd.readLine())!=null)
		{
			line++;
			rl.add(""+line+" "+s);
		}
		int index=0;
		while(!rl.isEmpty())
		{
			
			System.out.println(rl.remove(index));
//			index++;
		}
	}
	/*
	 * exercise 4,print upperCase
	 */
	public void getUpperCase(String filename) throws Exception{
		LinkedList lst=new LinkedList();
		BufferedReader brd=new BufferedReader(new FileReader(filename));
		String s;
		while((s=brd.readLine())!=null)
			lst.add(s);
		Iterator it=lst.iterator();
		while(it.hasNext())
			System.out.println(((String)it.next()).toUpperCase());
	}
	/*
	 * exercise 5,指定内容，然后在文件中搜索，输出包含类容的行
	 */
	public void getFuckContent(String filename, String content) throws Exception{
		LinkedList lst=new LinkedList();
		BufferedReader brd=new BufferedReader(new FileReader(filename));
		String s;
		while((s=brd.readLine())!=null)
			lst.add(s);
		Iterator it=lst.iterator();
		
		while(it.hasNext())
		{
			String line=(String)it.next();
			if(line.indexOf(content)!=-1)
			System.out.println(line);
		}
	}
}
