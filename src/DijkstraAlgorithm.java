import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DijkstraAlgorithm {
    public static void dijkstra(List<List<Edge>> adjacencyList, int [][] adjacencyMatrix, int numVertices, int initialVertex) {
        initialize(adjacencyList, adjacencyMatrix, numVertices, initialVertex);
    }

    private static void initialize(List<List<Edge>> adjacencyList, int [][] adjacencyMatrix, int numVertices, int initialVertex) {
        int [] key = new int[numVertices];
        Integer [] parent = new Integer[numVertices];
        boolean [] visited = new boolean[numVertices];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, null);
        Arrays.fill(visited, false);

        key[0] = 0;

        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(numVertices, Comparator.comparingInt(v -> v.getKey()));

        priorityQueue.add(new Vertex(initialVertex, 0));

        if(adjacencyList != null) {
            relaxation(visited, parent, key, priorityQueue, adjacencyList);
        } else {
            relaxation(visited, parent, key, priorityQueue, adjacencyMatrix);
        }

        printDijkstra(initialVertex, numVertices, key);
    }

    private static void relaxation(boolean [] visited, Integer [] parent, int [] key, PriorityQueue<Vertex> priorityQueue, List<List<Edge>> adjacencyList) {
        while(!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll().getVertex();
            List<Edge> adjVertices = adjacencyList.get(u);
            visited[u] = true;

            for (Edge adjVertex : adjVertices) {
                if(!visited[adjVertex.getVertex()] && (adjVertex.getWeight() + key[u]) < key[adjVertex.getVertex()]){
                    priorityQueue.remove(new Vertex(adjVertex.getVertex(), key[adjVertex.getVertex()]));
                    parent[adjVertex.getVertex()] = u;
                    key[adjVertex.getVertex()] = adjVertex.getWeight() + key[u];
                    priorityQueue.add(new Vertex(adjVertex.getVertex(), key[adjVertex.getVertex()]));
                }
            }
        }
    }
    private static void relaxation(boolean [] visited, Integer [] parent, int [] key, PriorityQueue<Vertex> priorityQueue, int [][] adjacencyMatrix) {
        while(!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll().getVertex();
            visited[u] = true;

            List<Integer> adjVertices = new ArrayList<>();

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if(adjacencyMatrix[u][i] != 0){
                    adjVertices.add(i);
                }
            }

            for (Integer adjVertex : adjVertices) {
                if(!visited[adjVertex] && (adjacencyMatrix[u][adjVertex] + key[u]) < key[adjVertex]){
                    priorityQueue.remove(new Vertex(adjVertex, key[adjVertex]));
                    parent[adjVertex] = u;
                    key[adjVertex] = adjacencyMatrix[u][adjVertex] + key[u];
                    priorityQueue.add(new Vertex(adjVertex, key[adjVertex]));
                }
            }
        }
    }

    private static void printDijkstra(int root, int numVertices, int [] key) {
        System.out.println("Algoritmo Dijkstra - Caminho mínimo:");
        System.out.println("\nOrigem Vértice Distância");
        for (int i = 1; i < numVertices; i++) {
            System.out.println("  " + root + "       " + i + "       " + key[i]);
        }
        System.out.println();
    }
}