package org.pojo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		Support su = new Support("www.fb.com", "Text");
		ArrayList<Datum> d = new ArrayList<Datum>();
		Datum da = new Datum(103, "Singapore Airlines", "Singapore", "Chennai", "www.sgAir.com");
		Datum dat = new Datum(104, "Indonesian Airlines", "Indonesia", "Chennai", "www.sgAir.com");
		Datum datu = new Datum(105, "Malaysia Airlines", "Malaysia", "Chennai", "www.sgAir.com");
		d.add(da);
		d.add(dat);
		d.add(datu);		
		Sam sam = new Sam("Name", "aji@gmail.com", 9600809646l, su,d);
		mapper.writeValue(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Test.json"), sam);

	}

}
