import java.io.IOException;
import java.util.*;

public class BreadthFirstSearch {
    // BFS a partir de uma lista de adjacência
    public static void bfs(List<List<Edge>> adjacencyList, int numberVertices, int initialVertex) throws NullPointerException, IOException {
        // Fila
        Queue<Integer> queue = new LinkedList<>();
        // Array de vértices visitados
        boolean [] visited = new boolean[numberVertices+1];
        // Array dos pais de um vértice visitado
        int [] parent = new int[numberVertices+1];
        // Array de level de cada vértice visitado
        int [] level = new int[numberVertices+1];
        Arrays.fill(level, -1);

        level[initialVertex] = 0;

        queue.add(initialVertex);
        System.out.print("BFS saída: ");
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
                    queue.add(vertex);
                }
            }
        }
        SearchGraphOutput.writeFile(parent, level, numberVertices);
        System.out.println();
    }

    public static void bfs(int[][] adjacencyMatrix, int numberVertices, int initialVertex) throws NullPointerException, IOException {
        // Fila
        Queue<Integer> queue = new LinkedList<>();
        // Array de vértices visitados
        boolean [] visited = new boolean[numberVertices+1];
        // Array dos pais de um vértice visitado
        int [] parent = new int[numberVertices+1];
        // Array de level de cada vértice visitado
        int [] level = new int[numberVertices+1];
        Arrays.fill(level, -1);

        level[initialVertex] = 0;

        queue.add(initialVertex);
        System.out.print("BFS saída: ");
        while(!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");
            visited[u] = true;
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if(adjacencyMatrix[u][i] != 0 && !visited[i]) {
                    parent[i] = u;
                    visited[i] = true;
                    level[i] = level[u] + 1;
                    queue.add(i);
                }
            }
        }
        SearchGraphOutput.writeFile(parent, level, numberVertices);
        System.out.println();
    }
}
