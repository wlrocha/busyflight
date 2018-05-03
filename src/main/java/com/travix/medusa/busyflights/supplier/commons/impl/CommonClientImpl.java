package com.travix.medusa.busyflights.supplier.commons.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.supplier.commons.CommonClient;
import com.travix.medusa.busyflights.supplier.commons.model.SearchRequest;
import com.travix.medusa.busyflights.supplier.commons.model.SearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommonClientImpl implements CommonClient
{

	private <R extends SearchResponse, T extends SearchRequest> List<R> search(T request, String url, Class<R[]> clazz) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<R[]> result = restTemplate.postForEntity(url, request, clazz);
		if(result.getStatusCode().is2xxSuccessful())
		{
			return Arrays.asList(result.getBody());
		}
		throw new Exception("Error on search:"+result.getStatusCode().getReasonPhrase()+result.getBody());

	}


	public <R extends SearchResponse, T extends SearchRequest> List<BusyFlightsResponse> search(BusyFlightsRequest request, ModelMapper mapper, String url, Class<T> requestClazz, Class<R[]> responseClazz) throws Exception
	{
		SearchRequest searchRequest = mapper.map(request, requestClazz);
		List<R> response = search(searchRequest, url, responseClazz);
		return response.stream().map( r -> mapper.map(r, BusyFlightsResponse.class)).collect(Collectors.toList());
	}
}
