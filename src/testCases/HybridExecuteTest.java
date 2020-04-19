package testCases;

import java.io.IOException;
import java.util.Properties;
import operation.ReadObject;
import operation.UIOperation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import excelExportAndFileIO.ReadExcelFile;

public class HybridExecuteTest {

	public WebDriver webdriver = null;
	ReadObject object = new ReadObject();
	UIOperation operation = null;
	Properties allObjects = new Properties();
	ExtentReports extentReports;
	ExtentTest extentTest;
	
	public HybridExecuteTest() throws IOException {
		allObjects = object.getObjectRepository();
		operation = new UIOperation();
	}

	@Test(dataProvider = "hybridData")
	public void testCase(String testcaseName, String keyword, String objectName, String objectType, String value)
			throws Exception {
		operation.perform(allObjects, keyword, objectName, objectType, value);
	}

	@DataProvider(name = "hybridData")
	public Object[][] getDataFromDataprovider() throws IOException {
		Object[][] object = null;
		ReadExcelFile file = new ReadExcelFile();

		Sheet mysheet = file.readExcel(System.getProperty("user.dir"), "src/testCases/TestCase.xlsx", "Sheet1");
		int rowCount = mysheet.getLastRowNum();
		int columnCount = mysheet.getRow(0).getLastCellNum();
		object = new Object[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			Row row = mysheet.getRow(i + 1);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				if (row.getCell(j) == null)
					object[i][j] = "";
				else
					object[i][j] = row.getCell(j).toString();

			}
		}
		System.out.println("");
		return object;
	}
}