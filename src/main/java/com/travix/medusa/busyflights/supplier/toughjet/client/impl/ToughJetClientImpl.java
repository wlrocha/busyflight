package com.travix.medusa.busyflights.supplier.toughjet.client.impl;

import com.travix.medusa.busyflights.supplier.toughjet.client.ToughJetClient;
import com.travix.medusa.busyflights.supplier.toughjet.model.ToughJetRequest;
import com.travix.medusa.busyflights.supplier.toughjet.model.ToughJetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ToughJetClientImpl implements ToughJetClient
{
	public static final String SEARCH_PATH = "/";
	private final RestTemplate toughJetRestTemplate;

	@Override
	public List<ToughJetResponse> search(ToughJetRequest request) throws Exception
	{
		ResponseEntity<ToughJetResponse[]> result = toughJetRestTemplate.postForEntity(SEARCH_PATH, request, ToughJetResponse[].class);
		if(result.getStatusCode().is2xxSuccessful())
		{
			return Arrays.asList(result.getBody());
		}
		throw new Exception("Error on toughJet search:"+result.getStatusCode().getReasonPhrase()+result.getBody());
	}
}
