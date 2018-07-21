
public class CompareInt {
	int val;//value	
	public CompareInt(int value){//constructor
		this.val = value;
	}	
	public int compareTo(CompareInt x){
		return this.val - x.val;
	}	
	public static int countComparisons(CompareInt[] arr, int x, int y){
		int count = 0;
		for(int i = x; i <= y; i++){
			count++;
		}
		return count;
	}
}