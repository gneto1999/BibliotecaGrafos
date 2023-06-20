import java.io.IOException;
import java.util.*;

public class DepthFirstSearch {

    public static void dfs(List<List<Edge>> adjacencyList, int [][] adjacencyMatrix, int numberVertices , int initialVertex) throws NullPointerException, IOException {
        initialize(adjacencyList, adjacencyMatrix, numberVertices, initialVertex);
    }

    private static void initialize(List<List<Edge>> adjacencyList, int [][] adjacencyMatrix, int numberVertices, int initialVertex) throws IOException {
        Stack<Integer> stack = new Stack<>();

        boolean [] visited = new boolean[numberVertices+1];
        int [] parent = new int[numberVertices+1];
        int [] level = new int[numberVertices+1];

        stack.push(initialVertex);
        Arrays.fill(level, -1);
        level[initialVertex] = 0;

        System.out.print("DFS saída: ");

        if(adjacencyList != null) {
            searching(stack, adjacencyList, visited, parent, level);
        } else {
            searching(stack, adjacencyMatrix, numberVertices, visited, parent, level);
        }

        SearchGraphOutput.writeFile(parent, level, numberVertices);
        System.out.println();
    }

    private static void searching(Stack<Integer> stack, List<List<Edge>> adjacencyList, boolean [] visited, int [] parent, int [] level) {
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
    }

    private static void searching(Stack<Integer> stack, int [][] adjacencyMatrix, int numberVertices, boolean [] visited, int [] parent, int [] level) {
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
    }
}