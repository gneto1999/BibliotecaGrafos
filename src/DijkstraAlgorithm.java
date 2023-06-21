import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DijkstraAlgorithm {
    public static int [] dijkstra(List<List<Edge>> adjacencyList, int [][] adjacencyMatrix, int numVertices, int initialVertex) {
        return initialize(adjacencyList, adjacencyMatrix, numVertices, initialVertex);
    }

    private static int [] initialize(List<List<Edge>> adjacencyList, int [][] adjacencyMatrix, int numVertices, int initialVertex) {
        int [] key = new int[numVertices+1];
        Integer [] parent = new Integer[numVertices+1];
        boolean [] visited = new boolean[numVertices+1];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, null);
        Arrays.fill(visited, false);

        key[initialVertex] = 0;

        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(numVertices, Comparator.comparingInt(v -> v.getKey()));

        priorityQueue.add(new Vertex(initialVertex, 0));

        if(adjacencyList != null) {
            relaxation(visited, parent, key, priorityQueue, adjacencyList);
        } else {
            relaxation(visited, parent, key, priorityQueue, adjacencyMatrix);
        }

        printDijkstra(initialVertex, numVertices, parent, key);

        return key;
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

    private static void printDijkstra(int root, int numVertices, Integer [] parent, int [] key) {
        System.out.println("Algoritmo Dijkstra - Caminho mínimo a partir do vértice " + root);
        System.out.println("\nOrigem Vértice Distância");
        for (int i = 0; i <= numVertices; i++) {
            if(key[i] != Integer.MAX_VALUE) {
                printPath(parent, i);
                System.out.print("  Distância(" + key[i] + ")");
                System.out.println();
            }
        }
        System.out.println();
    }

    private static void printPath(Integer[] parent, int vertex) {
        if (parent[vertex] != null) {
            printPath(parent, parent[vertex]);
            System.out.print(" -> ");
        }
        System.out.print(vertex);
    }
}