import java.io.IOException;
import java.util.*;

public class BreadthFirstSearch {
    // BFS a partir de uma lista de adjacência
    public static void bfs(List<List<Edge>> adjacencyList, int [][] adjacencyMatrix, int numberVertices, int initialVertex) throws NullPointerException, IOException {
        initialize(adjacencyList, adjacencyMatrix, numberVertices, initialVertex);
        System.out.println();
    }

    private static void initialize(List<List<Edge>> adjacencyList, int [][] adjacencyMatrix, int numberVertices, int initialVertex) throws IOException {
        // Fila
        Queue<Integer> queue = new LinkedList<>();
        // Array de vértices visitados
        boolean [] visited = new boolean[numberVertices+1];
        // Array dos pais de um vértice visitado
        int [] parent = new int[numberVertices+1];
        // Array de level de cada vértice visitado
        int [] level = new int[numberVertices+1];
        int [] distance = new int[numberVertices+1];

        Arrays.fill(level, -1);
//        Arrays.fill(distance, -1);

        level[initialVertex] = 0;

        queue.add(initialVertex);
        System.out.print("BFS saída: ");

        if(adjacencyList != null) {
            searching(queue, adjacencyList, visited, parent, level, distance);
        } else {
            searching(queue, adjacencyMatrix, visited, parent, level, distance);
        }
        printDistance(initialVertex, numberVertices, distance);
        SearchGraphOutput.writeFile(parent, level, numberVertices);
    }

    private static void searching(Queue<Integer> queue, List<List<Edge>> adjacencyList, boolean [] visited, int [] parent, int [] level, int[] distance) {
        while(!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");
            visited[u] = true;

            Iterator<Edge> neighbors = adjacencyList.get(u).iterator();

            while (neighbors.hasNext()) {
                int vertex = neighbors.next().getVertex();
                if(!visited[vertex]) {
                    parent[vertex] = u;
                    visited[vertex] = true;
                    level[vertex] = level[u] + 1;
                    distance[vertex] = distance[u] + 1;
                    queue.add(vertex);
                }
            }
        }
    }

    private static void searching(Queue<Integer> queue, int [][] adjacencyMatrix , boolean [] visited, int [] parent, int [] level, int[] distance) {
        while(!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");
            visited[u] = true;
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if(adjacencyMatrix[u][i] != 0 && !visited[i]) {
                    parent[i] = u;
                    visited[i] = true;
                    level[i] = level[u] + 1;
                    distance[i] = distance[u] + 1;
                    queue.add(i);
                }
            }
        }
    }

    private static void printDistance(int root, int numVertices, int [] distance) {
        System.out.println("\nBFS - Caminho mínimo:");
        System.out.println("\nOrigem Vértice Distância");
        for (int i = 1; i < numVertices; i++) {
            System.out.println("  " + root + "       " + i + "       " + distance[i]);
        }
        System.out.println();
    }
}