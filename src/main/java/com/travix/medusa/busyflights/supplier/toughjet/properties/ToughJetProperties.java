package com.travix.medusa.busyflights.supplier.toughjet.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "toughJet")
@Data
public class ToughJetProperties
{
	private String endpoint;
}
