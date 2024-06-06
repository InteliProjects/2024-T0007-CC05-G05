package com.inteli.models;

import com.inteli.algorithms.AlgorithmRunner;
import com.inteli.algorithms.MaxFlowAlgorithms;

import java.util.List;
import java.util.Map;

/**
 * The main entry point for the application, demonstrating the setup and execution of a graph-based algorithm.
 * This class initializes the graph by reading data from specified directories, runs a selected max flow algorithm,
 * and outputs the results, including the maximum flow value and a JSON representation of the graph for frontend visualization.
 */
public class Main {
    /**
     * The main method to run the application.
     *
     * Initializes the graph with nodes and paths from data files, selects a max flow algorithm,
     * runs the algorithm, and then outputs the maximum flow value and creates a JSON file
     * representing the graph state after algorithm execution.
     *
     */
    public static void main(String[] args) {
        double flow = generateResponseGraph("data", "");
        System.out.println(flow);
    }
    public static double generateResponseGraph(String dataDirPath, String algorithm) {
        MaxFlowAlgorithms selectedAlgorithm;
        switch (algorithm) {
            case "ford-fulkerson":
                selectedAlgorithm = MaxFlowAlgorithms.FORD_FULKERSON;
            case "edmonds-karp":
                selectedAlgorithm = MaxFlowAlgorithms.EDMONDS_KARP;
            default:
                selectedAlgorithm = MaxFlowAlgorithms.FORD_FULKERSON;
        }
        GraphImplementer graphImplementer = new GraphImplementer(dataDirPath); // Initializes the graph from data.
        List<Node> nodes = graphImplementer.getNodes();
        List<Path> paths = graphImplementer.getPaths();
        Map<Integer, Integer> identifierMap = graphImplementer.getIdentifierDictionary();

        AlgorithmRunner algorithmRunner = new AlgorithmRunner(dataDirPath, selectedAlgorithm); // Instance to run the selected algorithm.
        double maxFlow = algorithmRunner.runAlgorithm(nodes, paths, identifierMap); // Executes the algorithm and calculates max flow.
        String jsonPath = "../frontend/src/data/graphDataTest.js"; // Path to output the JSON file for frontend visualization.
        algorithmRunner.jsonWriter(nodes, algorithmRunner.getResidualGraph(), jsonPath); // Writes the JSON representation of the graph.

        return maxFlow;
    }
}
