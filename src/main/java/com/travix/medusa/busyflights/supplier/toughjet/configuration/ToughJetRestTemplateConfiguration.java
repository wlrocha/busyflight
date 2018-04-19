package com.travix.medusa.busyflights.supplier.toughjet.configuration;

import com.travix.medusa.busyflights.supplier.toughjet.properties.ToughJetProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@RequiredArgsConstructor
public class ToughJetRestTemplateConfiguration
{
	private final RestTemplateBuilder restTemplateBuilder;
	private final ToughJetProperties toughJetProperties;

	@Bean
	RestTemplate toughJetRestTemplate()
	{
		return restTemplateBuilder.rootUri(toughJetProperties.getEndpoint()).build();
	}
}
