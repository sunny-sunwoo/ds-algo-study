package ds_algo_study.dijkstra_mst;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Kruskal MST - how to connect all nodes with minimum weight
 * 1. offer all edges to minHeap
 * 2. poll and add to the queue if 2 nodes are not connected
 * 
 * Time Complexity - O(LogV) + O(ELogE) -> O(ELogE)
 * @author Sunny Park
 */
public class MST_Kruskal {
    public static Queue<Edge> kruskalMst(Graph g) {
        Queue<Edge> mst = new LinkedList<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
        
        for (Edge e : g.edges()) {
            minHeap.offer(e);
        }
        
        DisjointSet ds = new DisjointSet(g.size());
        while (!minHeap.isEmpty()) {
            Edge currEdge = minHeap.poll();
            int v = currEdge.either(), otherV = currEdge.either(v);
            if (!ds.isConnected(v, otherV)) {
                ds.merge(v, otherV);
                mst.offer(currEdge);
            }
        }
        return mst;
    }
    
    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0,  1,  1);
        g.addEdge(0,  2,  2);
        g.addEdge(1,  2,  3);
        g.addEdge(1,  3,  4);
        g.addEdge(2,  3,  5);
        System.out.println(kruskalMst(g));
        
    }
}
