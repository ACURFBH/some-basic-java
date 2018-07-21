
public class Main {

	public static void main(String[] args) {
		
		for(int i=1; i<=4; i++){
			Kthsmallest_mergeSort_test();
			Kthsmallest_quickSort_test();
		}
//		swap_test();
		System.out.println("complete!!!");
	}
	private static void Kthsmallest_mergeSort_test(){
		CompareInt[] arr = new CompareInt[16];
		generate_elements_and_place_into(arr, 16);
		displayElements(arr);
		int min = KthSmallest.mergeSortImpl(5, arr);
		System.out.println(min + " is the 5th smallest\n");
		displayElements(arr);
	}
	
	private static void Kthsmallest_quickSort_test(){
		CompareInt[] arr = new CompareInt[16];
		generate_elements_and_place_into(arr, 16);
		displayElements(arr);
		int min = KthSmallest.quickSelectImpl(4, arr);
		System.out.println(min + " is the " + 4 + "th smallest\n");
		displayElements(arr);
	}
	
	private static void min_heap_test(MinHeap heap, int n){
		fillHeap(heap, n);
		displayHeap(heap);
	}
	private static void fillHeap(MinHeap heap, int n){
		int x;
		for(int i=0; i<n; i++){
			x = (int)(Math.random()*n +1);
			heap.add(new CompareInt(x));
		}System.out.println();
	}
	private static void displayHeap(MinHeap heap){
		int n = heap.size;
		System.out.print("[");
		for(int i=1; i<=n; i++){
			System.out.print(" " + heap.heap[i].val);
		}
		System.out.println(" ]");
	}
	
	private static void merge_sort_test(CompareInt[] arr){
		mergeSortTest(arr, arr.length);
	}
	private static void mergeSortTest(CompareInt[] arr, int n){
		generate_elements_and_place_into(arr, n);
		System.out.println("original array");
		displayElements(arr);		
		Sorting.mergeSort(arr);
		System.out.println("mergeSort: ");
		displayElements(arr);		
	}
	private static void generate_elements_and_place_into(CompareInt[] arr, CompareInt[] brr, int x){
		int a;
		for(int i=0; i<x; i++){
			a = (int)(Math.random()*x+1);
			arr[i] = new CompareInt(a);
			brr[i] = new CompareInt(a);
		}
	}
	private static void generate_elements_and_place_into(CompareInt[] arr, int x){
		int a;
		for(int i=0; i<x; i++){
			a = (int)(Math.random()*x*2+1);
			arr[i] = new CompareInt(a);
		}
	}
	private static void displayElements(CompareInt[] arr){
		int n = arr.length;
		System.out.print("[");
		for(int i=0; i<n; i++){
			System.out.print(" " + arr[i].val);
		}
		System.out.println(" ]");
	}
	private static void swap_test(){
		CompareInt x = new CompareInt(5), y = new CompareInt(7);
		System.out.println("x: " + x.val + "\ty: " + y.val);
		swap(x, y);
		System.out.println("x: " + x.val + "\ty: " + y.val);
		if(x.compareTo(y) == 2){
			System.out.println("swap success!!!");
		}else{
			System.out.println("swap failure!!!");
		}
	}
	private static void swap(CompareInt x, CompareInt y){
		int i = x.val, j = y.val;
		
		x = y;
		y = new CompareInt(i);
	}
}