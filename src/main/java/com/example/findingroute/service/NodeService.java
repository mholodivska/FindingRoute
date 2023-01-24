package com.example.findingroute.service;

import com.example.findingroute.domain.Node;

import java.util.ArrayList;
import java.util.List;

public class NodeService {
    private List<Node> countries;
    private static NodeService nodeService;

    private NodeService() {
        initCountries();
    }

    public static NodeService getInstance() {
        if (nodeService == null) {
            nodeService = new NodeService();
        }
        return nodeService;
    }

    private void initCountries() {
        String countriesJson = ApiCallService.getCountriesJson();
        if (countriesJson != null) {
            countries = JsonParserService.parse(countriesJson);
            for (Node country : countries) {
                createNeighbours(country, countries);
            }
        }
    }

    private void createNeighbours(Node country, List<Node> countries) {
        for (String neighbourCode : country.getNeighboursStrings()) {
            Node potentialNeighbour = findNode(neighbourCode, countries);
            if (potentialNeighbour != null) {
                country.addNeighbour(potentialNeighbour);
            }
        }
    }

    public Node findNodeFromCopy(String countryName) {
        return findNode(countryName, getCopyCountries());
    }

    public Node findNode(String countryName, List<Node> countries) {
        Node country = null;
        if (countries.stream().anyMatch(countryNode -> countryNode.getNameCode().equalsIgnoreCase(countryName))) {
            country = countries.stream().filter(countryNode -> countryNode.getNameCode().equalsIgnoreCase(countryName)).findFirst().get();
        }
        return country;
    }

    public List<Node> getCopyCountries() {
        List<Node> countriesCopy = new ArrayList<>();
        for (Node node : countries) {
            countriesCopy.add(new Node(node));
        }
        for (Node country : countriesCopy) {
            createNeighbours(country, countriesCopy);
        }
        return countriesCopy;
    }
}
