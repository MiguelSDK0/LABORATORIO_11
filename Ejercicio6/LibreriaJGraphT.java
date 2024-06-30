package Ejercicio6;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import javax.swing.*;
import java.awt.*;

public class LibreriaJGraphT {
    private Graph<String, DefaultWeightedEdge> graph;

    public LibreriaJGraphT() {
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        addEdgeWithWeight("A", "B", 4.5);
        addEdgeWithWeight("B", "C", 2.8);
        addEdgeWithWeight("A", "C", 3.0);
        addEdgeWithWeight("A", "E", 18.0);
        addEdgeWithWeight("C", "F", 13.0);
        addEdgeWithWeight("D", "C", 7.0);

        createAndShowGUI();
    }

    private void addEdgeWithWeight(String source, String target, double weight) {
        DefaultWeightedEdge edge = graph.addEdge(source, target);
        if (edge != null) {
            graph.setEdgeWeight(edge, weight);
        }
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Uso de libreria de JGraphT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(getGraphString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private String getGraphString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-------- Lista de Vertices --------\n");
        for (String vertex : graph.vertexSet()) {
            sb.append("---> Vertice: " + vertex).append("\n");
        }

        sb.append("\n-------------- Aristas --------------\n");
        for (DefaultWeightedEdge edge : graph.edgeSet()) {
            sb.append("--->(" + graph.getEdgeSource(edge)).append(" - ").append(graph.getEdgeTarget(edge)).append(") : ").append(graph.getEdgeWeight(edge)).append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibreriaJGraphT());
    }
}

