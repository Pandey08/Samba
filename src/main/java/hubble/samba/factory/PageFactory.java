package hubble.samba.factory;

import hubble.samba.pageobjects.BuildActionsPage;
import hubble.samba.pageobjects.ComponentPage;
import hubble.samba.pageobjects.LandingPage;
import hubble.samba.pageobjects.LoginPage;


public class PageFactory {
	
	public LoginPage getLoginPageObject() {
		return LoginPage.getInstance();
	}
	
	public LandingPage getLandingPageObject(){
		return LandingPage.getInstance();
	}

	public ComponentPage getComponentPageObject(){
		return ComponentPage.getInstance();
	}
	
	public BuildActionsPage getBuildActionsPageObject(){
		return BuildActionsPage.getInstance();
	}
}
