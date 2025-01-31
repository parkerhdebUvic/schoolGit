import java.util.*;

public class HashMap<K extends Comparable<K>, V> implements Map<K,V> {

	private List<List<Entry<K,V>>> 	table;
	private int	count;
	private int  tableSize;
 
	// For Part III
	private long getLoops;
	private long putLoops;
	
 
	public HashMap() {
		tableSize = 1000003; // prime number
		table = new ArrayList<List<Entry<K,V>>>(tableSize);

		// initializes table as a list of empty lists
		for (int i = 0; i < tableSize; i++) {
			table.add(new LinkedList<Entry<K,V>>());
		}

		count = 0;

		// For Part III:
		resetGetLoops();
		resetPutLoops();
	}

	// For Part III
	public long getGetLoopCount() {
		return getLoops;
	}

	// For Part III
	public long getPutLoopCount() {
		return putLoops;
	}

	// For Part III
	public void resetGetLoops() {
		getLoops = 0;
	}
	
	// For Part III
	public void resetPutLoops() {
		putLoops = 0;
	}

	public boolean containsKey(K key) {
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode()) % tableSize;
		boolean result = false;

		// TODO: complete the rest of this method
		List<Entry<K,V>> l = table.get(index);
		Iterator<Entry<K,V>> it = l.iterator();

		while (it.hasNext()){
			Entry<K,V> cur = it.next();
			if (cur.getKey().compareTo(key) == 0){
				result = true;
			}
		}

		return result; // so it compiles
	}

	public V get (K key) throws KeyNotFoundException {
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode()) % tableSize;
		
		if (!this.containsKey(key)){
			throw new KeyNotFoundException();
		} else {
			List<Entry<K,V>> l = table.get(index);
			Iterator<Entry<K,V>> it = l.iterator();
	
			while (it.hasNext()){
				Entry<K,V> cur = it.next();
				if (cur.getKey().compareTo(key) == 0){
					return cur.getValue();
				}
			}
		}
		return null;
		// TODO: complete the rest of this method
	}


	public List<Entry<K,V>>	entryList() {
		List <Entry<K,V>> resultList = new LinkedList<Entry<K,V>>();
		// TODO: complete the rest of this method

		// Tip: you will need to iterate through each index in the table (and each index holds a list)
		//      you will THEN need to iterate through each element in the linked list chain at a 
		//      specific index and add each element to l
		Iterator<List<Entry<K,V>>> it1 = table.iterator();
		while (it1.hasNext()){
			List<Entry<K,V>> l = it1.next();
			Iterator<Entry<K,V>> it2 = l.iterator();
			while (it2.hasNext()){
				resultList.add(it2.next());
			}
		}

		return resultList;
	}
	
	public void put (K key, V value){
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode())%tableSize;
		
		// TODO: complete the rest of this method

		// if key is found, update value.  if key is not found add a new Entry with key,value
		// The tester expects that you will add the newest Entry to the END of the list
		List<Entry<K,V>> l = table.get(index);
		
		if (this.containsKey(key)){
			Iterator<Entry<K,V>> it = l.iterator();
			while (it.hasNext()){
				Entry<K,V> cur = it.next();
				if (cur.getKey().compareTo(key) == 0){
					cur.setValue(value);
				}
			}
		} else {
			Entry<K,V> temp = new Entry<K,V>(key, value);
			l.add(temp);
			count++;
		}

	}

	public int size() {
		return count;
	}

    public void clear() {
		for(int i = 0; i < tableSize; i++) {
			table.get(i).clear();
		}
        count = 0;
    }

}