import java.util.ArrayList;

public class Group {
  private ArrayList<Frog> list;

  public Group() {
    list = new ArrayList<>();
  }

  // adds frog f to the group and places 
  // it in alphabetical order within the group
  // O(log(n))
  public void addFrog(Frog f) {
    addFrogHelper(f, 0, list.size()-1);
  }

  private void addFrogHelper(Frog f, int low, int high){
    if (low > high){
      list.add(low, f);
      return;
    }

    int mid = (low + high) / 2;

    if (list.get(mid).compareTo(f) == 0){
      list.add(mid, f);
      return;
    }
    else if (list.get(mid).compareTo(f) > 0){
      addFrogHelper(f, low, mid-1);
    }
    else if (list.get(mid).compareTo(f) < 0){
      addFrogHelper(f, mid+1, high);
    }
  }

  // returns the number of frogs in the group
  public int size() {
    return list.size();
  }

  // returns the Frog at index i
  // O(1)
  public Frog get(int i) {
    if (i >= 0 && i <= list.size()-1){
      return list.get(i);
    } else {
      throw new IndexOutOfBoundsException("Invalid index: " + i);
    }
  }

  // returns a Group[] array with two 
  // elements, g1 and g2 in that order.
  // g1 contains sthe frogs in the group from 
  // indices 0 to ⌊n/2⌋ - 1 inclusive.
  // g2 contains all the other frogs in the group
  // O(n)
  public Group[] halfGroups() {
    Group[] halfGroups = new Group[2];
    Group g1 = new Group();
    Group g2 = new Group();
    
    if (this.size() > 0){
      for (int i=0; i <= (this.size()/2)-1; i++) {
        g1.addFrog(this.get(i));
      }
      for (int i=(this.size()/2); i <= (this.size())-1; i++){
        g2.addFrog(this.get(i));
      }
    }

    halfGroups[0] = g1;
    halfGroups[1] = g2;

    return halfGroups;
  }

  // returns a String of the list of frogs that 
  // identically matches how Arrays.toString(fs)
  // would print our a Frog{} array fs
  @Override
  public String toString() {
    return list.toString();
  }

  public static boolean FrogEquals(Group g1, Group g2) {
    if (g1.toString().equals(g2.toString()) == true){
      return true;
    }
    
    if (g1.size() == g2.size() && g1.size()%2 == 0) {
      Group[] G1 = g1.halfGroups();
      Group[] G2 = g2.halfGroups();

      return FrogEquals(G1[0], G2[1]) || FrogEquals(G2[0], G1[1]);
    }
    return false;
  }
}
