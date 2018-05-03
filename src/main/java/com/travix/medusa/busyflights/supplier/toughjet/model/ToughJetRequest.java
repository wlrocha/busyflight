package com.travix.medusa.busyflights.supplier.toughjet.model;

import com.travix.medusa.busyflights.supplier.commons.model.SearchRequest;
import lombok.Data;


@Data
public class ToughJetRequest implements SearchRequest
{

    private String from;
    private String to;
    private String outboundDate;
    private String inboundDate;
    private int numberOfAdults;

}
