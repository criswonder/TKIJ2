package c8;

import java.util.Stack;

public class NodeTest {
	public static Stack st=new Stack();
	public static void main(String args[]){
		Node root=new Node(45);
		Node node23=new Node(23);
		Node node3=new Node(3);
		Node node44=new Node(44);
		Node node42=new Node(42);
		
		Node node56=new Node(56);
		Node node77=new Node(77);
		Node node76=new Node(76);
		Node node88=new Node(88);
		
		root.setLC(node23);
		node23.setLC(node3);
		node23.setRC(node44);
		node44.setLC(node42);
		
		root.setRC(node56);
		node56.setRC(node77);
		node77.setLC(node76);
		node77.setRC(node88);
		PutIntoStack(root);
		while(!st.isEmpty())
		{
		
		System.out.println(st.pop());
		}
	}
	public static void PutIntoStack(Node n){
		
		if(n.getLC()!=null){
			PutIntoStack(n.getLC());
		}
		if(n.getRC()!=null)
			PutIntoStack(n.getRC());
		st.add(n);
	}
	
}
