/* BaseballElimination.java
   CSC 226 - Fall 2023
   Assignment 5 - Baseball Elimination Program
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java BaseballElimination
	
   To conveniently test the algorithm with a large input, create a text file
   containing one or more test divisions (in the format described below) and run
   the program with
	java -cp .;algs4.jar BaseballElimination file.txt (Windows)
   or
    java -cp .:algs4.jar BaseballElimination file.txt (Linux or Mac)
   where file.txt is replaced by the name of the text file.
   
   The input consists of an integer representing the number of teams in the division and then
   for each team, the team name (no whitespace), number of wins, number of losses, and a list
   of integers represnting the number of games remaining against each team (in order from the first
   team to the last). That is, the text file looks like:
   
	<number of teams in division>
	<team1_name wins losses games_vs_team1 games_vs_team2 ... games_vs_teamn>
	...
	<teamn_name wins losses games_vs_team1 games_vs_team2 ... games_vs_teamn>

	
   An input file can contain an unlimited number of divisions but all team names are unique, i.e.
   no team can be in more than one division.
*/

/* NOTES

Instructions
The assignment is to implement an algorithm to determine which teams in a sports league division have been eliminated from the playoffs. 
A Java template has been provided containing an empty method BaseballElimination, which takes a single argument consisting of a Scanner object which connects you to the input consisting of the data related to a division of teams within a sports league. 

The expected behavior of the method is as follows:
Input: A baseball division consisting of n teams.
Output: An ArrayList consisting of all the teams that have been eliminated from the league playoffs.
We will assume that the team with the most wins in the division at the end of the season is the only team to make the playoffs. 
So, a team is eliminated from the playoffs if they cannot possibly win as many games as any other team in the division.
You may use the FordFulkerson, FlowNetwork, and FlowEdge classes from the Algorithms (Sedgewick) textbook through the agls4.jar file. 
You may not change any of these classes and thus you do not need to include this code in your program.
I have included a pdf of section 16.4 (two pages) from the Algorithm Design and Applications textbook. You can use this to determine how the reduction algorithm works. 
To get full marks in this assignment, you need to figure out how to reduce this problem to a network flow problem in order to solve it.
 */

//  compile with: javac -cp .:algs4.jar BaseballElimination.java
//  run with: java -cp .:algs4.jar BaseballElimination
import edu.princeton.cs.algs4.*;
import java.util.*;
import java.io.File;

//Do not change the name of the BaseballElimination class
public class BaseballElimination{
	
	// We use an ArrayList to keep track of the eliminated teams.
	public ArrayList<String> eliminated = new ArrayList<String>();

	/* BaseballElimination(s)
		Given an input stream connected to a collection of baseball division
		standings we determine for each division which teams have been eliminated 
		from the playoffs. For each team in each division we create a flow network
		and determine the maxflow in that network. If the maxflow exceeds the number
		of inter-divisional games between all other teams in the division, the current
		team is eliminated.
	*/
	private static int NchooseK(int n, int k) {
		if (k == 0 || k == n) {
			return 1;
		}
		return NchooseK(n-1, k-1) + NchooseK(n-1, k);
	}

	private static FlowNetwork initializeFlowNetwork(int N, int V, int W, int R, ArrayList<String> teams, ArrayList<Integer> wins, ArrayList<Integer> remaining, ArrayList<ArrayList<Integer>> matches) {
		// // initialize flow network
		FlowNetwork G = new FlowNetwork(V);
		int source = 0;
		// // add edges from source
		int index_offset = NchooseK(N-1, 2) + 1;
		int match_count = 1;
		
		for (int i = 0; i < N-1; i++) {
			for (int j = 0; j < i; j++) {
				FlowEdge E = new FlowEdge(source, match_count, matches.get(i).get(j));
				G.addEdge(E);

				int teamA = index_offset + j;
				int teamB = index_offset + i;

				E = new FlowEdge(match_count, teamA, Double.POSITIVE_INFINITY);
				G.addEdge(E);

				E = new FlowEdge(match_count, teamB, Double.POSITIVE_INFINITY);
				G.addEdge(E);

				match_count++;
			}
			int teamA = index_offset + i;
			FlowEdge E = new FlowEdge(teamA, V - 1, W + R - wins.get(i));
			G.addEdge(E);
		}

		return G;
		
	}
	
