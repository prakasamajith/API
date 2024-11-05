package com.omrbranch.pojo.sample;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.pojo.address.CityList_Input_Pojo;
import com.omrbranch.pojo.address.StateList;
import com.omrbranch.pojo.address.StateListAndCityList_Output_Pojo;
import com.omrbranch.pojo.login.Login_Output_Pojo;
import com.omrbranch.pojo.login.UserRole;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Sample_Api extends BaseClass {
	String stateIdText;

	@Test(priority = 1)
	public void login() {
		addHeader("accept", "application/json");
		addBasicAuth("ajithprakasam@gmail.com", "Prakasam@77");
		Response response = addReqType("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "Verify Status Code");
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		String first_name = login_Output_Pojo.getData().getFirst_name();
		System.out.println(first_name);
		Assert.assertEquals(first_name, "Prakasam", "Verify First Name");
		String last_name = login_Output_Pojo.getData().getLast_name();
		System.out.println(last_name);
		Assert.assertEquals(last_name, "Ajith", "Verify Last Name");
		int id = login_Output_Pojo.getData().getId();
		System.out.println(id);
		Assert.assertEquals(id, 2928, "Verify Id");
		ArrayList<UserRole> user_role = login_Output_Pojo.getData().getUser_role();
		for (UserRole user : user_role) {
			int id2 = user.getId();
			System.out.println(id2);
			String role = user.getRole();
			System.out.println(role);
			String status = user.getStatus();
			System.out.println(status);
		}
	}

	@Test(priority = 2)
	public void getStateList() {
		// 1.Add Header
		addHeader("accept", "application/json");
		// 2.Req type
		Response response = addReqType("GET", "https://omrbranch.com/api/stateList");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		StateListAndCityList_Output_Pojo stateList_Output_Pojo = response.as(StateListAndCityList_Output_Pojo.class);
		ArrayList<StateList> stateList = stateList_Output_Pojo.getData();
		for (StateList eachStateList : stateList) {
			String stateName = eachStateList.getName();
			if (stateName.equals("Tamil Nadu")) {
				int stateIdNum = eachStateList.getId();
				stateIdText = String.valueOf(stateIdNum);
				System.out.println(stateIdNum);
				break;
			}
		}

	}

	@Test(priority = 3)
	public void getCityList() {
		// 1.Add Headers
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		lstHeader.add(h1);
		lstHeader.add(h2);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		// 2.Payload/Req Body
		CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(stateIdText);
		addBody(cityList_Input_Pojo);
		// 3.Req type
		Response response = addReqType("POST", "https://omrbranch.com/api/cityList");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		StateListAndCityList_Output_Pojo stateListAndCityList_Output_Pojo = response
				.as(StateListAndCityList_Output_Pojo.class);
		ArrayList<StateList> cityList = stateListAndCityList_Output_Pojo.getData();
		for (StateList eachCityList : cityList) {
			String cityName = eachCityList.getName();
			if (cityName.equals("Yercaud")) {
				int cityIdNum = eachCityList.getId();
				System.out.println(cityIdNum);
				break;
			}
			
		}
	}
}
