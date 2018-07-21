/**
 * A Heap implementation class
 * 
 * @param heap the array that holds the heap data
 * @param size the number of elements currently stored in the heap
 */
public class MinHeap {
	
	CompareInt[] heap;
	int size;//size is the last index

	/**
	 * Constructs a new heap with maximum capacity n
	 * Remember to index your heap at 1 instead of 0!
	 * @param n the maximum number of elements allowed in the heap
	 */
	public MinHeap(int n) {
		heap = new CompareInt[n+1];//ignore index 0
		size = 0;
	}
	
	/**
	 * Adds an element to the heap
	 * 
	 * @param val the value to be added to the heap
	 */
	public void add(CompareInt val) {//work on this method
		int i=1;
		while(heap[i] != null && val.compareTo(heap[i]) >= 0){
			i++;
		}
		insertIntoHeap(val, i);
	}
	private void insertIntoHeap(CompareInt value, int loc){
		if(loc >= heap.length) return;
		shiftRight(loc, size);
		heap[loc] = value;
		size++;
	}
	private void shiftRight(int start, int end){
		for(int i=end; i>=start; i--){
			heap[i+1] = heap[i];
		}
	}
	
	/**
	 * Extracts the smallest element from the heap
	 */
	public CompareInt extractMin() {//work on this method
		CompareInt min = heap[1];//index is empty, idk y
		shiftLeft(heap);
		size--;
		return min;
	}
	private void shiftLeft(CompareInt[] arr){
		if(size<=0) return;
		for(int i=1; i<size; i++){
			arr[i] = arr[i+1];
		}
	}
	
	
}