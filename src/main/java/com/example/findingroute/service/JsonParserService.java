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
            JSONObject next = (JSONObject) o;
            String cca3 = (String) next.get("cca3");
            JSONArray bordersJson = (JSONArray) next.get("borders");
            List<String> bordersList = bordersJson.toList().stream()
                    .map(object -> Objects.toString(object, null))
                    .toList();
            countries.add(new Node(cca3, bordersList));
        }

        return countries;
    }
}
