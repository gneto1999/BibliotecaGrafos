import java.io.IOException;
import java.util.List;

public class AveragePathLength {
    public static void avg(List<List<Edge>> adjacencyList, int [][] adjacencyMatrix, boolean hasWeight) throws IOException {
        int totalDistance = 0;
        int peers = 0;

        if(hasWeight && adjacencyList != null) {
            for (int i = 0; i <= Graph.getNumberVertices(); i++) {
                int [] key = DijkstraAlgorithm.dijkstra(Graph.getAdjacencyList(), Graph.getAdjacencyMatrix(), Graph.getNumberVertices(), i);
                for (int j = 0; j <= Graph.getNumberVertices(); j++) {
                    if(i != j && key[j] != Integer.MAX_VALUE) {
                        totalDistance += key[j];
                        peers++;
                    }
                }
            }
        } else {
            for (int i = 0; i <= Graph.getNumberVertices(); i++) {
                int [] distance = BreadthFirstSearch.bfs(Graph.getAdjacencyList(), Graph.getAdjacencyMatrix(), Graph.getNumberVertices(), i);
                for (int j = 0; j <= Graph.getNumberVertices(); j++) {
                    if(i != j && distance[j] != 0) {
                        totalDistance += distance[j];
                        peers++;
                    }
                }
            }
        }

        double average = (double) totalDistance/peers;

        System.out.println("\n\nDistância média do grafo: " + average);
    }
}
