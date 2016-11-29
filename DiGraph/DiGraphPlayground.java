package A6_Dijkstra;

//Test Cases for DiGraph, Topo Sort, and Dijkstra's algorithm
public class DiGraphPlayground {
	
	public static void main (String[] args) {

		//basicTests();
		//topoSortTest0();
		//topoSortTest1();
		//dijkstraTest();
	    }
	    
	    public static void basicTests(){
		      addNode0();//True
		      addNode1();//False
		      addNode2();//False
		      addEdge0();//False
		      addEdge1();//False
		      addEdge2();//False
		      addEdge3();//False
		      addEdge4();//True
		      numEdges0();//5
		      numEdges1();//4
		      numNodes0();//5 nodes and 3 edges
	          numNodes1();
		      delNode0();//True
		      delNode1();//False
		      delNode2();//False, true, true
		      delEdge0();//True
		      delEdge1();//False and false
		      delEdge2();//1)false 2)true 3)false 4)true
		      
	    }
	    
	    public static void topoSortTest0(){
	    	DiGraph d = new DiGraph();
	    	long startTime = System.currentTimeMillis();
	    	
	    	for(int i = 0; i < 10000; i++){
	    		d.addNode((long) i, Integer.toString(i));
	    	}
	    	
	    	for(int i = 0; i < 10000-1; i++){
	    		for(int j = 0; j < 10000-1; j++){
	    			d.addEdge((long) i, Integer.toString(i), Integer.toString(j), 1, null);
	    		}
	    	}

	    	
	    	long endTime = System.currentTimeMillis();
	    	long totalTime = endTime - startTime;
	    	System.out.println("numEdges: "+d.numEdges());
		    System.out.println("numNodes: "+d.numNodes());
		    System.out.println("Build took: "+ totalTime + " ms or " + (totalTime/1000) + " seconds."); 
		    
		    long startTime2 = System.currentTimeMillis();
		    d.topoSort();
		    long endTime2 = System.currentTimeMillis();
		    long totalTime2 = endTime2 - startTime2;
		    System.out.println("Topo sort took: "+ totalTime2 + " ms or " + (totalTime2/1000) + " seconds."); 
		    printTOPO(d.topoSort());

	    }
	    
