package com.travix.medusa.busyflights;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class BusyFlightsApplicationTests {

	public static final String FLIGHT_SEARCH_PATH = "/flight/search";
	public static final String ORIGIN = "LHR";
	public static final String DESTINATION = "AMS";
	public static final String DEPARTURE_DATE = "2011-12-03";
	public static final String RETURN_DATE = "2018-11-01";

	private BusyFlightsRequest request;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Before
	public void setupRequest()
	{
		request = new BusyFlightsRequest();
		request.setOrigin(ORIGIN);
		request.setDestination(DESTINATION);
		request.setDepartureDate(DEPARTURE_DATE);
		request.setReturnDate(RETURN_DATE);
		request.setNumberOfPassengers(2);
	}

	@Test
	public void goodRequestShouldSucceed() throws Exception{

		mvc.perform(post(FLIGHT_SEARCH_PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request))
					)
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void missingOriginShouldFail() throws Exception{
		request.setOrigin(null);
		mvc.perform(post(FLIGHT_SEARCH_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))
		).andExpect(status().is4xxClientError());
	}

	@Test
	public void missingDestinationShouldFail() throws Exception{
		request.setDestination(null);
		mvc.perform(post(FLIGHT_SEARCH_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))
		).andExpect(status().is4xxClientError());
	}

	@Test
	public void missingDepartureDateShouldFail() throws Exception{
		request.setDepartureDate(null);
		mvc.perform(post(FLIGHT_SEARCH_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))
		).andExpect(status().is4xxClientError());
	}

	@Test
	public void missingReturnDateShouldSucceed() throws Exception{
		request.setReturnDate(null);
		mvc.perform(post(FLIGHT_SEARCH_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))
		)
		.andExpect(status().isOk());
	}

	@Test
	public void missingNumberOfPassengersShouldFail() throws Exception{
		request.setNumberOfPassengers(0);
		mvc.perform(post(FLIGHT_SEARCH_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))
		)
		.andExpect(status().is4xxClientError());
	}

	@Test
	public void invalidNumberOfPassengersShouldFail() throws Exception{
		request.setNumberOfPassengers(-123);
		mvc.perform(post(FLIGHT_SEARCH_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))
		).andExpect(status().is4xxClientError());
	}

	@Test
	public void moreThanFourPassengersShouldFail() throws Exception{
		request.setNumberOfPassengers(5);
		mvc.perform(post(FLIGHT_SEARCH_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))
		).andExpect(status().is4xxClientError());
	}

	@Test
	public void invalidDepartureDateFormatShouldFail() throws Exception{
		request.setDepartureDate("3000-13-01");
		mvc.perform(post(FLIGHT_SEARCH_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))
		).andExpect(status().is4xxClientError());
	}

	@Test
	public void invalidReturnDateFormatShouldFail() throws Exception{
		request.setReturnDate("31-01");
		mvc.perform(post(FLIGHT_SEARCH_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))
		).andExpect(status().is4xxClientError());
	}
}
