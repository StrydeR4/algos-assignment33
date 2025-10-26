package edu.algos;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;

public class Main {
    static class InputGraph {
        String name;
        int vertices;
        List<Map<String, Number>> edges;
    }

    static class OutputGraph {
        String name;
        MSTResult prim;
        MSTResult kruskal;
    }

    public static void main(String[] args) throws Exception {
        String inputPath = "input.json";
        if (args.length > 0) inputPath = args[0];
        String json = Files.readString(Path.of(inputPath));

        Gson gson = new Gson();
        Type listType = new TypeToken<List<InputGraph>>(){}.getType();
        List<InputGraph> inputs = gson.fromJson(json, listType);

        List<OutputGraph> outputs = new ArrayList<>();

        for (InputGraph ig : inputs) {
            Graph g = new Graph(ig.vertices);
            for (Map<String, Number> e : ig.edges) {
                int u = e.get("u").intValue();
                int v = e.get("v").intValue();
                long w = e.get("w").longValue();
                g.addEdge(u, v, w);
            }

            MSTResult primRes = Prim.run(g);
            MSTResult kruskalRes = Kruskal.run(g);

            if (primRes.totalCost != kruskalRes.totalCost) {
                System.out.printf("Costs differ for %s: Prim=%d, Kruskal=%d%n",
                        ig.name, primRes.totalCost, kruskalRes.totalCost);
            }

            OutputGraph og = new OutputGraph();
            og.name = ig.name;
            og.prim = primRes;
            og.kruskal = kruskalRes;
            outputs.add(og);

            System.out.printf("Graph: %s | V=%d | E=%d%n", ig.name, g.V, g.E());
            System.out.printf("  Prim: cost=%d | edges=%d | time=%dms | ops=%d%n",
                    primRes.totalCost, primRes.mstEdges.size(), primRes.timeMs, primRes.operations);
            System.out.printf("  Kruskal: cost=%d | edges=%d | time=%dms | ops=%d%n%n",
                    kruskalRes.totalCost, kruskalRes.mstEdges.size(), kruskalRes.timeMs, kruskalRes.operations);
        }

        try (Writer w = new FileWriter("output.json")) {
            Gson pretty = new GsonBuilder().setPrettyPrinting().create();
            pretty.toJson(outputs, w);
        }

        System.out.println("Results saved to output.json");
    }
}
