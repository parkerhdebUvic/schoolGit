import java.util.*;

public class ListMap<K extends Comparable<K>, V > implements Map<K, V> {

	Node<K, V> head;
	int numElements;
	
	public ListMap() {

	}
	
	public boolean containsKey(K key) {
		Node<K, V> cur = head;
		while (cur != null){
			if (cur.getKey().equals(key)) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	public V get (K key) throws KeyNotFoundException {
		Node<K, V> cur = head;
		while (cur != null){
			if (cur.getKey().equals(key)) {
				return cur.getValue();
			}
			cur = cur.next;
		}
		throw new KeyNotFoundException();
	}

	public void put (K key, V value) {
		if (head == null) {
			head = new Node<K,V>(key, value);
			numElements++;
		} else {
			Node<K, V> cur = head;
			Node<K, V> prev = null;
			while (cur != null){
				if (cur.getKey().equals(key)) {
					cur.setValue(value);
					return;
				} 
				prev = cur;
				cur = cur.next;
			}
			prev.next = new Node<K,V>(key, value);
			numElements++;
		}
	}
	
	public void remove (K key) {
		if (head == null) {
			return;
		} else if (head.getKey().equals(key)){
			head = head.next;
			numElements--;
		} else {
			Node<K, V> cur = head;
			Node<K, V> prev = null;
			while (cur != null){
				if (cur.getKey().equals(key)) {
					prev.next = cur.next;
					numElements--;
					return;
				} 
				prev = cur;
				cur = cur.next;
			}
		}
	}

	public int size() {
		return numElements;
	}


	public void clear() {
		head = null;
		numElements = 0;
	}
	
	public List<Entry<K,V>> entryList() {
		List<Entry<K,V>> entries = new LinkedList<Entry<K,V>>();
		Node<K,V> cur = head;
		while (cur != null) {
			Entry<K,V> e = new Entry<K,V>(cur.getKey(), cur.getValue());
			entries.add(e);
			cur = cur.next;
		}
		return entries;
	}
	
}