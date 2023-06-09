import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = "";

        int answer;
        do {
            showMenu();
            System.out.print("\nAnswer: ");
            answer = sc.nextInt();
            sc.nextLine();

            switch (answer) {
                case 0:
                    System.out.println("Saindo do Programa...");
                    break;
                case 1:
                    System.out.print("Path (src\\arq.txt): ");
                    path = sc.nextLine();
                    break;
                case 2:
                    System.out.println("1 - Matriz de Adjacência com peso");
                    System.out.println("2 - Matriz de Adjacência sem peso");
                    System.out.println("3 - Lista de Adjacência com peso");
                    System.out.println("4 - Lista de Adjacência sem peso");
                    System.out.println();
                    System.out.print("Answer: ");
                    answer = sc.nextInt();
                    sc.nextLine();

                    if(answer == 1) {
                        adjacencyMatrixWeight(path);
                        Graph.printAdjacencyMatrix();
                    } else if(answer == 2) {
                        adjacencyMatrix(path);
                        Graph.printAdjacencyMatrix();
                    } else if(answer == 3) {
                        adjacencyListWeight(path);
                        Graph.printAdjacencyList();
                    } else {
                        adjacencyList(path);
                        Graph.printAdjacencyList();
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção incorreta!");
            }
        } while (answer != 0);
    }

    static void showMenu() {
        System.out.println("-------------- Biblioteca Grafos --------------");
        System.out.println("1 - Arquivo de entrada");
        System.out.println("2 - Representação do Grafo");
        System.out.println("3 - Busca em Grafos");
        System.out.println("4 - Componentes Conexos");
        System.out.println("0 - Sair do programa");
    }

    static void adjacencyListWeight(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] parts = line.split(" ");

                if (parts.length < 2) {
                    int numVertices = Integer.parseInt(parts[0]);
                    Graph.buildAdjacencyList(numVertices);
                } else {
                    int source = Integer.parseInt(parts[0]);
                    int dest = Integer.parseInt(parts[1]);
                    int weight = Integer.parseInt(parts[2]);
                    Graph.addEdgeInAdjacencyList(source, dest, weight);
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void adjacencyList(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] parts = line.split(" ");

                if (parts.length < 2) {
                    int numVertices = Integer.parseInt(parts[0]);
                    Graph.buildAdjacencyList(numVertices);
                } else {
                    int source = Integer.parseInt(parts[0]);
                    int dest = Integer.parseInt(parts[1]);
                    Graph.addEdgeInAdjacencyList(source, dest);
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void adjacencyMatrix(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] parts = line.split(" ");

                if (parts.length < 2) {
                    int numVertices = Integer.parseInt(parts[0]);
                    Graph.buildAdjacencyMatrix(numVertices);
                } else {
                    int source = Integer.parseInt(parts[0]);
                    int dest = Integer.parseInt(parts[1]);
                    Graph.addEdgeInAdjacencyMatrix(source, dest);
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void adjacencyMatrixWeight(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] parts = line.split(" ");

                if (parts.length < 2) {
                    int numVertices = Integer.parseInt(parts[0]);
                    Graph.buildAdjacencyMatrix(numVertices);
                } else {
                    int source = Integer.parseInt(parts[0]);
                    int dest = Integer.parseInt(parts[1]);
                    int weight = Integer.parseInt(parts[2]);
                    Graph.addEdgeInAdjacencyMatrix(source, dest, weight);
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}