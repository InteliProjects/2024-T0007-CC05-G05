package com.inteli.models;

/**
 * Enum defining the various types of nodes that can exist within a graph.
 * Each node type represents a specific category within the network,
 * such as sources, targets, and various intermediaries, ensuring that only
 * valid types are used throughout the system.
 */
public enum NodeType {
    S, // Source
    T, // Target
    CL, // Client
    FO, // Supplier
    PA, // Storage yard
    PT,
    UB, // Processing plants
    BR,
    UP,
    PO; //  Port

    /**
     * Converts a string representation of a node type into a {@link NodeType} enum.
     * This method facilitates the use of NodeType enums from string values, such as those read from files.
     *
     * @param stringNodeType The string representation of the node type.
     * @return The corresponding {@link NodeType}.
     * @throws IllegalArgumentException If the provided string does not match any node type.
     */
    public static NodeType fromString(String stringNodeType) {
        for (NodeType nodeType : NodeType.values()) {
            if (nodeType.name().equalsIgnoreCase(stringNodeType)) {
                return nodeType;
            }
        }
        throw new IllegalArgumentException("Not a valid node type: " + stringNodeType);
    }
}
