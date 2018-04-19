package com.travix.medusa.busyflights.supplier.toughjet.configuration;

import com.travix.medusa.busyflights.supplier.toughjet.mapper.ToughJetRequestMapper;
import com.travix.medusa.busyflights.supplier.toughjet.mapper.ToughJetResponseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ToughJetModelMapperConfiguration
{
	@Bean
	public ModelMapper toughJetModelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(new ToughJetRequestMapper());
		mapper.addMappings(new ToughJetResponseMapper());
		return mapper;
	}
}
