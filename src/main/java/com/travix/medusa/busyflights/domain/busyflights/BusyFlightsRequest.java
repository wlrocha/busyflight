package com.travix.medusa.busyflights.domain.busyflights;

import com.travix.medusa.busyflights.validation.DateFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;


@Data
public class BusyFlightsRequest
{
    @NotNull(message = "The origin is required")
    @Length(min=3, max=3, message = "The Origin should be a 3 letter IATA code")
    private String origin;
    @NotNull(message = "The destination is required")
    @Length(min=3, max=3, message = "The Destination should be a 3 letter IATA code")
    private String destination;
    @NotNull(message = "Departure date is required")
    @DateFormat
    private String departureDate;
    @DateFormat
    private String returnDate;
    @Range(min=1, max = 4, message = "The number of passengers should be between 1 and 4")
    private int numberOfPassengers;
}
