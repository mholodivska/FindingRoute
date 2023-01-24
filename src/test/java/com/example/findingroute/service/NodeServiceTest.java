package com.example.findingroute.service;

import com.example.findingroute.domain.Node;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NodeServiceTest {

    NodeService nodeService = new NodeService();

    @Test
    void findTheNodeExisted() {
        Node nodeUkr = new Node("UKR", Arrays.asList("BLR", "HUN", "MDA", "POL", "ROU", "RUS", "SVK"));
        Node ukrExists = NodeService.findNode("UKR");
        assertEquals(nodeUkr.getNeighboursStrings(), ukrExists.getNeighboursStrings());
    }
    @Test
    void findTheNodeNotExisted() {
        Node dddNotExists = NodeService.findNode("DDDD");
        assertNull(dddNotExists);
    }
}