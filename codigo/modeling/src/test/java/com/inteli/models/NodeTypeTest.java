package com.inteli.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodeTypeTest {
    @Test
    void testFromStringValidType() {
        assertEquals(NodeType.S, NodeType.fromString("S"));
        assertEquals(NodeType.S, NodeType.fromString("s"));
        assertEquals(NodeType.T, NodeType.fromString("T"));
        assertEquals(NodeType.CL, NodeType.fromString("CL"));
        assertEquals(NodeType.FO, NodeType.fromString("FO"));
        assertEquals(NodeType.PA, NodeType.fromString("PA"));
        assertEquals(NodeType.PT, NodeType.fromString("PT"));
        assertEquals(NodeType.UB, NodeType.fromString("UB"));
        assertEquals(NodeType.BR, NodeType.fromString("BR"));
        assertEquals(NodeType.UP, NodeType.fromString("UP"));
        assertEquals(NodeType.PO, NodeType.fromString("PO"));

    }

    @Test
    void testFromStringInvalidType() {
        assertThrows(IllegalArgumentException.class, () -> NodeType.fromString("invalid"));
        assertThrows(IllegalArgumentException.class, () -> NodeType.fromString("S "));
        assertThrows(IllegalArgumentException.class, () -> NodeType.fromString("C L"));
    }
}
