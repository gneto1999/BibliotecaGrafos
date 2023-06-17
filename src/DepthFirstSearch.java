import java.io.IOException;
import java.util.*;

public class DepthFirstSearch {

    public static void dfs(List<List<Edge>> adjacencyList, int numberVertices , int initialVertex) throws NullPointerException, IOException {
        Stack<Integer> stack = new Stack<>();

        boolean [] visited = new boolean[numberVertices+1];
        int [] parent = new int[numberVertices+1];
        int [] level = new int[numberVertices+1];

        stack.push(initialVertex);
        Arrays.fill(level, -1);
        level[initialVertex] = 0;

        System.out.print("DFS saída: ");
        while(!stack.isEmpty()) {
            int currentVertex = stack.pop();
            if (!visited[currentVertex]) {
                System.out.print(currentVertex + " ");

                visited[currentVertex] = true;

                List<Edge> neighbors = adjacencyList.get(currentVertex);

                for (Edge neighbor : neighbors) {
                    int vertex = neighbor.getVertex();

                    if (!visited[vertex]) {
                        parent[currentVertex] = vertex;
                        level[vertex] = level[currentVertex] + 1;
                        stack.push(vertex);
                    }
                }
            }
        }
        SearchGraphOutput.writeFile(parent, level, numberVertices);
        System.out.println();
    }

    public static void dfs(int[][] adjacencyMatrix, int numberVertices , int initialVertex) throws NullPointerException, IOException {
        Stack<Integer> stack = new Stack<>();

        boolean [] visited = new boolean[numberVertices+1];
        int [] parent = new int[numberVertices+1];
        int [] level = new int[numberVertices+1];

        stack.push(initialVertex);
        Arrays.fill(level, -1);
        level[initialVertex] = 0;

        System.out.print("DFS saída: ");
        while(!stack.isEmpty()) {
            int currentVertex = stack.pop();
            if (!visited[currentVertex]) {
                System.out.print(currentVertex + " ");

                visited[currentVertex] = true;

                for (int vertex = 1; vertex <= numberVertices; vertex++) {
                    if (adjacencyMatrix[currentVertex][vertex] == 1 && !visited[vertex]) {
                        parent[currentVertex] = vertex;
                        level[vertex] = level[currentVertex] + 1;
                        stack.push(vertex);
                    }
                }
            }
        }
        SearchGraphOutput.writeFile(parent, level, numberVertices);
        System.out.println();
    }
}
