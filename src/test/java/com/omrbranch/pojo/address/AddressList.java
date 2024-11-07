package com.omrbranch.pojo.address;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressList {
	private int id;
	private String address_code;
	private int user_id;
	private String first_name;
	private String last_name;
	private String mobile;
	private String apartment;
	private String address;
	private int country_id;
	private int state_id;
	private int city_id;
	private String zipcode;
	private String lat;
	private String lng;
	@JsonProperty("default")
	private int mydefault;
	private String status;
	private String address_type;
	private String created_at;
	private String updated_at;
	private String city;
	private String state;
	private String country;
	private int is_selected;
}
