package c4;

public class test1 {
	public test1(){
		
		
	};
	public test1(int a){
		this();
	}
	public void method1()
	{
		test1 aa=new test1();
		return;
		
	}
	public static void main(String args[]){
//		System.out.println(sum(0));
		System.out.println(sum(100));
//		System.out.println(sum(10));
		printSum(10);
	}
	public static int sum(int to){
		if(to<=0)
			return 0;
		int sum=0,start=0;
		while(start<to+1){
			sum=sum+start;
			printSum(start);
			start++;
		}
		return sum;
	}
	public static void printSum(int sum){
		if(sum<=1){
			System.out.println(1);
			return;
		}
		StringBuilder str_sum=new StringBuilder("1");
		for(int i=2;i<=sum;i++){
			str_sum.append("+"+i);
		}
		System.out.println(str_sum.toString());
	}
	public void poppuble(){
		int arry[]={1,2,23,2,3,1,33};
		for(int o=0;o<arry.length-1;o++){
			for(int i=0;i<arry.length-o-1;i++){
				if(arry[i]<arry[i+1])
					continue;
				int tem;
				tem=arry[i];
				arry[i]=arry[i+1];
				arry[i+1]=tem;
			}
		}
		System.out.println(arry);
	}
}
