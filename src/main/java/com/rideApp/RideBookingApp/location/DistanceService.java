package com.rideApp.RideBookingApp.location;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class DistanceService {

    @Value("${openroute.api.key}")
    private String apiKey;

    private final RestClient restClient = RestClient.builder().build();


    // =========================================================
    // Main method
    // Source + Destination
    //        ↓
    // Coordinates
    //        ↓
    // Road distance
    //        ↓
    // Distance in KM
    // =========================================================

    public double calculateDistance(String source, String destination) {

        // 1. Get source coordinates
        double[] sourceCoordinates = getCoordinates(source);

        // 2. Get destination coordinates
        double[] destinationCoordinates = getCoordinates(destination);

        // 3. Calculate actual road distance
        return getRoadDistance(
                sourceCoordinates,
                destinationCoordinates
        );
    }


    // =========================================================
    // Geocoding
    // Location name → Longitude + Latitude
    // =========================================================

    private double[] getCoordinates(String location) {

        Map response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("api.openrouteservice.org")
                        .path("/geocode/search")
                        .queryParam("api_key", apiKey)
                        .queryParam("text", location)
                        .build())
                .retrieve()
                .body(Map.class);


        // Get features
        List<Map<String, Object>> features =
                (List<Map<String, Object>>) response.get("features");


        // Location not found
        if (features == null || features.isEmpty()) {

            throw new RuntimeException(
                    "Location not found: " + location
            );
        }


        // Take first result
        Map<String, Object> firstFeature =
                features.get(0);


        // Get geometry
        Map<String, Object> geometry =
                (Map<String, Object>) firstFeature.get("geometry");


        // Get coordinates
        List<Number> coordinates =
                (List<Number>) geometry.get("coordinates");


        // IMPORTANT:
        // OpenRouteService returns:
        // [longitude, latitude]

        double longitude =
                coordinates.get(0).doubleValue();

        double latitude =
                coordinates.get(1).doubleValue();


        return new double[]{
                longitude,
                latitude
        };
    }


    // =========================================================
    // Directions API
    // Coordinates → Road Distance
    // =========================================================

    private double getRoadDistance(
            double[] source,
            double[] destination) {


        Map<String, Object> requestBody = Map.of(

                "coordinates",
                new double[][]{
                        source,
                        destination
                },

                // We don't need turn-by-turn instructions
                "instructions",
                false,

                // We don't need route geometry
                "geometry",
                false
        );


        Map response = restClient.post()

                .uri(
                        "https://api.openrouteservice.org/v2/directions/driving-car"
                )

                .header(
                        "Authorization",
                        apiKey
                )

                .header(
                        "Content-Type",
                        "application/json"
                )

                .body(requestBody)

                .retrieve()

                .body(Map.class);


        // Get routes
        List<Map<String, Object>> routes =
                (List<Map<String, Object>>) response.get("routes");


        if (routes == null || routes.isEmpty()) {

            throw new RuntimeException(
                    "Unable to calculate route"
            );
        }


        // First route
        Map<String, Object> route =
                routes.get(0);


        // Get summary
        Map<String, Object> summary =
                (Map<String, Object>) route.get("summary");


        // Distance is returned in meters
        Number distance =
                (Number) summary.get("distance");


        // Convert meters → kilometers
        return distance.doubleValue() / 1000.0;
    }
}