package scrappingFunctions;

import java.io.IOException;

public interface HotelScrapper {
    String location(String name) throws IOException;

    String services(String name) throws IOException;

    String ratings(String name) throws IOException;

    String comments(String name) throws IOException;
}