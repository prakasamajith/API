package com.omrbranch.category.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataClass {
	private int id;
	private String name;
	private String image;
	private int subcategory_count;
	private ArrayList<ChildCatList> child_cat_list;
}
