/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphlink;

import linkedlist.LinkedList;

/**
 *
 * @author User
 */
public class Vertex<E> {
    private E data;
    protected LinkedList<Edge<E>> listAdj;
    private boolean visited;

    public Vertex(E data) {
        this.data = data;
        this.listAdj = new LinkedList<Edge<E>>();
        this.visited = false;
    }

    public E getData() {
        return data;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return data.equals(vertex.data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public String toString() {
        return this.data + "--> " + this.listAdj.toString() + "\n";
    }
}
