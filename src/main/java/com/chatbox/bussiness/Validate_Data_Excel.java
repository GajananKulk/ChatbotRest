package com.chatbox.bussiness;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Validate_Data_Excel
{
	public String getBalance(int c_no)throws IOException
	{
		/*System.out.println(c_no);
		File excel = new File("BankData.xls");
	        FileInputStream fis = new FileInputStream(excel);
	           */
		InputStream stream = Validate_Data_Excel.class.getResourceAsStream("/BankData.xls");
	        //HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFWorkbook wb = new HSSFWorkbook(stream);
	        HSSFSheet ws = wb.getSheetAt(0);

	        int rowNum = ws.getLastRowNum() + 1;
	        
	        int colNum = ws.getRow(0).getLastCellNum();
	        
	        int cardnumHeaderIndex = -1, balanceHeaderIndex = -1;
	        HSSFRow rowHeader = ws.getRow(0);
	        
	        for (int j = 0; j < colNum; j++) {
	            HSSFCell cell = rowHeader.getCell(j);
	            
	            String cellValue = cellToString(cell);
	            if ("CARD NO".equalsIgnoreCase(cellValue)) {
	            	cardnumHeaderIndex = j;
	                
	            } else if ("BALANCE".equalsIgnoreCase(cellValue)) {
	            	balanceHeaderIndex = j;
	                
	            }
	        }      
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet("data");
	        int card_no=123456;
	        int otp1=123,bal=0;
	        String balance="";
	        boolean matchFound = false;
	        
	        for (int i = 1; i < rowNum; i++)
	        {
	            HSSFRow row = ws.getRow(i);
	            String cardNumber = cellToString(row.getCell(cardnumHeaderIndex));
	            int cardnumber = Integer.valueOf((String) cardNumber);
	            
	            if(cardnumber==card_no)
	            {
	             balance = cellToString(row.getCell(balanceHeaderIndex));
	            	matchFound = true;
	            }
	            
	        }
	        
            //System.out.println(obj.toString());
//            JSONObject obj1 = new JSONObject();
//            obj1.put("value", bal);
            /*try {

    			FileWriter file = new FileWriter("D:\\ChatBox\\intents\\Balance.json");
    			file.write(obj1.toString());
    			file.flush();
    			file.close();

    		} catch (IOException e) {
    			e.printStackTrace();
    		}*/
            
            System.out.println("Balance :="+bal);
            if (!matchFound) {
	            System.out.println("Sorry...!! You Entered wrong card number");
	        }
	       return balance;
	    }
	public static String cellToString(HSSFCell cell) {

        int type;
        Object result = null;
        type = cell.getCellType();

        switch (type) {

        case XSSFCell.CELL_TYPE_NUMERIC:
            result = Integer.valueOf((int) cell.getNumericCellValue())
                    .toString();
            break;
            
        case HSSFCell.CELL_TYPE_STRING:
            result = cell.getStringCellValue();
            break;
            
        case HSSFCell.CELL_TYPE_BLANK:
            result = "";
            break;
            
        case HSSFCell.CELL_TYPE_FORMULA:
            result = cell.getCellFormula();
        }
        return result.toString();
    }
}
