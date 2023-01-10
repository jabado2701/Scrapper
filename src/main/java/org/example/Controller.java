package org.example;

import java.io.IOException;

public class Controller {
    public void controller() throws IOException {
        new GetFunctions(new Booking()).gets();
    }

}
