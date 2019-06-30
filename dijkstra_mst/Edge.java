package ds_algo_study.dijkstra_mst;

import com.google.common.base.MoreObjects;

/**
 * reference: https://algs4.cs.princeton.edu/43mst/Edge.java.html
 * @author JungwooP
 *
 */
public class Edge implements Comparable<Edge>{
    private final int v;
    private final int w;
    private final double weight;
    
    public Edge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (w < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public int either() {
        return v;
    }
    
    public int either(int vertex) {
        if (vertex == v) return w;
        if (vertex == w) return v;
        else throw new IllegalArgumentException("illegal endpoint");
    }
    

    public boolean contains(int vertex) {
        return v == vertex || w == vertex;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight, o.weight);
    }
    
    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
          .add("vertex1", v)
          .add("vertex2", w)
          .add("weight", weight)
          .toString();
    }

}
