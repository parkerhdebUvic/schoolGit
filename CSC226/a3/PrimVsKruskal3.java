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
   
	java -cp .:algs4.jar PrimVsKruskal file.txt
	
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

import javax.xml.stream.util.EventReaderDelegate;

import java.io.File;

//Do not change the name of the PrimVsKruskal class
public class PrimVsKruskal3{

	/* PrimVsKruskal(G)
		Given an adjacency matrix for connected graph G, with no self-loops or parallel edges,
		determine if the minimum spanning tree of G found by Prim's algorithm is equal to 
		the minimum spanning tree of G found by Kruskal's algorithm.
		
		If G[i][j] == 0.0, there is no edge between vertex i and vertex j
		If G[i][j] > 0.0, there is an edge between vertices i and j, and the
		value of G[i][j] gives the weight of the edge.
		No entries of G will be negative.
	*/
	public static EdgeWeightedGraph matrixToWeightedGraph(double[][] matrix) {
		int V = matrix.length;
		EdgeWeightedGraph graph = new EdgeWeightedGraph(V); //from Princeton Library
		for (int i = 0; i < V; i++) {
			for (int j = i + 1; j < V; j++) { //check if edge is already in graph
				if (matrix[i][j] > 0.0) {
					Edge e2 = new Edge(i, j, matrix[i][j]);
					graph.addEdge(e2);
				}
			}
		}
		return graph;
	}

	static boolean PrimVsKruskal(double[][] G){
		int n = G.length;

		/* Build the MST by Prim's and the MST by Kruskal's */
		/* (You may add extra methods if necessary) */

		/* ... Your code here ... */
		// EdgeWeightedGraph graph = matrixToWeightedGraph(G);
		// PrimMST p_mst = new PrimMST(graph);
		// KruskalMST k_mst = new KruskalMST(graph);
		
		/* Determine if the MST by Prim equals the MST by Kruskal */
		boolean pvk = true;
		
		/* ... Your code here ... */
		

		pvk = earlyDetection(G, n);

		// String roundedPrim = String.format("%.5f", p_mst.weight());
		// String roundedKruskal = String.format("%.5f", k_mst.weight());

		// System.out.println(roundedPrim);
		// System.out.println(roundedKruskal);

		// if (!roundedPrim.equals(roundedKruskal)){
		// 	pvk = false;
		// }
		

		return pvk;	
	}
	public static class DoubleWrapper implements Comparable<DoubleWrapper> {
			private double value;

			public DoubleWrapper(double value) {
				this.value = value;
			}

			public double getValue() {
				return value;
			}

			@Override
			public int compareTo(DoubleWrapper other) {
				return Double.compare(this.value, other.value);
			}

			@Override
			public String toString() {
				return Double.toString(value);
			}
		}

