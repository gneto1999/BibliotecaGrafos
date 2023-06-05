import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new ArrayList<>();
    }

    public void buildAdjacencyList(int[][] graph){
        for(int i = 0; i < graph.length; i++){
            int vertex1 = graph[i][0];
            int vertex2 = graph[i][1];
            int weight = graph[i][2];

            addEdge(vertex1, vertex2, weight);
            addEdge(vertex2, vertex1, weight);
        }

    }
    public void addEdge(int vertex1, int vertex2, int weight){
        while(vertex1 >= adjacencyList.size()){
            adjacencyList.add(new ArrayList<>());
        }
        adjacencyList.get(vertex1).add(new Edge(vertex2, weight));
    }

    public void printAdjacencyList(){
        for(int i = 0; i < adjacencyList.size(); i++){
            System.out.print(i + ": ");
            for(Edge edge: adjacencyList.get(i)){
                System.out.print(edge.vertex + "(" + edge.weigth + ") ");
            }
            System.out.println();
        }
    }


}
