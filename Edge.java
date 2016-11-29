package A6_Dijkstra;

public class Edge {

	private String sLabel, dLabel, eLabel;
	private long id, weight;
	
	public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel){
		this.sLabel = sLabel;
		this.dLabel = dLabel;
		this.eLabel = Long.toString(idNum);
		this.weight = weight;
		id = idNum;
	}
	
	public long getId(){
		return id;
	}
	
	public long getWeight(){
		return weight;
	}
	
	public String getSLabel(){
		return sLabel;
	}
	
	public String getDLabel(){
		return dLabel;
	}
	
	public String getELabel(){
		return eLabel;
	}
}
