package hubble.samba.constants;



import hubble.samba.util.YamlParser;

import java.util.HashMap;
import java.util.Map;

public class LoginPageConstants {
	
	public static Map<String, String> LoginPageLocators = new HashMap<String, String>();

	public static Map<String, String> getLoginPageLocators() {
		return LoginPageLocators;
	}

	public static void setLoginPageLocators(Map<String, String> loginPageLocators) {
		LoginPageLocators = loginPageLocators;
	}

	public static void loadLoginPageLocators() {
		setLoginPageLocators(YamlParser.parseYamlFile("LoginLocators.yaml"));
	}

}
