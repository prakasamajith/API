package org.api;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Sample {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// 1.Create the Object for jsonParser
		JSONParser jsonParser = new JSONParser();
		// 2.Pass the json file to fetch values
		Object parse = jsonParser
				.parse(new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\Employee.json"));
		JSONObject jsonObject = (JSONObject) parse;
		Object object = jsonObject.get("data");
//		System.out.println(object);
		JSONObject jsonObject2 = (JSONObject) object;
		Object object2 = jsonObject2.get("id");
		System.out.println(object2);
		Object object3 = jsonObject2.get("flightName");
		System.out.println(object3);
		Object object4 = jsonObject2.get("Country");
		System.out.println(object4);
		Object object5 = jsonObject2.get("Destinations");
		System.out.println(object5);
		Object object6 = jsonObject2.get("URL");
		System.out.println(object6);
		Object object7 = jsonObject2.get("Created_Date");
		System.out.println(object7);
		Object object8 = jsonObject2.get("Updated_Date");
		System.out.println(object8);
		Object object9 = jsonObject.get("support");
//		System.out.println(object9);
		JSONObject jsonObject3 = (JSONObject) object9;
		Object object10 = jsonObject3.get("url");
		System.out.println(object10);
		Object object11 = jsonObject3.get("text");
		System.out.println(object11);

	}
}
