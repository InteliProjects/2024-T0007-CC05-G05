package com.inteli.models;

/**
 * Represents a directed edge in a graph, connecting two nodes and facilitating a certain flow.
 * This class is essential for modeling transportation paths in the network, including capacities,
 * current flow, and the transportation mode.
 */
public class Path {
    private Node originNode;
    private Node destinationNode;
    private String description;
    private double flow;
    private double capacity;
    private String transportationModeType;
    private String subTransportationModeType;

    /**
     * Constructs a Path with specified characteristics.
     *
     * @param originNode The starting point of the path.
     * @param destinationNode The endpoint of the path.
     * @param description A textual description of the path.
     * @param flow The current amount of flow on the path.
     * @param capacity The maximum capacity of the path.
     * @param transportationModeType The mode of transportation used on this path.
     * @param subTransportationModeType A more specific type of the transportation mode, if applicable.
     */
    public Path(Node originNode, Node destinationNode, String description, double flow, double capacity, String transportationModeType, String subTransportationModeType) {
        setOriginNode(originNode);
        setDestinationNode(destinationNode);
        setDescription(description);
        setCapacity(capacity);
        setFlow(flow);
        setTransportationModeType(transportationModeType);
        setSubTransportationModeType(subTransportationModeType);
    }

    /**
     * Increments the flow on this path by a specified amount, ensuring it does not exceed the path's capacity.
     *
     * @param increment The amount to add to the current flow.
     */
    public void incrementFlow(double increment) {
        if ((this.flow + increment) > this.capacity) {
            this.flow = this.capacity;
        } else {
            this.flow += increment;
        }
    }

    /**
     * Retrieves the origin node of this path.
     *
     * @return The origin node.
     */
    public Node getOriginNode() {
        return originNode;
    }

    /**
     * Retrieves the destination node of this path.
     *
     * @return The destination node.
     */
    public Node getDestinationNode() {
        return destinationNode;
    }

    /**
     * Retrieves the description of this path.
     *
     * @return The path's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the current flow through this path.
     *
     * @return The current flow.
     */
    public double getFlow() {
        return flow;
    }

    /**
     * Retrieves the maximum capacity of this path.
     *
     * @return The path's capacity.
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * Retrieves the main transportation mode type of this path.
     *
     * @return The transportation mode type.
     */
    public String getTransportationModeType() {
        return transportationModeType;
    }

    /**
     * Retrieves the sub-transportation mode type of this path.
     *
     * @return The sub-transportation mode type.
     */
    public String getSubTransportationModeType() {
        return subTransportationModeType;
    }

    /**
     * Calculates and returns the remaining capacity of the path.
     *
     * @return The difference between the path's capacity and its current flow.
     */
    public double getRemainingCapacity() {
        return capacity - flow;
    }

    /**
     * Sets the sub-transportation mode type for this path.
     *
     * @param subTransportationModeType The specific sub-transportation mode type.
     */
    public void setSubTransportationModeType(String subTransportationModeType) {
        this.subTransportationModeType = subTransportationModeType;
    }

    /**
     * Sets the description for this path.
     *
     * @param description The textual description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the current flow of this path.
     *
     * @param flow The flow amount to set.
     */
    private void setFlow(double flow) {
        if (flow > capacity) {
            this.flow = capacity;
        } else if (flow < 0) {
            this.flow = 0;
        } else {
            this.flow = flow;
        }
    }

    /**
     * Sets the capacity of this path.
     *
     * @param capacity The capacity to set.
     */
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    /**
     * Sets the origin node of this path.
     *
     * @param originNode The origin node to set.
     */
    public void setOriginNode(Node originNode) {
        this.originNode = originNode;
    }

    /**
     * Sets the destination node of this path.
     *
     * @param destinationNode The destination node to set.
     */
    public void setDestinationNode(Node destinationNode) {
        this.destinationNode = destinationNode;
    }

    /**
     * Sets the transportation mode type for this path.
     *
     * @param transportationModeType The transportation mode type to set.
     */
    public void setTransportationModeType(String transportationModeType) {
        this.transportationModeType = transportationModeType;
    }
}