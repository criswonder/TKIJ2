package c13;

public class A extends B{
	public int a=1;
	public A(){
		a=3;
	}
	public static void main(String[] args){
		A a=new A();
	}
}
class B{
	public int b=1;
	public B(){
		b=4;
	}
}