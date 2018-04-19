package com.travix.medusa.busyflights.supplier.crazyair.configuration;

import com.travix.medusa.busyflights.supplier.crazyair.mapper.CrazyAirRequestMapper;
import com.travix.medusa.busyflights.supplier.crazyair.mapper.CrazyAirResponseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CrazyAirModelMapperConfiguration
{
	@Bean
	public ModelMapper crazyAirModelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(new CrazyAirRequestMapper());
		mapper.addMappings(new CrazyAirResponseMapper());
		return mapper;
	}

}
