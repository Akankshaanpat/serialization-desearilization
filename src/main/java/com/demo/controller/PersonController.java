package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Address;
import com.demo.entity.Person;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class PersonController {
	@Autowired
	private XmlMapper xmlMapper;

	
	@GetMapping(value = "/xml/serialize", produces = MediaType.APPLICATION_XML_VALUE)//JSON TO XML
	public ResponseEntity<?> serializePerson() throws Exception {
		Person person = new Person();
		person.setName("Eknath Shinde");
		person.setAge(50);

		Address address = new Address();
		address.setStreet("Jui Nagar");
		address.setCity("Mumbai");
		address.setPostalCode("400671");

		person.setAddress(address);

		return new ResponseEntity<>(person, HttpStatus.OK);
	}

	@PostMapping(value = "/xml/deserialize", consumes = MediaType.APPLICATION_XML_VALUE)//XML TO JSON
	public Person deserializationPerson(@RequestBody String person) throws Exception {

		Person personObj = xmlMapper.readValue(person, Person.class);
		System.out.println(personObj);
		return personObj;
	}

	@GetMapping(value = "/json/serialize", produces = MediaType.APPLICATION_JSON_VALUE)//JAVA OBJ TO JSON
	public ResponseEntity<?> serializePersonObj() throws Exception {
		Person person = new Person();
		person.setName("Amit Kumar");
		person.setAge(28);

		Address address = new Address();
		address.setStreet("Highway street");
		address.setCity("delhi");
		address.setPostalCode("546730");

		person.setAddress(address);

		return new ResponseEntity<>(person, HttpStatus.OK);
	}

	@PostMapping(value = "/json/deserialize", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deserializationPersonObj(@RequestBody String person) throws Exception {
		
//		if(person.trim().startsWith("<")) {
//			
//			//Parse the XML data into JSONNode Object
//		JsonNode jsonNode=xmlMapper.readTree(person);
//		
//		//covert JSONNode Object into XML String 
//		String xmlData=xmlMapper.writeValueAsString(jsonNode);
//		
//		return new ResponseEntity<>(xmlData,HttpStatus.OK);
//		}else {
//			
//			ObjectMapper objectMapper = new ObjectMapper();
//			//Parse the JSON data into JSONNode Object
//			JsonNode jsonNodeTwo=objectMapper.readTree(person);
//			
//			//covert JSONNode Object into JSON String 
//			String jsonData=objectMapper.writeValueAsString(jsonNodeTwo);
//				
//			return new ResponseEntity<>(jsonData,HttpStatus.OK);
ObjectMapper objMapper=new ObjectMapper();
		
		Person 	personObj=objMapper.readValue(person, Person.class);
		log.info("json to person object: "+personObj);
			return new ResponseEntity<>(personObj,HttpStatus.OK);
		}
	}

