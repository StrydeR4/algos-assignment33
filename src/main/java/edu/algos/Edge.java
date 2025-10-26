package edu.algos;

public class Edge implements Comparable<Edge> {
    public final int u, v;
    public final long weight;

    public Edge(int u, int v, long weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return u + "-" + v + ":" + weight;
    }
}

