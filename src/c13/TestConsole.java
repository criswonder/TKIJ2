package c13;

public class TestConsole {
	public static void main(String[] args){
		WebService ws=new WebService();
		Thread threa1=new Thread(ws, "mao");
		System.out.println("----------------------------------");
		ws.notify();
	}

}
