package com.toy.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toy.constant.GlobalConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.datamodel.StringModel;

public class Read_XLS {

	public String fileName;
	public FileInputStream ipstr = null;
	public FileOutputStream opstr = null;
	private XSSFWorkbook wb = null;
	private XSSFSheet ws = null;
	List<String> list = new ArrayList<String>();

	/**
	 * This is constructor
	 * 
	 * @param fileName:     contains file name of sheet
	 * @param relativePath: relative path of project where file is
	 */
	public Read_XLS(String fileName, String relativePath) {
		this.fileName = fileName;
		try {
			ipstr = new FileInputStream(relativePath + fileName);
			wb = new XSSFWorkbook(ipstr);
			ws = wb.getSheetAt(0);
			ipstr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To retrieve No Of Rows from .xls file's sheets.
	 * 
	 * @param wsName
	 * @return number of row
	 */
	public int retrieveNoOfRows(String wsName) {
		int rows = 0;
		int sheetIndex = wb.getSheetIndex(wsName);
		if (sheetIndex == -1)
			return 0;
		else {
			ws = wb.getSheetAt(sheetIndex);
			int rowCount = ws.getLastRowNum() + 1;
			for (int i = 0; i < rowCount; i++) {
				XSSFRow row = ws.getRow(i);
				if (row == null)
					break;
				XSSFCell cell = row.getCell(0);
				String value = gFunc_ReadCellValue(cell);
				if (value.isEmpty()) {
					break;
				}
				rows++;
			}
			// return rowCount;
		}
		return rows;
	}

	/**
	 * To retrieve No Of Columns from .xls file's sheets.
	 * 
	 * @param wsName
	 * @return number of column
	 */
	public int retrieveNoOfCols(String wsName) {
		int sheetIndex = wb.getSheetIndex(wsName);
		int column = 0;
		if (sheetIndex == -1)
			return 0;
		else {
			ws = wb.getSheetAt(sheetIndex);
			int colCount = ws.getRow(0).getLastCellNum();
			for (int i = 0; i < colCount; i++) {
				XSSFRow row = ws.getRow(0);
				if (row == null)
					break;
				XSSFCell cell = row.getCell(i);
				String value = gFunc_ReadCellValue(cell);
				if (!value.isEmpty()) {
					column++;
				}

			}
			return column;
		}
	}

	/**
	 * To retrieve test data from test case data sheets.
	 * 
	 * @param wsName
	 * @return return all list of test data
	 */
	public Object[][] retrieveTestData1(String wsName) {
		int sheetIndex = wb.getSheetIndex(wsName);
		if (sheetIndex == -1)
			return null;
		else {
			int rowNum = retrieveNoOfRows(wsName);
			int colNum = retrieveNoOfCols(wsName);

			Object data[][] = new Object[rowNum - 1][colNum];

			for (int i = 0; i < rowNum - 1; i++) {
				XSSFRow row = ws.getRow(i + 1);
				for (int j = 0; j < colNum; j++) {
					if (row == null) {
						data[i][j] = "";
					} else {
						XSSFCell cell = row.getCell(j);
						if (cell == null) {
							data[i][j] = "";
						} else {
							// cell.setCellType(Cell.CELL_TYPE_STRING);
							String value = gFunc_ReadCellValue(cell);
							data[i][j] = value;
						}
					}
				}
			}
			return data;
		}

	}

	public static String gFunc_ReadCellValue(Cell cell) {
		String strResult = "";
		Cell celldata = (Cell) cell;
		try {
			switch (celldata.getCellType()) {
			case BLANK:
				strResult = "";
				break;
			case FORMULA:
				strResult = String.valueOf(cell.getCellFormula());
				break;
			case BOOLEAN:
				strResult = String.valueOf(cell.getBooleanCellValue());
				break;
			case ERROR:
				strResult = String.valueOf(cell.getErrorCellValue());
				break;
			case NUMERIC:
				strResult = String.valueOf(cell.getNumericCellValue());
				if (strResult.endsWith(".0")) {
					strResult = strResult.replace(".0", "");
				}
				break;
			case STRING:
				strResult = cell.getStringCellValue();
				break;
			default:
				strResult = "";
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("The cell is blank");
		}
		return strResult;
	}

	public static void createOutputExcelTemplate() throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet studentsSheet = workbook.createSheet(GlobalConstant.RAQ_OutPut_Sheet);
		int lastRow = studentsSheet.getLastRowNum();
		Row row = studentsSheet.createRow(lastRow);
		row.createCell(0).setCellValue("SeriesName");
		row.createCell(1).setCellValue("FirstName");
		row.createCell(2).setCellValue("LastName");
		row.createCell(3).setCellValue("ZipCode");
		row.createCell(4).setCellValue("Email");
		row.createCell(5).setCellValue("Phone");
		row.createCell(6).setCellValue("Address");
		row.createCell(7).setCellValue("City");
		row.createCell(8).setCellValue("TrimName");
		row.createCell(9).setCellValue("VendorName");
		row.createCell(10).setCellValue("DealerCode");
		row.createCell(11).setCellValue("CommentText");
		row.createCell(12).setCellValue("ExteriorColor");
		row.createCell(13).setCellValue("InteriorColor");
		row.createCell(14).setCellValue("Price");
		row.createCell(15).setCellValue("OfferRibbon");
		row.createCell(16).setCellValue("OfferDescription");
		row.createCell(17).setCellValue("SiteName");
		row.createCell(18).setCellValue("CampaignCode");
		row.createCell(19).setCellValue("Language");
		try {
			FileOutputStream fos = new FileOutputStream(new File(GlobalConstant.RAQ_OutPut_File));
			workbook.write(fos);
			fos.close();
			System.out.println("Output excel template is created");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
	}

	public static void writeContactListExcel(RequestQuoteModel requestQuoteMode, String language) throws IOException {

		FileInputStream fis = new FileInputStream(new File(GlobalConstant.RAQ_OutPut_File));
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet studentsSheet = workbook.getSheetAt(0);

		int lastRow = studentsSheet.getLastRowNum();
		Row row1 = studentsSheet.createRow(lastRow + 1);

		int cellIndex = 0;
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getSeriesName());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getFirstName());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getLastName());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getZipCode());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getEmail());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getPhone());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getAddress());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getCity());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getTrim());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getVendorName());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getDealerCode());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getCommentText());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getExteriorColor());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getInteriorColor());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getPrice());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getOfferRibbon());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getOfferDescription());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getSiteName());
		row1.createCell(cellIndex++).setCellValue(requestQuoteMode.getCampaignCode());
		row1.createCell(cellIndex++).setCellValue(language);
		try {
			FileOutputStream fos = new FileOutputStream(new File(GlobalConstant.RAQ_OutPut_File));
			workbook.write(fos);
			fos.close();
			System.out.println("Added data successfully in excel");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
	}

	public static void createExcelContentSheet(String url1, String url2) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet carSheet = workbook.createSheet(GlobalConstant.CarContentSheet);
		int lastRow = carSheet.getLastRowNum();
		Row row = carSheet.createRow(lastRow);
		row.createCell(0).setCellValue("Production");
		row.createCell(1).setCellValue("TestENV");
		Row row2 = carSheet.createRow(lastRow + 1);
		row2.createCell(0).setCellValue(url1);
		row2.createCell(1).setCellValue(url2);

		Sheet suvSheet = workbook.createSheet(GlobalConstant.SUVContentSheet);
		int lastRow1 = suvSheet.getLastRowNum();
		Row row1 = suvSheet.createRow(lastRow1);
		row1.createCell(0).setCellValue("Production");
		row1.createCell(1).setCellValue("TestENV");
		Row row12 = suvSheet.createRow(lastRow1 + 1);
		row12.createCell(0).setCellValue(url1);
		row12.createCell(1).setCellValue(url2);

		Sheet truckSheet = workbook.createSheet(GlobalConstant.TrucksContentSheet);
		int lastRow11 = truckSheet.getLastRowNum();
		Row row11 = truckSheet.createRow(lastRow11);
		row11.createCell(0).setCellValue("Production");
		row11.createCell(1).setCellValue("TestENV");
		Row row22 = truckSheet.createRow(lastRow11 + 1);
		row22.createCell(0).setCellValue(url1);
		row22.createCell(1).setCellValue(url2);

		try {
			FileOutputStream fos = new FileOutputStream(new File(GlobalConstant.BuildPrice_OutPut_File));
			workbook.write(fos);
			fos.close();
			System.out.println("Output excel template is created");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
	}

	public static void writeContentInExcel(List<StringModel> appContent, List<StringModel> appContent2,
			String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(new File(GlobalConstant.BuildPrice_OutPut_File));
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet studentsSheet = workbook.getSheet(sheetName);

		int lastRow = studentsSheet.getLastRowNum();
		// Row row1 = studentsSheet.createRow(lastRow+1);
		int i = 0;
		System.out.println("1: " + appContent.size());
		System.out.println("2: " + appContent2.size());
		for (StringModel data : appContent) {
			int cellIndex = 0;
			Row row1 = studentsSheet.createRow(lastRow + i + 1);
			row1.createCell(cellIndex++).setCellValue(data.getText());			
			row1.createCell(cellIndex++).setCellValue(appContent2.get(i).getText());
			row1.createCell(cellIndex++).setCellValue(data.getTextUrl());
			row1.createCell(cellIndex++).setCellValue(appContent2.get(i).getTextUrl());
			i++;
		}
		try {
			FileOutputStream fos = new FileOutputStream(new File(GlobalConstant.BuildPrice_OutPut_File));
			workbook.write(fos);
			fos.close();
			System.out.println("Added data successfully in excel");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
	}

	public static void writeContentInExcel(String cardName, String header, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(new File(GlobalConstant.BuildPrice_OutPut_File));
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet studentsSheet = workbook.getSheet(sheetName);

		int lastRow = studentsSheet.getLastRowNum();
		Row row1 = studentsSheet.createRow(lastRow + 3);
		row1.createCell(0).setCellValue(cardName);
		row1.createCell(1).setCellValue(header);

		try {
			FileOutputStream fos = new FileOutputStream(new File(GlobalConstant.BuildPrice_OutPut_File));
			workbook.write(fos);
			fos.close();
			System.out.println("Added data successfully in excel");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
	}
	
}
