package com.inteli.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class StorageTest {
    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage(100.0, 50.0);
    }

    @Test
    void testInitialConditions() {
        assertEquals(100.0, storage.getMaximumCapacity(), "Maximum capacity should be set correctly.");
        assertEquals(50.0, storage.getCurrentCapacity(), "Current capacity should be set correctly.");
    }

    @Test
    void testSetMaximumCapacity() {
        storage.setMaximumCapacity(200.0);
        assertEquals(200.0, storage.getMaximumCapacity(), "Maximum capacity should be updated correctly.");

        // Testing boundary conditions
        storage.setMaximumCapacity(-10.0);
        assertEquals(0.0, storage.getMaximumCapacity(), "Maximum capacity should not be negative.");
    }

    @Test
    void testSetCurrentCapacity() {
        storage.setCurrentCapacity(150.0);
        assertEquals(100.0, storage.getCurrentCapacity(), "Current capacity should not exceed maximum capacity.");

        storage.setCurrentCapacity(-10.0);
        assertEquals(0.0, storage.getCurrentCapacity(), "Current capacity should not be negative.");

        storage.setMaximumCapacity(200.0); // Reset maximum capacity for this test
        storage.setCurrentCapacity(150.0);
        assertEquals(150.0, storage.getCurrentCapacity(), "Current capacity should be updated correctly.");
    }

}
