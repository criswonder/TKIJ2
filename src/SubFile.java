import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * sub is short for substitute 
 */
public class SubFile {
	/*
	 * @file the file to substitute
	 * @regxs the regx to match string in the file
	 * @subs the strings to substitute the regx
	 * regularly regxs.length=subs.length
	 */
	public void sub(String file,String[] regxs,String[] subs) throws Exception{
		BufferedReader bfr=new BufferedReader(new FileReader(file));
		
		StringBuilder sb=new StringBuilder();
		Set regExs=new HashSet();
		Set subbs=new HashSet();
		for(int i=0;i<regxs.length;i++)
		{
			regExs.add(regxs[i]);
			subbs.add(subs[i]);
		}
		String s=null;
		while((s=bfr.readLine())!=null)
		{
			Iterator it=regExs.iterator();
			Iterator it2=subbs.iterator();
			while(it.hasNext())
			{
				Pattern p=Pattern.compile((String)it.next()); 
				Matcher m=p.matcher(s); 
				s=m.replaceFirst((String)it2.next());
				sb.append(s+"\r\n");
//				out.write(s.getBytes());
			}
		}
		FileOutputStream out = new FileOutputStream(file);
		
		out.write(sb.toString().getBytes());
		bfr.close();
		out.close();

	}
	
	public static void main(String[] args) throws Exception{
		SubFile sf=new SubFile();
		sf.sub("Data.txt", new String[]{"o"},new String[]{"aa"});
	}

}
