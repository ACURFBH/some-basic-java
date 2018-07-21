/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;



public class MovieRatingsProcessor {

	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
		List<String> list = new ArrayList<String>();
		if(movieRatings==null || movieRatings.isEmpty()) return list;
		String s;
		TreeMap<String, PriorityQueue<Integer>> tree = new TreeMap<String, PriorityQueue<Integer>>(movieRatings);
		while(!tree.isEmpty()){
			s = tree.firstKey();
			if(s!=null && !s.isEmpty()){
				list.add(s);
				tree.remove(s); 
			}
		}
		Collections.sort(list);
		return list;
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		List<String> list = new ArrayList<String>();
		if(movieRatings==null || movieRatings.isEmpty() || rating<=0) return list;
		String s;
		TreeMap<String, PriorityQueue<Integer>> tree = new TreeMap<String, PriorityQueue<Integer>>(movieRatings);
		while(!tree.isEmpty()){
			s = tree.firstKey();
			if(rating < tree.get(s).peek()){
				list.add(s);
			}
			tree.remove(s);
		}
		Collections.sort(list);
		return list;
	}
	
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		if(movieRatings==null || movieRatings.isEmpty()) return map;
		int count;//track number of nodes deleted
		String s;//to access each heap
		//iterate through tree map
		for(Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()){
			s = entry.getKey();//need string variable for removal more than heap data structure
			count=0;
			//iterate through priority queue
			if(movieRatings.get(s)==null || movieRatings.get(s).isEmpty() || movieRatings.get(s).peek() >= rating){
				continue;//may be redundant
			}else{
				while(movieRatings.get(s) != null && !movieRatings.get(s).isEmpty() && movieRatings.get(s).peek() < rating){
					//remove all low ratings from queue
					count++;
					if(movieRatings.get(s).size()<=1){
						movieRatings.remove(s);//remove empty queue from tree map
						break;
					}else{
						movieRatings.get(s).remove();//remove low ratings from queue
						break;
					}				
				}
				if(count > 0){
					map.put(s, new Integer(count));//add new element to map
				}
			}			
		}		
		return map;
	}
}
