package com.example.findingroute.service;

import com.example.findingroute.domain.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NodeService {
    private static List<Node> countries = new ArrayList<>();

    public NodeService() {
        initCountries();
    }

    private void initCountries() {
        String countriesJson = ApiCallService.getCountriesJson();
        if (countriesJson != null) {
            countries = JsonParserService.parse(countriesJson);
            for (Node country : countries) {
                createNeighbours(country);
            }
        }
    }

    private void createNeighbours(Node country) {
        for (String neighbourCode : country.getNeighboursStrings()) {
            Node potentialNeighbour = findTheNode(neighbourCode);
            if (potentialNeighbour != null) {
                country.addNeighbour(potentialNeighbour);
            }
        }
    }

    public static Node findTheNode(String countryName) {
        Node country = null;
        if (countries.stream().anyMatch(countryNode -> countryNode.getName().equalsIgnoreCase(countryName))) {
            country = countries.stream().filter(countryNode -> countryNode.getName().equalsIgnoreCase(countryName)).findFirst().get();
        }
        return country;
    }
}
