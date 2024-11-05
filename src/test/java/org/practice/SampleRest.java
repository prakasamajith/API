package org.practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SampleRest {

	public static void main(String[] args) {
		SampleRest sample = new SampleRest();
//		sample.createFlight();
//		sample.updateFlight();
		sample.updatePatch();
//		sample.getFlight();
//		sample.deleteFlight();
	}

	public void createFlight() {
		RequestSpecification reqSpec;
		// 1.Initialize the Rest Assured Class
		reqSpec = RestAssured.given();
		// 2.Pass the Header name, Body(Req Body , Payload) , Auth
		reqSpec = reqSpec.header("Content-Type", "application/json");
		// 3.Payload , Req Body
		reqSpec = reqSpec.body("{\r\n" + "    \"flightName\": \"AirIndia Express\",\r\n"
				+ "    \"Country\": \"India\",\r\n" + "    \"Destinations\": \"37\",\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/AirIndia_Express\"\r\n" + "}");
		// 4.Req Type , Endpoint
		Response response = reqSpec.post("https://omrbranch.com/api/flights");
		// Status Code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		// Res body-------->AsString
		String asString = response.asString();
		System.out.println(asString);
		// Res body--------->AsPrettyString
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);

	}

	public void updateFlight() {
		RequestSpecification update;
		update = RestAssured.given();
		update = update.header("Content-Type", "application/json");
		update = update.body("{\r\n" + "    \"flightName\": \"Singapore Airlines\",\r\n"
				+ "    \"Country\": \"Singapore\",\r\n" + "    \"Destinations\": \"77\",\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/AirIndia_Express\"\r\n" + "}");
		Response response = update.put("https://omrbranch.com/api/flight/34398");
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		String asString = response.asString();
		System.out.println(asString);
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
	}

	public void updatePatch() {
		RequestSpecification updateSingle;
		updateSingle = RestAssured.given();
		updateSingle.header("Conten-Type","application/json");
		updateSingle.body("{\r\n"
	    		+ "    \"Destinations\": 51\r\n"
	    		+ "}");
	    Response response = updateSingle.patch("https://omrbranch.com/api/flight/34398");
	    int statusCode = response.getStatusCode();
	    System.out.println(statusCode);
	    String asPrettyString = response.asPrettyString();
	    System.out.println(asPrettyString);
	}

	public void getFlight() {
		RequestSpecification reqGet;
		reqGet = RestAssured.given();
		Response response = reqGet.get("https://omrbranch.com/api/flight/34398");
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
	}

	public void deleteFlight() {
		RequestSpecification delete;
		delete = RestAssured.given();
		Response response = delete.delete("https://omrbranch.com/api/flight/34398");
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
	
	}

}
