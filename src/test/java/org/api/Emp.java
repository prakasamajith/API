package org.api;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Emp {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(new FileReader(System.getProperty("user.dir") + ("\\src\\test\\resources\\Emp.json")));
		JSONObject jsonObject = (JSONObject) object;
		Object object2 = jsonObject.get("page");
		System.out.println(object2);
		Object object3 = jsonObject.get("per_page");
		System.out.println(object3);
		Object object4 = jsonObject.get("total");
		System.out.println(object4);
		Object object5 = jsonObject.get("total_pages");
		System.out.println(object5);
		Object object6 = jsonObject.get("data");
		JSONArray array = (JSONArray) object6;
	for (int i = 0; i < array.size(); i++) {
			Object object7 = array.get(i);
			JSONObject jsonObject2 = (JSONObject) object7;
			Object object8 = jsonObject2.get("id");
			System.out.println(object8);
			Object object9 = jsonObject2.get("flightName");
			System.out.println(object9);
			Object object10 = jsonObject2.get("Country");
			System.out.println(object10);
			Object object11 = jsonObject2.get("Destinations");
			System.out.println(object11);
			Object object12 = jsonObject2.get("URL");
			System.out.println(object12);
	}
		Object object7 = jsonObject.get("support");
		JSONObject jsonObject3 = (JSONObject) object7;
		Object object8 = jsonObject3.get("url");
		System.out.println(object8);
		Object object9 = jsonObject3.get("text");
		System.out.println(object9);
	}
}
