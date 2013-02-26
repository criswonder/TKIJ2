package c12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;

public class AppendFile {
	public static void main(String args[]) throws FileNotFoundException{
		append("Data.txt","fsdfdsf");


	}

public static void append(String file, String conent) {   
    BufferedWriter out = null;   
    try {   
        out = new BufferedWriter(new OutputStreamWriter(   
                new FileOutputStream(file, true)));   
        out.write(conent);   
    } catch (Exception e) {   
        e.printStackTrace();   
    } finally {   
        try {   
            out.close();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }    
}
public static void apend2(String fileName, String content) {   
    try {   
        // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件   
        FileWriter writer = new FileWriter(fileName, true);   
        writer.write(content);   
        writer.close();   
    } catch (IOException e) {   
        e.printStackTrace();   
    }   
} 
}
