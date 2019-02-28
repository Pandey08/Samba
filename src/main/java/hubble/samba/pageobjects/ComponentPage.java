package hubble.samba.pageobjects;

import java.util.Iterator;
import java.util.List;

import hubble.samba.constants.BuildActionsPageConstants;
import hubble.samba.constants.ComponentPageConstants;
import hubble.samba.util.DriverUtilities;
import hubble.samba.util.ResourceBundleUtil;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ComponentPage {
	
	private static ComponentPage instance = null;
	WebDriver webDriver = DriverUtilities.webDriver;
	DriverUtilities driverUtil= DriverUtilities.instance();
	ResourceBundleUtil rb= new ResourceBundleUtil();
	private static Logger logger = Logger.getLogger(ComponentPage.class.getName());

	private ComponentPage(){
		
	}
	
	public static ComponentPage getInstance(){
		if (instance == null){
			ComponentPageConstants.loadComponentPageLocators();
//			ComponentPageConstants.loadBuildDetailsMap();
			instance = new ComponentPage();
		}
		return instance;	
	}

	//private String buildDeployComponentName =ComponentPageConstants.getBuildDetailsMap().get("DEPLOYMENT_COMPONENT").get("COMPONENT_NAME");
	private String buildDeployComponentName ="housekeeper";

	
	private By toolsComponentPage = By.xpath(ComponentPageConstants.getComponentPageLocators().get("TOOLS"));
	private By buildActionsToolsComponentPage = By.xpath(ComponentPageConstants.getComponentPageLocators().get("BUILD_ACTIONS_TOOLS"));
	private By componentsDropdown = By.xpath(ComponentPageConstants.getComponentPageLocators().get("COMPONENTS_DROPDOWN"));
	private By componentStatusDropdown = By.xpath(ComponentPageConstants.getComponentPageLocators().get("COMPONENT_STATUS_DROPDOWN"));
	private By componentStatusPage = By.xpath(ComponentPageConstants.getComponentPageLocators().get("COMPONENT_STATUS_PAGE"));
	private By componentsTextComponentStatusPage = By.xpath(ComponentPageConstants.getComponentPageLocators().get("COMPONENTS"));
	private By selectComponentsDropdown = By.xpath(ComponentPageConstants.getComponentPageLocators().get("SELECT_COMPONENTS_DROPDOWN"));
	private By selectComponent = By.xpath(ComponentPageConstants.getComponentPageLocators().get("SELECT_COMPONENT"));
	private By fetchComponentStatus = By.xpath(ComponentPageConstants.getComponentPageLocators().get("FETCH_BUTTON"));
	
	private By selectInstance = By.xpath(ComponentPageConstants.getComponentPageLocators().get("SELECT_INSTANCE"));
	private By selectActions = By.xpath(ComponentPageConstants.getComponentPageLocators().get("SELECT_ACTIONS"));
	private By graceInterval = By.xpath(ComponentPageConstants.getComponentPageLocators().get("GRACE_INTERVAL"));
	private By restartButton = By.xpath(ComponentPageConstants.getComponentPageLocators().get("RESTART_BUTTON"));
	private By componentStatusTable = By.xpath(ComponentPageConstants.getComponentPageLocators().get("COMPONENT_STATUS_TABLE"));
	private By instanceState = By.xpath(ComponentPageConstants.getComponentPageLocators().get("INSTANCE_STATE"));

	
	
	private WebElement getToolsComponentPage() {
		return driverUtil.findElement(toolsComponentPage);
	}
	private WebElement getBuildActionsToolsComponentPage() {
		return driverUtil.findElement(buildActionsToolsComponentPage);
	}
	private WebElement getcomponentsDropdown() {
		return driverUtil.findElement(componentsDropdown);
	}
	private WebElement getcomponentStatusDropdown() {
		return driverUtil.findElement(componentStatusDropdown);
	}
	private WebElement getSelectComponentsDropdown() {
		return driverUtil.findElement(selectComponentsDropdown);
	}
	private void generateSelectComponent(String component) {
		selectComponent=By.xpath(rb.generateXpath(BuildActionsPageConstants.getBuildActionsPageLocators().get("SELECT_COMPONENT"), component));
	}
	private WebElement getSelectComponent() {
		return driverUtil.findElement(selectComponent);
	}
	private WebElement getFetchComponentStatus() {
		return driverUtil.findElement(fetchComponentStatus);
	}
	private WebElement getSelectInstance() {
		return driverUtil.findElement(selectInstance);
	}
	private WebElement getSelectActions() {
		return driverUtil.findElement(selectActions);
	}
	private WebElement getGraceInterval() {
		return driverUtil.findElement(graceInterval);
	}
	private WebElement getRestartButton() {
		return driverUtil.findElement(restartButton);
	}
	private WebElement getInstanceState() {
		return driverUtil.findElement(instanceState);
	}
	
	
	
	public void selectBuildActionsComponentPage() throws InterruptedException {
		logger.info("Inside selectBuildActionsComponentPage");
		driverUtil.waitForElementPresent(toolsComponentPage);
		getToolsComponentPage().click();
		driverUtil.waitForElementPresent(buildActionsToolsComponentPage);
		getBuildActionsToolsComponentPage().click();

	}
	
	public void selectComponentStatusPage() throws InterruptedException {
		logger.info("Inside selectComponentStatusPage");
		driverUtil.waitForElementPresent(componentsDropdown);
		getcomponentsDropdown().click();
		driverUtil.waitForElementPresent(componentStatusDropdown);
		getcomponentStatusDropdown().click();
		driverUtil.closeWindow();
		driverUtil.switchWindowTitle("Samba(hubble2/alpha) - Schedule Requests");
	}
	
	public void enterComponentComponentStatusPage() throws InterruptedException {
		logger.info("Inside enterComponentComponentStatusPage");
		driverUtil.waitForElementPresent(componentStatusPage);
		//getSelectComponentsDropdown().click();
		System.out.println("buildDeployComponentName:::"+buildDeployComponentName);
		generateSelectComponent(buildDeployComponentName);
		getSelectComponent().click();
	}
	
	public void clickFetchComponentStatusPage() throws InterruptedException {
		logger.info("Inside clickFetchComponentStatusPage");
		driverUtil.waitForElementPresent(fetchComponentStatus);
		getFetchComponentStatus().click();
	}
	
	public void selectRelatedComponentInstances() throws InterruptedException {
		logger.info("Inside selectRelatedComponentInstances");
		System.out.println("inside selectRelatedComponentInstances");
		driverUtil.waitForElementPresent(selectInstance);
		System.out.println("1");
		List<WebElement> elements= driverUtil.findElements(selectInstance);
		System.out.println("2");
		Iterator<WebElement> iterator= elements.iterator();
		System.out.println("3");
		while (iterator.hasNext()) {
			System.out.println("4");
			iterator.next().click();
			System.out.println("5");
		}
		System.out.println("6");
		
	}
	
	
	public void clickRestartServerComponent() throws InterruptedException {
		logger.info("Inside clickRestartServerComponent");
		System.out.println("inside clickRestartServerComponent");
		driverUtil.waitForElementPresent(selectActions);
		Select actions= new Select(getSelectActions());
		actions.selectByValue("RESTART");
		driverUtil.waitForElementPresent(graceInterval);
		getGraceInterval().sendKeys("5");
		getRestartButton().click();
		System.out.println(driverUtil.switchToAlertGetText());
		driverUtil.switchToAlertAccept();
		
	}

	public Boolean validateInstanceHappyRestart() throws InterruptedException {
		logger.info("Inside validateInstanceHappyRestart");
		Thread.sleep(12000);
		System.out.println("inside validateInstanceHappyRestart");
		driverUtil.waitForElementPresent(componentStatusTable);
		int tableLength=driverUtil.findElements(instanceState).size();
		int successElement=0;
		System.out.println("tableLength::"+tableLength);
		for(int i=1;i<=tableLength;i++){
			String state=driverUtil.findElements(instanceState).get(i).getText();
			System.out.println("state::"+i+state);
			if(state.toLowerCase().contains("happy")){
				successElement++;
			}
		}
		System.out.println("tableLength::"+tableLength);
		System.out.println("successElement::"+successElement);
		return tableLength==successElement;
		
	}
	
}
