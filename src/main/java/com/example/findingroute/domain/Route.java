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

    public String searchTheRoute() {
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

    private String traceTheRoute() {
        Node node = end;
        List<Node> route = new ArrayList<>();
        if (node.getPrev() == null) {
            return null;
        }

        while (node != null) {
            route.add(node);
            node = node.getPrev();
        }
        Collections.reverse(route);
        String rezultRoute = route.toString();

        for (Node nodeToClear : route) {
            nodeToClear.setPrev(null);
            nodeToClear.setVisited(false);
        }

        return rezultRoute;
    }
}
