package A6_Dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;


public class DiGraph implements DiGraph_Interface{
	private Map<String, Node> nodeHash = new HashMap <String,Node>();
	private Map<Long,Node> idHash = new HashMap <Long,Node>();
	private Map<Long, Edge> edgeHash = new HashMap <Long,Edge>();
	private Map<String,LinkedList<Edge>> vertices = new HashMap<String,LinkedList<Edge>>();
	private int numEdges = 0, numNodes = 0;

	public boolean addNode(long idNum, String label) {
		//Tests if node is in graph, id < 0, or if id has been used
		if(nodeHash.containsKey(label) || idNum < 0 || idHash.containsKey(idNum) || label == null){
			return false;
		}else{
			//Adds new node in all graph structures
			Node newNode = new Node(idNum, label);
			nodeHash.put(label, newNode);
			idHash.put(idNum, newNode);
			vertices.put(label, new LinkedList<Edge>());
			numNodes++;
			return true;
		}
	}
	
	//Used mainly for testing
	public boolean addNode(Node n){
		return addNode(n.getId(), n.getLabel());
	}

	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {

		//Tests if id < 0, if the sNode/dNode are in the graph, and id id has been used
		if(idNum < 0 || !nodeHash.containsKey(sLabel) || !nodeHash.containsKey(dLabel) || edgeHash.containsKey(idNum)){
			return false;
		}
	
		//Tests if an edge is already between the two points
		for(int i=0; i < vertices.get(sLabel).size(); i++){
			if(vertices.get(sLabel).get(i).getDLabel().equals(dLabel)){
				return false;
			}
		}	
	
		//Updates all graph maps and increments numEdges
		Edge e = new Edge(idNum, sLabel, dLabel,weight,eLabel); 
		vertices.get(sLabel).add(e);
		nodeHash.get(dLabel).changeInDegree(1);
		edgeHash.put(idNum,	e);
		numEdges++;
		return true;
	}

	public boolean delNode(String label) {
		//Tests if node isn't in graph
		if(!nodeHash.containsKey(label)){
			return false;
		}
		//Deletes edges going from deleted node
		int len = vertices.get(label).size();
		for(int i=0; i < len; i++){
			String temp = vertices.get(label).get(i).getDLabel();
			if(nodeHash.containsKey(temp)){
				nodeHash.get(temp).changeInDegree(-1);
			}
		}

		if(nodeHash.get(label).getInDegree() > 0){
			findInEdge(label);
		}

		//Subtracts out edges
		numEdges = numEdges - len;
		//removes from vertex list, id and node hash lists, then increments numNodes in graph
		vertices.remove(label);
		idHash.remove(nodeHash.get(label).getId());
		nodeHash.remove(label);
		numNodes--;
		return true;
	}
	
	//Finds edges that come into the vertex and delegates to delEdge
	public boolean findInEdge(String label){
		for(Entry<String, LinkedList<Edge>> s: vertices.entrySet()){
			for(int i=0; i < vertices.get(s.getKey()).size();i++){
				if(vertices.get(s.getKey()).get(i).getDLabel().equals(label)){
					delEdge(s.getKey(),label);
				}
			}
		}	
		return false;
	}

	public boolean delEdge(String sLabel, String dLabel) {
		boolean tOrF = false;
		//Tests if edge is in graph
		if(!vertices.containsKey(sLabel) || !vertices.containsKey(dLabel)){
			return false;
		}
		
		//Searches if edge exists from sNode
		//Then removes edge from edge and vertices map 
		for(int i=0; i < vertices.get(sLabel).size(); i++){
			if(vertices.get(sLabel).get(i).getDLabel().equals(dLabel)){
				edgeHash.remove(vertices.get(sLabel).get(i).getId());
				vertices.get(sLabel).remove(i);
				tOrF = true;
			}
		}
		
		if(tOrF == false){
			return tOrF;
		} else{
			nodeHash.get(dLabel).changeInDegree(-1);
			numEdges--;
			return tOrF;
		}	
	}


	public long numNodes() {
		return numNodes;
	}

	public long numEdges() {
		return numEdges;
	}

	public String[] topoSort() {
		
		LinkedList<Node> q = new LinkedList<Node>();
		int count = 0;
		String[] topoS = new String[numNodes];
		DiGraph graph = new DiGraph();
		Node tempNode;
		
		//Copies graph into a temp graph
		for(Map.Entry<String, Node> entry : nodeHash.entrySet()){
			graph.nodeHash.put(entry.getKey(), entry.getValue());
		}
		
		//Adds entries of inDegree 0 to linkedList 
		for(Map.Entry<String, Node> entry : graph.nodeHash.entrySet()){
			if(entry.getValue().getInDegree() == 0){
				q.add(entry.getValue());
			}
		}
		
		while(q.size() > 0){
			//Removes and saves top node of linkedList
			tempNode = q.remove();
			String temp = tempNode.getLabel();
			
			//visits child of current node and determines if any is only visited from curNode
			for(int i=0; i < vertices.get(temp).size(); i++){
				graph.nodeHash.get(vertices.get(temp).get(i).getDLabel()).changeInDegree(-1);
				if(graph.nodeHash.get(vertices.get(temp).get(i).getDLabel()).getInDegree() == 0){
					q.add(graph.nodeHash.get(vertices.get(temp).get(i).getDLabel()));
				}
			}
			topoS[count] = tempNode.getLabel();
			count++;
		}

		if(topoS[topoS.length-1] == null){
			return null;
		}
		return topoS;
	}

	public ShortestPathInfo[] shortestPath(String label) { //Takes in start node label
		
		Queue<Node> nodeOrder = new LinkedList<Node>();
		ArrayList<ShortestPathInfo> known = new ArrayList<ShortestPathInfo>();
		HashMap<String, Long> disMap = new HashMap<String, Long>();
		
		//Puts the all nodes and initial values in distance map
		for(Entry<String, Node> entry: nodeHash.entrySet()){
			if(entry.getKey().equals(label)){
				disMap.put(entry.getKey(), (long) 0);
				nodeOrder.add(nodeHash.get(label));
			} else {
				disMap.put(entry.getKey(), Long.MAX_VALUE);
			}
		}
				
		//Runs through queue while full and compares edges
		while(!nodeOrder.isEmpty()){
			
			Node curNode = nodeOrder.remove();
		
			for(Edge e: vertices.get(curNode.getLabel())){
				//Gets end node of edge
				Node destNode = nodeHash.get(e.getDLabel());
				//Tests curDistance in map vs new distance && updates map
				long distance = disMap.get(curNode.getLabel()) + e.getWeight();
				
				//If distance is changed set new distance is disMap
				if(distance < disMap.get(destNode.getLabel()) || disMap.get(destNode.getLabel()) == Long.MAX_VALUE){
					disMap.put(destNode.getLabel(), distance);
					nodeOrder.add(new Node(destNode.getId(), destNode.getLabel()));
				}
			}
		}
			
		//Stores shortestPathInfo in arraylist
		for(Entry<String, Long> set: disMap.entrySet()){
			if(set.getValue() == Long.MAX_VALUE){
				known.add(new ShortestPathInfo(set.getKey(), -1));
			} else {
				known.add(new ShortestPathInfo(set.getKey(), set.getValue()));
			}
		}
		
		//Converts arraylist to array
		ShortestPathInfo shortestPaths[] = new ShortestPathInfo[numNodes];
		known.toArray(shortestPaths);
		return shortestPaths;
	}

}
