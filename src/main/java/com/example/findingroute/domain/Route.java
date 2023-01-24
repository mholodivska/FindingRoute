package com.example.findingroute.domain;

import lombok.Data;

import java.util.*;

@Data
public class Route {
    private Node start;
    private Node end;

    public Route(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    public List<String> searchTheRoute() {
        Queue<Node> queue = new LinkedList<>();
        start.setVisited(true);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            for (Node node : currentNode.getNeighbours()) {
                if (!node.isVisited()) {
                    node.setVisited(true);
                    queue.add(node);
                    node.setPrev(currentNode);
                    if (node == end) {
                        queue.clear();
                        break;
                    }
                }
            }
        }
        return traceTheRoute();
    }

    private List<String> traceTheRoute() {
        Node node = end;
        if (node.getPrev() == null) {
            return null;
        }
        List<Node> route = new ArrayList<>();

        while (node != null) {
            route.add(node);
            node = node.getPrev();
        }
        Collections.reverse(route);
        List<String> names = getArrayOfCountryNames(route);

        for (Node nodeToClear : route) {
            nodeToClear.setPrev(null);
            nodeToClear.setVisited(false);
        }

        return names;
    }

    private List<String> getArrayOfCountryNames(List<Node> route) {
        List<String> result = new ArrayList<>();
        for (Node node1 : route) {
            result.add(node1.getNameCode());
        }
        return result;
    }
}