	    public static void topoSortTest1(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "p");
	    	d.addNode(4, "a");
	    	d.addNode(3, "g");
	    	d.addNode(2, "e");
	    	d.addEdge(0, "p", "a", 0, null);
	    	d.addEdge(1, "a", "g", 0, null);
	    	d.addEdge(2, "g", "e", 0, null);
	    	d.addEdge(3, "e", "p", 0, null);
	    	d.addEdge(4, "p", "g", 0, null);
	    	d.addEdge(5, "a", "e", 0, null);
	    	d.addEdge(6, "p", "e", 0, null);
	    	d.delNode("e");
	    	System.out.println("NumNode: " + d.numNodes() + " NumEdges: " + d.numEdges());
	    	printTOPO(d.topoSort());
	    }
	    
	    public static void dijkstraTest(){
	    	DiGraph d = new DiGraph();
	    	long startTime = System.currentTimeMillis();
	    	
	    	d.addNode(1, "A");
	    	d.addNode(2, "B");
	    	d.addNode(3, "C");
	    	d.addNode(4, "D");
	    	d.addNode(5, "E");
	    	d.addNode(6,"F");
	    	d.addNode(7, "G");
	    	d.addNode(8, "H");
	    	d.addNode(9,"I");
	    	d.addNode(10, "J");
	    	d.addEdge(1, "A", "B", 1, null);
	    	d.addEdge(2, "A", "C", 4, null);
	    	d.addEdge(3, "A", "I", 15, null);
	    	d.addEdge(4, "B", "C", 2, null);
	    	d.addEdge(5, "B", "E", 6, null);
	    	d.addEdge(6, "B", "D", 2, null);
	    	d.addEdge(7, "C", "D", 1, null);
	    	d.addEdge(8, "C", "F", 5, null);
	    	d.addEdge(9, "C", "E", 1, null);
	    	d.addEdge(10, "D", "G", 1, null);
	    	d.addEdge(11, "D", "F", 3, null);
	    	d.addEdge(12, "E", "G", 3, null);
	    	d.addEdge(13, "F", "H", 4, null);
	    	d.addEdge(14, "G", "H", 1, null);

	    	//Tests build time
	    	long endTime = System.currentTimeMillis();
	    	long totalTime = endTime - startTime;
	    	System.out.println("numEdges: "+d.numEdges());
		    System.out.println("numNodes: "+d.numNodes());
		    System.out.println("Build took: "+ totalTime + " ms or " + (totalTime/1000) + " seconds.");
		    System.out.println();
		    //Test dijkstra's time
		    startTime = System.currentTimeMillis();
		    ShortestPathInfo finish[] = new ShortestPathInfo[(int) d.numNodes()];
		    finish = d.shortestPath("A");
		    endTime = System.currentTimeMillis();
	    	totalTime = endTime - startTime;
	    	System.out.println("Algorithm took: "+ totalTime + " ms or " + (totalTime/1000) + " seconds.");
	    	for(int i = 0; i < finish.length; i++){
	    		System.out.println(finish[i].getDest() + " : " + finish[i].getTotalWeight());
	    	}
	    }
	    
	    public static void printTOPO(String[] toPrint){
	      System.out.print("TOPO Sort: ");
	      for (String string : toPrint) {
	      System.out.print(string+" ");
	    }
	      System.out.println();
	    }
	    
	    //Basic Graph functionality tests***********************
	    
	    //Regular
	    public static void addNode0(){
	    	DiGraph d = new DiGraph();	    	
	    	d.addNode(1, "f");
	    	System.out.println("AddNode0: " + d.addNode(2,"z"));
	    }
	    
	    //Duplicate id
	    public static void addNode1(){
	    	DiGraph d = new DiGraph();	    	
	    	d.addNode(1, "f");
	    	System.out.println("AddNode1: " + d.addNode(1, "e"));
	    }
	    
	    //Duplicate label
	    public static void addNode2(){
	    	DiGraph d = new DiGraph();	    	
	    	d.addNode(1, "f");
	    	System.out.println("AddNode2: " + d.addNode(2, "f"));
	    }
	    
	    //Duplicate id
	    public static void addEdge0(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	d.addNode(7, "t");
	    	d.addEdge(0, "f", "s", 0, null);
	    	System.out.println("AddEdge0: " + d.addEdge(0, "f", "t", 0, null));
	    }
	    
	    //Duplicate edge
	    public static void addEdge1(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	d.addNode(7, "t");
	    	d.addEdge(0, "s", "f", 0, null);
	    	d.addEdge(1, "f", "s", 0, null);
	    	System.out.println("AddEdge1: " + d.addEdge(0, "f", "t", 0, null));
	    }
	    
	    //No source
	    public static void addEdge2(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	d.addNode(7, "t");
	    	System.out.println("AddEdge2: " + d.addEdge(0, "w", "s", 0, null));
	    }
	    
	    //No destination
	    public static void addEdge3(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	d.addNode(7, "t");
	    	System.out.println("AddEdge3: " + d.addEdge(0, "f", "w", 0, null));
	    }
	    
	    //Normal
	    public static void addEdge4(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	d.addNode(7, "t");
	    	d.addNode(6, "si");
	    	d.addEdge(0, "f", "s", 0, null);
	    	System.out.println("AddEdge4: " + d.addEdge(1, "f", "si", 0, null));
	    }
	    
	    //Normal
	    public static void numEdges0(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	d.addNode(7, "t");
	    	d.addNode(0, "fo");
	    	d.addNode(4, "fi");
	    	d.addNode(6, "si");
	    	d.addEdge(0, "f", "s", 0, null);
	    	d.addEdge(1, "f", "si", 0, null);
	    	d.addEdge(2, "s", "t", 0, null);
	    	d.addEdge(3, "fo", "fi", 0, null);
	    	d.addEdge(4, "fi", "si", 0, null);
	    	System.out.println("NumEdges0: " + d.numEdges());
	    }
	    
	    //Deletes
	    public static void numEdges1(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	d.addNode(7, "t");
	    	d.addNode(0, "fo");
	    	d.addNode(4, "fi");
	    	d.addNode(6, "si");
	    	d.addEdge(0, "f", "s", 0, null);
	    	d.addEdge(1, "f", "si", 0, null);
	    	d.addEdge(2, "s", "t", 0, null);
	    	d.addEdge(3, "fo", "fi", 0, null);
	    	d.addEdge(4, "fi", "si", 0, null);
	    	d.delEdge("f", "s");
	    	System.out.println("NumEdges1: " + d.numEdges());
	    }
	    
	    //NumNodes
	    public static void numNodes0(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	d.addNode(7, "t");
	    	d.addNode(0, "fo");
	    	d.addNode(4, "fi");
	    	d.addNode(6, "si");
	    	d.addEdge(0, "f", "s", 0, null);
	    	d.addEdge(1, "f", "si", 0, null);
	    	d.addEdge(2, "s", "t", 0, null);
	    	d.addEdge(3, "fo", "fi", 0, null);
	    	d.addEdge(4, "fi", "si", 0, null);
	    	d.delNode("f");
	    	System.out.println("NumNodes0: " + d.numNodes() + " and numEdges: " + d.numEdges());
	    }
	    
	    public static void numNodes1(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	Node n = new Node(3, "s");
	    	d.addNode(n);
	    	System.out.println("NumNode2.1: " + d.numNodes());
	    	d.addEdge(0, "f", "s", 0, null);
	    	System.out.println("NumNode2.2: edges-" + d.numEdges());
	    	System.out.println("NumNode2.21: indegree-" + n.getInDegree());
	    	d.delNode("s");
	    	System.out.println("NumNode2.3: node-" + d.numNodes() + " and edges-" + d.numEdges());
	    	d.addNode(3, "s");
	    	d.addEdge(0, "f", "s", 0, null);
	    	System.out.println("NumNode2.4: node-" + d.numNodes() + " and edges-" + d.numEdges());
	    }
	    
	    
	    //Simple true
	    public static void delNode0(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	System.out.println("DelNodes0: " + d.delNode("f"));
	    }
	    
	    //Simple false
	    public static void delNode1(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	System.out.println("DelNodes0: " + d.delNode("x"));
	    }
	    
	    //add after delete 
	    public static void delNode2(){
	    	DiGraph d = new DiGraph();
	    	System.out.println("DelNodes2.1: " + d.delNode("f"));
	    	d.addNode(1, "f");
	    	System.out.println("DelNodes2.2: " + d.delNode("f"));
	    	d.addNode(3, "f");
	    	System.out.println("DelNodes2.3: " + d.delNode("f"));
	    }
	    
	    //Simple del
	    public static void delEdge0(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	d.addEdge(0, "f", "s", 0, null);
	    	System.out.println("DelEdge0: " + d.delEdge("f", "s"));
	    }
	    
	    //No Del
	    public static void delEdge1(){
	    	DiGraph d = new DiGraph();
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	d.addEdge(0, "f", "s", 0, null);
	    	System.out.println("DelEdge1: " + d.delEdge("f", "x") + " and " + d.delEdge("x", "f"));
	    }
	    
	    //Add after del
	    public static void delEdge2(){
	    	DiGraph d = new DiGraph();
	    	System.out.println("DelEdge2.1: " + d.delEdge("f", "s"));
	    	d.addNode(1, "f");
	    	d.addNode(3, "s");
	    	d.addEdge(0, "f", "s", 0, null);
	    	System.out.println("DelEdge2.2: " + d.delEdge("f", "s"));
	    	System.out.println("DelEdge2.3: " + d.delEdge("f", "s"));
	    	d.addEdge(0, "f", "s", 0, null);
	    	System.out.println("DelEdge2.4: " + d.delEdge("f", "s"));
	    }
	    
}
