import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class CopyOfDirList {
	public static void main(String[] args){
		OutputStream out=new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				// TODO Auto-generated method stub
				
			}
		};
		BufferedOutputStream bos=new BufferedOutputStream(out);
		FilterOutputStream fos=new FilterOutputStream(out);
	}
	public void test(){
		File path=new File(".");
		path.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}
}
