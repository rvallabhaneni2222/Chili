package info.yalamanchili.qa;

import info.yalamanchili.commons.PropertyFileLoader;

import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public abstract class WebDriverTest<T> {
	public static WebDriver driver;
	public static Selenium selenium;
	public static String baseURL;

	@BeforeClass
	public static void init() {
		loadProperties();
		driver = new FirefoxDriver();
		selenium = new WebDriverBackedSelenium(driver, baseURL);
		driver.get(baseURL);
	}

	@AfterClass
	public static void destroy() {
		driver.close();
	}

	public void waitforAjaxResponce() {
		long end = System.currentTimeMillis() + 5000;
		while (System.currentTimeMillis() < end) {
			if (!driver.getPageSource().contains("Loading...")) {
				break;
			}
		}

	}

	protected static void loadProperties() {
		Properties properties = PropertyFileLoader
				.loadProperties("env.properties");
		baseURL = properties.getProperty("app-base-url")
				+ properties.getProperty("app-root-page");
	}
}
