import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private static List<List<Edge>> adjacencyList;
    private static int [][] adjacencyMatrix;

    public static void buildAdjacencyList(int numVertices){
        adjacencyList = new ArrayList<>(numVertices);

        for(int i = 0; i <= numVertices; i++){
            adjacencyList.add(new ArrayList<>());
        }
    }

    public static void buildAdjacencyMatrix(int numVertices){
        adjacencyMatrix = new int[numVertices+1][numVertices+1];
    }

    public static void addEdgeInAdjacencyList(int vertex1, int vertex2, int weight){
        adjacencyList.get(vertex1).add(new Edge(vertex2, weight));
        adjacencyList.get(vertex2).add(new Edge(vertex1, weight));
    }

    public static void addEdgeInAdjacencyList(int vertex1, int vertex2){
        adjacencyList.get(vertex1).add(new Edge(vertex2));
        adjacencyList.get(vertex2).add(new Edge(vertex1));
    }

    public static void addEdgeInAdjacencyMatrix(int vertex1, int vertex2, int weight) {
        adjacencyMatrix[vertex1][vertex2] = weight;
        adjacencyMatrix[vertex2][vertex1] = weight;
    }

    public static void addEdgeInAdjacencyMatrix(int vertex1, int vertex2) {
        adjacencyMatrix[vertex1][vertex2] = 1;
        adjacencyMatrix[vertex2][vertex1] = 1;
    }

    public static void exportAdjacencyList() {
        try(PrintWriter writer = new PrintWriter(new FileWriter("saida.txt"))){
            for(int i = 0; i < adjacencyList.size(); i++){
                writer.print(i + ": ");
                for(Edge edge: adjacencyList.get(i)){
                    writer.print(edge.vertex + "(" + edge.weight + ") ");
                }
                writer.println();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void printAdjacencyList() {
        for(int i = 0; i < adjacencyList.size(); i++){
            System.out.print(i + " -> ");
            for(Edge edge: adjacencyList.get(i)){
                System.out.print(edge.vertex + "(" + edge.weight + ") -> ");
            }
            System.out.println();
        }
    }

    public static void printAdjacencyMatrix() {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
