package com.example.findingroute.service;

import com.example.findingroute.domain.Node;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NodeServiceTest {

    @Test
    void findTheNodeExisted() {
        Node nodeUkr = new Node("UKR", Arrays.asList("BLR", "HUN", "MDA", "POL", "ROU", "RUS", "SVK"));
        Node ukrExists = NodeService.getInstance().findNodeFromCopy("UKR");
        assertEquals(nodeUkr.getNeighboursStrings(), ukrExists.getNeighboursStrings());
    }
    @Test
    void findTheNodeNotExisted() {
        Node dddNotExists = NodeService.getInstance().findNodeFromCopy("DDDD");
        assertNull(dddNotExists);
    }
}