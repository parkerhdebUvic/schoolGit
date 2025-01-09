//Parker DeBruyne: V00837207

/*
* HeapPriorityQueue.java
*
* An implementation of a minimum PriorityQueue using a heap.
* based on the implementation in "Data Structures and Algorithms
* in Java", by Goodrich and intamassia
*
* inthis implementation will throw a Runtime, HeapEmptyException
*	if the heap is empty and removeMin is called.
*
* inthis implementation will throw a Runtime, HeapFullException
*	if the heap is full and insert is called.
*
*/
public class HeapPriorityQueue implements PriorityQueue {

	protected final static int DEFAULT_SIZE = 10000;
	
	protected int[] storage;
	protected int currentSize;
	int rootIndex = 0; // should be 0 or 1 depending on implementation

	/*
	 * Constructor that initializes the array to hold DEFAULT_SIZE elements
	 */
	public HeapPriorityQueue() {
		// TODO: implement this
		storage = new int[DEFAULT_SIZE];
		currentSize = 0;
		// if using a 1-based implementation, remember to allocate an 
		// extra space in the array since index 0 is not used. 
	}
	
	/*
	 * Constructor that initializes the array to hold size elements
	 */
	public HeapPriorityQueue(int size) {
		// TODO: implement this
		storage = new int[size];
		currentSize = 0;
		// if using a 1-based implementation, remember to allocate an 
		// extra space in the array since index 0 is not used. 
	}

	private int getLeftLeaf(int parent){
		return 2*parent + 1;
	}
	private int getRightLeaf(int parent){
		return 2*parent + 2;
	}
	private int getParentIndex (int child){
		return (child-1)/2;
	}

	private boolean hasLeftLeaf (int index){
		return getLeftLeaf(index) < currentSize;
	}
	private boolean hasRightLeaf (int index){
		return getRightLeaf(index) < currentSize;
	}
	private boolean hasParent (int index){
		return getParentIndex(index) >= 0;
	}

	private int leftLeaf (int index){
		return storage[getLeftLeaf(index)];
	}
	private int rightLeaf (int index){
		return storage[getRightLeaf(index)];
	}
	private int parent (int index){
		return storage[getParentIndex(index)];
	}

	private void swap(int indexOne, int indexTwo){
		int temp = storage[indexOne];
		storage[indexOne] = storage[indexTwo];
		storage[indexTwo] = temp;
	}

	public void insert (int element) throws HeapFullException {
		// TODO: implement this
		if (isFull()) throw new HeapFullException();
		if (storage.length == currentSize){
			return;
		} else {
			storage[currentSize] = element;
			currentSize++;
			bubbleUp(currentSize-1);
		}
    }
	

	private void bubbleUp(int index) {
		// TODO: implement this
		while(hasParent(index) && parent(index) > storage[index]) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}
		
	}
			
	public int removeMin() throws HeapEmptyException {
		// TODO: implement this
		if (isEmpty()) throw new HeapEmptyException();
		int temp = storage[rootIndex];
		if (currentSize <= 0){
			storage[rootIndex] = 0;
			return temp;
		} else if (currentSize == 1){
			storage[rootIndex] = 0;
			currentSize--;
			return temp;
		} else {
			storage[rootIndex] = storage[currentSize - 1];
			currentSize--;
			bubbleDown(rootIndex);
			return temp;
		}
		
	}
	
	private void bubbleDown(int index) {
		// TODO: implement this
		while (hasLeftLeaf(index)){
			int smallerLeafIndex = getLeftLeaf(index);
			if (hasRightLeaf(index) && rightLeaf(index) < leftLeaf(index)){
				smallerLeafIndex = getRightLeaf(index);
			}

			if (storage[index] < storage[smallerLeafIndex]){
				break;
			} else {
				swap(index, smallerLeafIndex);
			}
			index = smallerLeafIndex;
		}
	}

	public boolean isEmpty(){
		// TODO: implement this
		return currentSize == 0; // so it compiles
	}
	
	public boolean isFull() {
		// TODO: implement this
		return (storage.length == currentSize); // so it compiles
	}
	
	public int size () {
		// TODO: implement this
		return currentSize; // so it compiles
	}

	public String toString() {
		String s = "";
		String sep = "";
		if (rootIndex == 0) {
			for (int i = 0; i < currentSize; i++) {
				s += sep + storage[i];
				sep = " ";				
			}
		} else if (rootIndex == 1) {
			for(int i=1; i<=currentSize; i++) {
				s += sep + storage[i];
				sep = " ";
			}
		}
		return s;
	}
}
