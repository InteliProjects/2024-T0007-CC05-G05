package com.inteli.algorithms;

/**
 * Enumerates the algorithms available for calculating the maximum flow in a network graph.
 * These algorithms are used to find the best way to push flow through a network from a source to a sink
 * such that the flow is maximized and does not exceed the capacities of the paths (edges) in the network.
 */
public enum MaxFlowAlgorithms {
    FORD_FULKERSON,
    EDMONDS_KARP

}
