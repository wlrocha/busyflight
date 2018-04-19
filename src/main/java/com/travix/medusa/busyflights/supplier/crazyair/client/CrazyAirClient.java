package com.travix.medusa.busyflights.supplier.crazyair.client;

import com.travix.medusa.busyflights.supplier.crazyair.model.CrazyAirRequest;

import java.util.List;


public interface CrazyAirClient
{
	List search(CrazyAirRequest request) throws Exception;
}
