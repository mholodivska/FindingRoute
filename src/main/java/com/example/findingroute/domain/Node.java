package com.example.findingroute.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Node {
    private String nameCode;
    private List<String> neighboursStrings;
    private List<Node> neighbours = new ArrayList<>();
    private boolean visited;
    private Node prev;

    public Node(String nameCode, List<String> neighboursStrings){
        this.nameCode = nameCode;
        this.neighboursStrings = neighboursStrings;
    }

    public Node(Node origin) {
        this.nameCode = origin.getNameCode();
        this.neighboursStrings = origin.getNeighboursStrings();
    }

    public void addNeighbour(Node node){
        this.neighbours.add(node);
    }

    @Override
    public String toString(){
        return this.nameCode;
    }
}
