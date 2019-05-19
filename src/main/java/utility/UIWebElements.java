package utility;

public class UIWebElements implements String_Xpath {

	public utility.WebPageElements CLOSEPOPUP = new utility.WebPageElements("Close Popup", "xpath",
			String_Xpath.closePopup);
	public utility.WebPageElements SEARCHBOX = new utility.WebPageElements("Search TextBox", "xpath",
			String_Xpath.searhBox);
	public utility.WebPageElements SEARCHRESULT = new utility.WebPageElements("Search Result suggetion", "xpath",
			String_Xpath.searchResult);
	public utility.WebPageElements PRICELIST = new utility.WebPageElements("Mobile Price List ", "xpath",
			String_Xpath.priceList);
	public utility.WebPageElements SORTLOWTOHIGH = new utility.WebPageElements("Sort low to high filter", "xpath",
			String_Xpath.sortLowToHigh);

}
