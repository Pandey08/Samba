package hubble.samba.pageobjects;


import hubble.samba.constants.BuildActionsPageConstants;
import hubble.samba.constants.LandingPageConstants;
import hubble.samba.util.DriverUtilities;
import hubble.samba.util.ResourceBundleUtil;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LandingPage {
	
	private static LandingPage instance = null;
	WebDriver webDriver = DriverUtilities.webDriver;
	DriverUtilities driverUtil= DriverUtilities.instance();
	ResourceBundleUtil rb= new ResourceBundleUtil();
	private static Logger logger = Logger.getLogger(LandingPage.class.getName());
	
	private LandingPage(){
		
	}
	public static LandingPage getInstance(){
		if (instance == null){
			LandingPageConstants.loadLandingPageLocators();
//			LandingPageConstants.loadBuildDetailsMap();
//			LandingPageConstants.loadComponentDetailsMap();
			instance = new LandingPage();
		}
		return instance;	
	}
	

//	private static String buildDeployComponentName =LandingPageConstants.getBuildDetailsMap().get("DEPLOYMENT_COMPONENT").get("COMPONENT_NAME");
	private static String landingPageHeading="Choose environment and installation";
	
	private By sambaLandingPageHeading = By.cssSelector(LandingPageConstants.getLandingPageLocators().get("LANDING_HEADING"));
	private By allHubble2LandingPage = By.cssSelector(LandingPageConstants.getLandingPageLocators().get("ALL_HUBBLE2"));
	private By selectionComponent = By.cssSelector(LandingPageConstants.getLandingPageLocators().get("SELECTION_COMPONENT"));
			
	private void generateSelectionComponent(String component) {
		selectionComponent=By.cssSelector(rb.generateXpath(LandingPageConstants.getLandingPageLocators().get("SELECTION_COMPONENT"), component));
	}
	
	private WebElement getSamabLandingPageHeading() {
		return driverUtil.findElement(sambaLandingPageHeading);
	}
	private WebElement getAllHubble2LandingPage() {
		return driverUtil.findElement(allHubble2LandingPage);
	}
	private WebElement getSelectComponentLandingPage() {
		return driverUtil.findElement(selectionComponent);
	}
	
	
	public Boolean sambaLandingPageLaunched() throws InterruptedException {
		logger.info("Inside sambaLandingPageLaunched");

		driverUtil.waitForElementPresent(sambaLandingPageHeading);
		if(driverUtil.isWebElementPresent(getSamabLandingPageHeading())){
			String headerText=driverUtil.getText(getSamabLandingPageHeading());
			System.out.println("headerText::"+headerText);
			return headerText.contains(landingPageHeading);

		}else{
			return false;
		}
	}
	
	public void clickAllHubble2() throws InterruptedException {
		logger.info("Inside clickAllHubble2");
		driverUtil.waitForElementPresent(allHubble2LandingPage);
		driverUtil.click(getAllHubble2LandingPage());
	}


	/////------------------------------------------------------------------------------///
//	public void selectOptionLandingPage() throws InterruptedException {
//		logger.info("Inside selectOptionLandingPage");
//		String landingPageSelections=LandingPageConstants.getComponentDetailsMap().get(LandingPageConstants.getBuildDetailsMap().get("DEPLOYMENT_COMPONENT").get("COMPONENT_NAME")).get("LANDING_PAGE");
//		System.out.println("landingPageSelections::"+landingPageSelections);
//		landingPageSelections=landingPageSelections.replaceAll("-", "/");
//		generateSelectionComponent(landingPageSelections);
//		driverUtil.waitForElementPresent(selectionComponent);
//		getSelectComponentLandingPage().click();
//
//	}
	
	
	

}
