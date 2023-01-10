package org.example;


import java.io.IOException;

public interface HotelScrapper {

    String ubication(String name) throws IOException;

    String services(String name) throws IOException;

    String ratings(String name) throws IOException;

    String comments(String name) throws IOException;


}
