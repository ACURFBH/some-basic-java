
public class Sorting {
	
	/**
	 * Implement the mergesort function, which should sort the array of
	 * integers in place
	 * 
	 * You will probably want to use helper functions here, as described in the lecture recordings
	 * (ex. merge(), a helper mergesort function)
	 * @param arr
	 */
	public static void swapSort(CompareInt[] arr){
		//handle invalid input
		if(arr == null || arr.length <= 1)
			return;
		//implement algorithm
		CompareInt k;
		for(int i=0; i<arr.length; i++){
			for(int j=i; j<arr.length; j++){
				if(arr[j].compareTo(arr[i]) < 0){
					k=arr[i];
					arr[i] = arr[j];
					arr[j] = k;
				}
			}
		}
	}
	public static void mergeSort(CompareInt[] arr) {//work on this method
		if(arr == null || arr.length < 1) return;
		mergeSort(arr, 0, arr.length-1);
	}
	private static void mergeSort(CompareInt[] A, int low, int high){
		if(high <= low) return;//if the array only has one element, it's ordered
		//(1) break array into 2 parts
		int mid = (high+low)/2;
		CompareInt[] sub1 = subArray(A, low, mid);
		CompareInt[] sub2 = subArray(A, mid+1, high);
		//(2) recursively sort the 2 components
		mergeSort(sub1);
		mergeSort(sub2);
		//(3) merge the 2 components into a single ordered array
		merge(A, sub1, sub2);
		//let the original be that ordered array
		//copy_into_from(A, C);
	}
	private static void merge(CompareInt[] result, CompareInt[] A, CompareInt[] B){//works correctly
		int i=0, j=0, k=0, m=A.length, n=B.length;
		CompareInt[] C = new CompareInt[m + n];
		while(i < m && j < n){
			if(A[i].compareTo(B[j]) < 0){
				C[k] = A[i];
				i++;
				k++;
			}else{
				C[k] = B[j];
				j++;
				k++;
			}
		}
		while(i<m){
			C[k] = A[i];
			k++;
			i++;
		}
		while(j<n){
			C[k] = B[j];
			k++;
			j++;
		}
		copy_into_from(result, C);
	}
	private static CompareInt[] subArray(CompareInt[] arr, int lo, int hi){//
		CompareInt[] result = new CompareInt[hi-lo+1];
		int k = 0;
		for(int i=lo; i<=hi; i++){
			result[k] = arr[i];
			k++;
		}
		return result;
	}
	private static void copy_into_from(CompareInt[] A, CompareInt[] B){
		int n = A.length;
		for(int i=0; i<n; i++){
			A[i] = B[i];
		}
	}
	/**
	 * Implement the quickSelect
	 * 
	 * Again, you will probably want to use helper functions here
	 * (ex. partition(), a helper quickselect function)
	 */
	public static CompareInt quickSelect(int k, CompareInt[] arr) {//work on this method
		//handle invalid array
		if(arr == null || arr.length <= 0)
			return null;
		else if(arr.length == 1)
			return arr[0];
		//handle invalid k value
		if(k <= 0)
			k = 1;
		else if(k > arr.length)
			k = arr.length;
		//implement method
		return quickSelect(arr, 0, arr.length-1, k);
	}
	private static CompareInt quickSelect(CompareInt[] arr, int beg, int end, int k){
		int center = quick(arr, beg, end);//center is an index
		if(center < k-1){//right side of center
			return quickSelect(arr, center+1, end, k);
		}else if(center > k-1){//left side of center
			return quickSelect(arr, beg, center-1, k);
		}else if(center == k-1){//this is correct
			//this should work
			return arr[k-1];
		}
		return arr[end+1];//this is unreachable
	}
	private static int partition(CompareInt[] arr, int beg, int end){
		while(beg < end){
			if(arr[beg].compareTo(arr[end]) <= 0){
				beg++;
			}else{
				if(arr[beg].compareTo(arr[end-1]) > 0){
					swap(arr[beg], arr[end-1]);
				}
				if(arr[end-1].compareTo(arr[end]) >= 0){
					swap(arr[end], arr[end-1]);
					end--;
				}
			}
		}
		return end;
	}
	private static int quick(CompareInt[] A, int lo, int hi){
		while(lo < hi){
			//hi is the focus
			if(A[lo].compareTo(A[hi]) <= 0){
				lo++;
			}else{
				if(A[lo].compareTo(A[hi-1]) > 0){
					//swap
					int temp = A[hi-1].val;
					A[hi-1] = A[lo];
					A[lo] = new CompareInt(temp);
					//end swap
				}
				if(A[hi-1].compareTo(A[hi]) >= 0){
					//swap
					int temp = A[hi-1].val;
					A[hi-1] = A[hi];
					A[hi] = new CompareInt(temp);
					//end swap
					hi--;
				}
			}
		}
		return hi;
	}
	private static void swap(CompareInt x, CompareInt y){//this should be modified
		int z = x.val;
		x = new CompareInt(y.val);
		y = new CompareInt(z);
	}

}
