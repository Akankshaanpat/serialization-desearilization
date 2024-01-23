package com.demo.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Configuration
public class MessageConverterConfig {

	@Bean
	XmlMapper getXmlMapper() {
		return new XmlMapper();
	}


	@Bean
	HttpMessageConverters customConverters() { //convert the httprequest 
		ObjectMapper objMapper = new ObjectMapper();//json conversion
		XmlMapper xmlMapper = new XmlMapper();  //xml conversion

		// Create message converters for JSON and XML
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(objMapper);
		MappingJackson2XmlHttpMessageConverter xmlConverter = new MappingJackson2XmlHttpMessageConverter(xmlMapper);

		return new HttpMessageConverters(jsonConverter, xmlConverter);
	}
}
