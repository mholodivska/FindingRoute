package com.example.findingroute.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Node {
    private String name;
    private List<String> neighboursStrings;
    private List<Node> neighbours;
    private boolean visited = false;
    private Node prev = null;

    public Node(String name, List<String> neighboursStrings){
        this.name = name;
        this.neighboursStrings = neighboursStrings;
        this.neighbours = new ArrayList<>();
    }

    public void addNeighbour(Node node){
        this.neighbours.add(node);
    }

    public String toString(){
        return this.name;
    }
}
