package com.oopsmails.springboot.mockbackend.multiplecalls;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@Slf4j
public class ApiClientSimple {
    public String callApi(String url) throws IOException {
        log.info("calling url: [{}]", url);
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        try (InputStream responseStream = connection.getInputStream()) {
            return new String(responseStream.readAllBytes());
        }
    }
}
