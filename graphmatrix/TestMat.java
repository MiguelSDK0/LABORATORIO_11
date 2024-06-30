/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphmatrix;

/**
 *
 * @author Miguel
 */
public class TestMat {
    public static void main(String[] args) {
        GraphMat<String> graph = new GraphMat<>(10);
        
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");
        graph.insertEdge("D", "E");

        graph.printAdjMatrix();

        System.out.println("DFS desde el vertice A:");
        graph.dfs("A");

        System.out.println("");
        System.out.println("Buscar vertice A: " + graph.searchVertex("A"));
        System.out.println("Buscar la arista A -> B: " + graph.searchEdge("A", "B"));
    }
}
