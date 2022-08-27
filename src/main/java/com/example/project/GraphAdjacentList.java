package com.example.project;

import java.util.ArrayList;

public class GraphAdjacentList implements Graph {

    private ArrayList<Vertex> vertices;
    private int numVertices;

    public GraphAdjacentList() {
        vertices = new ArrayList<>();
    }

    // Agregar una arista desde un vertice 'from' a un vertice 'to'
    public boolean addEdge(int from, int to) {
        Vertex fromV = null, toV = null;
        for (Vertex v : vertices) {
            if (from == v.data) { // verificando si 'from' existe
                fromV = v;
            } else if (to == v.data) { // verificando si 'to' existe
                toV = v;
            }
            if (fromV != null && toV != null) {
                break; // ambos nodos deben existir, si no paramos
            }
        }
        if (fromV == null) {
            fromV = new Vertex(from);
            vertices.add(fromV);
            numVertices++;
        }
        if (toV == null) {
            toV = new Vertex(to);
            vertices.add(toV);
            numVertices++;
        }        
        return fromV.addAdjacentVertex(toV);
    }

    // Eliminamos la arista del vertice 'from' al vertice 'to'
    public boolean removeEdge(int from, int to) {
        Vertex fromV = null;
        for (Vertex v : vertices) {
            if (from == v.data) {
                fromV = v;
                break;
            }
        }
        if (fromV == null) {
            return false;
        }
        return fromV.removeAdjacentVertex(to);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices) {
            sb.append("Vertex: ");
            sb.append(v.data);
            sb.append("\n");
            sb.append("Adjacent vertices: ");
            for (Vertex v2 : v.adjacentVertices) {
                sb.append(v2.data);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getNumEdges(){
        int count = 0;
        for(int i = 0; i < this.vertices.size(); i++){
            count += this.vertices.get(i).adjacentVertices.size();
        }
        return count;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public int countConnectedComponents(){
        return -1;
    }

    public boolean removeVertex(int vertex){
	    Vertex verticeTemp = null;
		int indice = 0;
		// Recorremos los vertices del grafo
		for (int i = 0; i < numVertices; i++) {
			// Encontramos la posicion de "vertex"
			verticeTemp = vertices.get(i);
			// Guardamos el indice encontrado
			if (vertex == this.vertices.get(i).data)
				indice = i;

		}
	    	//En caso no haya el vertice devuelve "false"
		if (verticeTemp == null)
			return false;
		// Removemos los adyacentes
		for (int j = 0; j < verticeTemp.adjacentVertices.size(); j++) {
			if (vertex == verticeTemp.adjacentVertices.get(j).data)
				verticeTemp.removeAdjacentVertex(vertex);
		}

		// Removemos el vertice
		vertices.remove(indice);

		numVertices--;

		return true;
    }
    public ArrayList<Vertex> depthFirstSearch(Vertex n) {
        return this.depthFirstSearch(n, new ArrayList<Vertex>());
    }

    public ArrayList<Vertex> depthFirstSearch(Vertex n, ArrayList<Vertex> visited) {
        visited.add(n);
        for (Vertex vertex : n.adjacentVertices) { 
            if (!vertices.contains(vertex)) {
                depthFirstSearch(n, visited); 
            }
        }
        return visited;
    }
    public static void main(String args[]) {
        GraphAdjacentList graph = new GraphAdjacentList();
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);        
        System.out.println(graph);
    }
}
