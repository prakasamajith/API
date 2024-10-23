package com.omrbranch.flight;

import org.testng.annotations.Test;
import com.omrbranch.baseclass.BaseClass;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SingaporeAirlines extends BaseClass {
	Integer id;

	@Test(priority = 1)
	public void createFlight() {

		addHeader("Content-Type", "application/json");
		addBody("{\r\n" + "    \"flightName\": \"AirIndia Express\",\r\n" + "    \"Country\": \"India\",\r\n"
				+ "    \"Destinations\": \"37\",\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/AirIndia_Express\"\r\n" + "}");
		Response response = addReqType("POST", "https://omrbranch.com/api/flights");
		JsonPath jsonPath = response.jsonPath();
		Object object = jsonPath.get("data.id");
		id = (Integer) object;
		System.out.println(id);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		String resBodyAsPrettyString = getResBodyAsPrettyString(response);
		System.out.println(resBodyAsPrettyString);
	}

	@Test(priority = 2)
	public void singleFlight() {
		addHeader("Content-Type", "application/json");
		Response response = addReqType("GET", "https://omrbranch.com/api/flight/" + id);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		String resBodyAsPrettyString = getResBodyAsPrettyString(response);
		System.out.println(resBodyAsPrettyString);
	}

	@Test(priority = 3)
	public void updateFlight() {
		addHeader("Content-Type", "application/json");
		addBody("{\r\n" + "    \"flightName\": \"Singapore Airlines\",\r\n" + "    \"Country\": \"Singapore\",\r\n"
				+ "    \"Destinations\": \"37\",\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Singapore_Airlines\"\r\n" + "}");
		Response response = addReqType("PUT", "https://omrbranch.com/api/flight/" + id);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		String resBodyAsPrettyString = getResBodyAsPrettyString(response);
		System.out.println(resBodyAsPrettyString);
	}

	@Test(priority = 4)
	public void updateSingleField() {
		addHeader("Content-Type", "application/json");
		addBody("{\r\n" + "    \"Destinations\": 77\r\n" + "}");
		Response response = addReqType("PATCH", "https://omrbranch.com/api/flight/" + id);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		String resBodyAsPrettyString = getResBodyAsPrettyString(response);
		System.out.println(resBodyAsPrettyString);

	}

	@Test(priority = 5)
	public void deleteFlight() {
		addHeader("Content-Type", "application/json");
		Response response = addReqType("DELETE", "https://omrbranch.com/api/flight/" + id);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

	}

	@Test(priority = 6)
	public void listFlight() {
		addHeader("Content-Type", "application/json");
		Response response = addReqType("GET", "https://omrbranch.com/api/flights?page=1628");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		String resBodyAsPrettyString = getResBodyAsPrettyString(response);
		System.out.println(resBodyAsPrettyString);

	}

}
