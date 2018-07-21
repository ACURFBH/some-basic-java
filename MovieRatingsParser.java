/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
		TreeMap<String, PriorityQueue<Integer>> tree = new TreeMap<String, PriorityQueue<Integer>>();
		if(allUsersRatings==null || allUsersRatings.isEmpty()) return tree;
		String title;
		PriorityQueue<Integer> heap;
		int i,j,n=allUsersRatings.size();
		for(i=0; i<n; i++){
			if(allUsersRatings.get(i)!=null && allUsersRatings.get(i).getUserRating()>0){//2nd condition IDK
				title = allUsersRatings.get(i).getMovie().toLowerCase();
				heap = new PriorityQueue<Integer>();
				if(title!=null && !title.isEmpty()){
					for(j=i; j<n; j++){
						if(allUsersRatings.get(j)!=null && allUsersRatings.get(j).getMovie().toLowerCase().equals(title) && allUsersRatings.get(j).getUserRating()>0){
							heap.add(new Integer(allUsersRatings.get(j).getUserRating())) ;
						}
					}
					if(!tree.containsKey(title)){
						tree.put(title, heap);
					}
				}
			}
			
		}
		return tree;
	}

}