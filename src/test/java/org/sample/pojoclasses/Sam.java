package org.sample.pojoclasses;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sam {
	private String name;
	private String email;
	private long phoneNo;
	private Support support;
	private ArrayList<Datum> datum;
}
