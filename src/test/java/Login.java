import hubble.samba.factory.PageFactory;
import hubble.samba.pageobjects.LoginPage;

import org.apache.log4j.Logger;
import org.junit.Assert;

public class Login {
	
		PageFactory pageFactoryObj = new PageFactory();
	   	private LoginPage sambaLogin = pageFactoryObj.getLoginPageObject();
	   	private static Logger logger = Logger.getLogger(Login.class.getName());

	
	public void launchHubbleSamabaPage(){
		logger.info("******** Launch Hubble Samba page *******");
		sambaLogin.openURL();
	}
	public void hubbleSamabaPageLaunched() throws InterruptedException{
		logger.info("******** Hubble Samba Login page launched *******");
		Assert.assertTrue(sambaLogin.sambaLoginPageLaunched());
		
	}
	public void enterUsername(String username) throws InterruptedException{
		logger.info("******** Enter OD Username *******");
		sambaLogin.enterUsername(username);
		
	}
	
	public void enterPassword(String password) throws InterruptedException{
		logger.info("******** Enter OD Password *******");
		sambaLogin.enterPassword(password);
		
	}
	
	public void clickLetMeIn() throws InterruptedException{
		logger.info("******** Click Let me in button *******");
		System.out.println("Please Enter OD Username and Passoword");
		Thread.sleep(5000);
		sambaLogin.clickLetMeIn();
		
	}
	
}
