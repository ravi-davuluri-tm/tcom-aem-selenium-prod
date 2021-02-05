package com.toy.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileUtil {

	public static Object readJsonData(String folderName, String fileName) throws Exception {
		if (fileName.isEmpty() || fileName == null)
			throw new Exception("Please provide valid file name");
		JSONParser parser = new JSONParser();
		Object jsonObject = null;

		try {
			jsonObject = parser.parse(new FileReader(folderName + "/" + fileName + ".json"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}	
} 