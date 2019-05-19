package testcase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

import utility.Screenshot;
import utility.SeleniumUtility;
import utility.UIWebElements;

public class SearchMobile extends SeleniumUtility {

	public static WebDriver driver;
	ChromeOptions options;
	DesiredCapabilities cap;
	private static ExtentReports extent;
	ExtentTest test;
	String timeStamp = Screenshot.timestamp();
	String htmlReportPath = "Test Result\\" + Screenshot.date() + "\\Test_Report" + timeStamp + ".html";
	public ExtentHtmlReporter extentHtmlReporter;

	public ScreenCapture SCREEN_CAPTURE = new ScreenCapture();

	UIWebElements we = new UIWebElements();

	@BeforeSuite
	public void setup() throws IOException {
		try {
			WebDriverManager.chromedriver().setup();
			options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.addArguments("--disable-notifications");

			extentHtmlReporter = new ExtentHtmlReporter(htmlReportPath);
			extent = new ExtentReports();
			extent.attachReporter(extentHtmlReporter);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	@Test
	public void login() throws InterruptedException, IOException {
		try {
			test = extent.createTest("Check low to High Price sorting");
			// launch browser
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// open website
			driver.get("https://www.flipkart.com");
			logCreator(1, "Website Opened");

			// get page title
			String pageTile = driver.getTitle();
			System.out.println("Page Title : " + pageTile);

			// close login pop up
			if (waitForElementPresent(driver, 30, we.CLOSEPOPUP)) {
				click(driver, we.CLOSEPOPUP);
				logCreator(1, "Login Pop up closed");
			}

			// enter search value
			setText(driver, we.SEARCHBOX, "mi mobiles");
			logCreator(1, "Search Value entered");

			// click search result
			click(driver, we.SEARCHRESULT);
			List<WebElement> mobilePriceElements = getWebElements(driver, we.PRICELIST);
			logCreator(1, "Search Result is Displayed");

			// print mobile price of first page
			for (WebElement price : mobilePriceElements) {
				System.out.println(price.getText().substring(1));
			}

			// click low to high sort filter
			click(driver, we.SORTLOWTOHIGH);
			logCreator(1, "click on the Low to high sort parameter");

			// get sorted price
			List<WebElement> sortedMobilePriceElements = getWebElements(driver, we.PRICELIST);
			List<Integer> priceValueList = new ArrayList<Integer>();
			for (WebElement price : sortedMobilePriceElements) {
				priceValueList.add((Integer.parseInt(price.getText().substring(1).replaceAll(",", ""))));
			}

			// check price is sorted or not
			List<Integer> sortedPrice = new ArrayList<Integer>(priceValueList);
			Collections.sort(sortedPrice);
			if (priceValueList.equals(sortedPrice)) {
				System.out.println("Sorted Price value :" + priceValueList);
				logCreator(1, "Price of the mobile is sorted by low to high");
			} else {
				System.out.println("sort result by flipkart :" + priceValueList);
				logCreator(0, "Price of the mobile is not sorted by low to high");
				Assert.fail("Price is not sorted");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				logCreator(0, result.getName() + " is Failed : " + result.getThrowable());

				driver.close();
				driver.quit();

			} else if (result.getStatus() == ITestResult.SUCCESS) {
				logCreator(1, "Low to high sort filter working successfully");
				driver.close();
				driver.quit();

			} else if (result.getStatus() == ITestResult.SKIP) {
				logCreator(4, "Test execution is skipped");
			}

		} catch (Exception e) {

		}
		extent.flush();
	}

	public void logCreator(int result, String msg) {
		try {
			// Lets Take Screenshot According To Platform

			SCREEN_CAPTURE.setPath(Screenshot.getscreenshot(driver));

			// Let's Put Result In File
			// 0 = Fail , 1 = Pass, 2 = Error, 3 = Info , 4 = Skip

			if (result == 0) {
				test.fail(msg, new MediaEntityModelProvider(SCREEN_CAPTURE));
			} else if (result == 1) {
				test.pass(msg, new MediaEntityModelProvider(SCREEN_CAPTURE));
			} else if (result == 2) {
				test.error(msg, new MediaEntityModelProvider(SCREEN_CAPTURE));
			} else if (result == 3) {
				test.info(msg, new MediaEntityModelProvider(SCREEN_CAPTURE));
			} else if (result == 4) {
				test.skip(msg);
			} else {
				test.info(msg, new MediaEntityModelProvider(SCREEN_CAPTURE));
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.fail("Not able to take any screenshot " + e.getMessage(),
					new MediaEntityModelProvider(SCREEN_CAPTURE));
		}
	}

}
