package ds_algo_study.dijkstra_mst;

import java.util.ArrayList;
import java.util.List;

/**
 * A graph is a list of adjacency lists.
 * V: num of vertices (nodes)
 * 
 * reference: https://www.geeksforgeeks.org/graph-and-its-representations/
 * @author Sunny Park
 */
public class Graph {
    int V; 
    List<Edge> edgeList;
    
    public Graph(int V) {
        this.V = V;
        edgeList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
        }
    }
    
    public void addEdge(int src, int dest, double weight) {
        edgeList.add(new Edge(src, dest, weight));
    }
    
    public List<Edge> edges() {
        return edgeList;
    }
    
    public List<Edge> getAdj(int v) {
        List<Edge> currAdj = new ArrayList<>();
        for (Edge e : edgeList) {
            if (e.contains(v)) {
                currAdj.add(e);
            }
        }
        return currAdj;
    }
    
    public int size() {
        return V;
    }
    
}
