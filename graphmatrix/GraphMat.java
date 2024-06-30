/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphmatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Miguel
 */

public class GraphMat<E> {
    private final Map<E, Integer> vertices;
    private final List<E> verticesList;
    private boolean[][] adjMatrix;
    private int vertexCount;

    public GraphMat(int maxVertices) {
        vertices = new HashMap<>();
        verticesList = new ArrayList<>();
        adjMatrix = new boolean[maxVertices][maxVertices];
        vertexCount = 0;
    }

    public void insertVertex(E v) {
        if (!vertices.containsKey(v)) {
            vertices.put(v, vertexCount);
            verticesList.add(v);
            vertexCount++;
        } else {
            System.out.println("El vertice ya existe");
        }
    }

    public void insertEdge(E v, E z) {
        Integer vIndex = vertices.get(v);
        Integer zIndex = vertices.get(z);

        if (vIndex != null && zIndex != null) {
            adjMatrix[vIndex][zIndex] = true;
            adjMatrix[zIndex][vIndex] = true;
        } else {
            System.out.println("El vertice no existe");
        }
    }

    public boolean searchVertex(E v) {
        return vertices.containsKey(v);
    }

    public boolean searchEdge(E v, E z) {
        Integer vIndex = vertices.get(v);
        Integer zIndex = vertices.get(z);

        if (vIndex != null && zIndex != null) {
            return adjMatrix[vIndex][zIndex];
        } else {
            System.out.println("El vertice no existe");
            return false;
        }
    }

    public void dfs(E v) {
        Integer startIndex = vertices.get(v);
        if (startIndex == null) {
            System.out.println("El vertice no existe");
            return;
        }

        boolean[] visited = new boolean[vertexCount];
        dfsRecursive(startIndex, visited);
    }

    private void dfsRecursive(int vIndex, boolean[] visited) {
        visited[vIndex] = true;
        System.out.print(verticesList.get(vIndex) + " ");

        for (int i = 0; i < vertexCount; i++) {
            if (adjMatrix[vIndex][i] && !visited[i]) {
                dfsRecursive(i, visited);
            }
        }
    }

    public void printAdjMatrix() {
        System.out.println("Matriz de adyacencia:");
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                System.out.print((adjMatrix[i][j] ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }
}