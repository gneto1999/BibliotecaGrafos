import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        String path = "";

        try {
            int option;
            do {
                showMenu();
                System.out.print("\nOpção: ");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 0:
                        System.out.println("Saindo do Programa...");
                        Graph.exportGraphData();
                        break;
                    case 1:
                        System.out.print("Caminho do arquivo: ");
                        path = sc.nextLine();
                        break;
                    case 2:
                        System.out.println("1 - Matriz de Adjacência com peso");
                        System.out.println("2 - Matriz de Adjacência sem peso");
                        System.out.println("3 - Lista de Adjacência com peso");
                        System.out.println("4 - Lista de Adjacência sem peso");
                        System.out.println();
                        System.out.print("Opção: ");
                        option = sc.nextInt();

                        sc.nextLine();

                        if(option == 1) {
                            adjacencyMatrixWeight(path);
                            Graph.printAdjacencyMatrix();
                        } else if(option == 2) {
                            adjacencyMatrix(path);
                            Graph.printAdjacencyMatrix();
                        } else if(option == 3) {
                            adjacencyListWeight(path);
                            Graph.printAdjacencyList();
                        } else if(option == 4) {
                            adjacencyList(path);
                            Graph.printAdjacencyList();
                        } else {
                            option = 100;
                        }
                        break;
                    case 3:
                        System.out.println("1 - BFS");
                        System.out.println("2 - DFS");
                        System.out.print("Opção: ");
                        option = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Vértice inicial: ");
                        int initialVertex = sc.nextInt();

                        if(option == 1) {
                            BreadthFirstSearch.bfs(
                                    Graph.getAdjacencyList(),
                                    Graph.getAdjacencyMatrix(),
                                    Graph.getNumberVertices(),
                                    initialVertex);
                        } else if(option == 2) {
                            DepthFirstSearch.dfs(
                                    Graph.getAdjacencyList(),
                                    Graph.getAdjacencyMatrix(),
                                    Graph.getNumberVertices(),
                                    initialVertex);
                        }
                        break;
                    case 4:
                        adjacencyMatrix(path);
                        Graph.findConnectedComponents(Graph.getAdjacencyList());
                        break;
                    case 5:
                        Prim.primMst(Graph.getAdjacencyList(), Graph.getAdjacencyMatrix(), Graph.getNumberVertices());
                        break;
                    case 6:
                        System.out.println("\n1 - BFS");
                        System.out.println("2 - Dijkstra\n");
                        System.out.print("Opção: ");
                        option = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Vértice incial: ");
                        initialVertex = sc.nextInt();

                        System.out.println();

                        if(option == 1) {
                            BreadthFirstSearch.bfs(
                                    Graph.getAdjacencyList(),
                                    Graph.getAdjacencyMatrix(),
                                    Graph.getNumberVertices(),
                                    initialVertex);
                        } else if(option == 2) {
                            DijkstraAlgorithm.dijkstra(Graph.getAdjacencyList(), Graph.getAdjacencyMatrix(), Graph.getNumberVertices(), initialVertex);
                        }
                        break;
                    default:
                        System.out.println("Opção incorreta!");
                }
            } while (option != 0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }

    }

    static void showMenu() {
        System.out.println("-------------- Biblioteca Grafos --------------" );
        System.out.println("1 - Arquivo de entrada");
        System.out.println("2 - Representação do Grafo");
        System.out.println("3 - Busca em Grafos");
        System.out.println("4 - Componentes Conexos");
        System.out.println("5 - Árvore geradora mínima MST");
        System.out.println("6 - Distância e Caminho Mínimo");
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