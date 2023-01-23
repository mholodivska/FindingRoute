package com.example.findingroute.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

@Service
public class ApiCallService {

    private static final String urlLink = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";

    public static String getCountriesJson() {
        String resultedCountriesJson = null;
        try {
            URLConnection connection = new URL(urlLink).openConnection();
            InputStream is = connection.getInputStream();
            resultedCountriesJson = new BufferedReader(new InputStreamReader(is))
                    .lines().collect(Collectors.joining("\n"));

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return resultedCountriesJson;
    }
}
