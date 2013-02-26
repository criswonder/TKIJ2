package c8;

interface Copyselector{
	Object current();
	void next();
	void back();
	boolean end();
	boolean begin();
}
public class CopySequence {
	private int i;
	private Object[] objects;
	public CopySequence(int len){
		objects=new Object[len];
	}
	public void add(String ele){
		objects[i]=ele;
		if(i<objects.length-1)i++;
	}
	public Selector getSelector(){
		return new Selector();
	}
	private class Selector implements Copyselector{
//		private int i;
		public Object current(){
			
			return objects[i];
			
		}
		public void next(){
			if(i<objects.length)
			i++;
		}
		public void back(){
			if(i>0)i--;
		}
		public boolean end(){
			return i==objects.length;
			
		}
		public boolean begin(){
			return i==0;
		}
	} 
	public static void main(String[] args){
		CopySequence cs=new CopySequence(10);
		for(int i=0;i<10;i++){
			cs.add(Integer.toString(i));
		}
		Copyselector copySelect=cs.getSelector();
		if(!copySelect.end()){
		while(!copySelect.begin()){ 
			System.out.println(copySelect.current());
			copySelect.back();
		}
		}
		System.out.println(cs.toString());
	}
}

