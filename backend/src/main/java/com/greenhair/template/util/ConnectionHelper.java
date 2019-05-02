package com.greenhair.template.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.springframework.stereotype.Component;

@Component
public class ConnectionHelper {
    
    public StringBuffer getContentfromConnection(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content;
    }
}