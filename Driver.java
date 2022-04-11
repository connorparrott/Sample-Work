package Lab8;


/**
 *
 * @author yaw
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph myGraph = new Graph(10);
        
        
        myGraph.addEdge(0, 1);
        myGraph.addEdge(0, 2);
        myGraph.addEdge(0, 3);
        myGraph.addEdge(0, 4);
        myGraph.addEdge(0, 5);
        //this is commented to check if it works when the highest number matches can uncomment to find highest of 6.
        //myGraph.addEdge(0, 6);
        myGraph.addEdge(1, 2);
        myGraph.addEdge(1, 6);
        myGraph.addEdge(3, 2);
        myGraph.addEdge(1, 8);
        myGraph.addEdge(3, 6);
        myGraph.addEdge(5, 1);
        myGraph.addEdge(7, 2);
        //Makes the graph have a self loop.
        //myGraph.addEdge(0, 0);
        
        //makes the graph have a parallel edge
        //myGraph.addEdge(1, 6);
    
        
        //getDegree test
        System.out.println(myGraph.getDegree(0));
        System.out.println(myGraph.getDegree(1));
        
        //getMaxDegreeTest
        System.out.println(myGraph.getMaxDegree());
        System.out.println();
        //isSimple test
        System.out.println(myGraph.isSimple());
        
    }
}
