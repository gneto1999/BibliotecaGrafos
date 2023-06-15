public class Edge {
    private int vertex;
    private int weight;

    public Edge(int vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }

    public Edge(int vertex){
        this.vertex = vertex;
    }

    public int getVertex() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }
}
