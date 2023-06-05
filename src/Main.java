import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String path = "src/grafo_1.txt";
        Graph graph = new Graph();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while(line != null) {
                String[] parts = line.split(" ");
                line = br.readLine();

                if(parts.length < 2) {
                    graph = new Graph();
                } else {
                    int source = Integer.parseInt(parts[0]);
                    int dest = Integer.parseInt(parts[1]);
                    int weight = Integer.parseInt(parts[2]);

                    graph.addEdge(source, dest, weight);
                }

                line = br.readLine();
            }
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        graph.printAdjacencyList();
        graph.exportAdjacencyList();
    }
}