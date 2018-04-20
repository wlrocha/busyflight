package com.travix.medusa.busyflights;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class BusyFlightsApplicationTests {

	public static final String FLIGHT_SEARCH_PATH = "/flight/search";
	public static final String ORIGIN = "LHR";
	public static final String DESTINATION = "AMS";
	public static final String DEPARTURE_DATE = "2011-12-03";
	public static final String RETURN_DATE = "2018-11-01";
	public static final String ORIGIN_KEY = "origin";
	public static final String DESTINATION_KEY = "destination";
	public static final String DEPARTURE_DATE_KEY = "departureDate";
	public static final String RETURN_DATE_KEY = "returnDate";
	public static final String NUMBER_OF_PASSENGERS_KEY = "numberOfPassengers";

	private MultiValueMap<String, String> request;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Before
	public void setupRequest()
	{
		request = new LinkedMultiValueMap<>();
		request.set(ORIGIN_KEY,ORIGIN);
		request.set(DESTINATION_KEY, DESTINATION);
		request.set(DEPARTURE_DATE_KEY,DEPARTURE_DATE);
		request.set(RETURN_DATE_KEY, RETURN_DATE);
		request.set(NUMBER_OF_PASSENGERS_KEY, "2");
	}

	@Test
	public void goodRequestShouldSucceed() throws Exception{

		mvc.perform(get(FLIGHT_SEARCH_PATH).params(request))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void missingOriginShouldFail() throws Exception{
		request.remove(ORIGIN_KEY);
		mvc.perform(get(FLIGHT_SEARCH_PATH).params(request))
			.andExpect(status().is4xxClientError());
	}

	@Test
	public void missingDestinationShouldFail() throws Exception{
		request.remove(DESTINATION_KEY);
		mvc.perform(get(FLIGHT_SEARCH_PATH).params(request))
			.andExpect(status().is4xxClientError());
	}

	@Test
	public void missingDepartureDateShouldFail() throws Exception{
		request.remove(DEPARTURE_DATE_KEY);
		mvc.perform(get(FLIGHT_SEARCH_PATH).params(request))
			.andExpect(status().is4xxClientError());
	}

	@Test
	public void missingReturnDateShouldSucceed() throws Exception{
		request.remove(RETURN_DATE_KEY);
		mvc.perform(get(FLIGHT_SEARCH_PATH).params(request))
		.andExpect(status().isOk());
	}

	@Test
	public void missingNumberOfPassengersShouldFail() throws Exception{
		request.remove(NUMBER_OF_PASSENGERS_KEY);
		mvc.perform(get(FLIGHT_SEARCH_PATH).params(request))
		.andExpect(status().is4xxClientError());
	}

	@Test
	public void invalidNumberOfPassengersShouldFail() throws Exception{
		request.set(NUMBER_OF_PASSENGERS_KEY,"-123");
		mvc.perform(get(FLIGHT_SEARCH_PATH).params(request))
			.andExpect(status().is4xxClientError());
	}

	@Test
	public void moreThanFourPassengersShouldFail() throws Exception{
		request.set(NUMBER_OF_PASSENGERS_KEY,"5");
		mvc.perform(get(FLIGHT_SEARCH_PATH).params(request))
			.andExpect(status().is4xxClientError());
	}

	@Test
	public void invalidDepartureDateFormatShouldFail() throws Exception{
		request.set(DEPARTURE_DATE_KEY,"3000-13-01");
		mvc.perform(get(FLIGHT_SEARCH_PATH).params(request))
			.andExpect(status().is4xxClientError());
	}

	@Test
	public void invalidReturnDateFormatShouldFail() throws Exception{
		request.set(RETURN_DATE_KEY,"31-01");
		mvc.perform(get(FLIGHT_SEARCH_PATH).params(request))
			.andExpect(status().is4xxClientError());
	}
}
