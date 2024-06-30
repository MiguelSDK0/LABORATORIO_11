/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphlink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import linkedlist.LinkedList;
import linkedlist.Node;

/**
 *
 * @author Miguel
 */
public class GraphLink<E> {
    protected LinkedList<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new LinkedList<Vertex<E>>();
    }

    public void insertVertex(E data) {
        if (listVertex.search(new Vertex<>(data)) != -1) {
            System.out.println("El vertice ya existe");
            return;
        }
        Vertex<E> newVertex = new Vertex<>(data);
        listVertex.insertLast(newVertex);
    }

    public void insertEdge(E verOri, E verDes) {
        int oriIndex = listVertex.search(new Vertex<>(verOri));
        int desIndex = listVertex.search(new Vertex<>(verDes));

        Vertex<E> vOri = null;
        if(oriIndex != -1){
            vOri = listVertex.getNth(oriIndex);
        }

        Vertex<E> vDes = null;
        if(desIndex != -1){
            vDes = listVertex.getNth(desIndex);
        }

        if (vOri != null && vDes != null) {
            Edge<E> nuevaArista = new Edge<>(vDes);
            vOri.listAdj.insertLast(nuevaArista);
            Edge<E> nuevaArista2 = new Edge<>(vOri);
            vDes.listAdj.insertLast(nuevaArista2);
        }
    }

    public boolean searchVertex(E v) {
        return listVertex.search(new Vertex<>(v)) != -1;
    }

    public boolean searchEdge(E v, E z) {
        int vIndex = listVertex.search(new Vertex<>(v));
        int zIndex = listVertex.search(new Vertex<>(z));

        if (vIndex == -1 || zIndex == -1) {
            return false;
        }

        Vertex<E> vVertex = listVertex.getNth(vIndex);
        Vertex<E> zVertex = listVertex.getNth(zIndex);

        if (vVertex != null) {
            int edgeIndex = vVertex.listAdj.search(new Edge<>(zVertex));
            return edgeIndex != -1;
        }

        return false;
    }

    public void removeVertex(E v) {
        int vIndex = listVertex.search(new Vertex<>(v));
        if (vIndex == -1) {
            return;
        }
        Vertex<E> vVertex = listVertex.getNth(vIndex);

        Node<Edge<E>> currentEdgeNode = vVertex.listAdj.first;
        while (currentEdgeNode != null) {
            Edge<E> edge = currentEdgeNode.getData();
            Vertex<E> connectedVertex = edge.getRefDest();

            connectedVertex.listAdj.removeNode(new Edge<>(vVertex));

            currentEdgeNode = currentEdgeNode.getNext();
        }
        listVertex.deleteNth(vIndex);
    }


    public void removeEdge(E v, E z) {
        int vIndex = listVertex.search(new Vertex<>(v));
        if (vIndex == -1) {
            return;
        }

        Vertex<E> vVertex = listVertex.getNth(vIndex);

        int zIndex = listVertex.search(new Vertex<>(z));
        if (zIndex == -1) {
            return;
        }

        Vertex<E> zVertex = listVertex.getNth(zIndex);

        vVertex.listAdj.removeNode(new Edge<>(zVertex));
    }

    public void dfs(E v) {
        resetVisited();
        int vIndex = listVertex.search(new Vertex<>(v));
        if (vIndex != -1) {
            Vertex<E> startVertex = listVertex.getNth(vIndex);
            dfsRecursive(startVertex);
        }
    }

    private void dfsRecursive(Vertex<E> vertex) {
        vertex.setVisited(true);
        System.out.print(vertex.getData() + " ");

        for (Edge<E> edge : vertex.listAdj) {
            Vertex<E> oppositeVertex = edge.getRefDest();
            if (!oppositeVertex.isVisited()) {
                dfsRecursive(oppositeVertex);
            }
        }
    }

    private void resetVisited() {
        for (int i = 0; i < listVertex.length(); i++) {
            Vertex<E> vertex = listVertex.getNth(i);
            vertex.setVisited(false);
        }
    }
    
    public void bfs(E v) {
        resetVisited();
        
        int vIndex = listVertex.search(new Vertex<>(v));
        if (vIndex == -1) {
            return;
        }

        Vertex<E> startVertex = listVertex.getNth(vIndex);
        Queue<Vertex<E>> queue = new java.util.LinkedList<>();
        queue.add(startVertex);
        startVertex.setVisited(true);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            System.out.print(current.getData() + " ");

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> oppositeVertex = edge.getRefDest();
                if (!oppositeVertex.isVisited()) {
                    oppositeVertex.setVisited(true);
                    queue.add(oppositeVertex);
                }
            }
        }
    }

    public List<E> bfsPath(E start, E end) {
        resetVisited();

        int startIndex = listVertex.search(new Vertex<>(start));
        int endIndex = listVertex.search(new Vertex<>(end));

        if (startIndex == -1 || endIndex == -1) {
            return null;
        }

        Vertex<E> startVertex = listVertex.getNth(startIndex);
        Vertex<E> endVertex = listVertex.getNth(endIndex);

        Queue<Vertex<E>> queue = new java.util.LinkedList<>();
        Map<Vertex<E>, Vertex<E>> predecessors = new HashMap<>();
        queue.add(startVertex);
        startVertex.setVisited(true);
        predecessors.put(startVertex, null);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();

            if (current.equals(endVertex)) {
                List<E> path = new ArrayList<>();
                for (Vertex<E> at = endVertex; at != null; at = predecessors.get(at)) {
                    path.add(at.getData());
                }
                Collections.reverse(path);
                return path;
            }

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> oppositeVertex = edge.getRefDest();
                if (!oppositeVertex.isVisited()) {
                    oppositeVertex.setVisited(true);
                    queue.add(oppositeVertex);
                    predecessors.put(oppositeVertex, current);
                }
            }
        }
        return null;
    }
    
    public void insertEdgeWeight(E verOri, E verDes, int weight) {
        int oriIndex = listVertex.search(new Vertex<>(verOri));
        int desIndex = listVertex.search(new Vertex<>(verDes));

        Vertex<E> vOri = oriIndex != -1 ? listVertex.getNth(oriIndex) : null;
        Vertex<E> vDes = desIndex != -1 ? listVertex.getNth(desIndex) : null;

        if (vOri != null && vDes != null) {
            Edge<E> newEdge1 = new Edge<>(vDes, weight);
            Edge<E> newEdge2 = new Edge<>(vOri, weight);
            vOri.listAdj.insertLast(newEdge1);
            vDes.listAdj.insertLast(newEdge2);
        }
    }
    
    public List<E> shortPath(E start, E end) {
        resetVisited();

        int startIndex = listVertex.search(new Vertex<>(start));
        int endIndex = listVertex.search(new Vertex<>(end));

        if (startIndex == -1 || endIndex == -1) {
            return null;
        }

        Vertex<E> startVertex = listVertex.getNth(startIndex);
        Vertex<E> endVertex = listVertex.getNth(endIndex);

        Map<Vertex<E>, Integer> distances = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> predecessors = new HashMap<>();
        PriorityQueue<Vertex<E>> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (int i = 0; i < listVertex.length(); i++) {
            Vertex<E> vertex = listVertex.getNth(i);
            distances.put(vertex, Integer.MAX_VALUE);
            predecessors.put(vertex, null);
        }

        distances.put(startVertex, 0);
        pq.add(startVertex);

        while (!pq.isEmpty()) {
            Vertex<E> current = pq.poll();

            if (current.equals(endVertex)) {
                List<E> path = new ArrayList<>();
                for (Vertex<E> at = endVertex; at != null; at = predecessors.get(at)) {
                    path.add(at.getData());
                }
                Collections.reverse(path);
                return path;
            }

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> oppositeVertex = edge.getRefDest();
                int newDist = distances.get(current) + edge.getWeight();
                if (newDist < distances.get(oppositeVertex)) {
                    distances.put(oppositeVertex, newDist);
                    predecessors.put(oppositeVertex, current);
                    pq.add(oppositeVertex);
                }
            }
        }
        return null;
    }
    
    @Override
    public String toString(){
        return this.listVertex.toString();
    }
}