package com.inteli.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testNodeCreationAndProperties() {
        Storage storage = new Storage(100, 10);
        Node node = new Node(1, "Node1", storage, NodeType.S);

        assertEquals(1, node.getIdentifier());
        assertEquals("Node1", node.getDescription());
        assertNotNull(node.getStorage());
        assertEquals(NodeType.S, node.getNodeType());
    }

    @Test
    void testNodeCreationWithInvalidDescription() {
        Storage storage = new Storage(100, 10);
        assertThrows(IllegalArgumentException.class, () -> new Node(1, null, storage, NodeType.S));
    }

    @Test
    void testHasStorageMethod() {
        Storage storage = new Storage(1000, 0);
        Node n1 = new Node(1, "Node 1", NodeType.S);
        Node n2 = new Node(2, "Node 2",storage ,NodeType.T);
        assertFalse(n1.hasStorage());
        assertTrue(n2.hasStorage());
    }
}