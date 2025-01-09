import java.awt.Color;
import java.util.*;

public class GraphAlgorithms{

  /* 
   * To draw a list of integers int_list (of type List<Integer)
   * to the canvas, call drawSequence(int_list, writer).
   *
   * The index of each integer in the list will be
   * plotted along the x-axis; the integer value itself
   * is plotted on the y-axis.
   *                                                      */

  public static List<Integer> MergeSort(List<Integer> S, PixelWriter writer) {
    // base case: if S.size() < 2, return S
    if (S.size() < 2) {
      return S;
    }

    // Divide S into lower and upper half, then recurse on S1 and S2
    List<Integer> S1 = new ArrayList<Integer>();
    List<Integer> S2 = new ArrayList<Integer>();
    divide(S1, S2, S); 

    // recurse
    S1 = MergeSort(S1, writer);
    S2 = MergeSort(S2, writer);

    // Merge S1 and S2 into S
    merge(S1, S2, S);
    drawSequence(S, writer);

    return S;
  }

  private static void divide(List<Integer> S1, List<Integer> S2, List<Integer> S) {
    int n = S.size();
    int n_l = (int) Math.floor(n/2);
    int n_h = (int) Math.ceil(n/2);

    
    for (int i=0; i < n_l; i++){
      S1.add(i, S.get(i));
    }

    for (int i = n_h; i < n; i++){
      S2.add(i-n_h, S.get(i));
    }
  }

  private static void merge(List<Integer> S1, List<Integer> S2, List<Integer> S) {
    int n_1 = S1.size();
    int n_2 = S2.size();

    int i = 0;
    int j = 0;

    while (i < n_1 && j < n_2) {
      if (S1.get(i) <= S2.get(j)) {
        S.set(i+j, S1.get(i));
        i++;
      } else {
        S.set(i+j, S2.get(j));
        j++;
      }
    }

    while (i < n_1) {
      S.set(i+j, S1.get(i));
      i++;
    }

    while (j < n_2) {
      S.set(i+j, S2.get(j));
      j++;
    }
  }


  public static List<Integer> QuickSort(List<Integer> S, PixelWriter writer) {
    int n = S.size();
    
    if (n < 2) {
      return S;
    }

    int x = 0;
    int p = (int) Math.floor(n/2);

    List<Integer> L = new ArrayList<Integer>();
    List<Integer> E = new ArrayList<Integer>();
    List<Integer> G = new ArrayList<Integer>();

    x = S.get(p);
    Split(L,E,G,S,x, n);
    L = QuickSort(L, writer);
    G = QuickSort(G, writer);
    Concatenate(L,E,G,S);

    drawSequence(S, writer);
    return S;
  }

  private static void Split(List<Integer> L, List<Integer> E, List<Integer> G, List<Integer> S, int x, int n) {
    L.clear();
    E.clear();
    G.clear();
    
    for (int i=0; i<n; i++) {
       int j = S.get(i);
    // Insert into L elements less than x
      if (j < x) {
        L.add(j);
      }
    // Insert into E elements equal to x
      else if (j == x) {
        E.add(j);
      }
    // Insert into G elements greater than x
      else {
        G.add(j);
      }
    }
    return;
  }

  private static void Concatenate(List<Integer> L, List<Integer> E, List<Integer> G, List<Integer> S) {
    int Ln = L.size();
    int En = E.size();
    int Gn = G.size();
    
    S.clear();

    for (int i=0; i<Ln; i++) {
      S.add(L.get(i));
    }

    for (int i=0; i<En; i++) {
      S.add(E.get(i));
    }

    for (int i=0; i<Gn; i++) {
      S.add(G.get(i));
    }

    return;
  }


  public static List<Integer> InsertionSort(List<Integer> S, PixelWriter writer) {
    int n = S.size();

    for (int k=1; k<n; k++) {
      int val = S.get(k);
      int j = k-1;
      while (j >= 0 && S.get(j) > val) {
        S.set(j+1, S.get(j));
        j--; 
      }
      S.set(j+1, val);
      drawSequence(S, writer);
    }
    return S;
  }

  public static List<Integer> RadixSort(List<Integer> S, PixelWriter writer) {
    int n = S.size();

    if (S == null || n == 0) {
      return S;
    }
   
    // d = find the max, get number of digits
    int d = String.format("%d", getMax(S)).length();

    // iterate over the "keys"
    // pass the key into bucket sort and sort
    for (int k=d-1; k>=0; k--) {
      BucketSort(S, k, d, writer);
      drawSequence(S, writer);
    }
    
    return S;
  }

  private static int getMax(List<Integer> S) {
    int n = S.size();

    if (S == null || n == 0) {
      return 0;
    }

    int max = S.get(0); // find largest int
    
    for (int i=0; i<n; i++) {
      int cur = S.get(i);

      if (cur > max){
        max = cur;
      }
    }

    return max; 
  }

  private static List<String> intsToStrings(List<Integer> S, int d) {
    int n = S.size();
    List<String> R = new ArrayList<>();

    if (S == null || n == 0) {
        return R;
    }

    for (int i = 0; i < n; i++) {
        int cur = S.get(i);

        String str = String.format("%0" + d + "d", cur); // Pad with leading zeros to make the string of length d

        R.add(str);
    }

    return R;
}
  
  private static void BucketSort(List<Integer> S, int k, int d, PixelWriter writer) {
    if (S == null || S.size() == 0) {
      return;
    }
    int n = S.size();
    
    // convert all to list of strings
    List<String> strings = intsToStrings(S, d);

    //create HashMap of buckets
    Map<Integer, List<Integer>> bucketList = new HashMap<>(); 
    
    //add a new bucket for every value 0 to 9
    for (int i=0; i < 10; i++) {  
      bucketList.put(i, new ArrayList<Integer>());
    }
    
    //iterate over each item in strings at index k and sort into it's bucket
for (int i = 0; i < n; i++) {
    String cur = strings.get(i);
    
    
    // Check if the index k is within the valid range for the current string
          int key = Integer.parseInt(Character.toString(cur.charAt(k)));
          int val = Integer.parseInt(cur);
          bucketList.get(key).add(val);
}

    // Concatenate buckets back to the original list
    S.clear();
    for (int i = 0; i < 10; i++) {
        if (bucketList.get(i) != null) {
            S.addAll(bucketList.get(i));
        }
    }

    return;
  }
  


  /* DO NOT CHANGE THIS METHOD */
  private static void drawSequence(List<Integer> sequence, PixelWriter writer) {
    for (Integer curr : sequence) {
      for (int j=0; j<sequence.size(); j++) {
        Color c = writer.getColor(j, curr);
        if (c.equals(Color.BLACK))
          writer.setPixel(j, curr, Color.WHITE);
      }
      int x = sequence.indexOf(curr);
      if (!writer.getColor(x, curr).equals(Color.BLACK)) {
        writer.setPixel(sequence.indexOf(curr), curr, Color.BLACK);
      }
    }
  } 


  /* THE FOLLOWING METHODS WILL NOT BE MARKED;
   * YOU MAY IMPLEMENT THEM OPTIONALLY
   */

	/* FloodFillDFS(v, writer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillDFS(PixelVertex v, PixelWriter writer, Color fillColour){
	}
	
	/* FloodFillBFS(v, writer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillBFS(PixelVertex v, PixelWriter writer, Color fillColour){
	}
	
}