	public BaseballElimination(Scanner s){
		
		/* ... Your code here ... */	
		// need to know number of verticies
		int N = Integer.parseInt(s.nextLine()); // number of teams
		int V = N - 1 + NchooseK(N-1, 2) + 2; // exclude one team being observed, include all teams, inclue (N-1)choose(2) match verticies, include sink and source

		// parse the rest of stdin for the team names, wins, remaining games, and matches
		ArrayList<String> teams = new ArrayList<>();
		ArrayList<Integer> wins = new ArrayList<>();
		ArrayList<Integer> remaining = new ArrayList<>();
		ArrayList<ArrayList<Integer>> matches = new ArrayList<>();

		while (s.hasNextLine()) {
			String line = s.nextLine().trim(); //read next line
			String[] lineArray = line.split("\\s+"); //tokenize line
			teams.add(lineArray[0]); // add team name to array
			wins.add(Integer.parseInt(lineArray[1])); // add wins to array
			remaining.add(Integer.parseInt(lineArray[2])); //add total remaining games to array
			
			ArrayList<Integer> remainingMatches = new ArrayList<>();
			for (int i = 3; i < lineArray.length; i++) {
				remainingMatches.add(Integer.parseInt(lineArray[i]));
			}
			matches.add(remainingMatches);
		}

		int total_R = 0;
		for (int R : remaining){
			total_R += R;
		}
		
		System.out.println(teams);
		
		for (int t = 0 ; t < teams.size(); t++){
			int W = wins.get(t);
			int R = remaining.get(t);
			ArrayList<String> copy_teams = new ArrayList<>(teams); copy_teams.remove(t);
			ArrayList<Integer> copy_wins = new ArrayList<>(wins); copy_wins.remove(t);
			ArrayList<Integer> copy_remaining = new ArrayList<>(remaining); copy_remaining.remove(t);

			ArrayList<ArrayList<Integer>> copy_matches = new ArrayList<>();
			for (int i = 0; i < matches.size(); i++) {
				ArrayList<Integer> sublist = new ArrayList<>(matches.get(i));
				sublist.remove(t);
				copy_matches.add(sublist);
			}
			copy_matches.remove(t);
			
			// skip if wins_t + remaining_t is less than some other team's wins
			int skip = 0;
			for (int win : wins) {
				if (wins.get(t) + remaining.get(t) < win) {
					if (!eliminated.contains(teams.get(t))) {
						eliminated.add(teams.get(t));
					}
					skip = 1;
				}
			}
			if (skip == 1){
				continue;
			}

			// System.out.println("Flow Network for " + teams.get(t));
			FlowNetwork G = initializeFlowNetwork(N, V, W, R, copy_teams, copy_wins, copy_remaining, copy_matches);
			
			int source = 0;
			int sink = V - 1;
			FordFulkerson maxflow = new FordFulkerson(G, source, sink);
			
			
			// System.out.println(G);
			
			for (FlowEdge e : G.adj(source)) {
				double flow = e.flow();
				double capacity = e.capacity();
				if (flow < capacity){
					if (!eliminated.contains(teams.get(t))) {
						eliminated.add(teams.get(t));
					}
				}
			}
		}
	}
		
	/* main()
	   Contains code to test the BaseballElimantion function. You may modify the
	   testing code if needed, but nothing in this function will be considered
	   during marking, and the testing process used for marking will not
	   execute any of the code below.
	*/
	public static void main(String[] args){
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
		
		BaseballElimination be = new BaseballElimination(s);		
		
		if (be.eliminated.size() == 0)
			System.out.println("No teams have been eliminated.");
		else
			System.out.println("Teams eliminated: " + be.eliminated);
	}
}

