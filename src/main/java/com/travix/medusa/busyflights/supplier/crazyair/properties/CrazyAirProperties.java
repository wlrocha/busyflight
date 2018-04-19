package com.travix.medusa.busyflights.supplier.crazyair.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "crazyAir")
@Data
public class CrazyAirProperties
{
	private String endpoint;
}
