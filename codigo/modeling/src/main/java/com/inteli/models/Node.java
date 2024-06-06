package com.inteli.models;

/**
 * Represents a node in a graph, potentially with storage capabilities, identified uniquely within the graph.
 * Nodes can represent various entities depending on their type, such as suppliers, consumers, or intermediaries.
 */
public class Node {
    private String description;
    private int identifier;
    private Storage storage;
    private final NodeType nodeType;

    /**
     * Constructs a new Node with storage capabilities.
     *
     * @param identifier  Unique identifier for the node.
     * @param description Descriptive text about the node.
     * @param storage     Storage capacity of the node, null if the node has no storage.
     * @param nodeType    The type of node, as defined in {@link NodeType}.
     */
    public Node(int identifier, String description, Storage storage, NodeType nodeType) {
        setIdentifier(identifier);
        setDescription(description);
        this.storage = storage;
        this.nodeType = nodeType;
    }

    /**
     * Constructs a new Node without storage capabilities.
     *
     * @param identifier  Unique identifier for the node.
     * @param description Descriptive text about the node.
     * @param nodeType    The type of node, as defined in {@link NodeType}.
     */
    public Node(int identifier, String description, NodeType nodeType) {
        this(identifier, description, null, nodeType);
    }

    /**
     * Checks if this node has storage capabilities.
     *
     * @return True if the node has storage, otherwise false.
     */
    public boolean hasStorage() {
        return storage != null;
    }

    /**
     * Sets the identifier for this node.
     *
     * @param identifier The new identifier to set.
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    /**
     * Sets the description for this node.
     *
     * @param description The new description to set.
     * @throws IllegalArgumentException If description is null.
     */
    public void setDescription(String description) {
        if (description == null) throw new IllegalArgumentException("Description must have a value.");
        this.description = description;
    }

    /**
     * Retrieves the identifier of this node.
     *
     * @return The identifier of the node.
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * Retrieves the description of this node.
     *
     * @return The description of the node.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the storage of this node, if any.
     *
     * @return The storage of the node, or null if it has no storage.
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Retrieves the type of this node.
     *
     * @return The {@link NodeType} of the node.
     */
    public NodeType getNodeType() {
        return this.nodeType;
    }
}
