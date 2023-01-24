package com.example.findingroute.service;

import com.example.findingroute.domain.Node;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class JsonParserService {
    private static final String CCA3 = "cca3";
    private static final String BORDERS = "borders";

    public static List<Node> parse(String countriesJson) {
        List<Node> countries = new ArrayList<>();
        JSONArray countriesArray = new JSONArray(countriesJson);

        for (Object o : countriesArray) {
            if (o instanceof JSONObject next &&
                    next.has(CCA3) && next.get(CCA3) instanceof String cca3 &&
                    next.has(BORDERS) && next.get(BORDERS) instanceof JSONArray bordersJson) {
                List<String> bordersList = bordersJson.toList().stream()
                        .map(Object::toString)
                        .toList();
                countries.add(new Node(cca3, bordersList));
            }
        }

        return countries;
    }
}
