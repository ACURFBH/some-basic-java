
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * SD2x Homework #6
 * This is an implementation of Breadth First Search (BFS) on a graph.
 * You may modify and submit this code if you'd like.
 */

public class BreadthFirstSearch {
	protected Set<Node> marked;
	protected Graph graph;

	public BreadthFirstSearch(Graph graphToSearch) {
		marked = new HashSet<Node>();
		graph = graphToSearch;
	}
	
	/**
	 * This method was discussed in the lesson
	 */
	public boolean bfs(Node start, String elementToFind) {
		if (!graph.containsNode(start)) {
				return false;//return -1
		}
		if (start.getElement().equals(elementToFind)) {
			return true;//return 0
		}
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					if (neighbor.getElement().equals(elementToFind)) {
						return true;
					}
					marked.add(neighbor);
					toExplore.add(neighbor);
				}
			}
		}
		return false;
	}
	
	public int bfsGenerationCount(Node start, String elementToFind) {//count number of generations
		if (!graph.containsNode(start)) return -1;
		if (start.getElement().equals(elementToFind)) return 0;
		int genCount = 0;//increment each time code skips to next generation
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {//while queue is full
			Node current = toExplore.remove();//look at the current node
			genCount++;//increments too often
			for (Node neighbor : graph.getNodeNeighbors(current)) {//each neighbor of the node
				if (!marked.contains(neighbor)) {//if the neighbor has not been visited
					if (neighbor.getElement().equals(elementToFind)) {
						return genCount;
					}
					marked.add(neighbor);//say we visited 
					toExplore.add(neighbor);//add neighbor to the queue
				}
			}
		}
		return -1;
	}
	
	public Set<Node> bfsDistMeasure(Node start, int distance) {//count number of generations
		if (!graph.containsNode(start)) {
				return null;//if node is not in graph
		}
		int genCount = 0;//increment each time code skips to next generation
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {//while queue is full
			Node current = toExplore.remove();//look at the current node
			genCount++;
			for (Node neighbor : graph.getNodeNeighbors(current)) {//each neighbor of the node
				if (!marked.contains(neighbor)) {//if the neighbor has not been visited
					if (genCount == distance) {
						return marked;
					}
					marked.add(neighbor);//say we visited 
					toExplore.add(neighbor);//add neighbor to the queue
				}
			}
		}
		return marked;//if distance exceeds number of generations
	}
	
	public Set<Node> getMarked(){
		return this.marked;
	}

}
