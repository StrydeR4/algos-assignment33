package edu.algos;

import java.util.*;

public class Prim {
    public static MSTResult run(Graph g) {
        int V = g.V;
        boolean[] visited = new boolean[V];
        long ops = 0;
        List<Edge> result = new ArrayList<>();
        long totalCost = 0;

        long start = System.nanoTime();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int startV = 0; startV < V; startV++) {
            if (visited[startV]) continue;
            visited[startV] = true;
            for (Edge e : g.adj.get(startV)) {
                pq.add(e); ops++;
            }
            while (!pq.isEmpty()) {
                Edge e = pq.poll(); ops++;
                int u = e.u, v = e.v;
                int next = visited[u] ? v : u;
                if (visited[next]) continue;
                result.add(e);
                totalCost += e.weight;
                visited[next] = true;
                for (Edge ne : g.adj.get(next)) {
                    pq.add(ne); ops++;
                }
            }
        }

        long end = System.nanoTime();
        long timeMs = (end - start) / 1_000_000L;
        return new MSTResult("Prim", result, totalCost, g.V, g.E(), ops, timeMs);
    }
}

