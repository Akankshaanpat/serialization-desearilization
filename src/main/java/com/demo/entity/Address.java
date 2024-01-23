package com.demo.entity;



import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
//@JsonPropertyOrder({"city","street","postalcode"})
public class Address {
	
	private String street;
	private String city;
	private String postalCode;
}
