package graph;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import utils.Direction;



// Un altro esempio interessante di applicazione del BFS è il problema del "flood fill",
//  che consiste nel colorare un'area connessa di una matrice a partire da una cella iniziale. 
// L'idea è simile a quella di un algoritmo di ricerca, ma invece di cercare un percorso,
//  vogliamo colorare tutte le celle connesse che hanno lo stesso valore.


public class FloodFill {


    // La matrice rappresenta il nostro "grafo", e le celle sono i nodi.
    public final int[][] grid;

    // La matrice è di dimensioni rows x cols, e possiamo muoverci in 4 direzioni (su, giù, sinistra, destra).
    public final int rows;
    public final int cols;

    // Nota che stavolta il grafo è implicito: è rappresentato dalla matrice stessa, e i nodi sono le celle.

    public FloodFill(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }


    // Il metodo floodfill prende in input le coordinate di una cella (r, c) sta volta, anzichè colorare tutte le celle connesse che hanno lo stesso valore, 
    // coloriamo tutte le celle connesse che hanno valore 0, cambiandole in 1.
    // Questo mi ricorda un po' come l'acqua si diffonde in Minecraft, riempiendo tutte le celle adiacenti che sono vuote (0) e trasformandole in acqua (1).
    public void floodfill(int r, int c) {

        // Una matrice booleana per tenere traccia delle celle già visitate, per evitare di entrare in un ciclo infinito.
        // NB: Non usiamo un set, perchè le celle sono rappresentate da coordinate (r, c), e set non è adatto a rappresentare coordinate.
        boolean[][] visited = new boolean[this.rows][this.cols];

        // La coda per il BFS, che conterrà le celle da visitare. Ogni cella è rappresentata da un array di due elementi: [riga, colonna].
        // Volendo potremmo anche creare una classe Cell con campi r e c, ma per semplicità usiamo un array.
        Queue<int[]> queue = new ArrayDeque<>();

        // La cella di partenza, calcolata in base a alle cordinate (r, c) passate come argomento al metodo. Questa è la cella da cui inizierà il flood fill.
        int[] startCell = {r, c};


        // Se la cella di partenza va oltrre i margini della matrice, non facciamo nulla e usciamo dal metodo.1
        if (!isInside(startCell)){
            return;
        }


        // Come nel classico bfs, aggiungiamo la cella di partenza alla coda, e poi nella matrice booleana corrispondente alla cella di partenza, segniamo che è stata visitata.
        // Inoltre, cambiamo il valore della cella di partenza da 0 a 1, per indicare che è stata colorata.
        queue.add(startCell);
        visited[r][c] = true;
        this.grid[r][c] = 1;


        // Finché la coda non è vuota
        while (!queue.isEmpty()) {

            // Prendiamo la cella di testa alla coda, e decidiamo se colorarla o meno. 
            // Se la cella ha valore 0, la coloriamo cambiandola in 1.
            // Se ha già valore 1, significa che è già stata colorata, quindi non facciamo nulla.
            int[] currentCell = queue.poll();
            if (this.grid[currentCell[0]][currentCell[1]] == 0) {
                this.grid[currentCell[0]][currentCell[1]] = 1;
                // Guardiamo la griglia colorarsi

                System.out.println("Coloro la cella (" + currentCell[0] + ", " + currentCell[1] + ")");
                for (int i = 0; i < this.rows; i++) {
                    for (int j = 0; j < this.cols; j++) {
                        System.out.print(this.grid[i][j] + " ");
                    }
                    System.out.println();
                }

            }

            int currentRow = currentCell[0];
            int currentCol = currentCell[1];
            
            // La direzione è un enum che istanzia 4 oggetti
            // UP(-1, 0),
            // DOWN(1, 0),
            // LEFT(0, -1),
            // RIGHT(0, 1);
            // andando a "aggiungere" questi valori alla cella corrente, otteniamo le coordinate della cella adiacente in quella direzione.
            for (Direction d : Direction.values()) {
                // La cella vicina è data dalle coordinate della cella corrente, sommata all'offset della direzione.
                //  Ad esempio, se siamo nella cella (2, 3) e la direzione è UP(-1, 0), la cella vicina sarà (2 + (-1), 3 + 0) = (1, 3).
                int[] nCell = {currentRow + d.dr, currentCol + d.dc};
                if (isInside(nCell) && !visited[currentRow + d.dr][currentCol + d.dc]  ) {
                    queue.add(nCell);
                    visited[nCell[0]][nCell[1]] = true;
                }

            }


        }



    }


    public boolean isInside(int[] cell) {
        int r = cell[0];
        int c = cell[1];
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }




    
}
