import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SearchGraphOutput {
    public static void writeFile(int [] parent, int [] level, int numberVertices) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("output\\search-graph-output.txt"));
        for (int i = 1; i <= numberVertices; i++) {
            bw.write("Vértice: " + i + ", Pai: " + parent[i] + ", Level: " + level[i]);
            bw.newLine();
        }
        System.out.println("\nArquivo de saída da busca em grafo gerado! Caminho: output/search-graph-output.txt");
        bw.close();
    }
}