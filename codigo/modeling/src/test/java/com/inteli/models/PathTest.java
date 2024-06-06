package com.inteli.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PathTest {
    private Path path;
    @BeforeEach
    void setup() {
        Node origin = new Node(1, "Origin", NodeType.S);
        Node destination = new Node(2, "Destination", NodeType.T);
        path = new Path(origin, destination, "OD Path", 150, 100, "Type", "SubType");
    }
    @Test
    void testPathProperties() {
        assertEquals("OD Path", path.getDescription());
        assertEquals(100, path.getCapacity());
    }

    @Test
    void testFlowCapacityConstraints() {
        assertEquals(100, path.getFlow(), "Flow should not exceed capacity.");
    }

}
