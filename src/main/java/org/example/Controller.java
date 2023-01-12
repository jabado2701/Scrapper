package org.example;

import scrappingFunctions.Booking;

public class Controller {
    public void controller() {
        new APICalls(new Booking()).gets();
    }
}