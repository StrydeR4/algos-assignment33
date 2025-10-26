package edu.algos;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public final int V;
    public final List<Edge> edges;
    public final List<List<Edge>> adj;

    public Graph(int V) {
        this.V = V;
        this.edges = new ArrayList<>();
        this.adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v, long w) {
        Edge e = new Edge(u, v, w);
        edges.add(e);
        adj.get(u).add(e);
        adj.get(v).add(e);
    }

    public int E() {
        return edges.size();
    }
}
