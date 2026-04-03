package AbstractComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjectclass.LonginPage;

public class BaseTest {

	public WebDriver driver;
	public WebDriverWait wait;
	public LonginPage loginpage;
	public Properties prop;
	public FileInputStream fis;

	public WebDriver initilizeDriver() throws IOException {

		prop = new Properties();
	//	fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\GlobalData.properties");
		fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/GlobalData.properties");
		prop.load(fis);

		//String browsername = prop.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

		String browsername = System.getProperty("browser") != null 
			    ? System.getProperty("browser")     
			    : prop.getProperty("browser"); 
		
		if (browsername.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (browsername.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Invalid browser name in GlobalData.properties");
		}

		driver.manage().window().maximize();
		// wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// reads from properties file and creates wait ONCE here
		int waitTime = Integer.parseInt(prop.getProperty("explicitWait"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));

		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// read json to string
		String JsonContent = FileUtils.readFileToString(new File(filePath), "UTF-8");

		// String to HashMap

		ObjectMapper objmapper = new ObjectMapper();

		List<HashMap<String, String>> data = objmapper.readValue(JsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	// Screenshots
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LonginPage launchApplication() throws IOException {
		driver = initilizeDriver();
		loginpage = new LonginPage(driver, wait);

		// 3. FETCH the URL from prop and PASS it into the method
		String url = prop.getProperty("url");
		loginpage.goTo(url); // Now it matches: goTo(String)

		return loginpage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
