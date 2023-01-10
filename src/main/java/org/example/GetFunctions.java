package org.example;


import java.io.IOException;


import static org.example.Tools.base;
import static spark.Spark.get;

public class GetFunctions {

    private final HotelScrapper hotelScrapper;

    public GetFunctions(HotelScrapper hotelScrapper) {
        this.hotelScrapper = hotelScrapper;
    }

    void gets() {

        try {
            get("/hotels/:name", ((request, response) -> {
                String url = base + request.params("name");
                response.header("content-type", "application/json");
                return hotelScrapper.ubication(url);
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
