package hubble.samba.util;

import java.io.File;

import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


public class SeleniumBrowserHandler {
	private static SeleniumBrowserHandler seleniumSup;
	int i = 0;
	private String targetBrowser;

	private static String driverType = "local";

	private static Properties hubProps = null;
	private static Logger logger = Logger.getLogger(SeleniumBrowserHandler.class.getName());
	
	public static SeleniumBrowserHandler instance() {
		if (seleniumSup == null) {
			seleniumSup = new SeleniumBrowserHandler();
		}
		return seleniumSup;
	}

	WebDriver webDriver;

	public WebDriver fetchBrowserInstance(String targetBrowserPropValue) {
		this.targetBrowser=targetBrowserPropValue;
		driverType="local";
		

		boolean remote = false;
		boolean isExcepton = false;

		if (driverType.equalsIgnoreCase("remote")) {
			remote = true;

		}
		
		

		if (targetBrowser.equalsIgnoreCase("chrome")) {
			logger.info("Browser is:Chrome");
			if (remote) {
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("disable-infobars");
				options.addArguments("test-type");
				options.addExtensions(new File("/Hubble/Extension/AppleConnect-Extension-for-Chrome_v1.0.4.crx"));
				capabilities.setCapability("chrome.binary", "<<your chrome path>>");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				isExcepton = createRemoteWD(capabilities);
			} else {

				try {
					String path = System.getProperty("user.dir");
					logger.info("Path:"+ path);
					File pathChrome = new File(path + "/drivers/chromedriver");
					//File pathChrome = new File(path + "/src/main/java/hubble/samba/drivers/chromedriver");
					// Chrome executable will be zipped in tar file, so if its not available try unzipping and extract the executable
					if (!pathChrome.exists()) {
						Runtime.getRuntime().exec("unzip " + pathChrome + ".zip  -d" + pathChrome.getParentFile().getAbsolutePath());
						// Please wait! IO takes time :-)
						Thread.sleep(10000);
					}

					System.setProperty("webdriver.chrome.driver", pathChrome.getAbsolutePath());
					ChromeOptions options = new ChromeOptions();
					options.addArguments("disable-infobars");
//					options.addExtensions(new File("./Extension/AppleConnect-Extension-for-Chrome_v1.0.4.crx"));
					webDriver = new ChromeDriver(options);
				} catch (Exception e) {
					e.printStackTrace();
					// ToDo create new BrowserException
				}
			}

		} else if (targetBrowser.equalsIgnoreCase("firefox")) {
			if (remote) {
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setBrowserName("firefox"); 
			    capabilities.setPlatform(org.openqa.selenium.Platform.ANY); 

				File firefoxProfileFolder = null;
				FirefoxProfile profile = new FirefoxProfile(firefoxProfileFolder);
				capabilities.setCapability(FirefoxDriver.PROFILE, profile);
				isExcepton = createRemoteWD(capabilities);
			} else {
				logger.info("********* Running on local browser ***********");
				try {
					webDriver = new FirefoxDriver();					
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		} else if ((targetBrowser.equalsIgnoreCase("safari"))) {
			if (remote) {
				isExcepton = createRemoteWD(DesiredCapabilities.safari());
			} else {
				try {
					logger.info("********* creating driver *******");
					webDriver = new SafariDriver();
					
					Thread.sleep(10000);
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		} 

		if (isExcepton) {
			i++;
			logger.info("retry count:Browser instantiation" + i);
			// logger.info("is exception "+isExcepton + "target browser"+targetBrowser);
			if (i < 4) {
				fetchBrowserInstance(targetBrowser);
			}
		}

		return webDriver;
	}




	private void logBrowserInstance(String browser, String exceptionMessage, boolean isException) {
		if (isException) {

			logger.info(browser + " is not instantiated:" + exceptionMessage);

		} else {
			logger.info(browser + " is successfully instantiated");
		}
	}



	private boolean createRemoteWD(DesiredCapabilities desiredCap) {
		try {

			String hostIP = System.getProperty("WINDOWS_IP");
			String hostPort = System.getProperty("WINDOWS_PORT");
			
			webDriver = new RemoteWebDriver(new URL("http://" + hostIP + ":".concat(hostPort)
			        + "/wd/hub"), desiredCap);
			logger.info("******* webDriver url ******** :"+webDriver.getCurrentUrl());
			
			Thread.sleep(15000);
		} catch (Exception e) {
			e.printStackTrace();
			return true;
			// logBrowserInstance("Remote Chrome",e.getMessage(),isExcepton);
		}
		// logBrowserInstance("Remote Chrome","",isExcepton);
		return false;
	}

	

	public static void main(String str[]) {

		WebDriver webDriver = null;
		try {
			webDriver = new RemoteWebDriver(new URL("http://" + str[1] + ":".concat(str[2]) + "/wd/hub"), DesiredCapabilities.internetExplorer());
			logger.info("Remote Driver connected...");
			Thread.sleep(5000);
			webDriver.get("https://www.icloud.com");
			Thread.sleep(5000);
			webDriver.quit();

		} catch (Exception e) {
			e.printStackTrace();
			webDriver.quit();

		}
	}
}
