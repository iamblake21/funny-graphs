package graph;

import java.util.*;
/**
 * Implementazione BFS (Breadth-First Search) che restituisce l'ordine di visita.
 */
public class BFS {




    public static List<Integer> getShortestPath(int start, int target, Graph g) {
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<Integer>();
        Map<Integer,Integer> parentMap = new HashMap<>();
        queue.add(start);
        visited.add(start);
        if (start == target) {
            return List.of(start);
        }
        while (!queue.isEmpty()) {
            int current = queue.poll();
            List<Integer> neighbors = g.getNeighbors(current);
            for (Integer neighbor : neighbors) {
                if (!visited.contains(neighbor)){
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    if (neighbor == target) {
                        List<Integer> path = new ArrayList<>();
                        Integer toCheck = target;
                        if (!parentMap.containsKey(target))
                            return null;
                        while (toCheck != null) {
                            path.add(toCheck);
                            toCheck = parentMap.get(toCheck);
                        }
                        return path.reversed();
                    }
                }
            }
        }
        return null;

    }



    public static Map<Integer, Integer> getParent(Graph g, int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer,Integer> parentMap = new HashMap<>();
        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            List<Integer> neighbors = g.getNeighbors(current);
            for (Integer neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                        parentMap.put(neighbor, current);
                }   
            }
        }
        return parentMap;
    }


    public static List<Integer> getPath(Graph g, int start, int target, Map<Integer, Integer> parentMap) {
        List<Integer> path = new ArrayList<>(); //Il percorso è vuoto.

        if (start == target) { //Se start è target, allora il percorso è semplicemente start.
            path.add(start);
            return path;
        }
        if (!parentMap.containsKey(target)) // In questo punto sappiamo che start non può essere target, in quel caso:
            return null;                   // Non potrebbe capitare che magari target sia stato raggiunto, ma non abbia un genitore, perchè sarebbe così che start è target, e abbiamo già gestito quel caso.
                                        //  Quindi se target non ha un genitore, vuol dire che non è stato raggiunto, e quindi non c'è un percorso da start a target.

        Integer current = target; // Partiamo da target.
        while (current != null) {
            path.add(current); // Ovviamente, target stesso è parte del percorso. quindi lo aggiungo al percorso, e poi mi sposto al suo genitore.
            current = parentMap.get(current); // A questo punto, mi basta assegnare a current il suo genitore, e continuare a risalire fino a quando non arrivo a start, che è l'unico nodo che ha genitore null.
        }
          return path.reversed(); // Il percorso è stato costruito al contrario, partendo da target e risalendo fino a start, quindi lo devo invertire per restituirlo in modo corretto.
    }


    /**
     * La bfs scopre tutti i nodi, sempre.
     * Ne deriva che se c'è un target da raggiungere, esso è stato raggiunto non appena viene incontrato.
     * 
     */
    public static Map<Integer,Integer> getDistance(Graph g, int start) {
        Queue<Integer> codaDiVisitatiDiCuiVisionareVicini = new ArrayDeque<Integer>();
        Set<Integer> alreadyVisited = new HashSet<Integer>();
        Map<Integer, Integer> distance = new HashMap<>();
        codaDiVisitatiDiCuiVisionareVicini.add(start); //Devo visitare solo i vicini.
        distance.put(start, 0); //Ovviamente, la distanza da me stesso a me stesso è 0.
        alreadyVisited.add(start); //Siccome sto partendo da me, non ho bisogno di visitarmi, ma devo scoprire i miei vicini. Una volta scoperti, loro saranno visitati, e dovrò scoprire i loro vicini.
        while (!codaDiVisitatiDiCuiVisionareVicini.isEmpty()) { //Finchè ho vicini da visitare
            Integer visitatoDiCuiVisitareIVicini = codaDiVisitatiDiCuiVisionareVicini.poll();
            List<Integer> vicini = g.getNeighbors(visitatoDiCuiVisitareIVicini);
            for (Integer vicino : vicini) { //per ogni vicino di cui on ho visitato i vicini
                if(!alreadyVisited.contains(vicino)){
                    codaDiVisitatiDiCuiVisionareVicini.add(vicino); 
                    alreadyVisited.add(vicino); // L'ho visitato, va messo in coda per scoprirne i vicini dopo.
                    distance.put(vicino, distance.get(visitatoDiCuiVisitareIVicini) + 1); // La distanza con questo mio vicino da me stesso è equivalente alla distanza che ho con me stesso + 1 (è un vicino).
                }
            }
        }

        return distance;
    }



    /**
     * Esegue BFS sul grafo `g` a partire dal nodo `start`.
     * Restituisce la lista dei nodi visitati nell'ordine di visita.
     */
    public static List<Integer> bfs(Graph g, int start) {
        Set<Integer> alreadyVisited = new HashSet<Integer>();
        Queue<Integer> coda = new ArrayDeque<>();
        List<Integer> visitedOrder = new ArrayList<Integer>();
        coda.add(start);
        alreadyVisited.add(start);
        while (!coda.isEmpty()) {
            int current = coda.poll();
            visitedOrder.add(current);
            List<Integer> vicini = g.getNeighbors(current);
            for (Integer integer : vicini) {
                if (!alreadyVisited.contains(integer)) {
                    coda.add(integer);
                    alreadyVisited.add(integer);
                }
            }            
        }
        return visitedOrder;
    }

    /**
     * La bfs visita tutti i nodi di un grafo, in questo modo è in grado di stabilire se essi sono collegati fra loro.
     * Il nodo iniziale start, comunica per forza con se stesso, per tanto target è raggiungibile non appena la bfs ci arriva.
     */
    public static boolean bfs(Graph g, int start, int target) {
        Set<Integer> alreadySeen = new HashSet<>();
        Queue<Integer> willSee = new ArrayDeque<>();
        willSee.add(start);
        alreadySeen.add(start);
        if (start == target) {
            return true;
        }
        while (!willSee.isEmpty()) {
            int imseeing = willSee.poll();
            List<Integer> neighbors = g.getNeighbors(imseeing);
            for (Integer neighbor : neighbors) {
                if (neighbor == target) {
                    return true;
                }
                if (!alreadySeen.contains(neighbor)){
                    willSee.add(neighbor);
                    alreadySeen.add(neighbor);
                }
            }
            
        }
        return false;
    }


}