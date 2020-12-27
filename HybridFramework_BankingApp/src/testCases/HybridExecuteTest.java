package testCases;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.ReadObject;
import Utility.UIOperation;
import excelExportAndFileIO.ReadExcelFile;

public class HybridExecuteTest {
	WebDriver driver = null;
    @Test(dataProvider="hybridData")
	public void testLogin(String testcaseName,String keyword,String objectName,String objectType,String value) throws Exception {
		// TODO Auto-generated method stub
      
    	
    	if(testcaseName!=null&&testcaseName.length()!=0){
    		System.setProperty("webdriver.chrome.driver","C://chromedriver//chromedriver.exe");
			driver = new ChromeDriver();
    	}
        ReadObject object = new ReadObject();
        Properties allObjects =  object.getObjectRepository();
        UIOperation operation = new UIOperation(driver);
      	//Call perform function to perform operation on UI
    			operation.perform(allObjects, keyword, objectName,
    				objectType, value);
    	    
	}

    
    @DataProvider(name="hybridData")
	public Object[][] getDataFromDataprovider() throws IOException{
    	Object[][] object = null; 
    	ReadExcelFile file = new ReadExcelFile();
        
         //Read keyword sheet
         Sheet TestData = file.readExcel(System.getProperty("user.dir")+"\\","TestCase2.xlsx" , "HybridFramework");
       //Find number of rows in excel file
     	int rowCount = TestData.getLastRowNum()-TestData.getFirstRowNum();
     object = new Object[rowCount][5];
     	for (int i = 0; i < rowCount; i++) {
    		//Loop over all the rows
    		Row row = TestData.getRow(i+1);
    		//Create a loop to print cell values in a row
    		for (int j = 0; j < row.getLastCellNum(); j++) {
    			//Print excel data in console
    			object[i][j] = row.getCell(j).toString();
    		}
         
    	}
     	System.out.println("");
     	  return object;	 
	}
}
