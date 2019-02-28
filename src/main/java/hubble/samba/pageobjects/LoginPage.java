package hubble.samba.pageobjects;



import hubble.samba.constants.LoginPageConstants;
import hubble.samba.util.DriverUtilities;
import hubble.samba.util.ResourceBundleUtil;
import hubble.samba.model.SambaProperties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	private static LoginPage instance = null;
	WebDriver webDriver = DriverUtilities.webDriver;
	DriverUtilities driverUtil= DriverUtilities.instance();
	ResourceBundleUtil rb= new ResourceBundleUtil();
	private static Logger logger = Logger.getLogger(LoginPage.class.getName());

	private LoginPage(){
	}
	public static LoginPage getInstance(){
		if (instance == null){
			LoginPageConstants.loadLoginPageLocators();
			instance = new LoginPage();
		}
		return instance;	
	}

	private static String loginPageHeading="Samba";
	
	private By sambaPageHeading = By.cssSelector(LoginPageConstants.getLoginPageLocators().get("SAMBA_HEADING"));
	private By loginUsername = By.cssSelector(LoginPageConstants.getLoginPageLocators().get("LOGIN_USERNAME"));
	private By loginPassword = By.cssSelector(LoginPageConstants.getLoginPageLocators().get("LOGIN_PASSWORD"));
	private By letMeInButton = By.cssSelector(LoginPageConstants.getLoginPageLocators().get("LOGIN_LET_ME_IN"));


//	private void generateSelectionComponent(String component) {
//		loginUsername=By.cssSelector(rb.generateXpath(LoginPageConstants.getLoginPageLocators().get("LOGIN_USERNAME"), component));
//	}
	private WebElement getSamabLoginPageHeading() {
		return driverUtil.findElement(sambaPageHeading);
	}
	private WebElement getLoginUsername() {
		return driverUtil.findElement(loginUsername);
	}
	private WebElement getLoginPassword() {
		return driverUtil.findElement(loginPassword);
	}
	private WebElement getLetMeInButton() {
		return driverUtil.findElement(letMeInButton);
	}
	
	
	
	public void openURL() {
		logger.info("Inside open url");
		String url = SambaProperties.configDetails.getProperty("URL");
		String browser= SambaProperties.configDetails.getProperty("BROWSER");

		logger.info("URL::"+url);
		logger.info("BROWSER::"+browser);

		driverUtil.createWebDriver(browser.trim());
		driverUtil.webDriver.get(url);
		ResourceBundleUtil.waitForPageToLoad("timeout5");

	}

	public Boolean sambaLoginPageLaunched() throws InterruptedException {
		logger.info("Inside sambaLoginPageLaunched");

		driverUtil.waitForElementPresent(sambaPageHeading);
		if(driverUtil.isWebElementPresent(getSamabLoginPageHeading())){
			String headerText=driverUtil.getText(getSamabLoginPageHeading());
			System.out.println("headerText::"+headerText);
			return headerText.contains(loginPageHeading);

		}else{
			return false;
		}
	}

	public void enterUsername(String username) throws InterruptedException {
		logger.info("Inside enterUsername");
		//generateSelectionComponent("j_username");
		driverUtil.waitForElementPresent(loginUsername);
		getLoginUsername().sendKeys(username);
	}

	public void enterPassword(String password) throws InterruptedException {
		logger.info("Inside enterPassword");
		driverUtil.waitForElementPresent(loginPassword);
		getLoginPassword().sendKeys(password);
	}

	public void clickLetMeIn() throws InterruptedException {
		logger.info("Inside clickLetMeIn");
		driverUtil.waitForElementPresent(letMeInButton);
		driverUtil.click(getLetMeInButton());
	}

}
