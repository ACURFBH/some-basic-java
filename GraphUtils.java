
import java.util.List;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {
		if(graph == null || src == null || dest == null || src.isEmpty() || dest.isEmpty()) return -1;
		if(!graph.containsElement(src) || !graph.containsElement(dest)) return -1;
		return findShortestPath(graph, src, dest);
	}
	
	private static int findShortestPath(Graph graph, String src, String dest){
		BreadthFirstSearch b = new BreadthFirstSearch(graph);
		return b.bfsGenerationCount(graph.getNode(src), dest);
	}
	

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
		Set<String> set = new java.util.HashSet<String>();
		if(graph == null || src == null || distance <= 0 || src.isEmpty()) return null;
		if(graph.getNumNodes()==1 && distance>0) return set;
		if(!graph.containsElement(src)) return null;
		BreadthFirstSearch b = new BreadthFirstSearch(graph);
		Set<Node>setOfNodes = b.bfsDistMeasure(graph.getNode(src), distance);
		for(Node n: setOfNodes){
			set.add(n.getElement());
		}
		return set;
	}


	public static boolean isHamiltonianPath(Graph g, List<String> values) {
		if(values==null || values.isEmpty() || g == null || !g.containsElement(values.get(0))) return false;
		if(!values.get(0).equals(values.get(values.size()-1))) return false;//1st = last
		int i,n = values.size()-1;
		Node node;
		for(i=1; i<n; i++){
			if(!g.containsElement(values.get(i))) return false;//what if string is null???
			node = g.getNode(values.get(i));
			if(!g.getNodeNeighbors(node).contains(g.getNode(values.get(i-1))) || !g.getNodeNeighbors(node).contains(g.getNode(values.get(i+1)))){
				return true;
			}
		}
		return true;//only time to return true
	}
	
	public static boolean recfoo(Node node, Graph g, List<String> values){//helper method
		if(values.size()==0) return true;
		if(node.getElement() != values.get(0)) return false;
		values.remove(0);
		if(values.get(0)!=null && g.getNodeNeighbors(node).contains(new Node(values.get(0)))){//flawed
			node = new Node(values.get(0));
		}
		//move to next node in graph
		
		return recfoo(node, g, values);
	}
	
}
