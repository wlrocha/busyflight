package com.travix.medusa.busyflights.supplier.crazyair.model;

import com.travix.medusa.busyflights.supplier.commons.model.SearchRequest;
import lombok.Data;

@Data
public class CrazyAirRequest implements SearchRequest
{

    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private int passengerCount;
}
