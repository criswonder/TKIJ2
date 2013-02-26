package c13;

public class WebService implements Runnable{
	

	public void run() {
		// TODO Auto-generated method stub
		mao();
		synchronized(this){
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		mao();
	}
	private void mao() {
		int i=0;
		while(i<5){
		System.out.println("ballasdbal");
		i++;
		}
	}

}
