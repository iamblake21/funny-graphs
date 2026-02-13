package graph;

import java.util.*;

/**
 * Classe semplice per rappresentare un grafo usando una adjacency list.
 * Nodi identificati con interi (es. 1,2,3...).
 */
public class Graph {
    private Map<Integer, List<Integer>> listaDiAdiacenza = new HashMap<>();

    public void addEdge(Integer nodo, Integer nodo1) {
        listaDiAdiacenza.computeIfAbsent(nodo, k -> new ArrayList<Integer>()).add(nodo1);
        listaDiAdiacenza.computeIfAbsent(nodo1, k -> new ArrayList<Integer>()).add(nodo);
    }

    public List<Integer> getNeighbors(Integer k) {
        List<Integer> result = (listaDiAdiacenza.get(k) != null) ?  listaDiAdiacenza.get(k) : Collections.emptyList();
        return result;
    }



    
}
