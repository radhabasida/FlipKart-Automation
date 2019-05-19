package utility;

import utility.WebPageElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SeleniumUtility {

	public void click(WebDriver driver, WebPageElements ele) {
		WebElement element = null;
		try {
			/* waitForElement(driver, ele); */
			element = getWebElement(driver, ele);
			element.click();

		} catch (Exception e) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
			} catch (Exception e2) {
				e.getStackTrace();
				System.out.println(e.getMessage());
				Assert.fail("Not able to click " + ele.getName() + " element.");
			}

		}

	}

	public void setText(WebDriver driver, WebPageElements ele, String text) {
		if (text != null) {
			try {
				WebElement element = null;
				/* waitForElement(driver, ele); */
				element = getWebElement(driver, ele);
				element.sendKeys(text);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail(text + " not entered in " + ele.getName() + " textbox.");
			}
		} else {
			Assert.fail(ele.getName() + " value is null in excel");
		}

	}

	public String getText(WebDriver driver, WebPageElements ele) {
		String text = null;
		try {
			WebElement element = null;
			element = getWebElement(driver, ele);
			text = element.getText();
			/* System.out.println(text); */
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Not able to get text for " + ele.getName());
		}
		return text;
	}

	public boolean waitForElementPresent(WebDriver driver, int seconds, final WebPageElements ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			if (ele.getLocator().equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("linktext")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("name")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("classname")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("css")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.getValue())));
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public WebElement getWebElement(WebDriver driver, WebPageElements element) {
		WebElement ele = null;
		try {
			if (element.getLocator().equalsIgnoreCase("xpath")) {
				ele = driver.findElement(By.xpath(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("id")) {
				ele = driver.findElement(By.id(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("name")) {
				ele = driver.findElement(By.name(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("linktext")) {
				ele = driver.findElement(By.linkText(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("partiallinktext")) {
				ele = driver.findElement(By.partialLinkText(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("classname")) {
				ele = driver.findElement(By.className(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("tagname")) {
				ele = driver.findElement(By.tagName(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("css")) {
				ele = driver.findElement(By.cssSelector(element.getValue()));
			}

		} catch (NoSuchElementException e) {

			Assert.fail("Not able to find element " + element.getName());

		}
		if (ele == null) {

			Assert.fail("Not able to find element " + element.getName());
		}
		return ele;
	}

	public List<WebElement> getWebElements(WebDriver driver, WebPageElements element) {
		List<WebElement> ele = null;
		try {
			if (element.getLocator().equalsIgnoreCase("xpath")) {
				ele = driver.findElements(By.xpath(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("id")) {
				ele = driver.findElements(By.id(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("name")) {
				ele = driver.findElements(By.name(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("linktext")) {
				ele = driver.findElements(By.linkText(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("partiallinktext")) {
				ele = driver.findElements(By.partialLinkText(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("classname")) {
				ele = driver.findElements(By.className(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("tagname")) {
				ele = driver.findElements(By.tagName(element.getValue()));
			} else if (element.getLocator().equalsIgnoreCase("css")) {
				ele = driver.findElements(By.cssSelector(element.getValue()));
			}

		} catch (NoSuchElementException e) {

			Assert.fail("Not able to find element " + element.getName());

		}
		if (ele.isEmpty()) {

			Assert.fail("Not able to find element " + element.getName());
		}
		return ele;
	}

}
