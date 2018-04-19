package com.travix.medusa.busyflights.supplier.crazyair.client.impl;

import com.travix.medusa.busyflights.supplier.crazyair.client.CrazyAirClient;
import com.travix.medusa.busyflights.supplier.crazyair.model.CrazyAirRequest;
import com.travix.medusa.busyflights.supplier.crazyair.model.CrazyAirResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CrazyAirClientImpl implements CrazyAirClient
{
	public static final String SEARCH_PATH = "/";
	private final RestTemplate crazyAirRestTemplate;

	@Override
	public List<CrazyAirResponse> search(CrazyAirRequest request) throws Exception
	{
		ResponseEntity<CrazyAirResponse[]> result = crazyAirRestTemplate.postForEntity(SEARCH_PATH, request, CrazyAirResponse[].class);
		if(result.getStatusCode().is2xxSuccessful())
		{
			return Arrays.asList(result.getBody());
		}
		throw new Exception("Error on crazyAir search:"+result.getStatusCode().getReasonPhrase()+result.getBody());
	}
}
