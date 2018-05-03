package com.travix.medusa.busyflights.supplier.crazyair.model;

import com.travix.medusa.busyflights.supplier.commons.model.SearchResponse;
import lombok.Data;

@Data
public class CrazyAirResponse implements SearchResponse
{

    private String airline;
    private double price;
    private String cabinclass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;
}
