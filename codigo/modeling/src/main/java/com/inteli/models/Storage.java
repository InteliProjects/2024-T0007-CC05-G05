package com.inteli.models;

/**
 * Represents storage capacity and current storage state for nodes within a graph that can store resources.
 * This class is designed to encapsulate the concept of storage, including its maximum capacity and current usage,
 * to be associated with specific nodes that require such a feature, like warehouses or reservoirs.
 */
public class Storage {
    private double maximumCapacity;
    private double currentCapacity;

    /**
     * Constructs a Storage instance with specified maximum and current capacities.
     * Ensures that storage capacities are set within logical constraints.
     *
     * @param maximumCapacity The maximum capacity of the storage.
     * @param currentCapacity The current usage or filled capacity of the storage.
     */
    public Storage(double maximumCapacity, double currentCapacity) {
        setMaximumCapacity(maximumCapacity);
        setCurrentCapacity(currentCapacity);
    }

    /**
     * Sets the maximum storage capacity, ensuring it cannot be negative.
     * If a negative value is passed, it defaults to zero.
     *
     * @param maximumCapacity The maximum storage capacity to be set.
     */
    public void setMaximumCapacity(double maximumCapacity) {
        this.maximumCapacity = Math.max(maximumCapacity, 0);
    }

    /**
     * Sets the current storage capacity, ensuring it does not exceed the maximum capacity or become negative.
     * Adjusts the current capacity to align with these constraints.
     *
     * @param currentCapacity The current storage capacity to be set.
     */
    public void setCurrentCapacity(double currentCapacity) {
        this.currentCapacity = Math.max(Math.min(currentCapacity, this.maximumCapacity), 0);
    }

    /**
     * Retrieves the maximum storage capacity.
     *
     * @return The maximum storage capacity.
     */
    public double getMaximumCapacity() {
        return this.maximumCapacity;
    }

    /**
     * Retrieves the current storage capacity, indicating how much of the storage is currently in use.
     *
     * @return The current storage capacity.
     */
    public double getCurrentCapacity() {
        return this.currentCapacity;
    }
}
