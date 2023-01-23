package com.example.findingroute.service;

import com.example.findingroute.domain.Node;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class JsonParserService {

    public static List<Node> parse(String countriesJson) {
        List<Node> countries = new ArrayList<>();
        JSONArray countriesArray = new JSONArray(countriesJson);

        for (Object o : countriesArray) {
            if (o instanceof JSONObject next) {
                if (next.has("cca3") && next.get("cca3") instanceof String cca3 &&
                next.has("borders") && next.get("borders") instanceof JSONArray bordersJson) {
                    List<String> bordersList = bordersJson.toList().stream()
                            .map(object -> Objects.toString(object, null))
                            .toList();
                    countries.add(new Node(cca3, bordersList));
                }
            }
        }

        return countries;
    }
}
