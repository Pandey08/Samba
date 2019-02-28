package hubble.samba.constants;

import hubble.samba.util.JsonFileParser;
import hubble.samba.util.YamlParser;

import java.util.HashMap;
import java.util.Map;

public class LandingPageConstants {
	
	public static Map<String, String> LandingPageLocators = new HashMap<String, String>();
	public static Map<String, HashMap<String,String>> buildDetailsMap = new HashMap<String, HashMap<String,String>>();
	public static Map<String, HashMap<String,String>> componentDetailsMap = new HashMap<String, HashMap<String,String>>();
			
		
	
	public static Map<String, String> getLandingPageLocators() {
		return LandingPageLocators;
	}
	
	public static void setLandingPageLocators(Map<String, String> landingPageLocators) {
		LandingPageLocators = landingPageLocators;
	}
	
	public static void loadLandingPageLocators() {
		setLandingPageLocators(YamlParser.parseYamlFile("LandingLocators.yaml"));
	}
	
	
//	public static Map<String, HashMap<String,String>> getBuildDetailsMap() {
//		return buildDetailsMap;
//	}
//
//	public static void setBuildDetailsMap(Map<String, HashMap<String,String>> buildDetails) {
//		buildDetailsMap = buildDetails;
//	}
//
//	public static void loadBuildDetailsMap() {
//		setBuildDetailsMap(JsonFileParser.parseJsonFile("Input.json"));
//	}
//
//
//	public static Map<String, HashMap<String,String>> getComponentDetailsMap() {
//		return componentDetailsMap;
//	}
//
//	public static void setComponentDetailsMap(Map<String, HashMap<String,String>> componentDetails) {
//		componentDetailsMap = componentDetails;
//	}
//
//	public static void loadComponentDetailsMap() {
//		setComponentDetailsMap(JsonFileParser.parseJsonFile("ComponentConfig.json"));
//	}
	

}
