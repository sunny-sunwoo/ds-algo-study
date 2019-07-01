package ds_algo_study.graph.dijkstra;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class Vertex implements Comparable<Vertex>{
    private static final Joiner EDGE_JOINER = Joiner.on(",");
    
    private final String name;
    private final List<Edge> adjacencies = new ArrayList<>();
    private double minDistance = Double.POSITIVE_INFINITY;
    private Vertex prev;
    
    public Vertex(String name) {
        this.name = name;
    }
    
    public Vertex(int num) {
        this.name = String.valueOf(num);
    }
    
    public String getName() {
        return this.name;
    }
    
    public Vertex setPrevious(Vertex v) {
        this.prev = v;
        return this;
    }
    
    public Vertex getPrevious() {
        return this.prev;
    }
    
    public Vertex setMinDistance(double d) {
        this.minDistance = d;
        return this;
    }
    
    public double getMinDistance() {
        return minDistance;
    }
    
    public List<Edge> getAdjacencies() {
        return adjacencies;
    }
    
    public Vertex addAdjacencies(Edge... edges) {
        adjacencies.addAll(Lists.newArrayList(edges));
        return this;
    }
    
    public Vertex addEdge(Edge e) {
        adjacencies.add(e);
        return this;
    }
    
    @Override
    public int compareTo(Vertex other) {
        return Double.compare(this.minDistance, other.minDistance);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(name, Double.doubleToLongBits(minDistance));
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        
        if (!(other instanceof Vertex)) {
            return false;
        }
        
        Vertex o = (Vertex) other;
        return (this.name).equals(o.name);
    }
    
    @Override
    public String toString() {
        return "name: " + name + "\t" + EDGE_JOINER.join(adjacencies);
    }
    
    
}
