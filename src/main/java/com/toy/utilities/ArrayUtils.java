package com.toy.utilities;

import java.util.List;

public class ArrayUtils {
	
	public static boolean isArrayListDataContains(List<String> data, String str){
		boolean status = false;
		for(String str1 : data) {
			System.out.println(str1);
			System.out.println(str);
			if(str1.toLowerCase().contains(str.toLowerCase())){
				status= true;
				break;
			}
		}
		return status;
	}
	
	
}