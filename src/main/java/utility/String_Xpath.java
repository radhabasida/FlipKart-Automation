package utility;

public interface String_Xpath {

	
	String closePopup = "//button[text()='✕']";
	String searhBox = "//input[@name='q']";
	String searchResult = "//form[contains(@class,'form-search')]/ul/li/a[1]";
	String priceList = "//div[@class='bhgxx2 col-12-12']//a//child::div[contains(@class,'row')]//descendant::div[contains(text(),'₹')][1]";
	String sortLowToHigh = "//div[contains(text(),'Price -- Low to High')]";

}
