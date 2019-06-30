package ds_algo_study.dijkstra_mst;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Prim MST - how to connect all nodes with minimum weight
 * Queue<E> mst, PQ<E> minHeap, boolean[] marked
 * 
 * 1. Visit the first vertex
 * 2. while minHeap is not empty, enqueue the polled edge & visit each vertex. 
 * 
 * visit(helper method) 
 * iterate over the edges from curr Vertex.
 * -> if v is marked, rt. 
 * -> else, enqueue to the minHeap.
 * 
 * @author Sunny Park
 *
 */

public class MST_Prim {
    public static Queue<Edge> primMST(Graph g) {
        Queue<Edge> mst = new LinkedList<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
        boolean[] marked = new boolean[g.size()];
        
        visit(minHeap, marked, 0, g);
        
        while (!minHeap.isEmpty()) {
            Edge currEdge = minHeap.poll();
            int v = currEdge.either();
            int otherV = currEdge.either(v);
            
            if (marked[v] && marked[otherV]) continue;
            mst.offer(currEdge);
            
            visit(minHeap, marked, v, g);
            visit(minHeap, marked, otherV, g);
        }
        
        return mst;
    }
    
    private static void visit(PriorityQueue<Edge> minHeap, boolean[] marked, int v, Graph g) {
        if (marked[v]) return;
        for (Edge e : g.getAdj(v)) {
            int otherV = e.either(v);
            if (!marked[otherV]) {
                minHeap.offer(e);
            }
        }
        marked[v] = true;
    }
    
    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0,  1,  1);
        g.addEdge(0,  2,  2);
        g.addEdge(1,  2,  3);
        g.addEdge(1,  3,  4);
        g.addEdge(2,  3,  5);
        System.out.println(primMST(g));
        
    }
}
