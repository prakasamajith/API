package com.omrbranch.flight;

import org.testng.annotations.Test;
import com.omrbranch.baseclass.BaseClass;
import io.restassured.response.Response;

public class Sample extends BaseClass{

	@Test
	public void login() {
		//1.Header
		addHeader("accept", "application/json");
		//2.Add Basic Auth
		addBasicAuth("ajithprakasam@gmail.com", "Prakasam@77");
		//3.Req Type, Endpoint
		Response response = addReqType("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		String resBodyAsPrettyString = getResBodyAsPrettyString(response);
		System.out.println(resBodyAsPrettyString);
	}
}
