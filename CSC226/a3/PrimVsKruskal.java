/* PrimVsKruskal.java
   CSC 226 - Fall 2023
   Assignment 3 - Prim MST versus Kruskal MST Template
   
   The file includes the "import edu.princeton.cs.algs4.*;" so that you can use
   any of the code in the algs4.jar file. You should be able to compile your program
   with the command
   
	javac -cp .;algs4.jar PrimVsKruskal.java
	
   To conveniently test the algorithm with a large input, create a text file
   containing a test graphs (in the format described below) and run
   the program with
   
	java -cp .;algs4.jar PrimVsKruskal file.txt
	
   where file.txt is replaced by the name of the text file. Note: different operating systems have different commands.
   You should all know how to run the algs4.jar file from lab work.
   
   The input consists of a graph (as an adjacency matrix) in the following format:
   
    <number of vertices>
	<adjacency matrix row 1>
	...
	<adjacency matrix row n>
	
   Entry G[i][j] >= 0.0 of the adjacency matrix gives the weight (as type double) of the edge from 
   vertex i to vertex j (if G[i][j] is 0.0, then the edge does not exist).
   Note that since the graph is undirected, it is assumed that G[i][j]
   is always equal to G[j][i].
*/

 import edu.princeton.cs.algs4.*;
 import java.util.Scanner;
 import java.io.File;

//Do not change the name of the PrimVsKruskal class
public class PrimVsKruskal{

	/* PrimVsKruskal(G)
		Given an adjacency matrix for connected graph G, with no self-loops or parallel edges,
		determine if the minimum spanning tree of G found by Prim's algorithm is equal to 
		the minimum spanning tree of G found by Kruskal's algorithm.
		
		If G[i][j] == 0.0, there is no edge between vertex i and vertex j
		If G[i][j] > 0.0, there is an edge between vertices i and j, and the
		value of G[i][j] gives the weight of the edge.
		No entries of G will be negative.
	*/
	static boolean PrimVsKruskal(double[][] G){
		int n = G.length;

		/* Build the MST by Prim's and the MST by Kruskal's */
		/* (You may add extra methods if necessary) */
		
		/* ... Your code here ... */

		/* Determine if the MST by Prim equals the MST by Kruskal */
		boolean pvk = true;
		/* ... Your code here ... */

		// define data structures
			// graph
			Edge[] E = new Edge[(n*(n-1))/2];

			// kruskal
			UF C = new UF(n); // Disjoint set C
			Edge[] KE = new Edge[(n*(n-1))/2];
			IndexMinPQ<Double> k_pq = new IndexMinPQ<>((n*(n-1))/2);
			EdgeWeightedGraph k_mst = new EdgeWeightedGraph(n);

			// prim
			double[] D = new double[n]; //distances
			Edge[] PE = new Edge[((n*(n-1)/2))];//edge-index array
			IndexMinPQ<Double> p_pq = new IndexMinPQ<>((n*(n-1))/2); //edge-weight priority queue
			EdgeWeightedGraph p_mst = new EdgeWeightedGraph(n);

		int e_count = 0;
		Edge e;
		for (int i=0; i<n; i++){
			D[i] = Double.POSITIVE_INFINITY; //initialize prim distances
			//e = new Edge(i,i,Double.POSITIVE_INFINITY);	//create edge to nowhere
			//PE[i] = e;	//keep track of index in p_pq and PE[] to find edges
			p_pq.insert(i, Double.POSITIVE_INFINITY);	//insert into p_pq

			for (int j=0; j<i; j++){ //graph is undirected, therefore symmetric
				if (G[i][j] != 0.0) {	//if an edge exists...
					e = new Edge(i, j, G[i][j]);	//create new edge...
					//E[e_count] = e;	//add it to Graph edge list
					KE[e_count] = e;	//keep track of index in k_pq and KE[] to find edges
					//PE[e_count] = e;
					k_pq.insert(e_count, G[i][j]); //insert into p_pq
					//p_pq.insert(e_count, Double.POSITIVE_INFINITY);	//insert into p_pq
					e_count++;	//increase edge index count
				}
			}
		}



		
		int itr = 0;
		while (!k_pq.isEmpty() && !p_pq.isEmpty()) {	 
			
			//kruskal step
				//dequeue min edge
				int k = k_pq.minIndex();
				Edge k_edge = KE[k];
				k_pq.delMin();

				//check for K cycle
				int u = k_edge.either();
				int w = k_edge.other(u);
				if (C.connected(u,w)) { 
					continue;
				}
				C.union(u,w);
				k_mst.addEdge(k_edge);
			
			//prim step
				//start at either vertex of k_edge: u 
				
				p_pq.changeKey(u, 0.0); //update key in p_pq, float u up
				p_pq.delMin(); //pop u off
				
				//check all adjacents, update weights, take the smallest, add to p_mst, reset all to infinity
				
				for (int j=0; j<n && j!=u; j++){
					//System.out.println(p_pq.keyOf(j));
					if (G[u][j] > 0.0 && p_pq.contains(j) && G[u][j] < p_pq.keyOf(j)) { //for everything adjacent to u...
						
							p_pq.changeKey(j, G[u][j]); //update it's key-weight in p_pq; float it to the top... IT: You'll float too!
						
					}
				} // lightest will be at the top of p_pq; MUST BE the same weight as k_edge and opposite verticie
				//select lowest weight edge
				int p = p_pq.minIndex();
				double W = p_pq.minKey();
				Edge p_edge = new Edge(u,p,W);
				p_mst.addEdge(p_edge);
				
				
			//early detection
			//if different verts, or edge weight, return false
				
				System.out.println("Iterration: " + itr);
				System.out.println("K edge: " + u + " to " + k_edge.other(u) + " | " + k_edge.weight());
				System.out.println("P edge: " + u + " to " + p_edge.other(u) + " | " + p_edge.weight());
				if (k_edge.weight() != p_edge.weight() || 
						k_edge.either() != p_edge.either() ||
						k_edge.other(u) != p_edge.other(u)) {
							
							pvk = false;
							break;
				}
				itr++;
		}
		return pvk;	
	}
		
	/* main()
	   Contains code to test the PrimVsKruskal function. You may modify the
	   testing code if needed, but nothing in this function will be considered
	   during marking, and the testing process used for marking will not
	   execute any of the code below. 
	*/
   public static void main(String[] args) {
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int n = s.nextInt();
		double[][] G = new double[n][n];
		int valuesRead = 0;
		for (int i = 0; i < n && s.hasNextDouble(); i++){
			for (int j = 0; j < n && s.hasNextDouble(); j++){
				G[i][j] = s.nextDouble();
				if (i == j && G[i][j] != 0.0) {
					System.out.printf("Adjacency matrix contains self-loops.\n");
					return;
				}
				if (G[i][j] < 0.0) {
					System.out.printf("Adjacency matrix contains negative values.\n");
					return;
				}
				if (j < i && G[i][j] != G[j][i]) {
					System.out.printf("Adjacency matrix is not symmetric.\n");
					return;
				}
				valuesRead++;
			}
		}
		
		if (valuesRead < n*n){
			System.out.printf("Adjacency matrix for the graph contains too few values.\n");
			return;
		}	
		
        boolean pvk = PrimVsKruskal(G);
        System.out.printf("Does Prim MST = Kruskal MST? %b\n", pvk);
    }
}
