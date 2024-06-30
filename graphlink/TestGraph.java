/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphlink;

import java.util.List;

/**
 *
 * @author Miguel
 */
public class TestGraph {
    public static void main(String[] args) {
        GraphLink<String> graph = new GraphLink<>();

//        graph.insertVertex("A");
//        graph.insertVertex("B");
//        graph.insertVertex("C");
//        graph.insertVertex("D");
//        graph.insertVertex("E");
//
//        graph.insertEdge("A", "B");
//        graph.insertEdge("A", "C");
//        graph.insertEdge("B", "D");
//        graph.insertEdge("C", "D");
//        graph.insertEdge("D", "E");
//
//        System.out.println(graph);
        
//        System.out.println("Buscar vertice A: " + graph.searchVertex("A"));
//        System.out.println("Buscar artista: C, B: " + graph.searchEdge("C", "D"));
//        
//        System.out.println("");
//        System.out.println("Eliminar vertice A:");
//        graph.removeVertex("A");
//        System.out.println(graph);
//        System.out.println("Eliminar aristas: B, D");
//        graph.removeEdge("B", "D");
//        System.out.println(graph);
        
//        System.out.println("");
//        System.out.println("DFS desde el vertice A:");
//        graph.dfs("A");
//        System.out.println("");
        
//        System.out.println("");
//        System.out.println("--- EJERCICIO 1 ---");
//        System.out.println("BFS desde el vertice A:");
//        graph.bfs("A");
//        System.out.println();
//        
//        System.out.println("Camino BFS desde A hasta E:");
//        List<String> path = graph.bfsPath("A", "E");
//        if (path != null) {
//            for (String vertex : path) {
//                System.out.print(vertex + " ");
//            }
//        } else {
//            System.out.println("No existe un camino desde A hasta E.");
//        }
//        System.out.println("");
//        
//        System.out.println("");
        System.out.println("--- EJERCICIO 2 ---");
        GraphLink<String> graph_2 = new GraphLink<>();
        graph_2.insertVertex("A");
        graph_2.insertVertex("B");
        graph_2.insertVertex("C");
        graph_2.insertVertex("D");
        graph_2.insertVertex("E");

        graph_2.insertEdgeWeight("A", "B", 4);
        graph_2.insertEdgeWeight("A", "C", 2);
        graph_2.insertEdgeWeight("B", "C", 5);
        graph_2.insertEdgeWeight("B", "D", 10);
        graph_2.insertEdgeWeight("C", "D", 3);
        graph_2.insertEdgeWeight("D", "E", 4);
        graph_2.insertEdgeWeight("C", "E", 8);

        System.out.println("Grafo inicial:");
        System.out.println(graph_2);

        System.out.println("Camino mas corto desde A hasta E:");
        List<String> path_2 = graph_2.shortPath("A", "E");
        if (path_2 != null) {
            for (String vertex : path_2) {
                System.out.print(vertex + " ");
            }
        } else {
            System.out.println("No existe un camino desde A hasta E.");
        }
        System.out.println("");
    }
}
