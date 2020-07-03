package utils;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Common {


	WebDriver driver; 

	public void setupBrowser(String browser, String url) {
		String currDir = System.getProperty("user.dir");

		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", currDir+"\\drivers\\chromedriver.exe");
			this.driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", currDir+"\\drivers\\geckodriver.exe");
			this.driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			this.driver = new EdgeDriver();
		}

		else {
			System.out.println("Driver object is not provided or valid, hence not invoking the browser");
			System.exit(0);
		}

		this.driver.manage().window().maximize();

		if(url!="")
			this.driver.get(url);
		else {
			System.out.println("url is not provided and hence quiting the test run");
			quitBrowser();			
		}
	}

	public void quitBrowser() {
		if(driver!=null)
			this.driver.quit();
	}

	public WebDriver getDriver() {
		return this.driver;
	}
}
