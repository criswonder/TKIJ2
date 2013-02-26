package c8;

public class Node {
	private int value;
	private Node leftChild;
	private Node rightChild;
	private Node parent;
	public Node(int v){
		value=v;
	}
	public Node getRC(){
		return rightChild;
	}
	public void setRC(Node rc){
		rightChild=rc;
	}
	public Node getLC(){
		return leftChild;
	}
	public void setLC(Node lc){
		leftChild=lc;
	}
	public String toString(){
		return Integer.toString(value);
	}
	
}
