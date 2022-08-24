package com.example.project;

import java.util.ArrayList;

public class GraphMatrix implements Graph {

    private int numVertices;
    private int[][] adjacency;

    public GraphMatrix(int numVertices) {
        this.numVertices = numVertices;
        this.adjacency = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                this.adjacency[i][j] = 0;
            }
        }
    }

    @Override
    public boolean addEdge(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            this.adjacency[from][to] = 1;
            this.adjacency[to][from] = 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            this.adjacency[from][to] = 0;
            this.adjacency[to][from] = 0;
            return true;
        }
        return false;
    }

    public boolean vertexDoesExist(int aVertex) {
        if (aVertex >= 0 && aVertex < this.numVertices) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Integer> depthFirstSearch(int n) {
        return this.depthFirstSearch(n, new ArrayList<Integer>());
    }

    public ArrayList<Integer> depthFirstSearch(int n, ArrayList<Integer> visited) {
        visited.add(n);
        for (int i = 0; i < this.numVertices; i++) {
            if (this.adjacency[n][i] == 1 && !visited.contains(i)) {
                depthFirstSearch(i, visited);
            }
        }
        return visited;
    }

    public String toString() {
        String s = "    ";
        for (int i = 0; i < this.numVertices; i++) {
            s = s + String.valueOf(i) + " ";
        }
        s = s + " \n";

        for (int i = 0; i < this.numVertices; i++) {
            s = s + String.valueOf(i) + " : ";
            for (int j = 0; j < this.numVertices; j++) {
                s = s + String.valueOf(this.adjacency[i][j]) + " ";
            }
            s = s + "\n";
        }
        return s;
    }

    public int countConnectedComponents() {
    	//inicio un contador para verificar la cantidad de componentes
    	int cont = 0;
    	
    	//Creo un nuevo array para almacenar la misma cantidad de vertices
    	ArrayList<Integer> arr = new ArrayList<Integer>();
    	
    	//En "arr" almaceno los vertices desde un "0" hasta un "numVertices-1"
    	for(int i = 0; i<this.numVertices;i++) {
    		arr.add(i);
    	}
    	
    	//Este "do while" itera hasta que "arr" este vacio,
    	//y esto se da cuando se termina de remover todos los vertices 
    	//de los subgrafos que ya se visitaron
    	do {
    		
    		//En "componenteX" almaceno el subgrafo encontrado ó 
    		//el grafo de un solo componente según sea el caso
	    	ArrayList<Integer> componenteX = depthFirstSearch(arr.get(0));
	    	
	    	//Conforme voy encontrando subgrafos, los remuevo para quedarme solo
	    	//Con los vertices que aún no se han visitado
	    	for(int j = 0; j<componenteX.size(); j++) 
	    		arr.remove(0);
	    	cont++;
	    	
	    }
    	//Termina cuando el grafo inicial queda vacio
    	//Lo cual indica que ya no hay mas vertices por visitar
    	while(arr.size()!=0);
    	
    	return cont;
    }

    public static void main(String args[]) {
        GraphMatrix graph = new GraphMatrix(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        System.out.println("The graph matrix:");
        System.out.println(graph);
        System.out.println("DFS:");
        System.out.println(graph.depthFirstSearch(0));
    }

}
