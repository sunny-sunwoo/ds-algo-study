package ds_algo_study.graph.dijkstra;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Dijkstra's Shortest Path Algorithm.
 * - how to find the shortest path (with min weight) from "SRC" to "DEST"
 * - using minHeap(Vertex), hashMap(Vertex-Double)
 * - all vertices are set as POSITIVE_INFINITY min distance. 
 * 
 * 1. create a minHeap: offer vertex src by setting minDistance to (0.0) 
 * 2. iterate through the minHeap
 *     - poll top vertex from minHeap.
 *     - find the top's adj edges
 *     - target's current minDistance vs newMinDistance(distance through top)
 *       check if map contains the target, target's current minDistance is less than newMinDistance
 *       - if yes -> continue
 *       - if no, check minDistance > newMinDistance 
 *          -> update the map with the target's minDistance, previous 
 *          -> offer to the minHeap.
 *       
 *     
 * @author Sunny Park
 *
 */

public class ShortestPathFinder {
    public static void retrieveShortestPath(Vertex src) {
        checkNotNull(src);
        PriorityQueue<Vertex> minHeap = new PriorityQueue<>();
        Map<Vertex, Double> map = new HashMap<>();
        
        minHeap.offer(src.setMinDistance(0.0));
        while (!minHeap.isEmpty()) {
            Vertex top = minHeap.poll();
            for (Edge edge : top.getAdjacencies()) {
                Vertex target = edge.getTarget();
                double newMinDist = top.getMinDistance() + edge.getWeight();
                
                if (map.containsKey(target) && target.getMinDistance() < newMinDist) {
                    continue;
                }
                
                if (target.getMinDistance() > newMinDist) {
                    map.put(target.setMinDistance(newMinDist).setPrevious(top), newMinDist);
                    minHeap.offer(target);
                }
            }
        }
    }
}
