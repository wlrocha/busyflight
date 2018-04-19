package com.travix.medusa.busyflights.supplier.crazyair.model;

import lombok.Data;

@Data
public class CrazyAirResponse {

    private String airline;
    private double price;
    private String cabinclass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;
}
