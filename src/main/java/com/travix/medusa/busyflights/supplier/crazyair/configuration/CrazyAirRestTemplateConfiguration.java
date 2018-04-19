package com.travix.medusa.busyflights.supplier.crazyair.configuration;

import com.travix.medusa.busyflights.supplier.crazyair.properties.CrazyAirProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@RequiredArgsConstructor
public class CrazyAirRestTemplateConfiguration
{
	private final RestTemplateBuilder restTemplateBuilder;
	private final CrazyAirProperties crazyAirProperties;

	@Bean
	RestTemplate crazyAirRestTemplate()
	{
		return restTemplateBuilder.rootUri(crazyAirProperties.getEndpoint())
										.build();
	}
}
