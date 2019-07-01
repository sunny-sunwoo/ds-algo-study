package ds_algo_study.graph.dijkstra;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Q. From each village, 
 * connect to the closest pond. Connect to only 1 pond.
 * 
 * Intuition. 
 * - basic approach: Apply Dijkstra from each pond to the villages.
 * - Create a virtual starting point that has an edge of 0 weight to the ponds!!
 * - Solution: Dijkstra from the starting point to the villages. and remove the virtual edge.
 * 
 * @author Sunny Park
 */
public class VillageConnector {
    public static void findOptimizedRoutes(Vertex... ponds) {
        Vertex virtualSrc = new Vertex("Virtual");
        for (Vertex pond : ponds) {
            virtualSrc.addEdge(new Edge(pond, 0.0));
        }
        retrieveShortestPath(virtualSrc);
        for (Vertex pond : ponds) {
            pond.setPrevious(null);
        }
    }
    
    private static void retrieveShortestPath(Vertex src) {
        Map<Vertex, Double> map = new HashMap<>();
        PriorityQueue<Vertex> minHeap = new PriorityQueue<>();
        minHeap.offer(src.setMinDistance(0.0));
        
        while (!minHeap.isEmpty()) {
            Vertex top = minHeap.poll();
            for (Edge edge : top.getAdjacencies()) {
                Vertex target = edge.getTarget();
                double candid = top.getMinDistance() + edge.getWeight();
                
                if (map.containsKey(target) && target.getMinDistance() < candid) {
                    continue;
                }
                
                if (candid < target.getMinDistance()) {
                    map.put(target.setPrevious(top).setMinDistance(candid), candid);
                    minHeap.offer(target);
                }
            }
        }
    }
}
