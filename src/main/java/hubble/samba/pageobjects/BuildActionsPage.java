package hubble.samba.pageobjects;

import hubble.samba.constants.BuildActionsPageConstants;
import hubble.samba.util.DriverUtilities;
import hubble.samba.util.ResourceBundleUtil;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class BuildActionsPage {

	private static BuildActionsPage instance = null;
	WebDriver webDriver = DriverUtilities.webDriver;
	DriverUtilities driverUtil= DriverUtilities.instance();
	ResourceBundleUtil rb= new ResourceBundleUtil();
	private static Logger logger = Logger.getLogger(BuildActionsPage.class.getName());

	private BuildActionsPage(){

	}

	public static BuildActionsPage getInstance(){
		if (instance == null){
			BuildActionsPageConstants.loadBuildActionsPageLocators();
			//			BuildActionsPageConstants.loadBuildDetailsMap();
			instance = new BuildActionsPage();
		}
		return instance;	
	}
	//	private String buildDeployComponentName =BuildActionsPageConstants.getBuildDetailsMap().get("DEPLOYMENT_COMPONENT").get("COMPONENT_NAME");
	//	private String buildGroupToSelect=BuildActionsPageConstants.getBuildDetailsMap().get("DEPLOYMENT_COMPONENT").get("BUILD_GROUP");
	//	private String buildVersionToEnter=BuildActionsPageConstants.getBuildDetailsMap().get("DEPLOYMENT_COMPONENT").get("BUILD_VERSION");
//	private String buildDeployComponentName="housekeeper";
//	private String buildGroupToSelect="HubbleNext";
//	private String buildVersionToEnter="18.8.2";
//	private String environmentToSel="alpha";

	private By selectedPageBuildActions = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("SELECTED_PAGE_BUILD_ACTIONS"));
	private By buildGroup = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("BUILD_GROUP"));
	private By selectorBuildGroup = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("BUILD_GROUP_SELECTOR"));
	private By component = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("COMPONENT"));
	private By componentSelectorNoneButton = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("COMPONENT_SELECTOR_NONE_BUTTON"));
	private By selectComponent = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("SELECT_COMPONENT"));
	private By selectComponentNone = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("SELECT_COMPONENT_NONE"));
	private By builds = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("BUILDS"));
	private By buildVersion = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("BUILD_VERSION"));
	private By setCurrent = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("SET_CURRENT"));
	private By install = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("INSTALL"));
	private By installRequestSubmitted = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("INSTALL_REQUEST_SUBMITTED"));
	private By clickHereInstallSubmitted = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("CLICK_HERE_INSTALL_REQUEST_SUBMITTED"));
	private By installSuccess = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("INSTALL_SUCCESS"));
	private By requestSubmittedRows = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("REQUEST_SUBMITTED_TABLE_ROWS"));
	private By environmentTag = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("ENVIRONMENT_TAG"));
	private By environmentToSelect = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("ENVIRONMENT_TO_SELECT"));
	private By selectBuildVersion = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("SELECT_BUILD_VERSION"));
	private By setCurrentSubmit = By.xpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("SET_CURRENT_SUBMIT"));

	

	private WebElement getSelectedPageBuildActions() {
		return driverUtil.findElement(selectedPageBuildActions);
	}
	private WebElement getSelectorBuildGroup() {
		return driverUtil.findElement(selectorBuildGroup);
	}
	private WebElement getComponent() {
		return driverUtil.findElement(component);
	}
	private WebElement getComponentSelectorNoneButton() {
		return driverUtil.findElement(componentSelectorNoneButton);
	}
	private void generateSelectComponent(String component) {
		selectComponent=By.xpath(rb.generateXpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("SELECT_COMPONENT"), component));
	}
	private WebElement getSelectComponent() {
		return driverUtil.findElement(selectComponent);
	}
	private WebElement getSelectComponentNone() {
		return driverUtil.findElement(selectComponentNone);
	}
	private WebElement getBuilds() {
		return driverUtil.findElement(builds);
	}
	private WebElement getBuildVersion() {
		return driverUtil.findElement(buildVersion);
	}
	private WebElement getSetCurrent() {
		return driverUtil.findElement(setCurrent);
	}
	private WebElement getInstallBuild() {
		return driverUtil.findElement(install);
	}
	private WebElement getInstallRequestSubmitted(){ return driverUtil.findElement(installRequestSubmitted); }
	private WebElement getClickHereInstallRequestSubmitted(){ return driverUtil.findElement(clickHereInstallSubmitted); }
	private void generateInstallSuccess(String rowNumber) {
		installSuccess=By.xpath(rb.generateXpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("INSTALL_SUCCESS"), rowNumber));
	}
	private WebElement getInstallSuccess(){ return driverUtil.findElement(installSuccess); }

	private WebElement getEnvironmentTag() {
		return driverUtil.findElement(environmentTag);
	}

	private void generateSelectEnvironment(String environment) {
		environmentToSelect=By.xpath(rb.generateXpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("ENVIRONMENT_TO_SELECT"), environment));
	}

	private WebElement getEnvironmentToSelect() {
		return driverUtil.findElement(environmentToSelect);
	}

	private WebElement getSelectBuildVersion() {
		return driverUtil.findElement(selectBuildVersion);
	}
	
	private WebElement getSetCurrentSubmit() {
		return driverUtil.findElement(setCurrentSubmit);
	}


	public Boolean selectedPageBuildActions() throws InterruptedException{
		logger.info("Inside selectedPageBuildActions");
		driverUtil.waitForElementPresent(selectedPageBuildActions);
		if(driverUtil.isWebElementPresent(getSelectedPageBuildActions())){
			return true;
		}else{
			return false;
		}
	}

	public void selectBuildGroup(String buildGroupToSelect) throws InterruptedException{
		logger.info("Inside selectBuildGroup");
		driverUtil.waitForElementPresent(buildGroup);
		System.out.println("buildGroupToSelect::"+buildGroupToSelect);
		Select buildGroup= new Select(getSelectorBuildGroup());
		buildGroup.selectByValue(buildGroupToSelect);
	}

	public void selectDeploymentComponent(String buildDeployComponentName) throws InterruptedException{
		logger.info("Inside selectDeploymentComponent");
		driverUtil.waitForElementPresent(component);
		getComponentSelectorNoneButton().click();
		generateSelectComponent(buildDeployComponentName);

		System.out.println("selectComponent::"+selectComponent);
		if(driverUtil.isElementPresent(selectComponent)){
			System.out.println("comp present");
			getSelectComponent().click();
		}else{
			System.out.println("comp not present");
			getSelectComponentNone().click();
		}
	}

	public void enterBuildVersion(String buildVersionToEnter) throws InterruptedException{
		logger.info("Inside enterBuildVersion");
		System.out.println("Inside enterBuildVersion");
		driverUtil.waitForElementPresent(builds);
		driverUtil.waitForElementPresent(buildVersion);
		getBuildVersion().click();
		getBuildVersion().clear();
		System.out.println("clear");
		Thread.sleep(3000);
		getBuildVersion().sendKeys(buildVersionToEnter);
	}

	public void setCurrentBuild() throws InterruptedException{
		logger.info("Inside setCurrentBuild");
		driverUtil.waitForElementPresent(setCurrent);
		getSetCurrent().click();
	}

	public void installBuild() throws InterruptedException{
		logger.info("Inside installBuild");
		driverUtil.waitForElementPresent(install);
		getInstallBuild().click();
	}

	public void clickOkAlertInstallBuild() throws InterruptedException{
		logger.info("Inside clickOkAlertInstallBuild");
		System.out.println("Alert text::"+driverUtil.switchToAlertGetText());
		Thread.sleep(5000);
		driverUtil.switchToAlertAccept();
	}

	public void clickHereInstallRequestSubmitted() throws InterruptedException{
		logger.info("Inside clickHereInstallRequestSubmitted");
		System.out.println("clickHereInstallRequestSubmitted");
		driverUtil.waitForElementPresent(installRequestSubmitted);
		System.out.println("wait done");
		if(driverUtil.isElementPresent(installRequestSubmitted)){
			System.out.println("present");
			Thread.sleep(5000);

			driverUtil.clickOpenNewTab();
			System.out.println("command Preseed");
			driverUtil.click(getClickHereInstallRequestSubmitted());
		}
	}

	public Boolean validateBuildInstalledSucessfully() throws InterruptedException{
		logger.info("Inside validateBuildInstalledSucessfully");
		System.out.println("validateBuildInstalledSucessfully");
		driverUtil.switchWindowTitle("Request Details Summary");

		System.out.println("window swithched");
		System.out.println(requestSubmittedRows);
		Thread.sleep(10000);

		int tableLength=driverUtil.findElements(requestSubmittedRows).size();
		int successElement=0;
		System.out.println("tableLength::"+tableLength);
		for(int i=1;i<=tableLength;i++){
			generateInstallSuccess(String.valueOf(i));
			System.out.println("generated element");
			if(driverUtil.isElementPresent(installSuccess)){
				System.out.println("Element Present");
				if(driverUtil.getText(getInstallSuccess()).toLowerCase().contains("success")){
					System.out.println("its success::"+i);
					successElement++;
				}else {
					return false;
				}
			}else {
				System.out.println("break::"+i);
				break;
			}
		}
		System.out.println("tableLength Final::"+tableLength);
		System.out.println("successElement Final::"+successElement);
		driverUtil.closeWindow();
		driverUtil.switchWindowTitle("Build Info");
		System.out.println("swithc done");
		return successElement==tableLength;
	}

	public void gotoHubbleEnvironment(String environmentToSel) throws InterruptedException{
		logger.info("Inside gotoHubbleEnvironment");
		System.out.println("gotoHubbleEnvironment");
		driverUtil.waitForElementPresent(environmentTag);
		if(driverUtil.isElementPresent(environmentTag)){
			getEnvironmentTag().click();
			generateSelectEnvironment(environmentToSel);
			System.out.println(environmentToSelect);
			getEnvironmentToSelect().click();
			driverUtil.closeWindow();
			driverUtil.switchWindowTitle("Samba(hubble2/alpha)");
			System.out.println("clk don");
		}
	}
	
	public void selectInstalledBuildVersion(String buildVersionToEnter) throws InterruptedException{
		logger.info("Inside selectInstalledBuildVersion");
		System.out.println("Inside selectInstalledBuildVersion");
		driverUtil.waitForElementPresent(selectBuildVersion);
		
		Select buildVersion= new Select(getSelectBuildVersion());
		buildVersion.selectByValue(buildVersionToEnter);
		System.out.println("selected bv");

	}
	
	public void setCurrentBuildAndSubmit() throws InterruptedException{
		logger.info("Inside setCurrentBuildAndSubmit");
		System.out.println("Inside setCurrentBuildAndSubmit");
		driverUtil.waitForElementPresent(setCurrent);
		getSetCurrent().click();
		driverUtil.waitForElementPresent(setCurrentSubmit);
		getSetCurrentSubmit().click();
		System.out.println("Alert text set current::"+driverUtil.switchToAlertGetText());
		Thread.sleep(5000);
		driverUtil.switchToAlertAccept();
		System.out.println("set current submitted");
	}
	
	
}
