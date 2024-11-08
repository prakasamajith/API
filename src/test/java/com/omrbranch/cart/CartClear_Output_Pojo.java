package com.omrbranch.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartClear_Output_Pojo {
	private int status;
	private String message;
	private int cart_count;
}
