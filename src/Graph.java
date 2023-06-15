import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private static List<List<Edge>> adjacencyList;
    private static int [][] adjacencyMatrix;
    private static int numberVertices;

    public static void buildAdjacencyList(int numVertices){
        numberVertices = numVertices;

        adjacencyList = new ArrayList<>(numVertices+1);

        for(int i = 0; i <= numVertices; i++){
            adjacencyList.add(new ArrayList<>());
        }
    }

    public static void buildAdjacencyMatrix(int numVertices){
        numberVertices = numVertices;
        adjacencyMatrix = new int[numVertices+1][numVertices+1];
    }

    public static int getNumberVertices() {
        return numberVertices;
    }

    public static List<List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public static int [][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public static void addEdgeInAdjacencyList(int vertex1, int vertex2, int weight){
        adjacencyList.get(vertex1).add(new Edge(vertex2, weight));
        adjacencyList.get(vertex2).add(new Edge(vertex1, weight));
    }

    public static void addEdgeInAdjacencyList(int vertex1, int vertex2){
        adjacencyList.get(vertex1).add(new Edge(vertex2));
        adjacencyList.get(vertex2).add(new Edge(vertex1));
    }

    public static void addEdgeInAdjacencyMatrix(int vertex1, int vertex2, int weight)  {
        adjacencyMatrix[vertex1][vertex2] = weight;
        adjacencyMatrix[vertex2][vertex1] = weight;
    }

    public static void addEdgeInAdjacencyMatrix(int vertex1, int vertex2) {
        adjacencyMatrix[vertex1][vertex2] = 1;
        adjacencyMatrix[vertex2][vertex1] = 1;
    }

    private static int edgesAmount() {
        int amount = 0;
        if(adjacencyList != null && adjacencyMatrix == null) {
            for (List<Edge> edges : adjacencyList) {
                amount += edges.size();
            }
            return amount/2;
        }

        for (int i = 0; i < adjacencyMatrix.length-1; i++) {
            for (int j = i+1; j < adjacencyMatrix.length; j++) {
                if(adjacencyMatrix[i][j] != 0) {
                    amount++;
                }
            }
        }

        return amount;
    }

    private static int [] degreesVertex() {
        int [] degrees = new int [numberVertices +1];

        if(adjacencyList != null) {
            for (int i = 0; i < adjacencyList.size(); i++) {
                degrees[i] = adjacencyList.get(i).size();
            }

            return degrees;
        }

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if(adjacencyMatrix[i][j] != 0) {
                    degrees[i] += 1;
                }
            }
        }

        return degrees;
    }

    private static double [] empiricalDistribution(int [] degrees) {
        int [] frequency = new int [numberVertices];
        double [] distribution = new double[numberVertices +1];

        for (int i = 0; i < numberVertices; i++) {
            frequency[degrees[i]] += 1;
        }

        for (int i = 0; i < numberVertices; i++) {
            distribution[i] += (double) frequency[i] / numberVertices;
        }

        return distribution;
    }

    public static void exportGraphData() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("output\\saida.txt"))){
            int edgAmount = edgesAmount();
            double avgDegree = (double) (edgAmount * 2) / numberVertices;
            bw.write("# n = " + numberVertices);
            bw.newLine();
            bw.write("# m = " + edgAmount);
            bw.newLine();
            bw.write("#d_medio = " + String.format("%.2f", avgDegree));
            bw.newLine();

            int [] degrees = degreesVertex();
            double [] empiricalDist = empiricalDistribution(degrees);

            for (int i = 1; i < empiricalDist.length; i++) {
                bw.write((i) + " " + empiricalDist[i]);
                bw.newLine();
            }
            System.out.println("\nArquivo de saída do grafo gerado! Caminho: output/saida.txt");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void printAdjacencyList() {
        for(int i = 0; i < adjacencyList.size(); i++){
            System.out.print(i + " -> ");
            for(Edge edge: adjacencyList.get(i)){
                System.out.print(edge.getVertex() + "(" + edge.getWeight() + ") -> ");
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