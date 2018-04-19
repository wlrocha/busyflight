package com.travix.medusa.busyflights.supplier.crazyair.model;

import lombok.Data;

@Data
public class CrazyAirRequest {

    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private int passengerCount;
}
