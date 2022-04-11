package Lab8;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Arrays;


/**
 *
 * @author yaw
 */
public class Graph {
    private LinkedList<Integer>[] adjacencyList;
    private int numEdges;
    
    public Graph(int numVertices) {
        adjacencyList = new LinkedList[numVertices];
        
        for (int i = 0; i < adjacencyList.length; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }
    
    public int getNumVertices() {
        return adjacencyList.length;
    }
    
    public int getNumEdges() {
        return numEdges;
    }
    
    public void addEdge(int vertex1, int vertex2) {
        adjacencyList[vertex1].add(vertex2);
        adjacencyList[vertex2].add(vertex1);
        
        numEdges++;
    }
    
    public LinkedList<Integer> getNeighbors(int vertex) {
        return adjacencyList[vertex];
    }
    
    // Return the degree value of the provided vertex.
    public int getDegree(int vertex){
        return adjacencyList[vertex].size();
    }
    
    // Return the degree value of the vertex with the largest degree value in the graph.
    public int getMaxDegree() {
        int max = 0;
        for(LinkedList temp : adjacencyList) {
        	if(temp.size() > max) {
        		max = temp.size();
        	}
        }
        return max;
    }
    
    // Return whether or not the graph is a simple graph.
    public boolean isSimple() {
    	//check if the graph has any self loops, check if there are any parallel edges
    	boolean isSimple = true;
    	int counter = 0;
    	for(LinkedList temp : adjacencyList) {
    		//System.out.println(temp.getFirst());
    		if(temp.contains((Integer) counter)){
    			//System.out.println(counter);
    			
    			//System.out.println("The graph has a self loop!");
    			isSimple = false;
    		}
    		counter++;
    		Object[] sample = temp.toArray();
    		Arrays.sort(sample);
    		for(int i = 0; i < sample.length -1; i++) {
    			if(sample[i] == sample[i+1]) {
    				//System.out.println("The graph has a parallel edge");
    				isSimple = false;
    			}
    		}
    	}
    	return isSimple;
    }
}

