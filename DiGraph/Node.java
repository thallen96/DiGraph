package A6_Dijkstra;

public class Node {

	private String label;
	private long id, inDegree;
	
	public Node(long id, String label){
		this.id = id;
		this.label = label;
		inDegree = 0;
	}
	
	public String getLabel(){
		return label;
	}

	public long getId(){
		return id;
	}
	
	public long getInDegree(){
		return inDegree;
	}
	
	public void changeInDegree(long num){
		inDegree += num;
	}
	
	public Node copyNode(){
		Node copy = this;
		return copy;
	}
}
