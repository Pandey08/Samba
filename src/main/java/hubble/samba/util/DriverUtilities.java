package hubble.samba.util;

import java.awt.Toolkit;
import java.util.List;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtilities {

	private static DriverUtilities _self;
	private String browsername;
	private static Logger logger = Logger.getLogger(DriverUtilities.class.getName());
	private DriverUtilities(){

	}

	public static DriverUtilities instance(){
		if(null == _self){
			_self = new DriverUtilities();
		}
		return _self;
	}
	public static WebDriver webDriver = null;
	private List <WebElement> elementList;

	public void createWebDriver(String browser){
		//if(webDriver == null){
		browsername = browser;
		logger.info("Broswername"+browsername);
		webDriver = SeleniumBrowserHandler.instance().fetchBrowserInstance(browser);
		//Toolkit toolkit = Toolkit.getDefaultToolkit();
		//Dimension screenResolution = new Dimension((int) toolkit.getScreenSize().getWidth(), 
		//		(int) toolkit.getScreenSize().getHeight());
		//webDriver.manage().window().setSize(screenResolution);
	//	 if((browsername.equalsIgnoreCase("Firefox"))||(browsername.equalsIgnoreCase("Windows-Firefox"))){
		webDriver.manage().window().setSize(new Dimension(1366,1080));
		//	webDriver.manage().window().maximize();
	//	 }

		//}else{
		//	logger.info("Driver not null.... CHECK....");
		//}
	}

   public void closeWebDriver() {
		if (webDriver != null){
			webDriver.close();
			webDriver.quit();
		}

	}
	
	public void close() {
		
		webDriver.quit();	
		webDriver = null;
		
	}
	
	public Object startSelenium(String targetBrowser,String setupUrl){

		try{
			webDriver=SeleniumBrowserHandler.instance().fetchBrowserInstance(targetBrowser);

			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenResolution = new Dimension((int) toolkit.getScreenSize().getWidth(), 
					(int) toolkit.getScreenSize().getHeight());
			webDriver.manage().window().setSize(screenResolution);
			webDriver.get(setupUrl);
		} catch (Exception e) {
			webDriver.get(setupUrl);
		}
		return webDriver;


	}

	public Object startSelenium(String targetBrowser){

		try{
			webDriver=SeleniumBrowserHandler.instance().fetchBrowserInstance(targetBrowser);

			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenResolution = new Dimension((int) toolkit.getScreenSize().getWidth(), 
					(int) toolkit.getScreenSize().getHeight());
			webDriver.manage().window().setSize(screenResolution);
			//			webDriver.get(setupUrl);
		} catch (Exception e) {
			//			webDriver.get(setupUrl);
		}
		return webDriver;
	}



	public void stopSelenium() {
		webDriver.close();
		webDriver.quit();
	}


	public void click(WebElement wbElement) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

		}
		wbElement.click();
	}

	
	public void driverImplicitWait(long timeInSeconds){	
		webDriver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
	}
	

	public String getText(WebElement wbElement) {
		return wbElement.getText();
	}
	
	public static void waitForPageToLoad(String milliSeconds){

		try {
			Thread.sleep(Integer.parseInt(milliSeconds));
		} catch (InterruptedException e) {
			logger.info("!!!!!!!!!!! EXCEPTION in waitForPageToLoad!!!!!!!!!!!");
			//        	logger.info(e.getMessage());
			//        	logger.info(e.getStackTrace());;
			//        	logoutAndLogin();
		}

	}
	
	public String getId (WebElement wbElement){
		return wbElement.getAttribute("id");

	}

	public boolean isVisible(WebElement wbElement) {

		return wbElement.isDisplayed();
	}

	public static void mouseDown(WebElement wbElement) {
		try {
			logger.info("@11");
//			Actions action = new Actions(webDriver);
//			action.moveToElement(wbElement);
//			logger.info("@12");
//			//action.clickAndHold(wbElement);
//			//action.release(wbElement);
//			action.click(wbElement);
//			logger.info("@13");
//			action.build().perform();
			Actions builder = new Actions(webDriver);
			builder.moveToElement(wbElement).click(wbElement).perform();
			Thread.sleep(2000);
		} catch (Exception e ) {
			logger.info("exception"+e);
		}
				
		
		
//		Actions builder = new Actions(webDriver);
//		builder.moveToElement(wbElement).click();
//		wbElement.click();
	}

	public void open(String urlStr) {
		webDriver.get(urlStr);

	}

	public void refresh() {		
		webDriver.navigate().refresh();
	}


	public void searchElementByClassFromList(String className){
		WebElement wbElement;
		for (WebElement member : elementList) {


			if(member.getAttribute("class").contains(className)) {
				wbElement = member;
				break;
			}
		}

	}



	public void switchFrame(String frameName) {
		webDriver.switchTo().frame(frameName);

	}

	public void switchToDefaultFrame() {
		webDriver.switchTo().defaultContent();
	}
	
	public String switchToAlertGetText() {
		logger.info(webDriver.switchTo().alert().getText());
		return webDriver.switchTo().alert().getText();
	}
	
	public void switchToAlertDismiss() {	
		webDriver.switchTo().alert().dismiss();
	}
	
	public void switchToAlertAccept() {
		webDriver.switchTo().alert().accept();;
	}
	
	public void test(String xpath) {
		// TODO Auto-generated method stub


	}

	public void type(WebElement wbElement, String str) {

		wbElement.sendKeys(str);
	}


	public void clear(WebElement element) {
		element.clear();
	}

	private final int MaxWaitTime = 40;

	private final int NoOfLoops = MaxWaitTime / 2;


	/**
	 * Method to wait for element to appear when page gets refreshed/reloaded (fixed timeout).
	 * 
	 * @param webElement
	 *            the web element
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void waitForWebElementPresent(WebElement webElement) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, MaxWaitTime);
			//wait.until(ExpectedConditions.visibilityOf(webElement));

		} catch (Exception e) {
			//logger.info("!!!!!!!!!!! EXCEPTION in waitForWebElementPresent !!!!!!!!!!!");
			//        	logger.info(e.getMessage());
			//        	logger.info(e.getStackTrace());
			//        	logoutAndLogin();
		}
	}


	/**
	 * Method to wait till element is not present on page.
	 * 
	 * @param webElement
	 *            the web element
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void waitForWebElementNotPresent(WebElement webElement) throws InterruptedException {
		for (int second = 0;; second++) {
			if (second >= NoOfLoops) {
				throw new InterruptedException("Timed out after " + MaxWaitTime + " seconds of waiting for element to be removed" + webElement);
			}
			try {
				boolean blnIsElementPresent = isWebElementPresent(webElement);
				if (blnIsElementPresent) {
					Thread.sleep(1000);
				} else {
					break;
				}
			} catch (Exception e) {
				//logger.info("!!!!!!!!!!! EXCEPTION in waitForWebElementNotPresent !!!!!!!!!!!");
				//            	logger.info(e.getMessage());
				//            	logger.info(e.getStackTrace());;
				//            	logoutAndLogin();
			}
			Thread.sleep(1000);
		}
	}

	/**
	 * Method to wait for element to appear when page gets refreshed/reloaded deprecated; not used in current automation scripts.
	 * 
	 * @param By
	 *            loctorBy
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void waitForElementPresent(By byLocator) throws InterruptedException {

		for (int second = 0;; second++) {
			if (second >= NoOfLoops) {
				throw new InterruptedException("Timed out after " + MaxWaitTime + " seconds of waiting for element " + byLocator);
			}
			try {
				boolean blnIsElementPresent = isElementPresent(byLocator);
				if (blnIsElementPresent) {
					//logger.info("inside wait for element - present - "+second);

					break;
				} else {
					//logger.info("inside wait for element - wait - "+second);

					Thread.sleep(1000);
				}
			} catch (Exception e) {
				//logger.info("!!!!!!!!!!! EXCEPTION in waitForElementPresent !!!!!!!!!!!");
				//            	logger.info(e.getMessage());
				//            	logger.info(e.getStackTrace());;
				//            	logoutAndLogin();
			}
			Thread.sleep(1000);
		}
	}

	/**
	 * Check whether element is present deprecated; not used in current automation scripts.
	 * 
	 * @param xpath
	 *            the xpath
	 * @return true, if is element present
	 */
	public boolean isElementPresent(By byLocator) {
		try {
			return webDriver.findElement(byLocator).isDisplayed();
			} catch (Exception e) {
				//logger.info("!!!!!!!!!!! EXCEPTION in isElementPresent !!!!!!!!!!!");
				//        	logger.info(e.getMessage());
				//        	logger.info(e.getStackTrace());;
				//        	logoutAndLogin();
			}
		return false;
		
	}

	/**
	 * Check whether element is present.
	 * 
	 * @param webElement
	 *            the web element
	 * @return true, if is web element present
	 */
	public boolean isWebElementPresent(WebElement webElement) {
		try {
			return webElement.isDisplayed();
		} catch (NoSuchElementException excptn) {
			//logger.info("!!!!!!!!!!! EXCEPTION in isWebElementPresent !!!!!!!!!!!");
			//        	logger.info(excptn.getMessage());
			//        	logoutAndLogin();
			return false;
		} catch (Exception e) {
			//logger.info("!!!!!!!!!!! EXCEPTION in isWebElementPresent !!!!!!!!!!!");
			//        	logger.info(e.getMessage());
			//        	logoutAndLogin();
			return false;
		}
	}



	public WebElement findElement(By webElement){

		WebElement wbElement = null;
		try {
			wbElement = webDriver.findElement(webElement);
			
		} catch (NoSuchElementException nse) {
			logger.info("NoSuchElementException :: findElement :: message"+ webElement.toString());
			//		logger.info("!!!!!!!!!!! EXCEPTION in findElement !!!!!!!!!!!");
			//    	logger.info(nse.getMessage());
			//    	logoutAndLogin();
		} catch (Exception ex) {
			logger.info("Exception :: findElement :: message"+ex.getMessage());
			//		logger.info("!!!!!!!!!!! EXCEPTION in findElement !!!!!!!!!!!");
			//    	logger.info(ex.getMessage());
			//    	logoutAndLogin();
		}
		return wbElement;
	}

	public void switchWindowTitle(String title){
		

			for(String winhandle :webDriver.getWindowHandles()){
				webDriver.switchTo().window(winhandle);
				if(webDriver.getTitle().contains(title)){
					// logger.info("You are in required window");
					break;
				} 
				else{
					// logger.info("Title of the page after - switchingTo: " + webDriver.getTitle());
					continue;
				}
			}
		
	}

	public void closeWindow() {
			if(webDriver != null)
				webDriver.close();	
	
	}

	public void mouseHover(WebElement elementName){
		String strJavaScript = ("var element = arguments[0];"+ "var mouseEventObj = document.createEvent('MouseEvents');"+ "mouseEventObj.initEvent( 'mouseover', true, true );"
                + "element.dispatchEvent(mouseEventObj);");
        JavascriptExecutor js =  (JavascriptExecutor) webDriver;
        js.executeScript(strJavaScript, elementName);
         
	}
	
	public List<WebElement> findElements(By webElement){
		return webDriver.findElements(webElement);
	}

	public void scrollToElement(By webElement){
		WebElement element =webDriver.findElement(webElement);
		((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public void scrollPage(){
		JavascriptExecutor jse = (JavascriptExecutor)webDriver;
//		jse.executeScript("window.scrollBy(0,250)", "");
	    jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void clickOpenNewTab() {
		Actions action = new Actions(webDriver);
		action.keyDown(Keys.COMMAND).build().perform();
		
	}

}
