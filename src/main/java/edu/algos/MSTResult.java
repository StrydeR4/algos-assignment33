package edu.algos;

import java.util.List;

public class MSTResult {
    public final String algorithm;
    public final List<Edge> mstEdges;
    public final long totalCost;
    public final int vertices;
    public final int edges;
    public final long operations;
    public final long timeMs;

    public MSTResult(String algorithm, List<Edge> mstEdges, long totalCost,
                     int vertices, int edges, long operations, long timeMs) {
        this.algorithm = algorithm;
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.vertices = vertices;
        this.edges = edges;
        this.operations = operations;
        this.timeMs = timeMs;
    }
}