	public static boolean earlyDetection(double[][] G, int n) {
		//This function tests if the MST from Prim's and the MST from Kruskal's is different, and returns false if they deviate early
		//Input: weighted connected graph with adjacency matrix
		//Output: true if both Kruskal's and Prim's produce the same MST, false if otherwise

		//Data structures:
			//Disjoint set C for kruskal's
				UF C = new UF(n);
			//Array D for Prim's
				double[] D = new double[n];
			//Edge Array PE for Prim's
				Edge[] PE = new Edge[(n*n)];
			//Edge Array KE for Kruskal's
				Edge[] KE = new Edge[(n*n)];
			//Priority queue Q_Prim for prim's verticies
				IndexMinPQ<DoubleWrapper> p_pq = new IndexMinPQ<>(n*n); // ((start,end), weight) : ((u,v),D[u]) — D[u] is the key
			//Priority queue Q_Kruskal for kruskal's edges, initialized with all edges sorted by weight
				IndexMinPQ<DoubleWrapper> k_pq = new IndexMinPQ<>(n*n); //maximum number of possible edges in simple, undirected graph
			//MST k_mst for edges added by kruskal's
				//Bag<Edge> k_mst = new Bag<>();	// storing MSTs in Bags instead makes comparison easier
				EdgeWeightedGraph k_mst = new EdgeWeightedGraph(n);
			//MST p_mst for prim's
				//Bag<Edge> p_mst = new Bag<>();	// storing MSTs in Bags instead makes comparison easier
				EdgeWeightedGraph p_mst = new EdgeWeightedGraph(n);
		

		int E = 0; //number of edges
		int k_mst_size = 0; //count of verticies in k_mst
		int p_mst_size = 0;	//count of verticies in p_mst
		int eIndex = 0; //counting edge array position 

		for (int i = 0; i < n; i++) {
			//Initialize D values to positive infinity for Prim's:
			D[i] = Double.POSITIVE_INFINITY;
			// Edge e;
			// e = new Edge(i, i, D[i]);
			// PE[i] = e;
			p_pq.insert(i, new DoubleWrapper(D[i])); // add (u, D[u]) to p_pq, with D[u] as the key while we're here
			
			//get number of edges
			
			for (int j = 0; j<n; j++) {
				if (G[i][j] != 0.0){
					E++; //count the number of edges while we're here
					Edge e = new Edge(i,j,G[i][j]);
					KE[eIndex] = e;
					PE[eIndex] = e;
					k_pq.insert(eIndex, new DoubleWrapper(G[i][j])); //add edges to k_pq with weights as keys while we're here
				}
				eIndex++;
			}
		}

		// at this point, we have populated p_pq with infinite weight verticies, and k_pq with all edge weights
		
		// Integer v = 0; //arbitrary vertex for Prim's
		// D[v] = 0.0;
		// p_pq.decreaseKey(v, 0.0);

		Edge[] k_edges = new Edge[n*n]; //for later comparison
		Edge[] p_edges = new Edge[n*n]; //for later comparison
		int k_edge_count = 0;
		int p_edge_count = 0;

		while (k_mst_size < n-1 || p_mst_size < n-1) {
			Edge k_edge = null; //for checking early detection
			Edge p_edge = null; //for checking early detection

			//Kruskal's
			if (k_mst_size < n-1 && !k_pq.isEmpty()) { //if k_mst isn't big enough, and there are still edges in the priority queue...
				//DoubleWrapper w2 = k_pq.minKey(); //get the smallest one... (off the top of the heap)
				int eI = k_pq.minIndex();
				Edge e = KE[eI];
				k_pq.delMin(); //remove it...

				int u = e.either(); //identify one side...
				int w = e.other(u);	//then the other...
				if (!C.connected(u, w)) { //check the UF if they're not already connected, otherwise it will create a cycle, so skip
					k_mst.addEdge(e); //add it to k_mst if it's all good...
					k_mst_size++; //increment count...
					C.union(u,w); //add them to the same connected component in UF...
					k_edge = e;		// save the edge to compare to prim's for early detection
				}
			}
			
			//Prim's ... needs to not start on an arbirary vertex, but on the same one as the edge chosen in Kruskal's...... hmmmmmmmm..
			Integer v = k_edge.either(); //arbitrary vertex for Prim's
			D[v] = 0.0;
			p_pq.decreaseKey(v, new DoubleWrapper(0.0));
			if (p_mst_size < n-1 && !p_pq.isEmpty()) { //if p_mst isn't big enough, and there are still verticies-edge-weight pairs in p_pq 

				//start at min, look at adjacents, update distances, add distances to MST
				
				int u = p_pq.minIndex(); //tells you the smallest destination vertex in p_pq
				p_pq.delMin(); //dequeue

				
				for (Edge e : k_mst.adj(u)) { //check every adjacent vertex
					int o = e.other(u); //get adjacent vertex o from across the edge e, from u
					if (p_pq.contains(o) && e.weight() < D[o]) { //if the edge is in p_pq and weight of o is less than D[o]
						D[o] = e.weight(); //update the weight of D[o]
						p_pq.decreaseKey(o, new DoubleWrapper(D[o]));	//decrease the key weight of o in p_pq
					}
				}

				int w = p_pq.minIndex();
				DoubleWrapper weight = p_pq.minKey();
				p_edge = new Edge(u,w, weight.getValue());
				p_mst.addEdge(p_edge);	
				p_mst_size++; //increment size
			}

			//Early detection compare
			if (k_edge != null && p_edge != null) {
				k_edges[k_edge_count++] = k_edge;
				p_edges[p_edge_count++] = p_edge;

				for (int i = 0; i < n*n ; i++){
					if (!edgeCompare(k_edges[i], p_edges[i])) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public static boolean edgeCompare(Edge e1, Edge e2) {
		if (e1 == null && e2 == null) { //double
			return true;
		}

		if (e1 == null || e2 == null) {
			return false;
		}
		boolean verticies = false;
		boolean weights = false;

		if (e1.either() == e2.either() && e1.other(e1.either()) == e2.other(e2.either())){
			verticies = true;
		}

		if (e1.weight() == e2.weight()) {
			weights = true;
		}
		return verticies && weights;
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
        System.out.printf("Test: Does Prim MST = Kruskal MST? %b\n", pvk);
    }
}
