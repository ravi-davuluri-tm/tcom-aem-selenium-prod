package com.toy.utilities;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.toy.datamodel.RequestQuoteModel;

public class Utilities {

	/**
	 * Generate random number
	 * 
	 * @param aStart
	 *            : start length of random integer
	 * @param aEnd
	 *            : end length of random number like 10-11 it will generate
	 *            random number between 10-11 digits
	 * @return
	 */
	public static int getRandomInteger(int aStart, int aEnd) {
		Random aRandom = new Random();
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = (long) aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * aRandom.nextDouble());
		return (int) (fraction + aStart);
	}

	/**
	 * Generate random string
	 * 
	 * @param len
	 *            : length of random string
	 * @return
	 */
	public static String randomString(int len) {
		String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

	/**
	 * Get absolute path
	 */
	public static String getPath() {
		String path = "";
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
		return path;
	}

	/**
	 * @param file
	 * @return
	 */
	public static String getFileName(String file) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		Calendar cal = Calendar.getInstance();
		String fileName = file + dateFormat.format(cal.getTime());
		return fileName;
	}

	/**
	 * Get absolute path
	 */
	public static String getPathUpload() {
		String path = "";
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("/", "//");
		return path;
	}

	/**
	 * Get time stamp
	 * 
	 * @return
	 */
	public static long getTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime();
	}

	/**
	 * Get time stamp
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String[] splitStringWithNewLine(String str) throws Exception {
		if (StringUtils.isBlank(str)) {
			throw new Exception("Please provide valid string");
		}
		return str.split("\\r?\\n");
	}

	/**
	 * Convert string with first letter in caps
	 * 
	 * @param inputString
	 * @return
	 */
	public static String titleCaseConversion(String inputString) {
		if (StringUtils.isBlank(inputString)) {
			return "";
		}

		if (StringUtils.length(inputString) == 1) {
			return inputString.toUpperCase();
		}

		StringBuffer resultPlaceHolder = new StringBuffer(inputString.length());

		Stream.of(inputString.split(" ")).forEach(stringPart -> {
			if (stringPart.length() > 1)
				resultPlaceHolder.append(stringPart.substring(0, 1).toUpperCase())
						.append(stringPart.substring(1).toLowerCase());
			else
				resultPlaceHolder.append(stringPart.toUpperCase());

			resultPlaceHolder.append(" ");
		});
		return StringUtils.trim(resultPlaceHolder.toString());
	}

	public static String getSeriesName(RequestQuoteModel data) {
		String[] str = data.getSeriesName().split(" ");
		StringBuilder series = new StringBuilder();
		int i = 0;
		for (String str1 : str) {
			i++;
			if (i == 1)
				continue;
			series = series.append(str1.trim()).append(" ");

		}
		return series.toString().trim();
	}

	public static String getSeriesYear(RequestQuoteModel data) {
		String[] str = data.getSeriesName().split(" ");
		return str[0];
	}
	
	public static String getSeriesNameEsp(RequestQuoteModel data) {
		String[] str = data.getSeriesName().split(" ");
		StringBuilder series = new StringBuilder();
		int i = 0;
		for (String str1 : str) {			
			series = series.append(str1.trim()).append(" ");
			i++;
			if (i == str.length-1)
				break;

		}
		return series.toString().trim();
	}

	public static String getSeriesYearEsp(RequestQuoteModel data) {
		String[] str = data.getSeriesName().split(" ");
		return str[str.length -1];
	}

	public static RequestQuoteModel setColor(RequestQuoteModel data, String url) {
		if (url.contains("interiorcolor")) {
			String str1[] = url.split("interiorcolor/");
			String str2[] = str1[1].split("/packages/");
			if(str2[0].contains("-"))
				data.setInteriorColor(str2[0].split("-")[1]);
			else
				data.setInteriorColor(str2[0]);			
		}
		if (url.contains("interiorcolor")) {
			String str3[] = url.split("exteriorcolor/");
			String str4[] = str3[1].split("/interiorcolor/");
			if(str4[0].contains("-"))
				data.setExteriorColor(str4[0].split("-")[1]);
			else
				data.setExteriorColor(str4[0]);
		}
		return data;
	}
	
	public static int getNumberFromString(String allAccessory){
		if(allAccessory == null || allAccessory == "" || allAccessory.equals(""))
			return 0;
		return Integer.parseInt(allAccessory.replaceAll("[^0-9]", ""));
	}
	
	public static String getCharactersFromString(String data){
		String resultString = data.replaceAll("\\P{L}", "");
		return resultString;
	}	
	public static String removeNumberFromString(String data){
		return  data.replaceAll("\\d","");		
	}
	
	public static String removeLine(String str) {
		String updatedString = StringUtils.EMPTY;
		String[] lines = str.split("\n");
		for(String str1 : lines) {
			updatedString = updatedString + " "+str1;
		}
		return updatedString.trim();		
	}
	
	public static String getCustomeTrim(String trim, String series) {
		String customTrim = null;
		if(series.contains("HIGHLANDER HYBRID"))
			customTrim = "HIGHLANDER HYBRID "+trim;
		else if(series.contains("COROLLA HATCHBACK"))
			customTrim = "COROLLA HATCHBACK "+trim;
		else if(series.contains("C-HR"))
			customTrim = trim;  // No changes on trim will
		else if(series.contains("86")) 
			customTrim = removeText("86", trim);   // Removing model if present
		else if(series.contains("GR SUPRA"))
			customTrim = trim;  // No changes in trim
		else if(series.contains("MIRAI"))
			customTrim = removeText("MIRAI", trim);
		else
			customTrim = trim;
		return customTrim;
	}
	
	public static String getCustomeModel(String model) {
		String customModel = null;	
		if(model.contains("GR SUPRA"))
			customModel = "SUPRA";  // No changes in trim
		else 
			customModel = model;		
		return customModel;
	}
	
	public static String removeText(String text, String trim) {
		return trim.replace(text, "").trim();
	}
}
