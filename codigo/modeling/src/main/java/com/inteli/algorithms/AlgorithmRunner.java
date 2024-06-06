package com.inteli.algorithms;

import com.inteli.models.GraphImplementer;
import com.inteli.models.Node;
import com.inteli.models.NodeType;
import com.inteli.models.Path;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Handles the execution of maximum flow algorithms on a given graph.
 * Supports multiple algorithm implementations and provides utilities for converting
 * graph data into JSON format for visualization or further processing.
 */
public class AlgorithmRunner {
    private final int V;
    private List<Node> nodes;
    private List<List<Path>> residualGraph;
    private MaxFlowAlgorithms algorithm;

    /**
     * Constructs an AlgorithmRunner with a specified data directory path and algorithm.
     * Initializes the graph structure from CSV files located at the given path.
     *
     * @param dataDirPath The path to the directory containing graph data files.
     * @param algorithm The maximum flow algorithm to be used.
     */
    public AlgorithmRunner(String dataDirPath, MaxFlowAlgorithms algorithm) {
        GraphImplementer graphImplementer = new GraphImplementer(dataDirPath);
        this.V = graphImplementer.getNodes().size();
        this.algorithm = algorithm;
        nodes = graphImplementer.getNodes();
        residualGraph = graphImplementer.getAdjacencyMatrix();
    }

    /**
     * Performs a breadth-first search to find an augmenting path from source to sink in the residual graph.
     *
     * @param source The source vertex identifier.
     * @param sink The sink vertex identifier.
     * @param parent Array to store the path found during BFS.
     * @return True if an augmenting path is found, otherwise false.
     */
    private boolean bfs(int source, int sink, int[] parent) {
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < V; v++) {
                // Check if the path exists and has capacity
                if (!visited[v] && residualGraph.get(u).get(v) != null && residualGraph.get(u).get(v).getRemainingCapacity() > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return visited[sink];
    }

    /**
     * Performs a depth-first search to find an augmenting path from source to sink in the residual graph.
     *
     * @param source The source vertex identifier.
     * @param sink The sink vertex identifier.
     * @param parent Array to store the path found during DFS.
     * @return True if an augmenting path is found, otherwise false.
     */
    private boolean dfs(int source, int sink, int[] parent) {
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false); // Initialize all vertices as not visited

        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        visited[source] = true;
        parent[source] = -1; // Source has no parent

        while (!stack.isEmpty()) {
            int u = stack.pop();

            for (int v = 0; v < V; v++) {
                if (residualGraph.get(u).get(v) == null) continue;
                if (!visited[v] && residualGraph.get(u).get(v).getRemainingCapacity() > 0) {
                    stack.push(v);
                    parent[v] = u;
                    visited[v] = true;
                    if (v == sink) return true; // Sink reached in DFS
                }
            }
        }
        return visited[sink]; // Return true if sink is reached
    }

    /**
     * Implements the Ford-Fulkerson algorithm to calculate the maximum flow from source to sink.
     *
     * @param source The source vertex identifier.
     * @param sink The sink vertex identifier.
     * @return The maximum flow value.
     */
    private double fordFulkerson(int source, int sink) {
        int[] parent = new int[V];
        double maxFlow = 0;

        while (bfs(source, sink, parent)) {
            double pathFlow = Double.POSITIVE_INFINITY;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph.get(u).get(v).getRemainingCapacity());
            }

            // Update residual capacities of the edges and reverse edges
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualGraph.get(u).get(v).incrementFlow(pathFlow);

