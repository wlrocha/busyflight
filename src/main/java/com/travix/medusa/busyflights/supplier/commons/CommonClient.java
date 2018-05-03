package com.travix.medusa.busyflights.supplier.commons;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.supplier.commons.model.SearchRequest;
import com.travix.medusa.busyflights.supplier.commons.model.SearchResponse;
import org.modelmapper.ModelMapper;

import java.util.List;


public interface CommonClient
{

	<R extends SearchResponse, T extends SearchRequest> List<BusyFlightsResponse> search(BusyFlightsRequest request,
																													ModelMapper mapper,
																													String url,
																													Class<T> requestClazz,
																													Class<R[]> responseClazz) throws Exception;

}
