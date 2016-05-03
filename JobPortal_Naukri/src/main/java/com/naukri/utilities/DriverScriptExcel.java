package com.naukri.utilities;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DriverScriptExcel {
	
	public static String[][] getTestData(String path, String sheetName) throws Exception{
		
		try{
			File filePath = null;
			FileInputStream fis = null;
			Workbook wb = null;
			Sheet sh = null;
			String[][] strData = null;
			
			filePath = new File(path);
			fis = new FileInputStream(filePath);
			wb = Workbook.getWorkbook(fis);
			sh = wb.getSheet(sheetName);
			
			int rowSize = sh.getRows();
			int columnSize = sh.getColumns();
			
			strData = new String[rowSize-1][columnSize];
			
			for(int i = 1; i<rowSize; i++){
				for(int j=0; j<columnSize; j++){
					strData[i-1][j] = (sh.getCell(j, i)).getContents();;
				}
			}
			return strData;
			
		} catch(Exception e){
			return null;
		}
	}
	
	
	
public static void writeTestData(String path, String sheetName) throws Exception{
		
		try{
			File filePath = null;
			FileOutputStream fos = null;
			WritableWorkbook wwb = null;
			WritableSheet wsh = null;
			
			filePath = new File(path);
			fos = new FileOutputStream(filePath);
			
			wwb = Workbook.createWorkbook(fos);
			wsh = wwb.createSheet(sheetName, 0);
			
			int rowCount = wsh.getRows();
			int colCount = wsh.getColumns();
			
			System.out.println(rowCount);
			System.out.println(colCount);
			
			for(int i=1; i<rowCount; i++){
				for(int j = 0; j<colCount; j++){
					Label lb = new Label(j, i, "Good");
					wsh.addCell(lb);
				}
			}
			
			Label lb1 = new Label(2, 0, "Result");
			wsh.addCell(lb1);
			
			wwb.write();
			wwb.close();
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	
	
	/*public static void main(String[] args) throws Exception {
		
		Object[][] obj = DriverScriptExcel.getTestData(".\\src\\test\\resources\\NaukriTestData.xls", "Sheet3");
		
		for(int i = 0; i<obj.length; i++){
			for(int j=0; j<obj[i].length; j++){
				System.out.println(obj[i][j]);
			}
		}
		
		DriverScriptExcel.writeTestData(".\\src\\test\\resources\\TestResult.xls", "Result");
	}*/

}
