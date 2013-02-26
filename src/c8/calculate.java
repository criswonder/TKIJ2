package c8;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class calculate {
	public Random a;
	public String sum;
	public String sub;//subtract
	public calculate(){
		a=new Random();
		sum="+";
		sub="-";
	}
	public static void main(String[] args) throws IOException{
		calculate cal=new calculate();
		int a=0;
		DataOutputStream out2 = new DataOutputStream(
				new BufferedOutputStream(
				new FileOutputStream("c:/calculate.txt")));
		while(a<50){
			File fl=new File("c:/calculate.txt");
			if(!fl.exists()){
				fl.createNewFile();
			}

			out2.writeChars(cal.a.nextInt(50)+"+"+cal.a.nextInt(50)+"=");
			out2.writeChars("\n");
			//System.out.println(cal.a.nextInt(50)+"+"+cal.a.nextInt(50)+"=");
			
			a++;
		}
		out2.close();
	}
}
