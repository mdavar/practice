package com.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReadWrite {
	@DataProvider(name="data")
	public static Object[][] dataprovider() throws IOException{
		File file= new File("C:\\Users\\Janhavi Patil\\Desktop\\OptionTrain\\testdata.xlsx");
		FileInputStream fis= new FileInputStream(file);
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet sheet= wb.getSheetAt(0);
		
		int rowcount= sheet.getLastRowNum();
		int colcount = sheet.getRow(0).getLastCellNum();
		
		Object[][] obj= new Object[rowcount][1];
		
		for (int i =0; i<rowcount;i++) {
			Map<Object,Object> datamap= new HashMap<Object,Object>();
			for(int j=0;j<colcount;j++) {
				datamap.put(sheet.getRow(0).getCell(j), sheet.getRow(i+1).getCell(j));
				
			}
			obj[i][0]=datamap;
		
		}
		 
		return obj;
		
				
		

	}
	
	@Test(dataProvider = "data")
	public void Test(Map map) {
		// TODO Auto-generated method stub
		System.out.println(map.get("Name"));
		System.out.println(map.get("Lastname"));

	}
}
