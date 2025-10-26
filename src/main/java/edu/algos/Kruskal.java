package edu.algos;

import java.util.*;

public class Kruskal {
    public static MSTResult run(Graph g) {
        int V = g.V;
        long ops = 0;
        List<Edge> edges = new ArrayList<>(g.edges);

        long start = System.nanoTime();
        Collections.sort(edges);
        ops += edges.size();

        DisjointSet ds = new DisjointSet(V);
        List<Edge> result = new ArrayList<>();
        long totalCost = 0;

        for (Edge e : edges) {
            ops++;
            if (ds.union(e.u, e.v)) {
                result.add(e);
                totalCost += e.weight;
            }
        }

        long end = System.nanoTime();
        long timeMs = (end - start) / 1_000_000L;
        return new MSTResult("Kruskal", result, totalCost, g.V, g.E(), ops, timeMs);
    }
}
