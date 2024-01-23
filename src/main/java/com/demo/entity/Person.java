package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
//@JsonIgnoreProperties({"age","Person_Address"})
//@JsonIncludeProperties({"name"})
public class Person {

	@JsonAlias({ "person_name" })
	private String name;
	// @JsonIgnore
	private Integer age;

	@JsonProperty("Person_Address")
	private Address address;

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", address=" + address + "]";
	}

}
