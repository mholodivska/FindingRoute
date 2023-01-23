package com.example.findingroute.service;

import com.example.findingroute.domain.Node;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeServiceTest {

    NodeService nodeService = new NodeService();

    @Test
    void findTheNodeExisted() {
        Node nodeUkr = new Node("UKR", Arrays.asList("BLR", "HUN", "MDA", "POL", "ROU", "RUS", "SVK"));
        Node ukrExists = NodeService.findTheNode("UKR");
        assertEquals(nodeUkr.getNeighboursStrings(), ukrExists.getNeighboursStrings());
    }
    @Test
    void findTheNodeNotExisted() {
        Node dddNotExists = NodeService.findTheNode("DDDD");
        assertNull(dddNotExists);
    }
}