package com.example.cityswift.server.util;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DistanceCalculator {
    public static String[] geocodeAddress(String address) {
        HttpClient client = HttpClient.newHttpClient();
        String geocodingUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + address;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(geocodingUrl)).header("User-Agent", "Java HttpClient").build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        String responseBody = response.body();

        String lat = extractValue(responseBody, "\"lat\":\"", "\"");
        String lon = extractValue(responseBody, "\"lon\":\"", "\"");

        return new String[]{lat, lon};
    }

    private static String extractValue(String source, String key, String endDelimiter) {
        int start = source.indexOf(key);
        if (start < 0) {
            return null;
        }
        start += key.length();
        int end = source.indexOf(endDelimiter, start);
        if (end < 0) {
            return null;
        }
        return source.substring(start, end);
    }

    public static double calculateDistance(String[] origin, String[] destination) {
        double lat1 = Math.toRadians(Double.parseDouble(origin[0]));
        double lon1 = Math.toRadians(Double.parseDouble(origin[1]));
        double lat2 = Math.toRadians(Double.parseDouble(destination[0]));
        double lon2 = Math.toRadians(Double.parseDouble(destination[1]));

        double earthRadius = 6371.01;
        double distance = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.cos(lon1 - lon2)) * earthRadius;

        return distance;
    }

}