                // Path reversePath = residualGraph.get(v).get(u);
                // reversePath.incrementFlow(-pathFlow);
            }

            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    /**
     * Implements the Edmonds-Karp algorithm to calculate the maximum flow from source to sink.
     *
     * @param source The source vertex identifier.
     * @param sink The sink vertex identifier.
     * @return The maximum flow value.
     */
    private double edmondsKarp(int source, int sink) {
        int[] parent = new int[V];
        double maxFlow = 0;

        while (dfs(source, sink, parent)) {
            double pathFlow = Double.POSITIVE_INFINITY;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph.get(u).get(v).getRemainingCapacity());
            }

            // Update residual capacities of the edges and reverse edges
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualGraph.get(u).get(v).incrementFlow(pathFlow);

                // Path reversePath = residualGraph.get(v).get(u);
                // reversePath.incrementFlow(-pathFlow);
            }

            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    /**
     * Runs the selected maximum flow algorithm on the graph.
     *
     * @param nodes The list of nodes in the graph.
     * @param paths The list of paths in the graph.
     * @param identifierMap A map from node identifiers to their indices in the adjacency list.
     * @return The maximum flow value computed by the algorithm.
     */
    public double runAlgorithm(List<Node> nodes, List<Path> paths, Map<Integer, Integer> identifierMap) {

        switch (algorithm) {
            case FORD_FULKERSON:
                 return fordFulkerson(0, 1);
            case EDMONDS_KARP:
                 return edmondsKarp(0 , 1);
        }
        return 0;
    }

    /**
     * Writes the graph data, including nodes and paths with flows and capacities, to a JSON file.
     *
     * @param nodes The list of nodes in the graph.
     * @param paths The adjacency matrix representing the paths in the graph.
     * @param jsonPath The path to the JSON file where the graph data will be written.
     */
    public void jsonWriter(List<Node> nodes, List<List<Path>> paths, String jsonPath) {
        Set<Integer> nodesWithFlow = new HashSet<>();
        for (List<Path> l : paths) {
            for (Path p : l) {
                if (p != null && p.getFlow() != 0) {
                    nodesWithFlow.add(p.getOriginNode().getIdentifier());
                    nodesWithFlow.add(p.getDestinationNode().getIdentifier());
                }
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(jsonPath))) {
            writer.write("const graphData = {\n");
            writer.write("        nodes: [\n");
            for (int i = 0; i < nodes.size(); i++) {
                if (nodesWithFlow.contains(nodes.get(i).getIdentifier())) {
                    writer.write("        " + nodeToJsonString(nodes.get(i)));
                    if (i < nodes.size() - 1) {
                        writer.write(",\n");
                    } else {
                        writer.write("\n");
                    }
                }
            }
            writer.write("  ],\n");
            writer.write("        edges: [\n");
            for (int i = 0; i < paths.size(); i++) {
                for (int j = 0; j < paths.size(); j++) {
                    if (paths.get(i).get(j) != null && paths.get(i).get(j).getFlow() != 0) {
                        writer.write("        " + pathToJsonString(paths.get(i).get(j)));
                        if (i + j < paths.size() * paths.size() - 1) {
                            writer.write(",\n");
                        } else {
                            writer.write("\n");
                        }
                    }
                }
            }
            writer.write("  ]\n");
            writer.write("    };\n");
            writer.write("export default graphData;");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a Node object to a JSON string representation.
     *
     * @param n The Node to be converted.
     * @return A string in JSON format representing the node.
     */
    private String nodeToJsonString(Node n) {
        Map<NodeType, String> nodeColorMap = new HashMap<>();
        nodeColorMap.put(NodeType.S, "#FFD700"); // Gold
        nodeColorMap.put(NodeType.T, "#FF8C00"); // Dark Orange
        nodeColorMap.put(NodeType.CL, "#1E90FF"); // Dodger Blue
        nodeColorMap.put(NodeType.FO, "#32CD32"); // Lime Green
        nodeColorMap.put(NodeType.PA, "#6A5ACD"); // Slate Blue
        nodeColorMap.put(NodeType.PT, "#FF69B4"); // Hot Pink
        nodeColorMap.put(NodeType.UB, "#8B4513"); // Saddle Brown
        nodeColorMap.put(NodeType.BR, "#B22222"); // Firebrick
        nodeColorMap.put(NodeType.UP, "#20B2AA"); // Light Sea Green
        nodeColorMap.put(NodeType.PO, "#FF6961"); // Red :)
        final String BLACK = "#000000";
        final String WHITE = "#FFFFFF";


        return String.format("{ id: \"%s\", label: \"%s\", color: \"%s\", fontColor: \"%s\", type: \"%s\" }", n.getIdentifier(),
                n.getDescription(), nodeColorMap.get(n.getNodeType()), BLACK, n.getNodeType());
    }

    /**
     * Converts a Path object to a JSON string representation.
     *
     * @param p The Path to be converted.
     * @return A string in JSON format representing the path.
     */
    private String pathToJsonString(Path p) {

        String flowCapacityString = String.format("%.2f/%.2f", p.getFlow(), p.getCapacity());
        return String.format("{ from: \"%s\", to: \"%s\", label: \"%s\", from_type: \"%s\", to_type: \"%s\"}",
                p.getOriginNode().getIdentifier(), p.getDestinationNode().getIdentifier(), flowCapacityString, p.getOriginNode().getNodeType(), p.getDestinationNode().getNodeType());
    }

    /**
     * Gets the list of nodes in the graph.
     *
     * @return A list of Node objects.
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Gets the residual graph after flow calculations.
     *
     * @return A list of lists representing the residual graph's adjacency matrix.
     */
    public List<List<Path>> getResidualGraph() {
        return residualGraph;
    }
}
