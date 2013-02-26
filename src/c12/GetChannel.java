package c12;

//: c12:GetChannel.java
//Getting channels from streams
//{Clean: data.txt}
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class GetChannel {
	public static void main(String[] args) throws Exception {
		ByteBuffer buff = ByteBuffer.allocate(1024);
		// Write a file:
		FileChannel fc = new FileOutputStream("data.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text ".getBytes()));
		fc.close();
		// Add to the end of the file:
		fc = new RandomAccessFile("data.txt", "rw").getChannel();
		fc.position(fc.size()); // Move to the end
		fc.write(ByteBuffer.wrap("Some more".getBytes()));
		fc.close();
		// Read the file: 650 Thinking in Java www.BruceEckel.com

		fc = new FileInputStream("data.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
	}
} // /:~