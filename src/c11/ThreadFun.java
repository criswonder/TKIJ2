package c11;

public class ThreadFun {
	public static void main(String[] args){
		ThreadTest a=new ThreadTest();
		ThreadTest1 b=new ThreadTest1();
		Thread th1=new Thread(a);
		Thread th2=new Thread(b);
//		a.run();
//		b.run();
		th1.run();
		th2.run();
//		th1.start();
//		th2.start();
	}
}
class ThreadTest implements Runnable{
	public void run(){
		int i=0;
		while(i<100){
		System.out.println("this is A");
		i++;
		}
	}
}
class ThreadTest1 implements Runnable{
	public void run(){
		int i=0;
		while(i<100){
		System.out.println("this is B");
		i++;
		}
	}
}