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
    // cambia solo la struttura dati che usi.


    // BFS:
    // Parto da A, segno A come visitato, e lo metto in coda per scoprirne i vicini.
    // Coda: [A]
    // A, visitato.
    // Finchè la coda non è vuota prendo il primo elemento e ne scopro i vicini.
    // Coda: []
    // Scopro i vicini di A, che sono B e C, e li metto in coda per scoprirne i vicini, se non li ho già visitati.
    // Coda: [B, C]
    // Prendo B, lo segno come visitato, e scopro i suoi vicini, che sono A, ma è già stato visitato, quindi non lo metto in coda.
    // Coda: [C]
    // Prendo C, lo segno come visitato, e scopro i suoi vicini, che sono A e D, ma A è già stato visitato, quindi non lo metto in coda, mentre D è nuovo, quindi lo metto in coda.
    // Coda: [D]
    // Prendo D, lo segno come visitato, e scopro i suoi vicini, che sono C, ma è già stato visitato, quindi non lo metto in coda.
    // Coda: []
    // Finito.
    // Praticamente, in coda ci sono sempre i nodi che ho scoperto ma di cui non ho ancora scoperto i vicini, 
    // e ogni volta che prendo un nodo dalla coda, lo segno come visitato, e scopro i suoi vicini, che se non li ho già visitati, li metto in coda per scoprirne i vicini dopo.

    // DFS:
    // Parto da A, segno A come visitato, e scopro i suoi vicini, che sono B e C, e li visito uno alla volta, se non li ho già visitati.
    // A, visitato.
    // Scopro i vicini di A, che sono B e C, e li visito uno alla volta, se non li ho già visitati.
    // Visito B, lo segno come visitato, e scopro i suoi vicini, che sono A, ma è già stato visitato, quindi non lo visito.
    // B, visitato.
    // Visito C, lo segno come visitato, e scopro i suoi vicini, che sono A e D, ma A è già stato visitato, quindi non lo visito, mentre D è nuovo, quindi lo visito.
    // C, visitato.
    // Visito D, lo segno come visitato, e scopro i suoi vicini, che sono C, ma è già stato visitato, quindi non lo visito.
    // D, visitato.
    // Finito.
    // Praticamente, in dfs, ogni volta che visiti un nodo, 
    // lo segni come visitato, e scopro i suoi vicini, che se non li hai già visitati,
    //  li visiti uno alla volta, e per ognuno di essi ripeti lo stesso processo,
    //  fino a quando non hai visitato tutti i nodi raggiungibili dal nodo di partenza.
    // Grafo
    //     A
    //    / \
    //   B   C
    //   |    |
    //    D   E



    // Un altra differenza sostanziale è il momento in cui segni come "visitato" un nodo.
    // Nella dfs, segni come visitato un nodo quando nel momento in cui lo visiti, mentre nella bfs, segni come visitato un nodo quando lo metti in coda per scoprirne i vicini dopo.
    // Cosa voglio dire:
    // Nella dfs, un nodo è visitato quando stai scoprendo i suoi vicini...
    // In questa bfs invece, un nodo è visitato quando viene scoperto come vicino di un nodo che stai visitando, e lo metti in coda per scoprirne i vicini dopo, così da evitare di metterlo più volte in coda, e quindi di visitarlo più volte, e rischiare di entrare in un ciclo infinito.
    // Ovviamente, non è detto che una bfs non possa essere implementata segnando come visitato un nodo quando lo visiti, ma c'è un rischio, che è quello di mettere più volte in coda lo stesso nodo, 
    // e quindi di visitarlo più volte, questo non causa loop infinito, ma sicuramente è inefficiente, perchè stai visitando più volte lo stesso nodo,
    //  e quindi stai facendo più lavoro del necessario.
    
    
    
    public void bfs (int seed, Graph g) {
        Queue<Integer>  queue= new ArrayDeque<>(); // Inizializzo la coda
        Set<Integer> visited = new HashSet<>(); // Inizializzo il set
        queue.add(seed); // Aggiungo il seed alla coda, e lo segno come già visitato
        visited.add(seed); // Perchè se non lo segno come già visitato, quando lo prendo dalla coda,
        //  e scopro i suoi vicini, li metto in coda, e quando li prendo dalla coda, non so se li ho già visitati o no, 
        // e rischio di visitarli più volte, rendendo l'algoritmo inefficiente.


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

    // Lo stack è una struttura dati che funziona come una pila,
    // ovvero, l'ultimo elemento inserito è il primo ad essere rimosso (LIFO - Last In, First Out).
    
    // La coda, invece, è una struttura dati che funziona come una fila,
    // ovvero, il primo elemento inserito è il primo ad essere rimosso (FIFO - First In, First Out)


        // Grafo
    //     A
    //    / \
    //   B   C
    //   |   |
    //   D   E

    

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

    

