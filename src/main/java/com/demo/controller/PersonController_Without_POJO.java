package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
public class PersonController_Without_POJO {
	
	@Autowired
	private XmlMapper xmlMapper;

	/*
	 * @GetMapping(value = "/noPOJO/xml/serialize", produces =
	 * MediaType.APPLICATION_XML_VALUE) public ResponseEntity<?> serializePerson()
	 * throws Exception {
	 * 
	 * String xmlData="<Person>" + "    <name>Eknath Shinde</name>" +
	 * "    <age>50</age>" + "    <Person_Address>" +
	 * "        <street>Jui Nagar</street>" + "        <city>Mumbai</city>" +
	 * "        <postalCode>400671</postalCode>" + "    </Person_Address>" +
	 * "</Person>";
	 * 
	 * return new ResponseEntity<>(xmlData, HttpStatus.OK); }
	 */
	@PostMapping(value = "/xml-to-json", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> xmlToJson(@RequestBody String xmlData) {
        try {
            // Create an XmlMapper to parse XML data
            XmlMapper xmlMapper = new XmlMapper();

            // Parse the XML data into a JsonNode
            JsonNode jsonNode = xmlMapper.readTree(xmlData);

            // Serialize the JsonNode to a JSON string
            String jsonData = jsonNode.toPrettyString();

            return ResponseEntity.ok(jsonData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing XML data: " + e.getMessage());
        }
    }
	
	@PostMapping(value = "/noPOJO/xml/deserialize", consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deserializationPerson(@RequestBody String person) throws Exception {
		// Parse the XML data into a JsonNode
		JsonNode jsonNode=xmlMapper.readTree(person);
		
        // Serialize the JsonNode to JSON
		ObjectMapper objMapper= new ObjectMapper();
		String jsonData=objMapper.writeValueAsString(jsonNode);
	
		 return new ResponseEntity<>( jsonData,HttpStatus.CREATED);
	}
	/*
	 * @GetMapping(value = "/noPOJO/json/serialize", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<?>
	 * serializePersonObj() throws Exception { ObjectMapper objMapper=new
	 * ObjectMapper();
	 * 
	 * //ObjectNode is a Jackson class that represents a JSON object. ObjectNode
	 * objPerson=objMapper.createObjectNode(); objPerson.put("name","Allu Arjun");
	 * objPerson.put("age", 41);
	 * 
	 * ObjectNode objAddress=objMapper.createObjectNode();
	 * objAddress.put("street","chirutha road"); objAddress.put("city","chennai");
	 * objAddress.put("postalCode","436701");
	 * 
	 * objPerson.set("Person_Address", objAddress);
	 * 
	 * return new ResponseEntity<>(objPerson, HttpStatus.OK); }
	 */
	
	@PostMapping(value = "/json-to-xml", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> jsonToXml(@RequestBody String jsonData) {
        try {
            // Create an JSONMapper to parse JSON data
			ObjectMapper objMapper=new ObjectMapper();

            // Parse the JSON data into a JsonNode
            JsonNode jsonNode = objMapper.readTree(jsonData);

            // Serialize the JsonNode to a JSON string
            String xmlData = xmlMapper.writeValueAsString(jsonNode);

            return ResponseEntity.ok(xmlData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing XML data: " + e.getMessage());
        }
    }
	@PostMapping(value = "/noPOJO/json/deserialize", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> deserializationPersonObj(@RequestBody String person) throws Exception {
		
		ObjectMapper objMapper=new ObjectMapper();
		// Parse the JSON data into a JsonNode
		JsonNode jsonNode = objMapper.readTree(person);
		// Serialize the JsonNode to XML String
		String jsonData=xmlMapper.writeValueAsString(jsonNode);
		return new ResponseEntity<>( jsonData,HttpStatus.CREATED);
	}
}
