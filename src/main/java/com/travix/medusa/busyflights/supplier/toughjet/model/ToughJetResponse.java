package com.travix.medusa.busyflights.supplier.toughjet.model;

import com.travix.medusa.busyflights.supplier.commons.model.SearchResponse;
import lombok.Data;

@Data
public class ToughJetResponse implements SearchResponse
{

    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private String outboundDateTime;
    private String inboundDateTime;

}
