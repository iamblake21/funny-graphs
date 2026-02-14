package graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class DFS {


    public void dfs (int seed, Graph g, Set<Integer> alreadyVisited) {
        alreadyVisited.add(seed);
        for (Integer neighbor : g.getNeighbors(seed)) {
            if(!alreadyVisited.contains(neighbor)) {
                dfs(neighbor, g, alreadyVisited);
            }
        }
    }


    // Ti faccio notare che letterlamente bfs e dfs sono identici, 
    // cambia solo la struttura dati che usi per tenere traccia dei nodi da visitare, 
    // e il fatto che in dfs devi tenere traccia dei nodi già visitati,
    // altrimenti rischi di entrare in un ciclo infinito.

    public void bfs (int seed, Graph g) {
        Queue<Integer>  queue= new ArrayDeque<>(); // Inizializzo la coda
        Set<Integer> visited = new HashSet<>(); // Inizializzo il set
        queue.add(seed); // Aggiungo il seed alla coda, e lo segno come già visitato
        visited.add(seed);

        while (!queue.isEmpty()) { //Finchè non esaurisco la coda
            int current = queue.poll(); //Prendo il primo elemento.
            for (Integer n : g.getNeighbors(current)) { // Per ogni vicino di quello processato
                if (!visited.contains(n)) { // Di cui non ho ancora visitato i vicini
                    queue.add(n); // Lo metto in coda per scoprirne i vicini
                    visited.add(n); // E lo segno come già visitato, così da non metterlo più in coda
                }
                
            }
        }


    }
    

    public void iterativeDfs (int seed, Graph g) {
        Stack<Integer> stack = new Stack<>(); // Inizializzo lo stack.
        Set<Integer> alreadyVisited = new HashSet<>(); // Inizializzo il set
        stack.push(seed); //Metto un punto di partenza
        while (!stack.isEmpty()) { //Finchè lo stack non è vuoto
            int current = stack.pop(); // Prendo il primo elemento
            if (!alreadyVisited.contains(current)) { // Se non l'ho mai visitato
            alreadyVisited.add(current); // Lo visito
            for (Integer integer : g.getNeighbors(current)) { //A quel punto prendo ogni suo vicino
            if (!alreadyVisited.contains(integer)){ // E se non l'ho mai visitatow
                    stack.push(integer); // Lo metto nello stack per essere visitato
                }
            }
            }
        }
    }
}

    

