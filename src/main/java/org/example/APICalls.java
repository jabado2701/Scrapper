package org.example;


import scrappingFunctions.HotelScrapper;

import static scrappingFunctions.Tools.base;
import static spark.Spark.get;

public class APICalls {

    private final HotelScrapper hotelScrapper;

    public APICalls(HotelScrapper hotelScrapper) {
        this.hotelScrapper = hotelScrapper;
    }

    void gets() {

        try {
            get("/hotels/:name", ((request, response) -> {
                String url = base + request.params("name");
                response.header("content-type", "application/json");
                return hotelScrapper.location(url);
            }));
            get("/hotels/:name/services", ((request, response) -> {
                String url = base + request.params("name");
                response.header("content-type", "application/json");
                return hotelScrapper.services(url);
            }));
            get("/hotels/:name/comments", ((request, response) -> {
                String url = base + request.params("name");
                response.header("content-type", "application/json");
                return hotelScrapper.comments(url);
            }));
            get("/hotels/:name/ratings", ((request, response) -> {
                String url = base + request.params("name");
                response.header("content-type", "application/json");
                return hotelScrapper.ratings(url);
            }));
        } catch (Exception e) {
            System.out.println("Error al obtener el código HTML");
        }

    }
}
