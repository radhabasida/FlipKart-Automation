package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	public static String getscreenshot(WebDriver driver) {

		String imagePath = "Test Result\\" + Screenshot.date() + "\\Screenshot\\" + Screenshot.timestamp() + ".png";
		// Create Dynamic path using configuration

		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(imagePath);
		try {
			FileUtils.copyFile(scr, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imagePath.replace("Test Result\\" + Screenshot.date() + "\\", ""); // Remove common path
	}

	public static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

	public static String time() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	public static String date() {
		return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	}
}
