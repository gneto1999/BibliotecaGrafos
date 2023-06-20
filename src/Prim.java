import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Prim {
    public static void primMst(List<List<Edge>> adjacencyList, int [][] adjacencyMatrix, int numVertices) throws IOException {
        initialize(adjacencyList, adjacencyMatrix, numVertices);
    }

    private static void initialize(List<List<Edge>> adjacencyList, int [][] adjacencyMatrix, int numVertices) throws IOException {
        int [] key = new int[numVertices];
        Integer [] parent = new Integer[numVertices];
        boolean [] visited = new boolean[numVertices];

        // Inicializa a chava com infinito, os pais com null e vertices visitados com false
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, null);
        Arrays.fill(visited, false);

        // Considerando o vertice 0 como a raiz
        key[0] = 0;

        // Fila de prioridade dos vertices
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(numVertices, Comparator.comparingInt(v -> v.getKey()));
        priorityQueue.add(new Vertex(0, 0));

        // Add no min heap os vertices com suas respectivas chaves
        for(int i = 1; i < numVertices; i++) {
            priorityQueue.add(new Vertex(i, key[i]));
        }

        if(adjacencyList != null) {
            mst(visited, parent, key, priorityQueue, adjacencyList);
        } else {
            mst(visited, parent, key, priorityQueue, adjacencyMatrix);
        }

        writeFile(parent, key, numVertices);
    }

    private static void mst(boolean [] visited, Integer [] parent, int [] key, PriorityQueue<Vertex> priorityQueue, int [][] adjacencyMatrix) {
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
                if(!visited[adjVertex] && adjacencyMatrix[u][adjVertex] < key[adjVertex]){
                    priorityQueue.remove(new Vertex(adjVertex, key[adjVertex]));
                    parent[adjVertex] = u;
                    key[adjVertex] = adjacencyMatrix[u][adjVertex];
                    priorityQueue.add(new Vertex(adjVertex, key[adjVertex]));
                }
            }
        }
    }
    private static void mst(boolean [] visited, Integer [] parent, int [] key, PriorityQueue<Vertex> priorityQueue, List<List<Edge>> adjacencyList) {
        while(!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll().getVertex();
            List<Edge> adjVertices = adjacencyList.get(u);
            visited[u] = true;

            for (Edge adjVertex : adjVertices) {
                if(!visited[adjVertex.getVertex()] && adjVertex.getWeight() < key[adjVertex.getVertex()]){
                    priorityQueue.remove(new Vertex(adjVertex.getVertex(), key[adjVertex.getVertex()]));
                    parent[adjVertex.getVertex()] = u;
                    key[adjVertex.getVertex()] = adjVertex.getWeight();
                    priorityQueue.add(new Vertex(adjVertex.getVertex(), key[adjVertex.getVertex()]));
                }
            }
        }
    }

    private static void writeFile(Integer [] parent, int [] key, int numberVertices) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("output\\mst.txt"));
        bw.write(" Aresta  Peso");
        for (int i = 1; i < numberVertices; i++) {
            bw.newLine();
            bw.write(parent[i] + " <--> " + i + ", " + key[i]);
        }
        System.out.println("\nArquivo de saída da Árvore Geradora Mínima gerado! Caminho: output/mst.txt");
        bw.close();
    }
}
