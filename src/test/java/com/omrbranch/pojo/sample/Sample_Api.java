package com.omrbranch.pojo.sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.category.pojo.CategoryList_Output_Pojo;
import com.omrbranch.category.pojo.ChildCatList;
import com.omrbranch.category.pojo.DataClass;
import com.omrbranch.pojo.address.AddUserAddress_Input_Pojo;
import com.omrbranch.pojo.address.AddUserAddress_Output_Pojo;
import com.omrbranch.pojo.address.AddressList;
import com.omrbranch.pojo.address.CityList_Input_Pojo;
import com.omrbranch.pojo.address.DeleteAddress_Input_Pojo;
import com.omrbranch.pojo.address.DeleteAddress_Output_Pojo;
import com.omrbranch.pojo.address.GetUserAddress_Output_Pojo;
import com.omrbranch.pojo.address.SearchResults;
import com.omrbranch.pojo.address.Search_Input_Pojo;
import com.omrbranch.pojo.address.Search_Output_Pojo;
import com.omrbranch.pojo.address.StateList;
import com.omrbranch.pojo.address.StateListAndCityList_Output_Pojo;
import com.omrbranch.pojo.address.UpdateUserAddress_Input_Pojo;
import com.omrbranch.pojo.address.UpdateUserAddress_Output_Pojo;
import com.omrbranch.pojo.login.Login_Output_Pojo;
import com.omrbranch.pojo.login.UserRole;
import com.omrbranch.product.pojo.Datum;
import com.omrbranch.product.pojo.Product_Input_Pojo;
import com.omrbranch.product.pojo.Product_Output_Pojo;
import com.omrbranch.product.pojo.Variation;
import com.omrbranch.product.pojo.VariationOption;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Sample_Api extends BaseClass {
	String stateIdText;
	String logtoken;
	int stateIdNum;
	int cityIdNum;
	String address_idNum;
	int addressId;
	String categoryId;
	String nutsIdText;

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
		logtoken = login_Output_Pojo.getData().getLogtoken();
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
				stateIdNum = eachStateList.getId();
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
				cityIdNum = eachCityList.getId();
				System.out.println(cityIdNum);
				break;
			}

		}
	}

	@Test(priority = 4)
	public void addUserAddress() {

		// 1.Add Headers
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		// 2.Payload/Req Body
		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = new AddUserAddress_Input_Pojo("Prakasam", "Ajith",
				"9600809646", "PD", stateIdNum, cityIdNum, 100, "636105", "Attur", "Home");
		addBody(addUserAddress_Input_Pojo);
		// 3.Request type
		Response response = addReqType("POST", "https://omrbranch.com/api/addUserAddress");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String actMessage = addUserAddress_Output_Pojo.getMessage();
		System.out.println(actMessage);
		int address_id = addUserAddress_Output_Pojo.getAddress_id();
		address_idNum = String.valueOf(address_id);
		System.out.println(address_idNum);
		Assert.assertEquals(actMessage, "Address added successfully", "Verify Address added successfully message");
	}

	@Test(priority = 5)

	public void updateUserAddress() {

		// 1.Add Headers
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		// 2.Payload/Req Body
		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(address_idNum,
				"Prakasam", "Ajith", "9600809646", "PD Apartment", stateIdNum, cityIdNum, 100, "636105", "Attur",
				"Home");
		addBody(updateUserAddress_Input_Pojo);
		// 3.Req type
		Response response = addReqType("PUT", "https://omrbranch.com/api/updateUserAddress");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		UpdateUserAddress_Output_Pojo updateUserAddress_Output_Pojo = response.as(UpdateUserAddress_Output_Pojo.class);
		String actMessage = updateUserAddress_Output_Pojo.getMessage();
		System.out.println(actMessage);
		Assert.assertEquals(actMessage, "Address updated successfully", "Verify Address updated successfully message");
	}

	@Test(priority = 6)
	public void getUserAddress() {

		// 1.Add Header
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		lstHeader.add(h1);
		lstHeader.add(h2);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		// 2.Req type
		Response response = addReqType("GET", "https://omrbranch.com/api/getUserAddress");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
		ArrayList<AddressList> allAddresses = getUserAddress_Output_Pojo.getData();
		for (AddressList addressList : allAddresses) {
			String address_code = addressList.getAddress_code();
			System.out.println(address_code);
			addressId = addressList.getId();
			System.out.println(addressId);
		}
	}

	@Test(priority = 7, enabled = false)
	public void deleteUserAddress() {
		// 1.Add Header
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		// 2.Payload / Req Body
		DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new DeleteAddress_Input_Pojo(95300);
		addBody(deleteAddress_Input_Pojo);
		// 3.Req type
		Response response = addReqType("DELETE", "https://omrbranch.com/api/deleteAddress");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		DeleteAddress_Output_Pojo deleteAddress_Output_Pojo = response.as(DeleteAddress_Output_Pojo.class);
		String actMessage = deleteAddress_Output_Pojo.getMessage();
		System.out.println(actMessage);
		Assert.assertEquals(actMessage, "Address deleted successfully", "Verify Address deleted successfully Message");

	}

	@Test(priority = 8, enabled = false)
	public void searchProduct() {
		// 1.Add Header
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		lstHeader.add(h1);
		lstHeader.add(h2);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		// 2.Payload /Req Body
		Search_Input_Pojo search_Input_Pojo = new Search_Input_Pojo("Nuts");
		addBody(search_Input_Pojo);
		// 3.Req type
		Response response = addReqType("POST", "https://omrbranch.com/api/searchProduct");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Search_Output_Pojo search_Output_Pojo = response.as(Search_Output_Pojo.class);
		ArrayList<SearchResults> data = search_Output_Pojo.getData();
		for (SearchResults results : data) {
			int id = results.getId();
			System.out.println(id);
		}
	}

	@Test(priority = 9)
	public void categoryList() {
		addHeader("accept", "application/json");
		Response response = addReqType("GET", "https://omrbranch.com/api/categoryList");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		CategoryList_Output_Pojo categoryList_Output_Pojo = response.as(CategoryList_Output_Pojo.class);
		ArrayList<DataClass> data = categoryList_Output_Pojo.getData();
		for (DataClass dataClass : data) {
			String dataNames = dataClass.getName();
			System.out.println(dataNames);
			if (dataNames.equals("Grocery")) {
				int groceryId = dataClass.getId();
				System.out.println(groceryId);
			}
			ArrayList<ChildCatList> child_cat_list = dataClass.getChild_cat_list();
			for (ChildCatList dataClass2 : child_cat_list) {
				String childListNames = dataClass2.getName();
				if (childListNames.equals("Fruit & Nuts")) {
					int fruitsAndNutsId = dataClass2.getId();
					categoryId = String.valueOf(fruitsAndNutsId);
					System.out.println(categoryId);
				}
			}
		}
	}

	@Test(priority = 10)
	public void productList() {
		// 1.Add Header
		List<Header> lstHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		lstHeader.add(h1);
		lstHeader.add(h2);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		// 2.Payload / Req Body
		Product_Input_Pojo product_Input_Pojo = new Product_Input_Pojo(categoryId, "0");
		addBody(product_Input_Pojo);
		// 3.Req type
		Response response = addReqType("POST", "https://omrbranch.com/api/productList");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Product_Output_Pojo product_Output_Pojo = response.as(Product_Output_Pojo.class);
		ArrayList<Datum> data = product_Output_Pojo.getData();
		for (Datum datum : data) {
			String productNames = datum.getName();
			if (productNames.equals("Nuts & Seeds - Raw Peanut")) {
				int nutsId = datum.getId();
				nutsIdText = String.valueOf(nutsId);
				System.out.println("Product Id:" + nutsIdText);
			}
			ArrayList<Variation> variations = datum.getVariations();
			for (Variation variationsData : variations) {
				String specifications = variationsData.getSpecifications();
				if (specifications.equals("500 g")) {
					int id2 = variationsData.getId();
					System.out.println("Variation Id:" + id2);
				}
			}
		}
	}
	
	

}
