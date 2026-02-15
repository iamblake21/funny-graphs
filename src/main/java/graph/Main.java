package graph;
import graph.FloodFill;

import java.util.*;

public class Main {
    public static void main(String[] args) {
  /*       Graph g = new Graph();

        // Build a strange graph to test BFS
        //    1
        //   / \
        //  2   3
        //   \ /
        //    4 - 5 - 6 - 7
        //    |   |
        //    8   9
        //.   \ /
        //     10

        
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 7);
        g.addEdge(4, 8);
        g.addEdge(5, 9);
        g.addEdge(8, 10);
        g.addEdge(9, 10);
        int start = 1; // punto di partenza della BFS
        int target = 10; // punto di arrivo per cui vogliamo trovare il percorso più breve
        Map<Integer,Integer> distance = BFS.getDistance(g, start);
        List shortestPath = BFS.getShortestPath(start, target, g);
        System.out.println(distance);
        System.out.print(shortestPath); */


        // Creiamo una griglia con un centro:

        int[][] grid = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };

        FloodFill floodFill = new FloodFill(grid);
        // Applichiamo il flood fill a partire dalla cella centrale (2, 2)
        floodFill.floodfill(2, 2);

    }
}
