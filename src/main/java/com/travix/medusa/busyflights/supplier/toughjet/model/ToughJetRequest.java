package com.travix.medusa.busyflights.supplier.toughjet.model;

import lombok.Data;


@Data
public class ToughJetRequest {

    private String from;
    private String to;
    private String outboundDate;
    private String inboundDate;
    private int numberOfAdults;

}
