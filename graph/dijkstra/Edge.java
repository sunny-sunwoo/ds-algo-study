package ds_algo_study.graph.dijkstra;

public class Edge {
    
    private final Vertex target;
    private final double weight;
    
    public Edge (Vertex target, double weight) {
        this.target = target;
        this.weight = weight;
    }
    
    public Vertex getTarget() {
        return target;
    }
    
    public double getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        return "target: " + target.getName() + ", weight: " + weight + "\n";
    }

}
